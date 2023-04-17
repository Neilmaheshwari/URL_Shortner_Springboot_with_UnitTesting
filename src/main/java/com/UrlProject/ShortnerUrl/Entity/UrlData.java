package com.UrlProject.ShortnerUrl.Entity;

import com.mongodb.lang.NonNull;

public class UrlData {

	@NonNull
	private String userId;
	@NonNull
	private String longUrl;
	public UrlData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UrlData(String userId, String longUrl) {
		super();
		this.userId = userId;
		this.longUrl = longUrl;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	@Override
	public String toString() {
		return "UrlData [userId=" + userId + ", longUrl=" + longUrl + "]";
	}
	
	
}
