package com.dad.saa.servicios;

import com.dad.saa.entidades.Comentario;
import com.dad.saa.repositorios.ComentarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServicios {

    @Autowired
    private ComentarioRepositorio comentarioRepository;

    public List<Comentario> obtenerComentariosPorPublicacion(String idPublicacion) {
        return comentarioRepository.findByIdPublicacion(idPublicacion);
    }

    public Comentario agregarComentario(Comentario comentario) {
        comentario.setFechaComentario(LocalDateTime.now());
        return comentarioRepository.save(comentario);
    }

    public Optional<Comentario> obtenerComentarioPorId(String id) {
        return comentarioRepository.findById(id);
    }

    public Comentario actualizarComentario(String id, Comentario nuevoComentario) {
        return comentarioRepository.findById(id).map(comentario -> {
            comentario.setTexto(nuevoComentario.getTexto());
            comentario.setNombrePersona(nuevoComentario.getNombrePersona());
            return comentarioRepository.save(comentario);
        }).orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
    }

    public void eliminarComentario(String id) {
        comentarioRepository.deleteById(id);
    }
}
