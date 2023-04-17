package com.UrlProject.ShortnerUrl.UrlTest;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.UrlProject.ShortnerUrl.Entity.UrlData;
import com.UrlProject.ShortnerUrl.Entity.UrlDataEntity;
import com.UrlProject.ShortnerUrl.Entity.UserDataEntity;
import com.UrlProject.ShortnerUrl.ResponseObj.ResponseUrl;
import com.UrlProject.ShortnerUrl.exception.NoUserExistException;
import com.UrlProject.ShortnerUrl.exception.NotExceptableUrlException;
import com.UrlProject.ShortnerUrl.repository.UrlRepository;
import com.UrlProject.ShortnerUrl.repository.UserRepository;
import com.UrlProject.ShortnerUrl.service.UrlService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class UrlPostTest {

	@Mock
	private UrlRepository urlRepository;
	
	
	@Mock
	private UserRepository userRepository;
	
	
	@InjectMocks
	private UrlService urlService;
	
	
	
	@Test
	public void checkingUrlDataTest() {
		UrlData urlData1=new UrlData("","https://localhost.com/iryttedsvghjuytre");
		
		Mono<ResponseUrl> urlObj1= urlService.checkUserData(urlData1);
		
		StepVerifier.create(urlObj1)
		.verifyError(NotExceptableUrlException.class);
		
		
		UrlData urlData2=new UrlData("","        ");
		
		Mono<ResponseUrl> urlObj2= urlService.checkUserData(urlData2);
		
		StepVerifier.create(urlObj2)
		.verifyError(NotExceptableUrlException.class);
		
		UrlData urlData3=new UrlData("","htt:/google.comgh");
		
		Mono<ResponseUrl> urlObj3= urlService.checkUserData(urlData3);
		
		StepVerifier.create(urlObj3)
		.verifyError(NotExceptableUrlException.class);
		
		UrlData urlData4=new UrlData(null,null);
		
		Mono<ResponseUrl> urlObj4= urlService.checkUserData(urlData4);
		
		StepVerifier.create(urlObj4)
		.verifyError(NotExceptableUrlException.class);
	
	}
	
	
	@Test
	public void userIdEmptyTest() {
		
		UrlData urlData=new UrlData("","https://google.com");
		
		UserDataEntity userData=new UserDataEntity("saferytyey",1);
		
		UrlDataEntity urlObj= new UrlDataEntity("saferytyey","ty87h6","https://rapipay.com",LocalDate.now(),0,true,HashMap.<LocalDate, Integer>newHashMap(0));
		
		Mockito.when(userRepository.save(Mockito.any(UserDataEntity.class))).thenReturn(Mono.fromSupplier(()->userData));
		
		Mockito.when(urlRepository.save(Mockito.any(UrlDataEntity.class))).thenReturn(Mono.fromSupplier(()->urlObj));
		
		Mono<ResponseUrl> responseUrl= urlService.checkUserData(urlData);
		
		StepVerifier.create(responseUrl)
		.expectNextCount(1)
		.verifyComplete();
		
		StepVerifier.create(responseUrl)
		.expectNextMatches(r-> r.getShortUrl().equals("http://localhost:8080/ty87h6"))
		.verifyComplete();
		
	}
	
	@Test
	public void userIdNotPresentTest() {
		
		UrlData urlData=new UrlData("  eretfvr67h   ","https://google.com");
		
		Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(Mono.empty());
		
		Mono<ResponseUrl> responseUrl= urlService.checkUserData(urlData);
		
		StepVerifier.create(responseUrl)
		.verifyError(NoUserExistException.class);
	}
	
	@Test
	public void userIdAndLongUrlExist() {
		UrlData urlData=new UrlData("  eretfvr67h   ","https://google.com");
		
		UserDataEntity userData=new UserDataEntity("eretfvr67h",1);
		
		UrlDataEntity urlObj= new UrlDataEntity("eretfvr67h","ty87h6","https://google.com",LocalDate.now(),0,true,HashMap.<LocalDate, Integer>newHashMap(0));
		
		Mockito.lenient().when(userRepository.findByUserId(Mockito.anyString())).thenReturn(Mono.fromSupplier(()-> userData));
		
		Mockito.lenient().when(urlRepository.findByUserIdAndLongUrlAndIsUrlActive(Mockito.anyString(),Mockito.anyString(),Mockito.anyBoolean())).thenReturn(Mono.fromSupplier(()-> urlObj));
		
		Mono<ResponseUrl> responseUrl= urlService.checkUserData(urlData);
		
		
		StepVerifier.create(responseUrl)
		.expectNextMatches(r->r.isUrlActive()==true);
	
		StepVerifier.create(responseUrl)
		.expectNextMatches(r->r.getShortUrl().equals("http://loaclhost:8080/ty87h6"));		
	}
	
	@Test
	public void userIdAndLongUrlExpiredTest() {
		UrlData urlData=new UrlData("  eretfvr67h   ","https://google.com");
		
		UserDataEntity userData=new UserDataEntity("eretfvr67h",1);
		
		UrlDataEntity urlObj= new UrlDataEntity("eretfvr67h","ty87h6","https://google.com",LocalDate.now(),0,true,HashMap.<LocalDate, Integer>newHashMap(0));
		
		Mockito.lenient().when(userRepository.findByUserId(Mockito.anyString()))
		.thenReturn(Mono.fromSupplier(()-> userData));
		
		Mockito.lenient().when(urlRepository.findByUserIdAndLongUrlAndIsUrlActive(Mockito.anyString(),Mockito.anyString(),Mockito.anyBoolean()))
		.thenReturn(Mono.empty());
		
		Mockito.lenient().when(userRepository.save(Mockito.any(UserDataEntity.class))).thenReturn(Mono.fromSupplier(()->userData));
		
		Mockito.lenient().when(urlRepository.save(Mockito.any(UrlDataEntity.class))).thenReturn(Mono.fromSupplier(()->urlObj));
		
		Mono<ResponseUrl> responseUrl= urlService.checkUserData(urlData);
	
		StepVerifier.create(responseUrl)
		.expectNextCount(1);
	}
}
