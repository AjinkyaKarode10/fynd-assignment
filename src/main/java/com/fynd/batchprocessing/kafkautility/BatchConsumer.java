package com.fynd.batchprocessing.kafkautility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fynd.batchprocessing.controller.ClientController;
import com.fynd.batchprocessing.pojo.CustomerPayload;

@Service
public class BatchConsumer {

	private static final Logger LOGGER=LoggerFactory.getLogger(BatchConsumer.class);
	
	private Date startDate = new Date();

	private static int COUNTER = 0;

	@Value(value = "${filenamePattern}")
	private String filenamePattern;

	//@KafkaListener(topics = {"userCreated" , "userModified"}, groupId = "group_id")
	@KafkaListener(topics = "#{'${spring.kafka.topicName}'.split(',')}",groupId = "group_id")
	public void consumeMessage(CustomerPayload payload) {

		LOGGER.info("Message received");
		try 
		{
			Date endDate = new Date();
			long difference = endDate.getTime() - startDate.getTime();
			
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			
			File file = new File(filenamePattern + date + "batch-"+COUNTER +".txt");
			file.createNewFile();

			long length = file.length() / 1024;
			if ((difference / (60 * 1000) % 60) > 5 
					|| length > 10) 
			{
				COUNTER = COUNTER+1;
				File newFile = new File(filenamePattern + date + "batch-" + COUNTER + ".txt" );
				file.createNewFile();
				LOGGER.info("New File created");
				appendDataToFile(newFile.getPath(), payload);
				startDate = new Date();
			} 
			else {
				LOGGER.info("Same File");
				appendDataToFile(file.getPath(), payload);

			}

			System.out.println("userCreated " + payload);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public static void appendDataToFile(String fileName, CustomerPayload payload) {
		try {
			// Open given file in append mode.
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
			out.write(payload.toString());
			out.newLine();
			out.close();
		} catch (IOException e) {
			System.out.println("exception occoured" + e);
		}
	}

}
