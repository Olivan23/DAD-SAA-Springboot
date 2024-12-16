package com.dad.saa.controladores;

import com.dad.saa.entidades.Usuario;
import com.dad.saa.dao.UsuarioDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthControlador {

	@Autowired
	private UsuarioDAO usuarioDAO;

	// Endpoint para registrar un nuevo usuario
	@PostMapping("/registro")
	public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
		// Verificar si el correo ya existe
		Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorCorreo(usuario.getCorreo());
		if (usuarioExistente != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo ya está registrado.");
		}

		// Crear el usuario
		int resultado = usuarioDAO.crearUsuario(usuario);
		if (resultado == 1) {
			// Asignar el rol 'USER' (id = 1)
			Long rolId = 1L; // 'USER' tiene id = 1

			// Asignar el rol 'USER' al usuario recién creado
			int rolAsignado = usuarioDAO.asignarRolAUsuario(usuario.getCorreo(), rolId);
			if (rolAsignado == 1) {
				return ResponseEntity.ok("Usuario creado con éxito.");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error al asignar el rol al usuario.");
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear usuario.");
		}
	}

	// Endpoint para iniciar sesión (autenticación)
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Usuario usuario) {
		Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorCorreo(usuario.getCorreo());
		if (usuarioExistente != null && usuarioExistente.getContrasena().equals(usuario.getContrasena())) {
			return ResponseEntity.ok().body("Inicio de sesión exitoso");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
	}

	// Endpoint para obtener el ID del usuario a partir del correo y la contraseña
	@PostMapping("/obtenerIdPorCredenciales")
	public ResponseEntity<?> obtenerIdPorCredenciales(@RequestBody Usuario usuario) {
		Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorCorreo(usuario.getCorreo());
		if (usuarioExistente != null && usuarioExistente.getContrasena().equals(usuario.getContrasena())) {
			return ResponseEntity.ok().body(usuarioExistente.getId());
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
	}

	// Endpoint para obtener el rol de un usuario por su ID
	@GetMapping("/rol/{usuarioId}")
	public ResponseEntity<?> obtenerRolPorUsuarioId(@PathVariable Long usuarioId) {
		String rol = usuarioDAO.obtenerRolPorUsuarioId(usuarioId);
		if (rol != null) {
			return ResponseEntity.ok().body(rol);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado para el usuario especificado");
		}
	}

	// Endpoint para obtener el nombre de un usuario por su ID
	@GetMapping("/usuario/nombre/{usuarioId}")
	public ResponseEntity<?> obtenerNombreUsuarioPorId(@PathVariable Long usuarioId) {
		// Llamamos al método del DAO para obtener el usuario por su ID
		Usuario usuario = usuarioDAO.obtenerUsuarioPorId(usuarioId);

		if (usuario != null) {
			// Si el usuario existe, devolvemos su nombre
			return ResponseEntity.ok().body(usuario.getNombreCompleto());
		} else {
			// Si no se encuentra el usuario, devolvemos un error 404
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
		}
	}

	// Endpoint para obtener el total de usuarios
	@GetMapping("/usuarios/count")
	public ResponseEntity<?> obtenerTotalDeUsuarios() {
		int totalUsuarios = usuarioDAO.contarUsuarios();
		return ResponseEntity.ok().body(totalUsuarios);
	}

	// Endpoint para obtener un usuario por su ID
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long usuarioId) {
		// Llamamos al método del DAO para obtener el usuario por su ID
		Usuario usuario = usuarioDAO.obtenerUsuarioPorId(usuarioId);

		if (usuario != null) {
			// Si el usuario existe, devolvemos los datos del usuario
			return ResponseEntity.ok().body(usuario);
		} else {
			// Si no se encuentra el usuario, devolvemos un error 404
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
		}
	}
	
	// Endpoint para obtener todos los usuarios
	@GetMapping("/usuarios")
	public ResponseEntity<?> obtenerTodosLosUsuarios() {
	    List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();

	    if (usuarios.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay usuarios registrados.");
	    }

	    return ResponseEntity.ok(usuarios);
	}


}