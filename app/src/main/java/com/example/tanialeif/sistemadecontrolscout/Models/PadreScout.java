package com.example.tanialeif.sistemadecontrolscout.Models;

public class PadreScout {
    String idPadre;
    String idScout;

    public PadreScout(){

    }

    public PadreScout(String idPadre, String idScout) {
        this.idPadre = idPadre;
        this.idScout = idScout;
    }

    public String getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(String idPadre) {
        this.idPadre = idPadre;
    }

    public String getIdScout() {
        return idScout;
    }

    public void setIdScout(String idScout) {
        this.idScout = idScout;
    }
}
