package com.dario.textileria.model;

import java.sql.Date;

public class Factura {
    private int idFactura;
    private int idPedido;
    private Date fechaFactura;
    private double total;

    public Factura() {
    }

    public Factura(int idPedido, Date fechaFactura, double total) {
        this.idPedido = idPedido;
        this.fechaFactura = fechaFactura;
        this.total = total;
    }

    public Factura(int idFactura, int idPedido, Date fechaFactura, double total) {
        this.idFactura = idFactura;
        this.idPedido = idPedido;
        this.fechaFactura = fechaFactura;
        this.total = total;
    }

    // Getters y setters
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
