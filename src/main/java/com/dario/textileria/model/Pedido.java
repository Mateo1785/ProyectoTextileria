package com.dario.textileria.model;

import java.util.Date;

public class Pedido {
    private int id_pedido;
    private int id_usuario;
    private int id_producto;
    private int cantidad;
    private Date fecha_pedido;

    public Pedido() {
        // Constructor vacío necesario para JavaBean
    }

    public Pedido(int id_usuario, int id_producto, int cantidad, Date fecha_pedido) {
        this.id_usuario = id_usuario;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.fecha_pedido = fecha_pedido;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }
}
