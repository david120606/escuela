package com.dsa.controllers;

import com.dsa.entities.*;
import com.dsa.entities.dto.DireccionDTO;
import com.dsa.entities.dto.EstudianteDTO;
import com.dsa.entities.dto.ProfesorDTO;
import com.dsa.entities.dto.TelefonoDTO;
import com.dsa.services.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public List<Persona> listar() {
        return personaService.listarPersonas();
    }

    @PostMapping("/estudiante")
    @Operation(summary = "Crea un estudiante", description = "Crea un nuevo estudiante en el sistema")
    @ApiResponse(responseCode = "200", description = "Estudiante creado exitosamente")
    public Persona crearEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO) {
        Estudiante estudiante = new Estudiante();
        estudiante.setCarrera(estudianteDTO.getCarrera());
        estudiante.setDireccion(convertirDireccionDTOAEntidad(estudianteDTO.getDireccion()));
        estudiante.setTelefonos(convertirTelefonosDTOAEntidades(estudianteDTO.getTelefonos()));
        return personaService.guardarPersona(estudiante);
    }

    @PostMapping("/profesor")
    public Persona crearProfesor(@Valid @RequestBody ProfesorDTO profesorDTO) {
        Profesor profesor = new Profesor();
        profesor.setNombre(profesorDTO.getNombre());
        profesor.setDireccion(convertirDireccionDTOAEntidad(profesorDTO.getDireccion()));
        profesor.setTelefonos(convertirTelefonosDTOAEntidades(profesorDTO.getTelefonos()));
        profesor.setAsignatura(profesorDTO.getAsignatura());
        return personaService.guardarPersona(profesor);
    }

    @GetMapping("/buscar")
    public List<Persona> buscarPorNombre(@RequestParam String nombre) {
        return personaService.buscarPorNombre(nombre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        personaService.eliminarPersonaPorId(id);
        return ResponseEntity.noContent().build();
    }


    private Direccion convertirDireccionDTOAEntidad(DireccionDTO direccionDTO) {
        Direccion direccion = new Direccion();
        direccion.setCalle(direccionDTO.getCalle());
        direccion.setCiudad(direccionDTO.getCiudad());
        return direccion;
    }

    private List<Telefono> convertirTelefonosDTOAEntidades(List<TelefonoDTO> telefonosDTO) {
        return telefonosDTO.stream().map(dto -> {
            Telefono telefono = new Telefono();
            telefono.setNumero(dto.getNumero());
            return telefono;
        }).collect(Collectors.toList());
    }
}

