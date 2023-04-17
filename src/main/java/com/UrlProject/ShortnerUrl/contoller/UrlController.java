package com.UrlProject.ShortnerUrl.contoller;

import java.net.URI;
import java.time.LocalDate;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UrlProject.ShortnerUrl.Entity.UrlData;
import com.UrlProject.ShortnerUrl.ResponseObj.ResponseReport;
import com.UrlProject.ShortnerUrl.ResponseObj.ResponseUrl;
import com.UrlProject.ShortnerUrl.exception.NotExceptableUrlException;
import com.UrlProject.ShortnerUrl.service.UrlService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UrlController {

	@Autowired
	private UrlService urlService;

	@GetMapping("/{shorturl}")
	public Mono<Void> getLongUrl(@PathVariable String shorturl, ServerHttpResponse response) {
		return urlService.redirectToLongUrl(shorturl).flatMap(url -> {
				response.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
				response.getHeaders().setLocation(URI.create(url));
				return response.setComplete();
			});
}
	
	@GetMapping("/reportall")
	public Flux<ResponseReport> getReportOfAllUrls(){
		return urlService.getReportOfAll();
	}
	
	@GetMapping("/report/{userId}")
	public Flux<ResponseReport> getReportOfUser(@PathVariable String userId){
		return urlService.getUserSpecificReport(userId);
	}
	
	@GetMapping("/reportbydate")
	public Flux<ResponseReport> getReportOfUserByCreationDate( @Validated @RequestParam LocalDate date){
		return urlService.getUserReportWithCreationDate(date);
	}
	@PostMapping("/storeurl")
	public Mono<ResponseUrl> storeUrl(@Validated @RequestBody UrlData urldata){
		return urlService.checkUserData(urldata);
	}
}
