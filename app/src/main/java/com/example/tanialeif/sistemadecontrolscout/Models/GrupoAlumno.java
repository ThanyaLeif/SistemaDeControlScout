package com.example.tanialeif.sistemadecontrolscout.Models;

public class GrupoAlumno {
    int idGrupo;
    String idScout;

    public GrupoAlumno(){

    }

    public GrupoAlumno(int idGrupo, String idScout) {
        this.idGrupo = idGrupo;
        this.idScout = idScout;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getIdScout() {
        return idScout;
    }

    public void setIdScout(String idScout) {
        this.idScout = idScout;
    }
}
