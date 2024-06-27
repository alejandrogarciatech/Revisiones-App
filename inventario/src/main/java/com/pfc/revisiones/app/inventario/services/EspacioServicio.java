package com.pfc.revisiones.app.inventario.services;

import java.util.List;
import java.util.Optional;

import com.pfc.revisiones.app.inventario.entities.Espacio;

public interface EspacioServicio {


    List<Espacio> findAll();

    Optional<Espacio> findById(String id);

}
