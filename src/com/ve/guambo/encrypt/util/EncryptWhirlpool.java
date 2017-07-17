package com.ve.guambo.encrypt.util;

import gnu.crypto.Registry;
import gnu.crypto.hash.HashFactory;
import gnu.crypto.hash.IMessageDigest;
import gnu.crypto.util.Util;
import lib.algorithm.Whirlpool;

public class EncryptWhirlpool extends Crypto {
	
	/**
	 * Creates a hash from given string using Whirlpool-T algorithm
	 * 
	 * @author Alvaro J. Urbaez (2016-03-31)
	 * @param cad -> This is the given original string
	 * @param uppercase -> Indicates if the result will be upper (true) or lower (false) case
	 * @return A hash encrypted by Whirlpool-T algorithm from given string
	 */
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
	}
	
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
		String whirlpoolT = toWhirlpoolT("The quick brown fox jumps over the lazy dog", true);
		String whirlpool = toWhirlpool("The quick brown fox jumps over the lazy dog", true);
		System.out.println("Whirlpool-T : "+whirlpoolT);
		System.out.println("Whirlpool   : "+whirlpool);
		String assertWhirlpoolT = "3CCF8252D8BBB258460D9AA999C06EE38E67CB546CFFCF48E91F700F6FC7C183AC8CC3D3096DD30A35B01F4620A1E3A20D79CD5168544D9E1B7CDF49970E87F1";
		String assertWhirlpool = "B97DE512E91E3828B40D2B0FDCE9CEB3C4A71F9BEA8D88E75C4FA854DF36725FD2B52EB6544EDCACD6F8BEDDFEA403CB55AE31F03AD62A5EF54E42EE82C3FB35";
		System.out.println("Whirlpool-T correct? "+(whirlpoolT.equals(assertWhirlpoolT)));
		System.out.println("Whirlpool correct? "+(whirlpool.equals(assertWhirlpool)));
	}
}
