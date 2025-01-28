package com.cleartax.training_superheroes.services;

import com.cleartax.training_superheroes.config.SqsConfig;
import com.cleartax.training_superheroes.dto.MessageBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

@Service
public class SuperHeroConsumer {

    @Autowired
    private SqsConfig sqsConfig;

    @Autowired
    private SqsClient sqsClient;

    @Autowired
    private MessageBody message;

    @Scheduled(fixedDelay = 2000) // Poll every 2 seconds
    public void pollMessages() {
        System.out.println("Polling for messages...");

        try {
            ReceiveMessageResponse receiveMessageResponse = sqsClient.receiveMessage(ReceiveMessageRequest.builder()
                    .queueUrl(sqsConfig.getQueueUrl())
                    .maxNumberOfMessages(5) // Batch size
                    .waitTimeSeconds(10) // Long polling
                    .build());

            List<Message> messages = receiveMessageResponse.messages();

            if (messages.isEmpty()) {
                System.out.println("No messages received");
                return;
            }

            for (Message message : messages) {
                System.out.println("Received message: " + message.body());
                processMessage(message);
            }
        } catch (Exception e) {
            System.err.println("Error polling messages: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processMessage(Message message) {
        try {
            System.out.println("Processing message: " + message.body());
            deleteMessage(message.receiptHandle());
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void deleteMessage(String receiptHandle) {
        try {
            DeleteMessageResponse deleteMessageResponse = sqsClient.deleteMessage(DeleteMessageRequest.builder()
                    .queueUrl(sqsConfig.getQueueUrl())
                    .receiptHandle(receiptHandle)
                    .build());
            System.out.println("Message deleted: " + deleteMessageResponse);
        } catch (Exception e) {
            System.err.println("Error deleting message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}