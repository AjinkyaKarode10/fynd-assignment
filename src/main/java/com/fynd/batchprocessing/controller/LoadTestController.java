package com.fynd.batchprocessing.controller;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fynd.batchprocessing.pojo.LoadTestResponse;
import com.fynd.batchprocessing.utility.RestUtility;

@RestController
public class LoadTestController {

	 
    @Value(value = "${defaultLoadTestFilePath}")
	private String defaultLoadTestFilePath;
    
	@Autowired
	RestUtility restUtility;
	
	private static final String EVENT_TYPE = "eventType";
	private static final String USER_CREATED_TOPIC = "userCreated";
	private static final String USER_MODIFIED_TOPIC = "userModified";
	private static final String USER_INVOICE_GENERATED_TOPIC = "userInvoiceGenerated";
	
	 @GetMapping("/loadTest")
	    public LoadTestResponse sendData(@RequestParam String filepath)
	 {
		 int countUserCreatedTopic=0;
		 int countUserModifiedTopic=0;
		 int countUserInvoiceGeneratedTopic=0;
		 
		 if(filepath == null || filepath.equals(""))
	    	{
			 	//defaultLoadTestFilePath
	    		filepath=defaultLoadTestFilePath;
	    	}
	    	JSONParser parser = new JSONParser();
	    	try {

	            Object obj = parser.parse(new FileReader(filepath));
	            JSONArray jsonArray = (JSONArray) obj;
	            int length = jsonArray.size();
	            for (int i =0; i< length; i++) 
	            {
	                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
	                if(jsonObject.get(EVENT_TYPE).equals(USER_CREATED_TOPIC))
	                {
	                	countUserCreatedTopic++;
	                }
	                else if(jsonObject.get(EVENT_TYPE).equals(USER_MODIFIED_TOPIC))
	                {
	                	countUserModifiedTopic++;
	                }
	                else if(jsonObject.get(EVENT_TYPE).equals(USER_INVOICE_GENERATED_TOPIC))
	                {
	                	countUserInvoiceGeneratedTopic++;
	                }
	                
	                long startTime = System.currentTimeMillis();
	                boolean result = restUtility.doPost(jsonObject);
	                
	                
	                //Thread.sleep(1000);
	                 
	            }
	            
	            LoadTestResponse response = new LoadTestResponse();
	            response.setTopicA(countUserCreatedTopic);
	            response.setTopicB(countUserModifiedTopic);
	            response.setTopicC(countUserInvoiceGeneratedTopic);
	            
	            return response;
	    	}
	    	catch (Exception e) {
				
			}
			return null;
	    	

	    }
	    
	    
	
}
