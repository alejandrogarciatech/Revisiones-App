package com.pfc.revisiones.app.inventario.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfc.revisiones.app.inventario.entities.Espacio;
import com.pfc.revisiones.app.inventario.services.EspacioServicio;

@RestController
@RequestMapping("/api/espacios")
public class EspaciosController {

    @Autowired
    private EspacioServicio servicio;

    @GetMapping
    public List<Espacio> list() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewById(@PathVariable String id) {
        Optional<Espacio> espacioOptional = servicio.findById(id);
        if (espacioOptional.isPresent()) {
            return ResponseEntity.ok(espacioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
