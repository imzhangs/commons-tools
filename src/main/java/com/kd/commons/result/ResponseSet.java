package com.kd.commons.result;

import com.alibaba.fastjson.JSONObject;

public class ResponseSet {
	
 
	public Integer code;

	public String msg;

	public ResponseSet(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResponseSet() {
		super();
	}
	
	public ResponseSet set(int code, String msg) {
		this.code = code;
		this.msg = msg;
		return this;
	}


	public ResponseSet set(ReturnCodeEnum ReturnCodeEnum) {
		this.code = ReturnCodeEnum.code;
		this.msg = ReturnCodeEnum.msg;
		return this;
	}

	public ResponseSet set(ReturnCodeEnum ReturnCodeEnum, String msg) {
		this.code = ReturnCodeEnum.code;
		this.msg = msg;
		return this;
	}
	
	
	public ResponseSet ok() {
		this.code = ReturnCodeEnum._200.code;
		this.msg = ReturnCodeEnum._200.msg;
		return this;
	}

	public ResponseSet ok(String msg) {
		this.code = ReturnCodeEnum._200.code;
		this.msg = msg!=null?msg:ReturnCodeEnum._200.msg;
		return this;
	}
	
	public ResponseSet paramError(String msg) {
		this.code = ReturnCodeEnum.PARAM_ERROR.code;
		this.msg = msg!=null?msg:ReturnCodeEnum.PARAM_ERROR.msg;
		return this;
	}
	

	public ResponseSet withError(Throwable e) {
		this.code = ReturnCodeEnum._500.code;
		this.msg = e.getMessage();
		return this;
	}

	public ResponseSet withError(ReturnCodeEnum ReturnCodeEnum, Exception e) {
		this.code = ReturnCodeEnum.code;
		this.msg = e.getMessage();
		return this;
	}

	public ResponseSet withError(int errorCode, Exception e) {
		this.code = errorCode;
		this.msg = e.getMessage();
		return this;
	}
	
	
	public String toJSONString(){
		return JSONObject.toJSONString(this);
	}

}
