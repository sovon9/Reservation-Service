package com.sovon9.Reservation_service.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.sovon9.Reservation_service.dto.GuestCommInfo;

@Service
public class KafkaProducerService
{
	KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate)
	{
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void publish(GuestCommInfo commInfo)
	{
		CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send("PMS2", commInfo);
		completableFuture.whenComplete((result, exception)->{
			if(null==exception)
			{
				System.out.println("=======> partition no: "+result.getRecordMetadata().partition()+
						" offset id: "+result.getRecordMetadata().offset());
			}
			else
			{
				System.out.println("exceptiom=> "+exception.getMessage());
			}
		});
	}
	
}
