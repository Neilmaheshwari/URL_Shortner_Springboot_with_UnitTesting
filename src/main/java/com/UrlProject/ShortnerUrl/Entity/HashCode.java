package com.UrlProject.ShortnerUrl.Entity;

import java.util.UUID;

public class HashCode {

	
	public static String generatingId(int len) {
		UUID uuid= UUID.randomUUID();
		return uuid.toString().substring(0, len);
		
	}
	
	
}
