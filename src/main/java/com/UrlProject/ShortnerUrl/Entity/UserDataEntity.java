package com.UrlProject.ShortnerUrl.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("UserData")
public class UserDataEntity {

	@Id
	private ObjectId id;
	private String userId;
	private long totalUrlGenerated;
	
	
	
	public UserDataEntity() {
		super();
		// TODO Auto-generated constructor stub
	}



	public UserDataEntity(ObjectId id, String userId, long totalUrlGenerated) {
		super();
		this.id = id;
		this.userId = userId;
		this.totalUrlGenerated = totalUrlGenerated;
	}

	

	public UserDataEntity(String userId, long totalUrlGenerated) {
		super();
		this.userId = userId;
		this.totalUrlGenerated = totalUrlGenerated;
	}



	public ObjectId getId() {
		return id;
	}



	public void setId(ObjectId id) {
		this.id = id;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public long getTotalUrlGenerated() {
		return totalUrlGenerated;
	}



	public void setTotalUrlGenerated(long totalUrlGenerated) {
		this.totalUrlGenerated = totalUrlGenerated;
	}



	@Override
	public String toString() {
		return "UserDataEntity [id=" + id + ", userId=" + userId + ", totalUrlGenerated=" + totalUrlGenerated + "]";
	}
	
	
	
	
}
