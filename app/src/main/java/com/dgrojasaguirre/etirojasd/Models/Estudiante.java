package com.dgrojasaguirre.etirojasd.Models;

public class Estudiante {
    private int id;
    private String dni;
    private String nombres;
    private String apellidos;
    private String correo;
    private String contrasena;

    public Estudiante(String dni, String nombres, String apellidos, String correo, String contrasena) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public boolean validarDni() {
        return this.dni != null && this.dni.trim().length() == 8;
    }

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }
}
