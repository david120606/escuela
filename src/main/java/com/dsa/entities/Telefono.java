package com.dsa.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "telefono")
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private String numero;

    // Relación inversa hacia Persona
    @ManyToOne
    @JoinColumn(name = "persona_id")
    @JsonBackReference
    private Persona persona;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }

    @Override
    public String toString() {
        return "Telefono{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", persona=" + persona +
                '}';
    }
}