package com.fynd.batchprocessing.exception;

public class TopicNotCreated extends RuntimeException{

	private String message;
	
	public TopicNotCreated(String message)
	{
		this.message = message;
	}
	
}
