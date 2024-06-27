package com.pfc.revisiones.app.inventario.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfc.revisiones.app.inventario.entities.Usuario;
import com.pfc.revisiones.app.inventario.repositories.UsuarioRepositorio;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio repositorio;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOptional = repositorio.findByUsername(username);

        if (usuarioOptional.isEmpty()) {
            throw new UsernameNotFoundException(
                    String.format("Usuario %s no se encuentra en la base de datos", username));
        }

        Usuario usuario = usuarioOptional.orElseThrow();

        List<GrantedAuthority> roles = usuario.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(usuario.getUsername(),
                usuario.getPassword(), usuario.isEnabled(),
                true, true, true, roles);
    }
}
