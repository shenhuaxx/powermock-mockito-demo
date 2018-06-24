package com.shenhua.powermock.mockito.demo.exception;

public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -981672856751929674L;
	
	private String exception;

	public BusinessException(String exception) {
		super(exception);
		this.exception = exception;
	}

	public String getException() {
		return exception;
	}
	
}
