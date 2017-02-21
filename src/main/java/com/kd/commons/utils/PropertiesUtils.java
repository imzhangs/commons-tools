package com.kd.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {
	private static Map<String,Properties> cacheProperties=new HashMap<String,Properties>();
	
	public static Properties getProperties(String filePath){
		if(cacheProperties.containsKey(filePath)){
			return cacheProperties.get(filePath);
		}
		try {
			InputStream inputStream;      
			ClassLoader cl = PropertiesUtils.class.getClassLoader();      
			if  (cl !=  null ) {      
				inputStream = cl.getResourceAsStream(filePath);      
			}else{      
				inputStream = ClassLoader.getSystemResourceAsStream(filePath);      
			}      
			Properties dbProps =  new  Properties();      
			dbProps.load(inputStream);      
			inputStream.close(); 
			cacheProperties.put(filePath, dbProps);
		}catch (IOException e) {
			 e.printStackTrace();
			 return null;
		}
		return cacheProperties.get(filePath);
	}
}
