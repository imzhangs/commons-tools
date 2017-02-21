package com.kd.commons.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpRequestUtil {

	private static final String APPLICATION_JSON = "application/json";
	

	/**
	 * post请求
	 * 
	 * @param url  url 
	 * @param param  params for url
	 * @return String 
	 * @throws IOException
	 */
	public static String postJSON(String url, JSONObject json) {
		CloseableHttpClient httpclient = createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
		httpPost.setHeader("Connection", "close");
		System.out.println("params ===>>" + json);
		CloseableHttpResponse response = null;
		try {
			StringEntity entity = new StringEntity(json.toJSONString(),"utf-8");
			entity.setContentEncoding("utf-8");
			entity.setContentType(APPLICATION_JSON);
			httpPost.setEntity(entity);
			response = httpclient.execute(httpPost);
			System.out.println("response:<<=====" + response.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/** 请求发送成功，并得到响应 **/
		String result = null;
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity = response.getEntity();
			try {
				result = EntityUtils.toString(httpEntity, "UTF-8");
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * post json 请求
	 * 
	 * @param url    http url
	 * @param param   params for url
	 * @return JSONObject
	 * @throws IOException
	 */
	public static JSONObject postJSONReturn(String url, JSONObject json) {
		JSONObject responseJSON = null;
		try {
			String reusltJSONStr = postJSON(url, json);
			responseJSON = JSONObject.parseObject(reusltJSONStr);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return responseJSON;
	}
	
	/**
	 * post json 请求
	 * 
	 * @param url   URL
	 * @param param params for url
	 * @return String
	 * @throws IOException
	 */
	public static String postJSON(String url, String json) {
		try {
			return postJSON(url, JSONObject.parseObject(json));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	

	/**
	 * Creates {@link CloseableHttpClient} instance with default configuration.
	 * @return
	 */
	public static CloseableHttpClient createDefault() {
		return HttpClientBuilder.create().build();
	}

	/**
	 * http发送post请求
	 * 
	 * @param url
	 * @param maps
	 * @return
	 */
	public static JSONObject sendPost(String url, Map<String, String> params) {
		CloseableHttpClient client = createDefault();
		/** NameValuePair是传送给服务器的请求参数 param.get("name") **/
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue().toString();
			System.out.println("key=" + key + " value=" + value);
			NameValuePair pair = new BasicNameValuePair(key, value);
			list.add(pair);
		}
		UrlEncodedFormEntity entity = null;
		try {
			/** 设置编码 **/
			entity = new UrlEncodedFormEntity(list, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		/** 新建一个post请求 **/
		HttpPost post = new HttpPost(url);
		post.setEntity(entity);
		HttpResponse response = null;
		try {
			/** 客服端向服务器发送请求 **/
			response = client.execute(post);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/** 请求发送成功，并得到响应 **/
		JSONObject jsonObject = null;
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity = response.getEntity();
			String result = null;
			try {
				result = EntityUtils.toString(httpEntity);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} // 返回json格式：
			jsonObject = JSONObject.parseObject(result);
		}
		return jsonObject;
	}

	public static String doPost(String url, JSONObject json, String charset) {
		CloseableHttpClient httpclient = createDefault();
		HttpPost httpPost = null;
		String result = null;
		CloseableHttpResponse response = null;
		try {
			httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
			httpPost.setHeader("Connection", "close");
			StringEntity e = new StringEntity(json.toJSONString());
			httpPost.setEntity(e);
			response = httpclient.execute(httpPost);

			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
				EntityUtils.consume(resEntity);
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (response != null) {

				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
