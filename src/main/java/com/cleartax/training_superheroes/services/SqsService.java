package com.cleartax.training_superheroes.services;

import com.cleartax.training_superheroes.dto.MessageBody;
import com.cleartax.training_superheroes.repos.MessageRepository;
import com.cleartax.training_superheroes.repos.SuperheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

@Service
public class SqsService {

    @Autowired
    private final SqsClient sqsClient;

    @Autowired
    public MessageRepository messageRepository;

    @Autowired
    private MessageBody message;

    public SqsService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
//        this.messageRepository = messageRepository;
    }

    // Send a message to the queue
    public void sendMessage(String queueUrl, String messageBody) {
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .delaySeconds(0) // No delay
                .build();

        sqsClient.sendMessage(request);
        message.setMessage(messageBody);
        messageRepository.save(message);
        System.out.println("Message sent: " + messageBody);
    }

    // Receive messages from the queue
    public List<Message> receiveMessages(String queueUrl) {
        ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(5) // Fetch up to 5 messages
                .waitTimeSeconds(10) // Long polling
                .build();

        ReceiveMessageResponse response = sqsClient.receiveMessage(request);
        return response.messages();
    }

//    // Delete a message from the queue
//    public void deleteMessage(String queueUrl, String receiptHandle) {
//        DeleteMessageRequest request = DeleteMessageRequest.builder()
//                .queueUrl(queueUrl)
//                .receiptHandle(receiptHandle)
//                .build();
//
//        sqsClient.deleteMessage(request);
//        System.out.println("Message deleted.");
//    }
}