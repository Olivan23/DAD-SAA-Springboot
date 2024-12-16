package com.dad.saa.controladores;

import com.dad.saa.entidades.Chat;
import com.dad.saa.servicios.ChatServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/chats")
@CrossOrigin(origins = "*")
public class ChatControlador {

    @Autowired
    private ChatServicio chatService;

    @PostMapping("/crear-o-verificar")
    public ResponseEntity<?> crearOVerificarChat(@RequestParam String id1, @RequestParam String id2) {
        Optional<Chat> chatExistente = chatService.verificarChatExistente(id1, id2);

        if (chatExistente.isPresent()) {
            return ResponseEntity.ok("Ya existe un chat entre los usuarios.");
        } else {
            Chat nuevoChat = chatService.crearChat(id1, id2);
            return ResponseEntity.ok(nuevoChat);
        }
    }
}
