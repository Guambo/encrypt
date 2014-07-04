package com.ve.guambo.encrypt.util;

import java.security.Key;
import java.util.Formatter;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptAES {
	
	public static byte[] encrypt(String value, String secretKey) {
        byte[] encrypted = null;
        try {
            byte[] raw = secretKey.getBytes();
            Key skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] iv = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec,ivParams);
            encrypted  = cipher.doFinal(value.getBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return encrypted;
    }

    public static  byte[] decrypt(byte[] encrypted, String secretKey) {
    	byte[] original = null;
		Cipher cipher = null;
        try {
            byte[] raw = secretKey.getBytes();
            Key key = new SecretKeySpec(raw, "AES");
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            
            //the block size (in bytes), or 0 if the underlying algorithm is not a block cipher
            byte[] ivByte = new byte[cipher.getBlockSize()];
            
            //This class specifies an initialization vector (IV). Examples which use
            //IVs are ciphers in feedback mode, e.g., DES in CBC mode and RSA ciphers with OAEP encoding operation.
            IvParameterSpec ivParamsSpec = new IvParameterSpec(ivByte);
            
            cipher.init(Cipher.DECRYPT_MODE, key, ivParamsSpec);
            original= cipher.doFinal(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return original;
    }
    
    public static String byteArrayToHexString(final byte[] hash) {
        Formatter formatter = new Formatter();
        StringBuffer sb = new StringBuffer();
        for (byte b : hash) {
            formatter.format("%02x", b);
            if (formatter.toString().length() % 2 == 0)
            	sb.append(formatter.toString().substring(formatter.toString().length() - 1));
        }
        formatter.close();
        return sb.toString();
    }
    
	public static void main(String...args) {
//		String password = "AJsoft22";
		String tdcNumber = "135";
//		String passSalt = "a8cb48ac71344ffe8692b05cf5c598f5"; //Es el MD5 del nombre&apellido
//		String passHash = "0e2f59840799b50c7ac6d3081a71ed86af829aee"; //Es el SHA1 del password-passSalt
		String secretKey = "df47124a42bf52521012dbf0cd92e34498b9890f"; //SHA1 de passSalt-passHash
		System.out.println("TDC plain: "+tdcNumber);
		secretKey = secretKey.substring(0, 16);
		System.out.println("Secret Key length: "+secretKey.length());
		byte[] encrypted = encrypt(tdcNumber, secretKey);
		System.out.println("TDC encrypted: "+byteArrayToHexString(encrypted));
		String decrypted = byteArrayToHexString(decrypt(encrypted, secretKey));
		System.out.println("TDC decrypted: "+decrypted);
		System.out.println(decrypted.equals(tdcNumber)?"Son iguales las cadenas":"Son diferentes las cadenas");
	}
}
