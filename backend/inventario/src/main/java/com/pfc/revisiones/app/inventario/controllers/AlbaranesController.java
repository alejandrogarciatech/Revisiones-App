package com.pfc.revisiones.app.inventario.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfc.revisiones.app.inventario.entities.Albaran;
import com.pfc.revisiones.app.inventario.services.AlbaranServicio;

@RestController
@RequestMapping("/api/albaranes")
public class AlbaranesController {

    @Autowired
    private AlbaranServicio servicio;

    @GetMapping
    public List<Albaran> list() {
        return servicio.findAll();
    }

@GetMapping("/{id}")
    public ResponseEntity<?> viewById(@PathVariable String id) {
        Optional<Albaran> albaranOptional = servicio.findById(id);
        if (albaranOptional.isPresent()) {
            return ResponseEntity.ok(albaranOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
