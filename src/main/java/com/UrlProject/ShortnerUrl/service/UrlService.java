package com.UrlProject.ShortnerUrl.service;

import java.time.LocalDate;
import java.util.HashMap;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.UrlProject.ShortnerUrl.Entity.HashCode;
import com.UrlProject.ShortnerUrl.Entity.UrlData;
import com.UrlProject.ShortnerUrl.Entity.UrlDataEntity;
import com.UrlProject.ShortnerUrl.Entity.UserDataEntity;
import com.UrlProject.ShortnerUrl.ResponseObj.ResponseReport;
import com.UrlProject.ShortnerUrl.ResponseObj.ResponseUrl;
import com.UrlProject.ShortnerUrl.exception.NoReportExistsException;
import com.UrlProject.ShortnerUrl.exception.NoSuchUrlExistException;
import com.UrlProject.ShortnerUrl.exception.NoUserExistException;
import com.UrlProject.ShortnerUrl.exception.NotExceptableUrlException;
import com.UrlProject.ShortnerUrl.exception.UrlLimitOnUserException;
import com.UrlProject.ShortnerUrl.exception.UrlNotValidAnymoreException;
import com.UrlProject.ShortnerUrl.repository.UrlRepository;
import com.UrlProject.ShortnerUrl.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UrlService {

	@Autowired
	private UrlRepository urlRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public static String domain="http://localhost:8080/";
	
	@Value("${urlLimit}")
	private long urlLimit;

	@Value("${urlClicksLimit}")
	private long urlClicksLimit;
	
	
	UrlValidator validator= new UrlValidator();
	
	public Mono<String> redirectToLongUrl(String shorturl) {
		return urlRepository.findByShortUrl(shorturl)
					.switchIfEmpty(Mono.error(new NoSuchUrlExistException("Sorry could not find Url you are looking for 😥")))
					.flatMap(urlObj->updateUrlData(urlObj))
					.map(urlObj-> urlObj.getLongUrl());
	
	}
	
//	Helper function updating data of URL object
	public Mono<UrlDataEntity> updateUrlData(UrlDataEntity urlObj){
		if(urlObj.isUrlActive()) {
			urlObj.setUrlClicksCount(urlObj.getUrlClicksCount()+1);
			HashMap<LocalDate,Integer> hm= urlObj.getUrlFetchData();
			if(urlObj.getUrlFetchData().containsKey(LocalDate.now())) {
				hm.put(LocalDate.now(), hm.get(LocalDate.now())+1);
				urlObj.setUrlFetchData(hm);
			}
			else {
				hm.put(LocalDate.now(), 1);
				urlObj.setUrlFetchData(hm);
			}
			if(urlObj.getUrlClicksCount()==urlClicksLimit) {
				urlObj.setIsUrlActive(false);
			}
			return urlRepository.save(urlObj);
		}
		else {
			return Mono.error(new UrlNotValidAnymoreException("Url is Not Valid anymore"));
		}
	}



	public Mono<ResponseUrl> checkUserData(UrlData urlData) {

		try {
			if(urlData.getLongUrl().contains("localhost") || urlData.getLongUrl().isBlank() || !validator.isValid(urlData.getLongUrl())) {
				return Mono.error(new NotExceptableUrlException("Please Enter a Valid Url 😴"));
			}
			else {
				
				if(urlData.getUserId().isBlank()) {
					return generateNewUserAndShortUrl(urlData);
				}
				else
				{ 
					urlData.getUserId().strip();
					return checkUserIdAndLongUrl(urlData);
				}
			}
		} catch (NullPointerException e) {
			return Mono.error(new NotExceptableUrlException("Null Value can't be accepted"));
		}
		
	}
 	
	public Mono<ResponseUrl> generateNewUserAndShortUrl(UrlData urldata){
		return userRepository.save(new UserDataEntity(HashCode.generatingId(10),1))
				.flatMap(userObj->urlRepository.save(new UrlDataEntity(userObj.getUserId(), HashCode.generatingId(6),urldata.getLongUrl(),LocalDate.now(),0L,true,HashMap.<LocalDate, Integer>newHashMap(0))))
				.map(urlObj-> new ResponseUrl(urlObj.getUserId(),domain+urlObj.getShortUrl(),urlObj.getUrlCreationDate(),urlObj.getUrlClicksCount(),urlObj.isUrlActive()));
	}
	
	
	public Mono<ResponseUrl> checkUserIdAndLongUrl(UrlData urldata){
		return  userRepository.findByUserId(urldata.getUserId())
				.switchIfEmpty(Mono.error(new NoUserExistException("No User with such id exist 😶.Please send an empty id to create new User 😄")))
				.flatMap(urlObj-> urlRepository.findByUserIdAndLongUrlAndIsUrlActive(urldata.getUserId(),urldata.getLongUrl(),true))
				.switchIfEmpty(creatingNewUrlInUrlTable(urldata))
				.map(url-> new ResponseUrl(url.getUserId(),domain+url.getShortUrl(),url.getUrlCreationDate(),url.getUrlClicksCount(),url.isUrlActive()));
				
	}
	
//	helper function to create new URL in table
	public Mono<UrlDataEntity> creatingNewUrlInUrlTable(UrlData urldata){
		return Mono.fromSupplier(() -> 1).flatMap(x -> userRepository.findByUserId(urldata.getUserId()).flatMap(user->{
			if(user.getTotalUrlGenerated()==urlLimit) { 
				return Mono.error(new UrlLimitOnUserException("You have reached maximum limit of creating urls 🤔."));
			}
			else {
					user.setTotalUrlGenerated(user.getTotalUrlGenerated()+1);
				return userRepository.save(user)
						.flatMap(urlobj->urlRepository.save(new UrlDataEntity(user.getUserId(), HashCode.generatingId(6),urldata.getLongUrl(),LocalDate.now(),0L,true,HashMap.<LocalDate, Integer>newHashMap(0))));
			}
			
		}));
}

	public Flux<ResponseReport> getReportOfAll(){
		return urlRepository.findAll()
				.switchIfEmpty(Mono.error(new NoReportExistsException("Report cannot be generated as no Url is generated 😪.")))
				.map(reportObj-> new ResponseReport(reportObj.getUserId(),reportObj.getShortUrl(),reportObj.getLongUrl(),reportObj.getUrlCreationDate(),reportObj.getUrlClicksCount(),reportObj.isUrlActive(),reportObj.getUrlFetchData()));
	}

	public Flux<ResponseReport> getUserSpecificReport(String userId){
		return urlRepository.findByUserId(userId)
				.switchIfEmpty(Mono.error(new NoReportExistsException("No report with User Id: "+userId+" exist 😔.")))
				.map(reportObj-> new ResponseReport(reportObj.getUserId(),reportObj.getShortUrl(),reportObj.getLongUrl(),reportObj.getUrlCreationDate(),reportObj.getUrlClicksCount(),reportObj.isUrlActive(),reportObj.getUrlFetchData()));
					
	}
	
	public Flux<ResponseReport> getUserReportWithCreationDate(LocalDate date){
		
		return urlRepository.findByUrlCreationDate(date)
					.switchIfEmpty(Mono.error(new NoReportExistsException("No Url was Created on this date 😔.")))
					.map(reportObj-> new ResponseReport(reportObj.getUserId(),reportObj.getShortUrl(),reportObj.getLongUrl(),reportObj.getUrlCreationDate(),reportObj.getUrlClicksCount(),reportObj.isUrlActive(),reportObj.getUrlFetchData()));
				
	}
}


