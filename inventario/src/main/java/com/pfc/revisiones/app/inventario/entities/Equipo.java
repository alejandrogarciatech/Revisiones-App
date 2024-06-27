package com.pfc.revisiones.app.inventario.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

// Esta anotación indica que la clase es una entidad
@Entity 

// Indica cual será el nombre de la tabla en la base de datos
@Table(name = "equipos") 
public class Equipo {

    @Id // Este atributo será la llave primaria de la tabla
    private String id;

    // El atributo no puede ser nulo ni vacío
    @NotEmpty(message = "{NotEmpty.equipo.nombre}") 
    private String nombre;

    // El atributo no puede ser nulo
    @NotNull(message = "{NotNull.equipo.tipoProducto}") 
    private String tipoProducto;

    // El resto de atributos
    private String marca;
    private String modelo;
    private String nSerie;
    private double peso;
    private double dimensiones;
    @NotBlank(message = "{NotBlank.equipo.ubicacion}")
    private String ubicacion;
    private String qrcode;

    // Relación uno a muchos con la entidad Incidencia
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> incidencias;

    // Embebed indica que la clase Audit será embebida en la clase Equipo
    @Embedded
    private Audit audit = new Audit();

    // Getters y Setters
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

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getnSerie() {
        return nSerie;
    }

    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(double dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String codigoBarras) {
        this.qrcode = codigoBarras;
    }

    // Constructor de la clase vacío
    public Equipo() {
    }

}
