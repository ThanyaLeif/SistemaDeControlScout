package com.example.tanialeif.sistemadecontrolscout.Models;

public class ProgresionScout {
    String idScout;
    int idProgresion;

    public ProgresionScout(){

    }

    public ProgresionScout(String idScout, int idProgresion) {
        this.idScout = idScout;
        this.idProgresion = idProgresion;
    }

    public String getIdScout() {
        return idScout;
    }

    public void setIdScout(String idScout) {
        this.idScout = idScout;
    }

    public int getIdProgresion() {
        return idProgresion;
    }

    public void setIdProgresion(int idProgresion) {
        this.idProgresion = idProgresion;
    }
}
