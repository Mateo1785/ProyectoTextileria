package com.dario.textileria.model;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String tipo;
    private String usuario;
    private String contrasena;

    // Constructor vacío que sera util despues
    public Usuario() {
    }

    // Constructor con parámetros para inicializarlos
    public Usuario(int idUsuario, String nombre, String tipo, String usuario, String contrasena) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.tipo = tipo;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Métodos getters y setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}


