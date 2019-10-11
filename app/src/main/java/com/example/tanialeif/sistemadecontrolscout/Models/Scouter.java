package com.example.tanialeif.sistemadecontrolscout.Models;

public class Scouter {
    String nombre;
    String apellidoPat;
    String apellidoMat;
    String CUM;
    String contrasenia;

    public Scouter(){

    }

    public Scouter(String nombre, String apellidoPat, String apellidoMat, String CUM, String contrasenia) {
        this.nombre = nombre;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
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
