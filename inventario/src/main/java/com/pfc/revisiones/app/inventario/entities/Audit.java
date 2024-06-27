package com.pfc.revisiones.app.inventario.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Embeddable
public class Audit {

    @Column(name = "creado_en")
    private LocalDateTime createAt;

    @Column(name = "actualizado_en")
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist(){
        System.out.println("Evento del ciclo de vida del entity antes de persistir en la base de datos");
        this.createAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Evento del ciclo de vida del entity antes de actualizar en la base de datos");
        this.updateAt = LocalDateTime.now();
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public Audit() {
        
    } 
}
