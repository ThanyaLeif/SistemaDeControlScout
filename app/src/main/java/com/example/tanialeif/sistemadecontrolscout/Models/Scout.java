package com.example.tanialeif.sistemadecontrolscout.Models;

public class Scout {
    public String nombre;
    public String apellidoPat;
    public String apellidoMat;
    public String direccion;
    public String telefono;
    public String fechaNac;
    public String cum;
    public String contrasenia;
    public String nivel;

    public Scout(){

    }

    public Scout(String nombre, String apellidoPat, String apellidoMat, String direccion, String telefono, String fechaNac, String cum, String contrasenia, String nivel) {
        this.nombre = nombre;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
        this.cum = cum;
        this.contrasenia = contrasenia;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getCum() {
        return cum;
    }

    public void setCUM(String cum) {
        this.cum = cum;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
