package com.kd.commons.result;


public class ModelResult<T> extends ResponseSet{
	
	public T data;
	
	public ModelResult(){
		super();
		this.set(ReturnCodeEnum._200);
	}
}
