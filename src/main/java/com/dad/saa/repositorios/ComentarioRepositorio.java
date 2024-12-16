package com.dad.saa.repositorios;

import com.dad.saa.entidades.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ComentarioRepositorio extends MongoRepository<Comentario, String> {
    List<Comentario> findByIdPublicacion(String idPublicacion);
}
