package com.dsa.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("PROFESOR")
public class Profesor extends Persona {

    @Column(name = "asignatura")
    private String asignatura;

    // Getters y Setters
    public String getAsignatura() { return asignatura; }
    public void setAsignatura(String asignatura) { this.asignatura = asignatura; }
}