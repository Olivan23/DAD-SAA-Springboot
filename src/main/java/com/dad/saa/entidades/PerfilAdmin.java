package com.dad.saa.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "perfilAdmin")
public class PerfilAdmin {

	@Id
	private String id;

	private String userId; 
	private String direccion;
	private String telefono;
	private String preferenciaMascota;
	private String experiencia;
	private String disponibilidad;

	// Getters y Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPreferenciaMascota() {
		return preferenciaMascota;
	}

	public void setPreferenciaMascota(String preferenciaMascota) {
		this.preferenciaMascota = preferenciaMascota;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public String getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
}
