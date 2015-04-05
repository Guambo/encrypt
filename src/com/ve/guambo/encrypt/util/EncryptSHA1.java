package com.ve.guambo.encrypt.util;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class EncryptSHA1 extends Crypto {
	
	public static String toSHA1(String cad) {
		String sha1 = "";
	    try {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(cad.getBytes(DEFAULT_CHARSET_UTF8));
	        final byte[] hash = crypt.digest();
	        Formatter formatter = new Formatter();
		    for (byte b : hash) {
		        formatter.format("%02x", b);
		    }
		    sha1 = formatter.toString();
		    formatter.close();
	    } catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return sha1;
	}
	
	public static String toHmacSHA1(String cad, String key) throws InvalidKeyException {
		String sha1 = "";
	    try {
	        Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec secret = new SecretKeySpec(key.getBytes(DEFAULT_CHARSET_UTF8), mac.getAlgorithm());
			mac.init(secret);
			byte[] hash = mac.doFinal(cad.getBytes(DEFAULT_CHARSET_UTF8));
	        Formatter formatter = new Formatter();
		    for (byte b : hash) {
		        formatter.format("%02x", b);
		    }
		    sha1 = formatter.toString();
		    formatter.close();
	    } catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return sha1;
	}
}
