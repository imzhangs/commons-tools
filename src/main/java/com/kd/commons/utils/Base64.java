package com.kd.commons.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Base64 {

	/**
	 * 编码
	 * 
	 * @param bstr
	 * @return String
	 */
	@SuppressWarnings("restriction")
	public static String encode(byte[] bstr) {
		return new sun.misc.BASE64Encoder().encode(bstr);
	}
	
	/**
	 * 编码
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		return encode(str.getBytes());
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return string
	 */
	@SuppressWarnings("restriction")
	public static byte[] decode(String str) {
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bt;
	}
	
	/**
	 * 解码
	 * 
	 * @param str
	 * @return string
	 */
	public static String decodeStr(String str) {
		return new String(decode(str));
	}
	
	
	public static void main(String[] args) throws Exception {
		
//		String rs=" data: {cre:'zlpc',mod:'f',statics:1,rfunc:103,fields:'url',feed_fmt:1,cateid:'1o_FWW',merge:3,this_page:1,dedup:32,pageurl:oUrl,offset:0,length:6}";
//		System.out.println(rs=encode(rs));
//		System.out.println(decodeStr(rs));
		
		String ss="http://blog.csdn.net/chszs/article/details/17027535?asdfasdf=asda&sdf=asdf";
		System.out.println(ss=URLEncoder.encode(ss, "utf-8"));
		System.out.println(URLDecoder.decode(ss, "utf-8"));
		
	}
}
