package com.example.tanialeif.sistemadecontrolscout.Models;

public class Grupo {
    int idGrupo;
    String idLider;

    public Grupo(){

    }

    public Grupo(int idGrupo, String idLider) {
        this.idGrupo = idGrupo;
        this.idLider = idLider;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getIdLider() {
        return idLider;
    }

    public void setIdLider(String idLider) {
        this.idLider = idLider;
    }
}
