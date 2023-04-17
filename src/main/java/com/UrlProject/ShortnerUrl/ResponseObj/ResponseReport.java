package com.UrlProject.ShortnerUrl.ResponseObj;

import java.time.LocalDate;
import java.util.HashMap;

public class ResponseReport {
	private String userId;
	private String shortUrl;
	private String longUrl;
	private LocalDate urlCreationDate;
	private long urlClicksCount;
	private boolean isUrlActive;
	private HashMap<LocalDate,Integer> urlFetchData= new HashMap<LocalDate,Integer>();
	public ResponseReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseReport(String userId, String shortUrl, String longUrl, LocalDate urlCreationDate,
			long urlClicksCount, boolean isUrlActive, HashMap<LocalDate, Integer> urlFetchData) {
		super();
		this.userId = userId;
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.urlCreationDate = urlCreationDate;
		this.urlClicksCount = urlClicksCount;
		this.isUrlActive = isUrlActive;
		this.urlFetchData = urlFetchData;
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
	public void setUrlActive(boolean isUrlActive) {
		this.isUrlActive = isUrlActive;
	}
	public HashMap<LocalDate, Integer> getUrlFetchData() {
		return urlFetchData;
	}
	public void setUrlFetchData(HashMap<LocalDate, Integer> urlFetchData) {
		this.urlFetchData = urlFetchData;
	}
	@Override
	public String toString() {
		return "ResponseReport [userId=" + userId + ", shortUrl=" + shortUrl + ", longUrl=" + longUrl
				+ ", urlCreationDate=" + urlCreationDate + ", urlClicksCount=" + urlClicksCount + ", isUrlActive="
				+ isUrlActive + ", urlFetchData=" + urlFetchData + "]";
	}
	
	
	
}
