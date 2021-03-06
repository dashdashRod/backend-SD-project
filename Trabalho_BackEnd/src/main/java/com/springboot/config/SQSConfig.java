package com.springboot.config;

import org.springframework.beans.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
public class SQSConfig{
	@Value("${cloud.aws.region.static}")
	private String region;
	
	@Value("${cloud.aws.credetials.acess-key}")
	private String awsAcessKey;
	
	@Value("${cloud.aws.credetials.secret-key}")
	private String awsSecretKey;
	
	@Bean
	public QueueMessagingTemplate queueMenssagingTemplate() {
		return new QueueMessagingTemplate(amazonSQSAsync());
	}
	
	public AmazonSQSAsync amazonSQSAsync() {
		return AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAcessKey,awsSecretKey))).build();
	}
}
