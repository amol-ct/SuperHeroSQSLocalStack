package com.cleartax.training_superheroes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class SqsClientConfig {

    private final SqsConfig sqsConfig;

    @Autowired
    public SqsClientConfig(SqsConfig sqsConfig) {
        this.sqsConfig = sqsConfig;
    }

    @Bean(name = "customSqsClient")
    public SqsClient sqsClient() {
        return SqsClient.builder()
                .region(Region.of(sqsConfig.getRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsSessionCredentials.create(
                                sqsConfig.getAccessKey(),
                                sqsConfig.getSecretKey(),
                                sqsConfig.getSessionToken()
                        )
                ))
                .build();
    }
}