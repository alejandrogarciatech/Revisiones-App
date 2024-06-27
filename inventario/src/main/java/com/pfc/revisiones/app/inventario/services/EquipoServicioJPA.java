package com.pfc.revisiones.app.inventario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfc.revisiones.app.inventario.entities.Equipo;
import com.pfc.revisiones.app.inventario.repositories.EquipoRepositorio;

@Service
public class EquipoServicioJPA implements EquipoServicio {

    @Autowired
    private EquipoRepositorio repositorio;

    // LISTAR TODOS LOS EQUIPOS
    @Override
    @Transactional(readOnly = true)
    public List<Equipo> findAll() {
        return (List<Equipo>) repositorio.findAll();
    }

    // BUSCAR EQUIPO POR ID
    @Override
    @Transactional(readOnly = true)
    public Optional<Equipo> findById(String id) {
        return repositorio.findById(id);
    }

    // BUSCAR EQUIPO POR NOMBRE
    @Override
    @Transactional(readOnly = true)
    public Optional<Equipo> findByNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    // BUSCAR EQUIPO POR TIPO DE PRODUCTO
    @Override
    @Transactional(readOnly = true)
    public Optional<Equipo> findByTipoProducto(String tipoProducto) {
        return repositorio.findByTipoProducto(tipoProducto);
    }

    // GUARDAR EQUIPO
    @Override
    @Transactional
    public Equipo save(Equipo equipo) {
        return repositorio.save(equipo);
    }

    // ACTUALIZAR EQUIPO
    @Transactional
    @Override
    public Optional<Equipo> update(String id, Equipo equipo) {
        Optional<Equipo> equipoOptional = repositorio.findById(id);
        if (equipoOptional.isPresent()) {
            Equipo equipoDb = equipoOptional.orElseThrow();

            equipoDb.setId(equipo.getId());
            equipoDb.setNombre(equipo.getNombre());
            equipoDb.setTipoProducto(equipo.getTipoProducto());
            equipoDb.setMarca(equipo.getMarca());
            equipoDb.setModelo(equipo.getModelo());
            equipoDb.setnSerie(equipo.getnSerie());
            equipoDb.setPeso(equipo.getPeso());
            equipoDb.setDimensiones(equipo.getDimensiones());
            equipoDb.setUbicacion(equipo.getUbicacion());

            return Optional.of(repositorio.save(equipoDb));
        }
        return equipoOptional;
    }

    // ELIMINAR EQUIPO
    @Override
    @Transactional
    public Optional<Equipo> delete(String id) {
        Optional<Equipo> equipoOptional = repositorio.findById(id);
        equipoOptional.ifPresent(equipoDb -> {
            repositorio.delete(equipoDb);
        });
        return equipoOptional;
    }

}
