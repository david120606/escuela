package com.dsa.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotNull(message = "La dirección no puede ser nulo")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @NotEmpty(message = "Debe tener al menos un teléfono")
    @Size(min = 1, message = "Debe tener al menos un teléfono")
    private List<Telefono> telefonos;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }

    public List<Telefono> getTelefonos() { return telefonos; }
    public void setTelefonos(List<Telefono> telefonos) { this.telefonos = telefonos; }


    public void addTelefono(Telefono telefono) {
        telefonos.add(telefono);
        telefono.setPersona(this);
    }

    public void removeTelefono(Telefono telefono) {
        telefonos.remove(telefono);
        telefono.setPersona(null);
    }
}