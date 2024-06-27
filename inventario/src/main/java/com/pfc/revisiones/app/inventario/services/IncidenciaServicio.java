package com.pfc.revisiones.app.inventario.services;

import java.util.List;
import java.util.Optional;

import com.pfc.revisiones.app.inventario.entities.Incidencia;

public interface IncidenciaServicio {

    List<Incidencia> findAll();

    Optional<Incidencia> findById(Long id);

    Optional<Incidencia> findByEquipoId(String equipo);
    
    Incidencia save(Incidencia incidencia);

    Optional<Incidencia> update(Long id, Incidencia incidencia);

    Optional<Incidencia> delete(Long id);
}
