package com.pfc.revisiones.app.inventario.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pfc.revisiones.app.inventario.services.UsuarioServicio;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String> {

    @Autowired
    private UsuarioServicio servicio;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null) {
            return true; // Si el nombre de usuario es nulo, dejamos que otras validaciones manejen esto.
        }
        return !servicio.existsByUsername(username);
    }

}
