package com.UrlProject.ShortnerUrl.repository;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.UrlProject.ShortnerUrl.Entity.UrlDataEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public interface UrlRepository extends ReactiveMongoRepository<UrlDataEntity, ObjectId> {

	Mono<UrlDataEntity> findByShortUrl(String shorturl);
	
	Mono<UrlDataEntity> findByUserIdAndLongUrlAndIsUrlActive(String userId, String longUrl,boolean b);

	Flux<UrlDataEntity> findByUserId(String userId);

	Flux<UrlDataEntity> findByUrlCreationDate(LocalDate date);

}
