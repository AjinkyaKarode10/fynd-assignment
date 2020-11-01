package com.fynd.batchprocessing.exception;

public class TopicNotFoundException extends RuntimeException{

	private String message;
	
	public TopicNotFoundException(String message)
	{
		super(message);
		this.message = message;
	}
	
}
