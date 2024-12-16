package com.dad.saa.servicios;

import com.dad.saa.entidades.PerfilAdmin;
import com.dad.saa.repositorios.PerfilAdminRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilAdminServicio {

	@Autowired
	private PerfilAdminRepositorio perfilAdminRepositorio;

	public PerfilAdmin agregarPerfil(PerfilAdmin perfilAdmin) {
		return perfilAdminRepositorio.save(perfilAdmin);
	}

	public Optional<PerfilAdmin> obtenerPerfilPorUserId(String userId) {
		return perfilAdminRepositorio.findByUserId(userId);
	}

	public PerfilAdmin actualizarPerfil(String userId, PerfilAdmin perfilActualizado) {
		Optional<PerfilAdmin> perfilExistente = perfilAdminRepositorio.findByUserId(userId);
		if (perfilExistente.isPresent()) {
			PerfilAdmin perfil = perfilExistente.get();
			perfil.setDireccion(perfilActualizado.getDireccion());
			perfil.setTelefono(perfilActualizado.getTelefono());
			perfil.setPreferenciaMascota(perfilActualizado.getPreferenciaMascota());
			perfil.setExperiencia(perfilActualizado.getExperiencia());
			perfil.setDisponibilidad(perfilActualizado.getDisponibilidad());
			return perfilAdminRepositorio.save(perfil);
		} else {
			throw new RuntimeException("Perfil no encontrado para el userId: " + userId);
		}
	}
}
