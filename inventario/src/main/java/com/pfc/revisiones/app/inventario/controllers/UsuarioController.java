package com.pfc.revisiones.app.inventario.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfc.revisiones.app.inventario.entities.Usuario;
import com.pfc.revisiones.app.inventario.services.UsuarioServicio;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServicio servicio;

    // MOSTRAR TODOS LOS USUARIOS
    @GetMapping
    public List<Usuario> list(){
        return servicio.findAll();
    }

    // MOSTRAR USUARIO POR USERNAME 
    @GetMapping("/{username}")
    public ResponseEntity<?> viewByUsername(@PathVariable String username){
        Optional<Usuario> usuarioOptional = servicio.findByUsername(username);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(usuario));
    }

    @PostMapping("/registro")
    public ResponseEntity<?> register(@Valid @RequestBody Usuario usuario, BindingResult result){
        usuario.setAdmin(false);
        return create(usuario, result);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err ->{
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
