package com.pfc.revisiones.app.inventario.entities;

import java.sql.Date;

public class Error {

    private String message;
    private String error;
    private int status;
    private Date date;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(java.util.Date date) {
        this.date = (Date) date;
    }

}
