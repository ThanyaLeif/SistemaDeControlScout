package com.example.tanialeif.sistemadecontrolscout.Models;

import java.io.Serializable;

public class Scouter implements Serializable {
    public String nombre;
    public String apellidoPat;
    public String apellidoMat;
    public String cum;
    public String contrasenia;
    public String direccion;
    public String telefono;
    public String nivel;

    public Scouter() {
    }

    public Scouter(String nombre, String apellidoPat, String apellidoMat, String CUM,
                   String contrasenia, String direccion, String telefono, String nivel) {
        this.nombre = nombre;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.cum = CUM;
        this.contrasenia = contrasenia;
        this.direccion = direccion;
        this.telefono = telefono;
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

    public String getCUM() {
        return cum;
    }

    public void setCUM(String CUM) {
        this.cum = CUM;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
