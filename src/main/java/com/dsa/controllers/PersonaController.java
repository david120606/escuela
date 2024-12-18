package com.dsa.controllers;

import com.dsa.entities.Estudiante;
import com.dsa.entities.Persona;
import com.dsa.entities.Profesor;
import com.dsa.entities.Telefono;
import com.dsa.services.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public Persona crearEstudiante(@Valid @RequestBody Estudiante e) {
        return personaService.guardarPersona(e);
    }

    @PostMapping("/profesor")
    public Persona crearProfesor(@Valid @RequestBody Profesor p) {
        return personaService.guardarPersona(p);
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

}

