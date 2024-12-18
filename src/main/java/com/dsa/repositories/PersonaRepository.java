package com.dsa.repositories;

import com.dsa.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    List<Persona> findByNombre(String nombre);
}
