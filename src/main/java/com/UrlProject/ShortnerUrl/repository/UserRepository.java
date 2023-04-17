package com.UrlProject.ShortnerUrl.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.UrlProject.ShortnerUrl.Entity.UserDataEntity;

import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserDataEntity, ObjectId> {

	Mono<UserDataEntity> findByUserId(String string);

}
