package com.dsa.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ESTUDIANTE")
public class Estudiante extends Persona {

    @Column(name = "carrera")
    private String carrera;

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }
}