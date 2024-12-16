package com.dad.saa.dao;

import com.dad.saa.entidades.Publicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PublicacionDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// Crear una nueva publicación
	public int crearPublicacion(Publicacion publicacion) {
		String sql = "INSERT INTO publicaciones (id_usuario, nombre, genero, tipo_mascota, raza, edad, tamano, vacunas, descripcion, imagen, estatus, adoptada, fecha_creacion) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())"; // Eliminar fecha_creacion como parámetro
		System.out.println("ID Usuario recibido: " + publicacion.getId_usuario());

		return jdbcTemplate.update(sql, publicacion.getId_usuario(), publicacion.getNombre(), publicacion.getGenero(),
				publicacion.getTipo_mascota(), publicacion.getRaza(), publicacion.getEdad(), publicacion.getTamano(),
				publicacion.getVacunas(), publicacion.getDescripcion(), publicacion.getImagen(),
				publicacion.getEstatus(), publicacion.getAdoptada());
	}

	// Obtener una publicación por ID
	public Publicacion obtenerPublicacionPorId(Long id) {
		try {
			String sql = "SELECT * FROM publicaciones WHERE id = ?";
			return jdbcTemplate.queryForObject(sql, new PublicacionRowMapper(), id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	// Obtener todas las publicaciones
	public List<Publicacion> obtenerTodasLasPublicaciones() {
		String sql = "SELECT * FROM publicaciones";
		return jdbcTemplate.query(sql, new PublicacionRowMapper());
	}

	// Actualizar una publicación
	public int actualizarPublicacion(Publicacion publicacion) {
		String sql = "UPDATE publicaciones SET id_usuario = ?, nombre = ?, genero = ?, tipo_mascota = ?, raza = ?, edad = ?, "
				+ "tamano = ?, vacunas = ?, descripcion = ?, imagen = ?, estatus = ?, adoptada = ?, fecha_creacion = ? WHERE id = ?";
		return jdbcTemplate.update(sql, publicacion.getId_usuario(), publicacion.getNombre(), publicacion.getGenero(),
				publicacion.getTipo_mascota(), publicacion.getRaza(), publicacion.getEdad(), publicacion.getTamano(),
				publicacion.getVacunas(), publicacion.getDescripcion(), publicacion.getImagen(),
				publicacion.getEstatus(), publicacion.getAdoptada(), publicacion.getFecha_creacion(),
				publicacion.getId());
	}

	// Eliminar una publicación por ID
	public int eliminarPublicacionPorId(Long id) {
		String sql = "DELETE FROM publicaciones WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	// Método para actualizar el campo 'adoptada'
	public int actualizarAdoptada(Long id, Boolean adoptada) {
	    String sql = "UPDATE publicaciones SET adoptada = ? WHERE id = ?";
	    return jdbcTemplate.update(sql, adoptada, id);
	}


	// RowMapper para mapear los resultados de la consulta a un objeto Publicacion
	private static class PublicacionRowMapper implements RowMapper<Publicacion> {
		@Override
		public Publicacion mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Publicacion(rs.getLong("id"), rs.getLong("id_usuario"), rs.getString("nombre"),
					rs.getString("genero"), rs.getString("tipo_mascota"), rs.getString("raza"), rs.getString("edad"),
					rs.getString("tamano"), rs.getString("vacunas"), rs.getString("descripcion"),
					rs.getString("imagen"), rs.getString("estatus"), rs.getBoolean("adoptada"),
					rs.getTimestamp("fecha_creacion").toLocalDateTime());
		}
	}
	
	// Método para obtener la cantidad de publicaciones
	public int obtenerCantidadDePublicaciones() {
	    String sql = "SELECT COUNT(*) FROM publicaciones";
	    return jdbcTemplate.queryForObject(sql, Integer.class); // Retorna el número de registros
	}

}
