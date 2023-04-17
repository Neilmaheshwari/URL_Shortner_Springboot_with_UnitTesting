package com.UrlProject.ShortnerUrl.Entity;

import java.time.LocalDate;
import java.util.HashMap;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("UrlData")
public class UrlDataEntity {
	
	@Id
	private ObjectId id;
	private String userId;
	private String shortUrl;
	private String longUrl;
	private LocalDate urlCreationDate;
	private long urlClicksCount;
	private boolean isUrlActive;
	private HashMap<LocalDate,Integer> urlFetchData= new HashMap<LocalDate,Integer>();
	
	
	public HashMap<LocalDate, Integer> getUrlFetchData() {
		return urlFetchData;
	}


	public void setUrlFetchData(HashMap<LocalDate, Integer> urlFetchData) {
		this.urlFetchData = urlFetchData;
	}


	public UrlDataEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	public UrlDataEntity(String userId, String shortUrl, String longUrl, LocalDate urlCreationDate, long urlClicksCount,
			boolean isUrlActive, HashMap<LocalDate, Integer> urlFetchData) {
		super();
		this.userId = userId;
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.urlCreationDate = urlCreationDate;
		this.urlClicksCount = urlClicksCount;
		this.isUrlActive = isUrlActive;
		this.urlFetchData = urlFetchData;
	}


	public UrlDataEntity(ObjectId id, String userId, String shortUrl, String longUrl, LocalDate urlCreationDate,
			long urlClicksCount, boolean isUrlActive, HashMap<LocalDate, Integer> urlFetchData) {
		super();
		this.id = id;
		this.userId = userId;
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.urlCreationDate = urlCreationDate;
		this.urlClicksCount = urlClicksCount;
		this.isUrlActive = isUrlActive;
		this.urlFetchData = urlFetchData;
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


	public String getShortUrl() {
		return shortUrl;
	}


	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}


	public String getLongUrl() {
		return longUrl;
	}


	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}


	public LocalDate getUrlCreationDate() {
		return urlCreationDate;
	}


	public void setUrlCreationDate(LocalDate urlCreationDate) {
		this.urlCreationDate = urlCreationDate;
	}


	public long getUrlClicksCount() {
		return urlClicksCount;
	}


	public void setUrlClicksCount(long urlClicksCount) {
		this.urlClicksCount = urlClicksCount;
	}


	public boolean isUrlActive() {
		return isUrlActive;
	}


	public void setIsUrlActive(boolean isUrlActive) {
		this.isUrlActive = isUrlActive;
	}


	@Override
	public String toString() {
		return "UrlDataEntity [id=" + id + ", userId=" + userId + ", shortUrl=" + shortUrl + ", longUrl=" + longUrl
				+ ", urlCreationDate=" + urlCreationDate + ", urlClicksCount=" + urlClicksCount + ", isUrlActive="
				+ isUrlActive + ", urlFetchData=" + urlFetchData + "]";
	}


	
	
	

}
