package com.example.tanialeif.sistemadecontrolscout.Models;

public class ProgresionLinea {
    int idProgresion;
    int idLinea;

    public ProgresionLinea(){

    }

    public ProgresionLinea(int idProgresion, int idLinea) {
        this.idProgresion = idProgresion;
        this.idLinea = idLinea;
    }

    public int getIdProgresion() {
        return idProgresion;
    }

    public void setIdProgresion(int idProgresion) {
        this.idProgresion = idProgresion;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }
}
