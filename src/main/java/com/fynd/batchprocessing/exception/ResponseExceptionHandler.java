package com.fynd.batchprocessing.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Value("${spring.kafka.topicName:}")    
	List<String> topicNameList = new ArrayList<String>();
	
	
	  @ExceptionHandler(TopicNotFoundException.class)
	  public final ResponseEntity<Object> handleTopicNotFound(TopicNotFoundException ex) {
	    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),"Valid Topicname : "+topicNameList);
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	  }
	  
	  @ExceptionHandler(KafkaGenericException.class)
	  public final ResponseEntity<ErrorDetails> handleKafkaException(KafkaGenericException ex) {
	    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),"Please try again later");
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_IMPLEMENTED);
	  }
	  
	  @ExceptionHandler(Exception.class)
	  public final ResponseEntity<ErrorDetails> handleGeneric(Exception ex) {
	    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),"");
	    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	  }
}
