package com.cleartax.training_superheroes.repos;

import com.cleartax.training_superheroes.dto.MessageBody;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<MessageBody, String> {
}
