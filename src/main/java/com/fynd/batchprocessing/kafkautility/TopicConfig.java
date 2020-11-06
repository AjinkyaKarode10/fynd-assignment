package com.fynd.batchprocessing.kafkautility;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {


	@Value(value = "${userCreated.topic.name}")
	private String userCreated;

	@Value(value = "${userModified.topic.name}")
	private String userModified;
	
	@Value(value = "${userInvoiceGenerated.topic.name}")
	private String userInvoiceGenerated;


	@Bean
	public NewTopic userCreated() {
		return TopicBuilder.name(userCreated)
			      .partitions(1)
			      .replicas(1)
			      .build();
	}

	@Bean
	public NewTopic userModified() {
		return TopicBuilder.name(userModified)
			      .partitions(1)
			      .replicas(1)
			      .build();
	}
	
	@Bean
	public NewTopic userInvoiceGenerated() {
		return TopicBuilder.name("userInvoiceGenerated")
			      .partitions(1)
			      .replicas(1)
			      .build();
	}
	
	
	
	
}
