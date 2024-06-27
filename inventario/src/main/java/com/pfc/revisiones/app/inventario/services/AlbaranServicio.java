package com.pfc.revisiones.app.inventario.services;

import java.util.List;
import java.util.Optional;

import com.pfc.revisiones.app.inventario.entities.Albaran;

public interface AlbaranServicio {

    List<Albaran> findAll();

    Optional<Albaran> findById(String id);

}
