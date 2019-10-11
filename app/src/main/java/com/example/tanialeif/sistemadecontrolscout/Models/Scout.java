package com.example.tanialeif.sistemadecontrolscout.Models;

public class Scout {
    public String nombre;
    public String apellidoPat;
    public String apellidoMat;
    public String direccion;
    public String telefono;
    public String CUM;
    public String contrasenia;

    public Scout(){

    }

    public Scout(String nombre, String apellidoPat, String apellidoMat, String direccion, String telefono, String CUM, String contrasenia) {
        this.nombre = nombre;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.direccion = direccion;
        this.telefono = telefono;
        this.CUM = CUM;
        this.contrasenia = contrasenia;
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

    public String getCUM() {
        return CUM;
    }

    public void setCUM(String CUM) {
        this.CUM = CUM;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
