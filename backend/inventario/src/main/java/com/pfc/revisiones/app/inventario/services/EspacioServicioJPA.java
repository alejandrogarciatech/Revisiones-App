package com.pfc.revisiones.app.inventario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfc.revisiones.app.inventario.entities.Espacio;
import com.pfc.revisiones.app.inventario.repositories.EspacioRepositorio;

@Service
public class EspacioServicioJPA implements EspacioServicio{

    @Autowired
    private EspacioRepositorio repositorio;

    @Override
    @Transactional(readOnly = true)
    public List<Espacio> findAll() {
        return (List<Espacio>) repositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Espacio> findById(String id) {
        return repositorio.findById(id);
    }
}
