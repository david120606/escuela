package com.dsa.entities.dto;

import com.dsa.entities.Persona;

public class TelefonoDTO {
    private Long id;
    private String numero;
    private Persona persona;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }

}
