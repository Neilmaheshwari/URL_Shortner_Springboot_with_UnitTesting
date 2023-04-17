package com.UrlProject.ShortnerUrl.UrlTest;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.UrlProject.ShortnerUrl.Entity.UrlDataEntity;
import com.UrlProject.ShortnerUrl.exception.NoSuchUrlExistException;
import com.UrlProject.ShortnerUrl.exception.UrlNotValidAnymoreException;
import com.UrlProject.ShortnerUrl.repository.UrlRepository;
import com.UrlProject.ShortnerUrl.service.UrlService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class UrlGetTest {

	@Mock
	private UrlRepository urlRepository;
	
	@InjectMocks
	private UrlService urlService;
	
	
	public static UrlDataEntity urlObj1= new UrlDataEntity("r47gsteuuye","hs89st","https://google.com",LocalDate.now(),0,true,HashMap.<LocalDate, Integer>newHashMap(0));
	public static UrlDataEntity urlObj2= new UrlDataEntity("r47gstrty5","ty87h6","https://rapipay.com",LocalDate.now(),3,false,HashMap.<LocalDate, Integer>newHashMap(0));
	

	@Test
	public void getShortUrlTest() {
		
		Mockito.when(urlRepository.findByShortUrl(Mockito.anyString())).thenReturn(Mono.fromSupplier(()-> urlObj1));
		
		Mockito.when(urlRepository.save(Mockito.any(UrlDataEntity.class))).thenReturn(Mono.fromSupplier(()->urlObj1));
	
		
		Mono<String> responseUrl1= urlService.redirectToLongUrl("hs89st");
	
		StepVerifier.create(responseUrl1)
		.expectNextMatches(r-> r.equals(urlObj1.getLongUrl()))
		.verifyComplete();
		
		Mockito.when(urlRepository.findByShortUrl(Mockito.anyString())).thenReturn(Mono.fromSupplier(()-> urlObj2));
		

		Mono<String> responseUrl2= urlService.redirectToLongUrl("hs89st");
		
		StepVerifier.create(responseUrl2)
		.verifyError(UrlNotValidAnymoreException.class);
		
	}
	
	@Test
	public void shortUrlNotExistTest() {
		Mockito.when(urlRepository.findByShortUrl(Mockito.anyString())).thenReturn(Mono.empty());
		
		Mono<String> responseUrl= urlService.redirectToLongUrl("hs89st");
		
		StepVerifier.create(responseUrl)
		.verifyError(NoSuchUrlExistException.class);
	}
	
}