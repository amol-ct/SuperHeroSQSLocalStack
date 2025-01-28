package com.cleartax.training_superheroes.controllers;


import com.cleartax.training_superheroes.config.SqsConfig;
import com.cleartax.training_superheroes.dto.Superhero;
import com.cleartax.training_superheroes.dto.SuperheroRequestBody;
import com.cleartax.training_superheroes.services.SqsService;
import com.cleartax.training_superheroes.services.SuperHeroConsumer;
import com.cleartax.training_superheroes.services.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.awscore.AwsClient;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.ArrayList;
import java.util.List;

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


    protected boolean canEqual(final Object other) {
        return other instanceof SuperheroController;
    }

    public SuperheroService getSuperheroService() {
        return this.superheroService;
    }

    public SqsConfig getSqsConfig() {
        return this.sqsConfig;
    }

    public SqsClient getSqsClient() {
        return this.sqsClient;
    }

    public AwsClient getAwsClient() {
        return this.awsClient;
    }

    public SqsService getSqsService() {
        return this.sqsService;
    }

    public void setSuperheroService(SuperheroService superheroService) {
        this.superheroService = superheroService;
    }

    public void setSqsConfig(SqsConfig sqsConfig) {
        this.sqsConfig = sqsConfig;
    }

    public void setSqsClient(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void setAwsClient(AwsClient awsClient) {
        this.awsClient = awsClient;
    }

    public void setSqsService(SqsService sqsService) {
        this.sqsService = sqsService;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SuperheroController)) return false;
        final SuperheroController other = (SuperheroController) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$superheroService = this.getSuperheroService();
        final Object other$superheroService = other.getSuperheroService();
        if (this$superheroService == null ? other$superheroService != null : !this$superheroService.equals(other$superheroService))
            return false;
        final Object this$sqsConfig = this.getSqsConfig();
        final Object other$sqsConfig = other.getSqsConfig();
        if (this$sqsConfig == null ? other$sqsConfig != null : !this$sqsConfig.equals(other$sqsConfig)) return false;
        final Object this$sqsClient = this.getSqsClient();
        final Object other$sqsClient = other.getSqsClient();
        if (this$sqsClient == null ? other$sqsClient != null : !this$sqsClient.equals(other$sqsClient)) return false;
        final Object this$awsClient = this.getAwsClient();
        final Object other$awsClient = other.getAwsClient();
        if (this$awsClient == null ? other$awsClient != null : !this$awsClient.equals(other$awsClient)) return false;
        final Object this$sqsService = this.getSqsService();
        final Object other$sqsService = other.getSqsService();
        if (this$sqsService == null ? other$sqsService != null : !this$sqsService.equals(other$sqsService))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $superheroService = this.getSuperheroService();
        result = result * PRIME + ($superheroService == null ? 43 : $superheroService.hashCode());
        final Object $sqsConfig = this.getSqsConfig();
        result = result * PRIME + ($sqsConfig == null ? 43 : $sqsConfig.hashCode());
        final Object $sqsClient = this.getSqsClient();
        result = result * PRIME + ($sqsClient == null ? 43 : $sqsClient.hashCode());
        final Object $awsClient = this.getAwsClient();
        result = result * PRIME + ($awsClient == null ? 43 : $awsClient.hashCode());
        final Object $sqsService = this.getSqsService();
        result = result * PRIME + ($sqsService == null ? 43 : $sqsService.hashCode());
        return result;
    }

    public String toString() {
        return "SuperheroController(superheroService=" + this.getSuperheroService() + ", sqsConfig=" + this.getSqsConfig() + ", sqsClient=" + this.getSqsClient() + ", awsClient=" + this.getAwsClient() + ", sqsService=" + this.getSqsService() + ")";
    }
}
