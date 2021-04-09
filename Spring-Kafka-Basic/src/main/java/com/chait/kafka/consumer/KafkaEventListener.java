package com.chait.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventListener {
	
	@KafkaListener(topics = "${kafka.topic}")
	public void listen(Message<String> message) {
		//this is getting message 
		//from Topic
		//i have only print it 
		//kartik you can do anything
		String payload = message.getPayload();
		System.out.println(payload);
		
		// i have use String you can use POJO also.
	}

}
