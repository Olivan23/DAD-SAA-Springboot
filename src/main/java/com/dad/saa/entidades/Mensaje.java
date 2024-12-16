package com.dad.saa.entidades;

import java.time.LocalDateTime;

public class Mensaje {
    private String texto;
    private String remitente;
    private LocalDateTime creado;

    // Getters y setters
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public LocalDateTime getCreado() {
        return creado;
    }

    public void setCreado(LocalDateTime creado) {
        this.creado = creado;
    }
}
