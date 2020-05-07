package com.example.tanialeif.sistemadecontrolscout.Models;

import java.io.Serializable;

public class Insignia implements Serializable {
    public String uid;
    public String nombre;
    public String descripcion;
    public String urlImagen;
    public String nivel;
    public boolean corporalidad;
    public boolean creatividad;
    public boolean caracter;
    public boolean afectividad;
    public boolean sociabilidad;
    public boolean espiritualidad;

    public Insignia() {
    }

    public Insignia(String uid, String nombre, String descripcion, String nivel, boolean corporalidad,
                    boolean creatividad, boolean caracter, boolean afectividad, boolean sociabilidad,
                    boolean espiritualidad) {
        this.uid = uid;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.corporalidad = corporalidad;
        this.creatividad = creatividad;
        this.caracter = caracter;
        this.afectividad = afectividad;
        this.sociabilidad = sociabilidad;
        this.espiritualidad = espiritualidad;
    }

    public Insignia(String uid, String nombre, String descripcion, String urlImagen, String nivel,
                    boolean corporalidad, boolean creatividad, boolean caracter, boolean afectividad,
                    boolean sociabilidad, boolean espiritualidad) {
        this.uid = uid;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.nivel = nivel;
        this.corporalidad = corporalidad;
        this.creatividad = creatividad;
        this.caracter = caracter;
        this.afectividad = afectividad;
        this.sociabilidad = sociabilidad;
        this.espiritualidad = espiritualidad;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public boolean isCorporalidad() {
        return corporalidad;
    }

    public void setCorporalidad(boolean corporalidad) {
        this.corporalidad = corporalidad;
    }

    public boolean isCreatividad() {
        return creatividad;
    }

    public void setCreatividad(boolean creatividad) {
        this.creatividad = creatividad;
    }

    public boolean isCaracter() {
        return caracter;
    }

    public void setCaracter(boolean caracter) {
        this.caracter = caracter;
    }

    public boolean isAfectividad() {
        return afectividad;
    }

    public void setAfectividad(boolean afectividad) {
        this.afectividad = afectividad;
    }

    public boolean isSociabilidad() {
        return sociabilidad;
    }

    public void setSociabilidad(boolean sociabilidad) {
        this.sociabilidad = sociabilidad;
    }

    public boolean isEspiritualidad() {
        return espiritualidad;
    }

    public void setEspiritualidad(boolean espiritualidad) {
        this.espiritualidad = espiritualidad;
    }
}
