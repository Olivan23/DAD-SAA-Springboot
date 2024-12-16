package com.dad.saa.controladores;

import com.dad.saa.entidades.Publicacion;
import com.dad.saa.dao.PublicacionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicaciones")
@CrossOrigin(origins = "*")
public class PublicacionControlador {

	@Autowired
	private PublicacionDAO publicacionDAO;

	// Endpoint para crear una nueva publicación
	@PostMapping
	public ResponseEntity<?> crearPublicacion(@RequestBody Publicacion publicacion) {
		int resultado = publicacionDAO.crearPublicacion(publicacion);
		if (resultado == 1) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Publicación creada con éxito.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la publicación.");
		}
	}

	// Endpoint para obtener todas las publicaciones
	@GetMapping
	public ResponseEntity<List<Publicacion>> obtenerTodasLasPublicaciones() {
		List<Publicacion> publicaciones = publicacionDAO.obtenerTodasLasPublicaciones();
		if (publicaciones.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(publicaciones);
		}
		return ResponseEntity.ok(publicaciones);
	}

	// Endpoint para obtener una publicación por ID
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPublicacionPorId(@PathVariable Long id) {
		Publicacion publicacion = publicacionDAO.obtenerPublicacionPorId(id);
		if (publicacion != null) {
			return ResponseEntity.ok(publicacion);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publicación no encontrada con ID: " + id);
		}
	}

	// Endpoint para actualizar una publicación
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarPublicacion(@PathVariable Long id, @RequestBody Publicacion publicacion) {
		publicacion.setId(id); // Aseguramos que el ID de la publicación sea el correcto
		int resultado = publicacionDAO.actualizarPublicacion(publicacion);
		if (resultado == 1) {
			return ResponseEntity.status(HttpStatus.OK).body("Publicación actualizada con éxito.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la publicación.");
		}
	}
	
	// Endpoint para actualizar solo el campo 'adoptada' de una publicación
	@PutMapping("/adoptada/{id}")
	public ResponseEntity<?> actualizarAdoptada(@PathVariable Long id, @RequestBody Boolean adoptada) {
	    int resultado = publicacionDAO.actualizarAdoptada(id, adoptada);
	    if (resultado == 1) {
	        return ResponseEntity.status(HttpStatus.OK).body("Campo 'adoptada' actualizado con éxito.");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el campo 'adoptada'.");
	    }
	}


	// Endpoint para eliminar una publicación
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPublicacion(@PathVariable Long id) {
		int resultado = publicacionDAO.eliminarPublicacionPorId(id);
		if (resultado == 1) {
			return ResponseEntity.status(HttpStatus.OK).body("Publicación eliminada con éxito.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publicación no encontrada con ID: " + id);
		}
	}
	
	// Endpoint para obtener la cantidad de publicaciones
	@GetMapping("/cantidad")
	public ResponseEntity<Integer> obtenerCantidadDePublicaciones() {
	    int cantidad = publicacionDAO.obtenerCantidadDePublicaciones();
	    return ResponseEntity.ok(cantidad);
	}

}
