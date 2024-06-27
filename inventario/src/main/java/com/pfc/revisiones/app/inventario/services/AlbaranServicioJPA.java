package com.pfc.revisiones.app.inventario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfc.revisiones.app.inventario.entities.Albaran;
import com.pfc.revisiones.app.inventario.repositories.AlbaranRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class AlbaranServicioJPA implements AlbaranServicio{

    @Autowired
    private AlbaranRepositorio repositorio; 
    
    @Override
    @Transactional(readOnly = true)
    public List<Albaran> findAll() {
        return (List<Albaran>) repositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Albaran> findById(String id) {
        return repositorio.findById(id);
    }

}
