package com.pfc.revisiones.app.inventario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfc.revisiones.app.inventario.entities.Incidencia;
import com.pfc.revisiones.app.inventario.services.IncidenciaServicio;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciasController {

    @Autowired
    private IncidenciaServicio servicio;

    // MOSTRAR TODAS LAS INCIDENCIAS
    @GetMapping
    public List<Incidencia> list() {
        return servicio.findAll();
    }

    // MOSTRAR INCIDENCIA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> viewById(@PathVariable Long id) {
        Optional<Incidencia> incidenciaOptional = servicio.findById(id);
        if (incidenciaOptional.isPresent()) {
            return ResponseEntity.ok(incidenciaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // MOSTRAR INCIDENCIA POR EQUIPO ID
    @GetMapping("/equipo/{equipoId}")
    public ResponseEntity<?> viewByEquipoId(@PathVariable String equipoId) {
        Optional<Incidencia> incidenciaOptional = servicio.findByEquipoId(equipoId);
        if (incidenciaOptional.isPresent()) {
            return ResponseEntity.ok(incidenciaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // CREAR INCIDENCIA
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody Incidencia incidencia, BindingResult result) {
        /* if (result.hasErrors()) {
            return validation.validate(result);
        } */
        Incidencia incidenciaNew = servicio.save(incidencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(incidenciaNew);
    }

    // ACTUALIZAR INCIDENCIA
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Incidencia incidencia,
            BindingResult result) {
        /* if (result.hasErrors()) {
            return validation.validate(result);
        } */
        Optional<Incidencia> incidenciaOptional = servicio.update(id, incidencia);
        if (incidenciaOptional.isPresent()) {
            return ResponseEntity.ok(incidenciaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // ELIMINAR INCIDENCIA

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Incidencia> incidenciaOptional = servicio.delete(id);
        if (incidenciaOptional.isPresent()) {
            return ResponseEntity.ok(incidenciaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
