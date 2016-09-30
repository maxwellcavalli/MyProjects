package br.com.example.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

public class HashUtil {

	public static String toHash(String value) throws Exception {
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		sha.update(value.getBytes());
		byte[] digest = sha.digest();
		
		return Base64.encodeBase64String(digest);
	}
	
}
