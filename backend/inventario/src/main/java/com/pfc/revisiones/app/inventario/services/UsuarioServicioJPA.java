package com.pfc.revisiones.app.inventario.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfc.revisiones.app.inventario.entities.Rol;
import com.pfc.revisiones.app.inventario.entities.Usuario;
import com.pfc.revisiones.app.inventario.repositories.RolRepositorio;
import com.pfc.revisiones.app.inventario.repositories.UsuarioRepositorio;

@Service
public class UsuarioServicioJPA implements UsuarioServicio{

    @Autowired
    private UsuarioRepositorio repositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) repositorio.findAll();
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {

        Optional<Rol> optionalRolUsuario = rolRepositorio.findByNombre("ROLE_USER");
        List<Rol> roles = new ArrayList<>();

        optionalRolUsuario.ifPresent(roles::add);

        if (usuario.isAdmin()) {
            Optional<Rol> optionalRolAdmin = rolRepositorio.findByNombre("ROLE_ADMIN");
            optionalRolAdmin.ifPresent(roles::add);
        }

        usuario.setRoles(roles);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return repositorio.save(usuario);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repositorio.existsByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional <Usuario> findByUsername(String username) {
        return repositorio.findByUsername(username);
    }

}
