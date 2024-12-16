package com.dad.saa.servicios;

import com.dad.saa.entidades.Chat;
import com.dad.saa.repositorios.ChatRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServicio {

    @Autowired
    private ChatRepositorio chatRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<Chat> verificarChatExistente(String id1, String id2) {
        // Usamos Criteria para buscar ambos IDs en cualquier orden
        Query query = new Query();
        query.addCriteria(Criteria.where("participantes").all(Arrays.asList(id1, id2)));

        // Ejecutamos la consulta con MongoTemplate
        List<Chat> chats = mongoTemplate.find(query, Chat.class);

        // Si existe un chat, lo devolvemos
        return chats.isEmpty() ? Optional.empty() : Optional.of(chats.get(0));
    }

    public Chat crearChat(String id1, String id2) {
        Chat nuevoChat = new Chat();
        nuevoChat.setParticipantes(Arrays.asList(id1, id2));
        nuevoChat.setMensajes(List.of()); // Mensajes vacíos
        return chatRepository.save(nuevoChat);
    }
}
