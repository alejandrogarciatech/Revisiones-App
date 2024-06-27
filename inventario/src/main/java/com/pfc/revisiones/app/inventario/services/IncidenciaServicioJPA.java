package com.pfc.revisiones.app.inventario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfc.revisiones.app.inventario.entities.Incidencia;
import com.pfc.revisiones.app.inventario.repositories.IncidenciaRepositorio;

@Service
public class IncidenciaServicioJPA implements IncidenciaServicio{

    @Autowired
    private IncidenciaRepositorio repositorio;

    // LISTAR TODAS LAS INCIDENCIAS
    @Override
    @Transactional(readOnly = true)
    public List<Incidencia> findAll() {
        return (List<Incidencia>) repositorio.findAll();
    }

    // BUSCAR INCIDENCIA POR ID
    @Override
    @Transactional(readOnly = true)
    public Optional<Incidencia> findById(Long id) {
        return repositorio.findById(id);
    }

    // BUSCAR INCIDENCIA POR EQUIPO ID
    @Override
    @Transactional(readOnly = true)
    public Optional<Incidencia> findByEquipoId(String equipo) {
        return repositorio.findByEquipoId(equipo);
    }

    // GUARDAR INCIDENCIA
    @Override
    @Transactional
    public Incidencia save(Incidencia incidencia) {
        return repositorio.save(incidencia);
    }

    // ACTUALIZAR INCIDENCIA
    @Override
    @Transactional
    public Optional<Incidencia> update(Long id, Incidencia incidencia) {
        Optional<Incidencia> incidenciaOptional = repositorio.findById(id);
        if (incidenciaOptional.isPresent()) {
            Incidencia incidenciaDb = incidenciaOptional.orElseThrow();

            incidenciaDb.setId(incidencia.getId());
            incidenciaDb.setDescripcion(incidencia.getDescripcion());
            incidenciaDb.setEstado(incidencia.getEstado());
            incidenciaDb.setPrioridad(incidencia.getPrioridad());
            incidenciaDb.setEquipo(incidencia.getEquipo());
            incidenciaDb.setIdUsuario(incidencia.getIdUsuario());
            

            return Optional.of(repositorio.save(incidenciaDb));
        }
        return incidenciaOptional;
    }

    // ELIMINAR INCIDENCIA
    @Override
    @Transactional
    public Optional<Incidencia> delete(Long id) {
        Optional<Incidencia> incidenciaOptional = repositorio.findById(id);
        if (incidenciaOptional.isPresent()) {
            repositorio.deleteById(id);
        }
        return incidenciaOptional;
    }

}
