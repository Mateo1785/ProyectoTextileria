package com.dario.textileria.model;

import java.util.Objects;

public class Producto {
    private int id_producto;
    private String nombre;
    private String descripcion;
    private int stock;
    private double precio_unitario;

    public Producto() {
    }

    public Producto(int id_producto, String nombre, String descripcion, int stock, double precio_unitario) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio_unitario = precio_unitario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }


}
