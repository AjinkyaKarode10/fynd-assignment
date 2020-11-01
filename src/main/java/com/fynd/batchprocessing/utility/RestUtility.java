package com.fynd.batchprocessing.utility;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestUtility {

	static RestTemplate restTemplate;
	
	static final String BASE_URL="http://localhost:8081/publish";
	
	
	public boolean doPost(JSONObject request)
	{
		
		restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.put("userId", Arrays.asList("OyYbyQBcF"));
		
		HttpEntity<Object> entity = new HttpEntity<Object>(request.toString(),headers);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(BASE_URL,
																HttpMethod.POST,
																entity,
																String.class);
		
		
		return responseEntity.getStatusCode().is2xxSuccessful();
		
	}
	
}
