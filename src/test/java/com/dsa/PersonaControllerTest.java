package com.dsa;

import com.dsa.controllers.PersonaController;
import com.dsa.entities.Direccion;
import com.dsa.entities.Estudiante;
import com.dsa.entities.Telefono;
import com.dsa.services.PersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonaController.class)
class PersonaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaService personaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        estudiante = new Estudiante();
        Direccion direccion = new Direccion();

        direccion.setCalle("Jumil 167");
        direccion.setCiudad("CDMX");
        Telefono telefono = new Telefono();
        telefono.setNumero("5530323846");
        List<Telefono> telefonoList = new ArrayList<>();
        telefonoList.add(telefono);
        estudiante.setNombre("Juan Perez");
        estudiante.setCarrera("Ingeniería");
        estudiante.setDireccion(direccion);
        estudiante.setTelefonos(telefonoList);

    }

    @Test
    void testCrearEstudiante() throws Exception {
        // Mockear la lógica del servicio
        given(personaService.guardarPersona(any(Estudiante.class))).willReturn(estudiante);

        // Realizar POST y validar respuesta
        mockMvc.perform(post("/api/personas/estudiante")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(estudiante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Perez"))
                .andExpect(jsonPath("$.carrera").value("Ingeniería"));
    }

    @Test
    void testListarPersonas() throws Exception {
        // Mockear la respuesta del servicio
        given(personaService.listarPersonas()).willReturn(Collections.singletonList(estudiante));

        // Realizar GET y validar respuesta
        mockMvc.perform(get("/api/personas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan Perez"));
    }

    @Test
    void testEliminarPersona() throws Exception {
        // Simular la eliminación exitosa
        Mockito.doNothing().when(personaService).eliminarPersonaPorId(1L);

        // Realizar DELETE y validar respuesta
        mockMvc.perform(delete("/api/personas/1"))
                .andExpect(status().isNoContent());
    }
}