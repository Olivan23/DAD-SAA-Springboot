package com.dad.saa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.dad.saa.entidades.Usuario;

@Repository
public class UsuarioDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// Crear un nuevo usuario
	public int crearUsuario(Usuario usuario) {
		String sql = "INSERT INTO usuarios (nombre_completo, correo, contrasena) VALUES (?, ?, ?)";
		return jdbcTemplate.update(sql, usuario.getNombreCompleto(), usuario.getCorreo(), usuario.getContrasena());
	}

	// Obtener un usuario por ID
	public Usuario obtenerUsuarioPorId(Long id) {
		try {
			String sql = "SELECT * FROM usuarios WHERE id = ?";
			return jdbcTemplate.queryForObject(sql, new UsuarioRowMapper(), id);
		} catch (EmptyResultDataAccessException e) {
			// Retornar null o manejar de acuerdo a la lógica de tu aplicación
			return null;
		}
	}

	// Método para obtener un usuario por correo
	public Usuario obtenerUsuarioPorCorreo(String correo) {
		try {
			String sql = "SELECT * FROM usuarios WHERE correo = ?";
			return jdbcTemplate.queryForObject(sql, new UsuarioRowMapper(), correo);
		} catch (EmptyResultDataAccessException e) {
			// Retornar null o manejar de acuerdo a la lógica de tu aplicación
			return null;
		}
	}

	// Obtener todos los usuarios
	public List<Usuario> obtenerTodosLosUsuarios() {
		String sql = "SELECT * FROM usuarios";
		return jdbcTemplate.query(sql, new UsuarioRowMapper());
	}

	// Actualizar un usuario
	public int actualizarUsuario(Usuario usuario) {
		String sql = "UPDATE usuarios SET nombre_completo = ?, correo = ?, contrasena = ? WHERE id = ?";
		return jdbcTemplate.update(sql, usuario.getNombreCompleto(), usuario.getCorreo(), usuario.getContrasena(),
				usuario.getId());
	}

	// Eliminar un usuario por ID
	public int eliminarUsuarioPorId(Long id) {
		String sql = "DELETE FROM usuarios WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	// Asignar un rol a un usuario (asumiendo que el correo ya existe en la base de
	// datos)
	public int asignarRolAUsuario(String correo, Long rolId) {
		String sql = "INSERT INTO usuarios_roles (usuario_id, rol_id) "
				+ "SELECT u.id, ? FROM usuarios u WHERE u.correo = ?";
		return jdbcTemplate.update(sql, rolId, correo);
	}

	// Obtener el rol de un usuario por su ID
	public String obtenerRolPorUsuarioId(Long usuarioId) {
		String sql = "SELECT r.nombre FROM roles r " + "INNER JOIN usuarios_roles ur ON r.id = ur.rol_id "
				+ "WHERE ur.usuario_id = ?";
		try {
			return jdbcTemplate.queryForObject(sql, String.class, usuarioId);
		} catch (EmptyResultDataAccessException e) {
			return null; // Manejar casos donde el usuario no tiene un rol asignado
		}
	}

	// Contar el total de usuarios
	public int contarUsuarios() {
		String sql = "SELECT COUNT(*) FROM usuarios";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	// RowMapper para mapear los resultados de la consulta a un objeto Usuario
	private static class UsuarioRowMapper implements RowMapper<Usuario> {
		@Override
		public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Usuario(rs.getLong("id"), rs.getString("nombre_completo"), rs.getString("correo"),
					rs.getString("contrasena"));
		}
	}
}