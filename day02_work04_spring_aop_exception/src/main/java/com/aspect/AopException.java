package com.aspect;

public class AopException {
	public void throwException(Throwable ex){
		System.out.println(ex.getMessage());
	}
}
