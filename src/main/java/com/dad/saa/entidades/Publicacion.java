package com.dad.saa.entidades;

import java.time.LocalDateTime;

public class Publicacion {

	private Long id;
	private Long id_usuario;
	private String nombre;
	private String genero;
	private String tipo_mascota;
	private String raza;
	private String edad;
	private String tamano;
	private String vacunas;
	private String descripcion;
	private String imagen;
	private String estatus;
	private Boolean adoptada;
    private LocalDateTime fecha_creacion; 

	// Constructor
    public Publicacion(Long id, Long idUsuario, String nombre, String genero, String tipoDeMascota, String raza,
            String edad, String tamano, String vacunas, String descripcion, String imagen, String estatus,
            Boolean adoptada, LocalDateTime fechaCreacion) {
		this.id = id;
		this.id_usuario = idUsuario;
		this.nombre = nombre;
		this.genero = genero;
		this.tipo_mascota = tipoDeMascota;
		this.raza = raza;
		this.edad = edad;
		this.tamano = tamano;
		this.vacunas = vacunas;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.estatus = estatus;
		this.adoptada = adoptada;
        this.fecha_creacion = fechaCreacion;
	}

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long idUsuario) {
		this.id_usuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTipo_mascota() {
		return tipo_mascota;
	}

	public void setTipo_mascota(String tipoDeMascota) {
		this.tipo_mascota = tipoDeMascota;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getTamano() {
		return tamano;
	}

	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	public String getVacunas() {
		return vacunas;
	}

	public void setVacunas(String vacunas) {
		this.vacunas = vacunas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Boolean getAdoptada() {
		return adoptada;
	}

	public void setAdoptada(Boolean adoptada) {
		this.adoptada = adoptada;
	}
	
	public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}
