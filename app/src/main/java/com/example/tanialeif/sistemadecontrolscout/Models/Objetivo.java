package com.example.tanialeif.sistemadecontrolscout.Models;

public class Objetivo {
    int id;
    String objetivo;

    public  Objetivo(){

    }

    public Objetivo(int id, String objetivo) {
        this.id = id;
        this.objetivo = objetivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
}
