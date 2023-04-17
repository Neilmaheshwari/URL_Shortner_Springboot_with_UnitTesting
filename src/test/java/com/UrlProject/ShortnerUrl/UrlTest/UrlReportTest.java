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
import com.UrlProject.ShortnerUrl.ResponseObj.ResponseReport;
import com.UrlProject.ShortnerUrl.exception.NoReportExistsException;
import com.UrlProject.ShortnerUrl.repository.UrlRepository;
import com.UrlProject.ShortnerUrl.service.UrlService;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class UrlReportTest {

	@Mock
	private UrlRepository urlRepository;
	
	@InjectMocks
	private UrlService urlService;
	
	
	public static UrlDataEntity urlObj1= new UrlDataEntity("r47gsteuuye","hs89st","https://google.com",LocalDate.parse("2023-04-10"),0,true,HashMap.<LocalDate, Integer>newHashMap(0));
	public static UrlDataEntity urlObj2= new UrlDataEntity("r47gsteryr","yt96f4","https://rapipay.com",LocalDate.now(),0,true,HashMap.<LocalDate, Integer>newHashMap(0));
	
	
	@Test
	public void ReportForAllTest() {
		Flux<UrlDataEntity> urlObj= Flux.just(urlObj1,urlObj2);
		Mockito.when(urlRepository.findAll()).thenReturn(Flux.from(urlObj));
		
		Flux<ResponseReport> responseReport= urlService.getReportOfAll();
		
		StepVerifier.create(responseReport)
		.expectNextCount(2)
		.verifyComplete();
	}
	
	@Test
	public void reportnotExistTest() {
		
		Mockito.when(urlRepository.findAll()).thenReturn(Flux.empty());
		
		Flux<ResponseReport> responseReport= urlService.getReportOfAll();
		
		StepVerifier.create(responseReport)
		.verifyError(NoReportExistsException.class);
		
		
	}
	
	@Test
	public void getUserSpecificReportTest() {
		Flux<UrlDataEntity> urlObj= Flux.just(urlObj1,urlObj2);
		Mockito.when(urlRepository.findByUserId(Mockito.anyString()))
		.thenReturn(Flux.from(urlObj).filter(obj-> obj.getUserId().equals("r47gsteryr")));
		
		Flux<ResponseReport> responseReport= urlService.getUserSpecificReport("r47gsteryr");
		
		StepVerifier.create(responseReport)
		.expectNextMatches(r-> r.getUserId().equals("r47gsteryr"))
		.verifyComplete();
		
	}
	
	@Test
	public void userSpecificReportNotExistTest() {
		Mockito.when(urlRepository.findByUserId(Mockito.anyString()))
		.thenReturn(Flux.empty());
		
		Flux<ResponseReport> responseReport= urlService.getUserSpecificReport("r47gsteryr");
		
		StepVerifier.create(responseReport)
		.verifyError(NoReportExistsException.class);
	
		
	}
	
	@Test
	public void userReportWithCreationDateTest() {
		
		Flux<UrlDataEntity> urlObj= Flux.just(urlObj1,urlObj2);
		Mockito.when(urlRepository.findByUrlCreationDate(Mockito.any(LocalDate.class))).thenReturn(Flux.from(urlObj).filter(obj-> obj.getUrlCreationDate().equals(LocalDate.parse("2023-04-10"))));
		
		Flux<ResponseReport> responseReport= urlService.getUserReportWithCreationDate(LocalDate.parse("2023-04-10"));
		
		StepVerifier.create(responseReport)
		.expectNextMatches(r-> r.getUrlCreationDate().equals(LocalDate.parse("2023-04-10")))
		.verifyComplete();
	}
	
	@Test
	public void reportOnDateNotExistTest() {
		
		Mockito.when(urlRepository.findByUrlCreationDate(Mockito.any(LocalDate.class))).thenReturn(Flux.empty());

		Flux<ResponseReport> responseReport= urlService.getUserReportWithCreationDate(LocalDate.parse("2023-04-10"));
		
		StepVerifier.create(responseReport)
		.verifyError(NoReportExistsException.class);

	}
}
