package com.cleartax.training_superheroes.controllers;


import com.cleartax.training_superheroes.config.SqsConfig;
import com.cleartax.training_superheroes.dto.Superhero;
import com.cleartax.training_superheroes.dto.SuperheroRequestBody;
import com.cleartax.training_superheroes.services.SqsService;
import com.cleartax.training_superheroes.services.SuperHeroConsumer;
import com.cleartax.training_superheroes.services.SuperheroService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.awscore.AwsClient;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.ArrayList;
import java.util.List;

@Data
@RestController
public class SuperheroController {

    private SuperheroService superheroService;

    @Autowired
    private SqsConfig sqsConfig;

    @Autowired
    private SqsClient sqsClient;

    @Autowired
    private AwsClient awsClient;

    @Autowired
    private SqsService sqsService;

    @Autowired
    private SuperHeroConsumer superHeroConsumer;

    @Autowired
    public SuperheroController(SuperheroService superheroService,SuperHeroConsumer superHeroConsumer, SqsClient sqsClient, AwsClient awsClient, SqsService sqsService) {

        this.superheroService = superheroService;
        this.sqsClient = sqsClient;
        this.awsClient = awsClient;
        this.sqsService = sqsService;
        this.superHeroConsumer=superHeroConsumer;
    }

//    @GetMapping("/hello")
//    public String hello(@RequestParam(value = "username", defaultValue = "World") String username) {
//
//        return String.format("Hello %s!", username);
//    }

    @GetMapping("/update_SuperHero_async")
    public String updateSuperHero(@RequestParam(value = "superHeroName", defaultValue = "thor") String superHeroName) {

        sqsService.sendMessage("http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-queue", superHeroName);
//        System.out.println(superHeroName);

        List<String> messages_output=new ArrayList<>();
        List<Message> res = sqsService.receiveMessages("http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-queue");
        for(Message message:res){
            System.out.println(message.body());
            messages_output.add(message.body());
        }
        return String.format("Hello %s!", superHeroName);
    }

    @GetMapping("/get_message_from_queue")
    public List<String> getMessage() {
        List<String> messages_output=new ArrayList<>();
        List<Message> res = sqsService.receiveMessages("http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-queue");
        for(Message message:res){
            System.out.println(message.body());
            messages_output.add(message.body());
        }
        return messages_output;
    }

//    @GetMapping("/superhero")
//    public Superhero getSuperhero(@RequestParam(value = "name", defaultValue = "Batman") String name,
//                                  @RequestParam(value = "universe", defaultValue = "DC") String universe) {
//        return superheroService.getSuperhero(name, universe);
//    }
//
//    @PostMapping("/superhero")
//    public Superhero persistSuperhero(@RequestBody SuperheroRequestBody superhero) {
//        return superheroService.persistSuperhero(superhero);
//    }

}
