package com.UrlProject.ShortnerUrl.ResponseObj;

import java.time.LocalDate;

public class ResponseUrl {
	
	private String userId;
	private String shortUrl;
	private LocalDate creationDate;
	private long urlClicksCount;
	private boolean isUrlActive;
	public ResponseUrl() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseUrl(String userId, String shortUrl, LocalDate creationDate, long urlClicksCount,
			boolean isUrlActive) {
		super();
		this.userId = userId;
		this.shortUrl = shortUrl;
		this.creationDate = creationDate;
		this.urlClicksCount = urlClicksCount;
		this.isUrlActive = isUrlActive;
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
	public LocalDate getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
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
	@Override
	public String toString() {
		return "ResponseUrl [userId=" + userId + ", shortUrl=" + shortUrl + ", creationDate=" + creationDate
				+ ", urlClicksCount=" + urlClicksCount + ", isUrlActive=" + isUrlActive + "]";
	}
	
	
	
}
