package com.fynd.batchprocessing.kafkautility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import com.fynd.batchprocessing.exception.KafkaGenericException;
import com.fynd.batchprocessing.exception.TopicNotFoundException;
import com.fynd.batchprocessing.pojo.CustomerPayload;

@Service
public class BatchProducer {

	@Value("${spring.kafka.topicName:}")    
	List<String> topicNameList = new ArrayList<String>();
	
	private static final Logger LOGGER=LoggerFactory.getLogger(BatchProducer.class);
	
//	private static final String USER_CREATED_TOPIC = "userCreated";
//	private static final String USER_MODIFIED_TOPIC = "userModified";
//	private static final String USER_INVOICE_GENERATED_TOPIC = "userInvoiceGenerated";
	
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(CustomerPayload payload) throws TopicNotFoundException,KafkaGenericException
    {
    	if(!topicNameList.contains(payload.getEventType()))
    	{
    		throw new TopicNotFoundException("Invalid Event-Type/Topic-name");
    	}
    	
    	//this.kafkaTemplate.send(TOPIC, payload);
//    	if(payload.getEventType().equals(USER_CREATED_TOPIC))
//    	{
//    		this.kafkaTemplate.send(USER_CREATED_TOPIC,payload);
//    	}
//    	else if(payload.getEventType().equals(USER_MODIFIED_TOPIC))
//    	{
//    		this.kafkaTemplate.send(USER_MODIFIED_TOPIC,payload);
//    	}
    	
    	ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(payload.getEventType(),
    																				payload);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
          
            @Override
            public void onFailure(Throwable ex) {
                throw new KafkaGenericException("Error , Message Not Produced on kafka");
            }

			@Override
			public void onSuccess(SendResult<String, Object> arg0) {
				LOGGER.info("Message Produced on kafka");
			}

        });
    	
    }


	
}


