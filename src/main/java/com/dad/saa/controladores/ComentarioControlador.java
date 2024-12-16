package com.dad.saa.controladores;

import com.dad.saa.entidades.Comentario;
import com.dad.saa.servicios.ComentarioServicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioControlador {

    @Autowired
    private ComentarioServicios comentarioService;

    @GetMapping("/{idPublicacion}")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorPublicacion(@PathVariable String idPublicacion) {
        return ResponseEntity.ok(comentarioService.obtenerComentariosPorPublicacion(idPublicacion));
    }

    @PostMapping
    public ResponseEntity<Comentario> agregarComentario(@RequestBody Comentario comentario) {
        return ResponseEntity.ok(comentarioService.agregarComentario(comentario));
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Comentario> obtenerComentarioPorId(@PathVariable String id) {
        return comentarioService.obtenerComentarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizarComentario(@PathVariable String id, @RequestBody Comentario nuevoComentario) {
        return ResponseEntity.ok(comentarioService.actualizarComentario(id, nuevoComentario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable String id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
