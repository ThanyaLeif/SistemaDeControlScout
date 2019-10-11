package com.example.tanialeif.sistemadecontrolscout.Models;

public class Progresion {
    int id;
    int idNivel;
    String nombre;
    String descripcion;

    public Progresion(){

    }

    public Progresion(int id, int idNivel, String nombre, String descripcion) {
        this.id = id;
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
