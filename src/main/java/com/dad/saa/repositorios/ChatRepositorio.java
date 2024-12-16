package com.dad.saa.repositorios;

import com.dad.saa.entidades.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepositorio extends MongoRepository<Chat, String> {
}
