package com.ve.guambo.encrypt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptMD5 {
	
	/**
	 * Metodo que se encarga de encriptar una cadena dada en su correspondiente MD5
	 * @param cadena
	 * @return
	 */
	public static String cifrarEnMD5(String cadena) {
		String md5 = null;
		char[] CONSTS_HEX = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(cadena.getBytes());
			StringBuilder sb = new StringBuilder(2*bytes.length);
			for (int i = 0; i < bytes.length; i++) {
				int bajo = (int)(bytes[i] & 0x0f);
				int alto = (int)((bytes[i] & 0xf0) >> 4);
				sb.append(CONSTS_HEX[alto]);
				sb.append(CONSTS_HEX[bajo]);
			}
			md5 = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}
	
	public static void main(String...args) {
		String [] phones = {"4241386778", "4143275563", "4143292574", "4143967990", "4143153115", "4241775883", "4241132530"};
		for (int i = 0; i < phones.length; i++) {
			System.out.println(phones[i]+"|"+EncryptMD5.cifrarEnMD5(phones[i]));
		}
	}
}
