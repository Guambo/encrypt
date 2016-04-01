package com.ve.guambo.encrypt.util;

//import gnu.crypto.Registry;
//import gnu.crypto.hash.HashFactory;
//import gnu.crypto.hash.IMessageDigest;
//import gnu.crypto.util.Util;
import lib.algorithm.Whirlpool;

public class EncryptWhirlpool extends Crypto {
	
	/*
	 * Creates a hash from given string using Whirlpool-T algorithm
	 * 
	 * @author Alvaro J. Urbaez (2016-03-31)
	 * @param cad -> This is the given original string
	 * @param uppercase -> Indicates if the result will be upper (true) or lower (false) case
	 * @return A hash encrypted by Whirlpool-T algorithm from given string
	 *
	public static String toWhirlpoolT(String cad, boolean uppercase) {
		String wp = "";
	    try {
	        IMessageDigest md = HashFactory.getInstance(Registry.WHIRLPOOL_HASH);
	        byte[] b = cad.getBytes();
	        md.update(b, 0, b.length);
	        byte[] digest = md.digest();
	        wp = Util.toString(digest);
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return (uppercase?wp.toUpperCase():wp.toLowerCase());
	}*/
	
	/**
	 * Creates a hash from given string using Whirlpool algorithm
	 * 
	 * @author Alvaro J. Urbaez (2016-03-31)
	 * @param cad -> This is the given original string
	 * @param uppercase -> Indicates if the result will be upper (true) or lower (false) case
	 * @return A hash encrypted by Whirlpool algorithm from given string
	 */
	public static String toWhirlpool(String cad, boolean uppercase) {
		String wp = "";
	    try {
	        Whirlpool w = new Whirlpool();
	        byte[] digest = new byte[Whirlpool.DIGESTBYTES];
	        w.NESSIEinit();
	        w.NESSIEadd(cad);
	        w.NESSIEfinalize(digest);
	        wp = Whirlpool.display(digest);
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return (uppercase?wp.toUpperCase():wp.toLowerCase());
	}
	
	public static void main(String...args) {
//		System.out.println("Whirlpool: "+toWhirlpoolT("Alvaro", false));
		System.out.println("Whirlpool: "+toWhirlpool("", false));
	}
}
