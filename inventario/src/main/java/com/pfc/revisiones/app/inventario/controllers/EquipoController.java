package com.pfc.revisiones.app.inventario.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.pfc.revisiones.app.inventario.entities.Equipo;
import com.pfc.revisiones.app.inventario.services.EquipoServicio;
import com.pfc.revisiones.app.inventario.services.QrCodeService;
import com.pfc.revisiones.app.inventario.validation.EquipoValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    @Autowired
    private EquipoServicio servicio;

    @Autowired
    private EquipoValidation validation;

    // MOSTRAR TODOS LOS EQUIPOS
    @GetMapping
    public List<Equipo> list() {
        return servicio.findAll();
    }

    // MOSTRAR EQUIPO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> viewById(@PathVariable String id) {
        Optional<Equipo> equipoOptional = servicio.findById(id);
        if (equipoOptional.isPresent()) {
            return ResponseEntity.ok(equipoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // MOSTRAR EQUIPO POR NOMBRE
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> viewByNombre(@PathVariable String nombre) {
        Optional<Equipo> equipoOptional = servicio.findByNombre(nombre);
        if (equipoOptional.isPresent()) {
            return ResponseEntity.ok(equipoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // MOSTRAR EQUIPO POR TIPO DE PRODUCTO
    @GetMapping("/tipoproducto/{tipoProducto}")
    public ResponseEntity<?> viewByTipoProducto(@PathVariable String tipoProducto) {
        Optional<Equipo> equipoOptional = servicio.findByTipoProducto(tipoProducto);
        if (equipoOptional.isPresent()) {
            return ResponseEntity.ok(equipoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // CREAR EQUIPO Y GENERAR CODIGO QR
    @Autowired
    private QrCodeService barCodeService;

    @PostMapping("/crear")
    public ResponseEntity<?> create(@Valid @RequestBody Equipo equipo, BindingResult result) throws Exception {
        validation.validate(equipo, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Equipo savedEquipo = servicio.save(equipo);
        String qrCode = barCodeService.generateBarCode(savedEquipo.getId());
        savedEquipo.setQrcode(qrCode);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEquipo);
    }

    // ACTUALIZAR EQUIPO
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Equipo equipo, BindingResult result, @PathVariable String id) {
        validation.validate(equipo, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Equipo> equipoOptional = servicio.update(id, equipo);
        if (equipoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(equipoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // ELIMINAR EQUIPO
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Equipo> equipoOptional = servicio.delete(id);
        if (equipoOptional.isPresent()) {
            return ResponseEntity.ok(equipoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // VALIDACION DE ERRORES
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
