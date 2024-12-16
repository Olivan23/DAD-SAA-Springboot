package com.dad.saa.repositorios;

import com.dad.saa.entidades.PerfilAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PerfilAdminRepositorio extends MongoRepository<PerfilAdmin, String> {
    Optional<PerfilAdmin> findByUserId(String userId);
}
