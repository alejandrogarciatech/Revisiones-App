package com.pfc.revisiones.app.inventario.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "espacios")
public class Espacio {

    @Id
    @Column(name = "id_espacio")
    private String id;

    private String nombre;

    private String ubicacion;

    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Espacio(String id, String nombre, String ubicacion, String url) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.url = url;
    }

    public Espacio() {
    }

    @Override
    public String toString() {
        return "Espacio [id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + "]";
    }



}
