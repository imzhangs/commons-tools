package com.kd.commons.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlUtils {
	/**
	 * 解析出url请求的路径，包括页面
	 * 
	 * @param strURL
	 *            url地址
	 * @return url路径
	 */
	public static String UrlPage(String strURL) {
		String strPage = null;
		String[] arrSplit = null;

		strURL = strURL.trim().toLowerCase();

		arrSplit = strURL.split("[?]");
		if (strURL.length() > 0) {
			if (arrSplit.length > 1) {
				if (arrSplit[0] != null) {
					strPage = arrSplit[0];
				}
			}
		}

		return strPage;
	}

	/**
	 * 去掉url中的路径，留下请求参数部分
	 * 
	 * @param strURL
	 *            url地址
	 * @return url请求参数部分
	 */
	private static String truncateUrlPage(String strURL) {
		String strAllParam = null;
		String[] arrSplit = null;

		strURL = strURL.trim();
		int lastAndIndex=strURL.lastIndexOf("&");
		int lastPoundIndex=strURL.lastIndexOf("#");
		if(lastPoundIndex>lastAndIndex){
			strURL=strURL.substring(0, lastPoundIndex);
		}
		arrSplit = strURL.split("[?]");
		if (strURL.length() > 1) {
			if (arrSplit.length > 1) {
				if (arrSplit[1] != null) {
					strAllParam = arrSplit[1];
				}
			}
		}

		return strAllParam;
	}

	/**
	 * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
	 * 
	 * @param URL
	 *            url地址
	 * @return url请求参数部分
	 */
	public static Map<String, String> URLRequest(String URL) {
		Map<String, String> mapRequest = new HashMap<String, String>();

		String[] arrSplit = null;

		String strUrlParam = truncateUrlPage(URL);
		if (strUrlParam == null) {
			return mapRequest;
		}
		// 每个键值为一组 www.2cto.com
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			// 解析出键值
			if (arrSplitEqual.length > 1) {
				// 正确解析
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

			} else {
				if (arrSplitEqual[0] != "") {
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}
	public static String getValue(String url,String name){
		Map<String, String> map=URLRequest(url);
		if(map!=null){
			return map.get(name);
		}
		return null;
	}
	
	public static String formatUrl(String url){
		Map<String,String> paramMap=UrlUtils.URLRequest(url);
		List<String> keyList=new ArrayList<String>();
		keyList.addAll(paramMap.keySet());
		Collections.sort(keyList,new Comparator<String>(){
			public int compare(String o1, String o2) {				
				return o1.compareTo(o2);
			}} );
		if(url.indexOf("?")>0){
			url=url.substring(0,url.indexOf("?"));
		}
		StringBuffer urlBuf=new StringBuffer(url);
		if(keyList.size()>0){
			urlBuf.append("?");
		}
		for(int i=0;i<keyList.size();i++){
			if(i>0){
				urlBuf.append("&");
			}
			String name=keyList.get(i);
			urlBuf.append(name+"="+paramMap.get(name));
			
		}
		return urlBuf.toString();
	}
	
	/**
	 * come back domain for url
	 * @param url
	 * @return
	 */
	public static String getDomain(String url) {
		if(null==url || "".equals(url)) {
			return null;
		}
		URL u = null;
		String host = null;
		try {
			u = new URL(url);
			host = u.getHost();// 获取主机名 
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return host;
	}
	
	/**
	 * whether to judge the url is detailed page or not by the specified regexs.
	 * @param url
	 * @param regex
	 * @return
	 */
	public static boolean isDetailed(String url, String[] regex) {
		if(null==regex || 0==regex.length) {
			return false;
		}
		for (int i=0; i < regex.length; i++) {
			if (url.matches(regex[i])) {
				return true;
			}
		}
		return false;
	}
	public static boolean isDetailed(String url, List<String> regex) {
		if(null==regex || 0==regex.size()) {
			return false;
		}
		for(String r : regex) {
			if(url.matches(r)) {
				return true;
			}
		}
		return false;
	}

	public static String encode(String s,String charset) {
        if (s == null) {
            return "";
        }
        try {
            s =  URLDecoder.decode(s, charset);
        } catch (Exception wow) {
        }
        try {
            return URLEncoder.encode(s, charset)
                    .replace("+", "%20").
                    replace("*", "%2A")
                    .replace("%7E", "~")
                    .replace("%3D", "=")
                    .replace("%40", "@");
        } catch (Exception wow) {
        }
        return s;
    }
}
