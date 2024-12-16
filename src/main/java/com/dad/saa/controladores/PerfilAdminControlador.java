package com.dad.saa.controladores;

import com.dad.saa.entidades.PerfilAdmin;
import com.dad.saa.servicios.PerfilAdminServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perfil-admin")
@CrossOrigin(origins = "*")
public class PerfilAdminControlador {

	@Autowired
	private PerfilAdminServicio perfilAdminServicio;

	@PostMapping("/agregar")
	public ResponseEntity<PerfilAdmin> agregarPerfil(@RequestBody PerfilAdmin perfilAdmin) {
		PerfilAdmin perfilGuardado = perfilAdminServicio.agregarPerfil(perfilAdmin);
		return ResponseEntity.ok(perfilGuardado);
	}

	@GetMapping("/obtener/{userId}")
	public ResponseEntity<PerfilAdmin> obtenerPerfil(@PathVariable String userId) {
		return perfilAdminServicio.obtenerPerfilPorUserId(userId).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/editar/{userId}")
	public ResponseEntity<PerfilAdmin> actualizarPerfil(@PathVariable String userId,
			@RequestBody PerfilAdmin perfilActualizado) {
		try {
			PerfilAdmin perfilEditado = perfilAdminServicio.actualizarPerfil(userId, perfilActualizado);
			return ResponseEntity.ok(perfilEditado);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
