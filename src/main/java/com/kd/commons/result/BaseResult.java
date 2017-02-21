package com.kd.commons.result;


import java.util.HashMap;
import java.util.Map;

public class BaseResult extends ResponseSet{
	
	public Map<String,Object> data;

	
	public BaseResult(){
		ok();
	}

	public void put(String key, Object val){
		if(data==null){
			data = new HashMap<String,Object>();
		}
		data.put(key, val);
	}
}
