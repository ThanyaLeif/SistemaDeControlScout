package com.example.tanialeif.sistemadecontrolscout.Models;

public class Lineas {
    int id;
    int idObjetivo;
    int idNivel;
    String nombre;
    String descripcion;

    public Lineas(){

    }

    public Lineas(int id, int idObjetivo, int idNivel, String nombre, String descripcion) {
        this.id = id;
        this.idObjetivo = idObjetivo;
        this.idNivel = idNivel;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(int idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
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
}
