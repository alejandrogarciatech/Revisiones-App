package com.pfc.revisiones.app.inventario.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "albaranes")
public class Albaran {

    @Id
    private String id;

    private Date fechaSalida;
    private Date fechaEntrada;
    private String descripcion;
    private String cliente;
    private Long idCliente;
    private Long idEspacio;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getFechaSalida() {
        return fechaSalida;
    }
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    public Date getFechaEntrada() {
        return fechaEntrada;
    }
    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public Long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    public Long getIdEspacio() {
        return idEspacio;
    }
    public void setIdEspacio(Long idEspacio) {
        this.idEspacio = idEspacio;
    }

    public Albaran() {
    }
    public Albaran(String id, Date fechaSalida, Date fechaEntrada, String descripcion, String cliente, Long idCliente,
            Long idEspacio) {
        this.id = id;
        this.fechaSalida = fechaSalida;
        this.fechaEntrada = fechaEntrada;
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.idCliente = idCliente;
        this.idEspacio = idEspacio;
    }

    @Override
    public String toString() {
        return "Albaran [id=" + id + ", fechaSalida=" + fechaSalida + ", fechaEntrada=" + fechaEntrada
                + ", descripcion=" + descripcion + ", cliente=" + cliente + ", idCliente=" + idCliente + ", idEspacio="
                + idEspacio + "]";
    }

}
