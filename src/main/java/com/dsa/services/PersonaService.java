package com.dsa.services;

import com.dsa.entities.Persona;
import com.dsa.entities.Telefono;
import com.dsa.repositories.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    public Persona guardarPersona(Persona p) {
        List<Telefono> telefonosRecibidos = p.getTelefonos();
        p.setTelefonos(new ArrayList<>());
        if (telefonosRecibidos != null) {
            for (Telefono tel : telefonosRecibidos) {
                p.addTelefono(tel);
            }
        }
        return personaRepository.save(p);
    }

    public List<Persona> buscarPorNombre(String nombre) {
        return personaRepository.findByNombre(nombre);
    }

    public Persona actualizarPersona(Persona p) {
        List<Telefono> telefonosRecibidos = p.getTelefonos();
        p.setTelefonos(new ArrayList<>());
        if (telefonosRecibidos != null) {
            for (Telefono tel : telefonosRecibidos) {
                p.addTelefono(tel);
            }
        }
        return personaRepository.save(p);
    }

    public void eliminarPersonaPorId(Long id) {
        personaRepository.deleteById(id);
    }


}