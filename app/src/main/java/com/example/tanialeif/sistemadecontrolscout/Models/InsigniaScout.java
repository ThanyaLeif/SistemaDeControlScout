package com.example.tanialeif.sistemadecontrolscout.Models;

import java.io.Serializable;

public class InsigniaScout implements Serializable {
    public String rel;
    public String uidInsignia;
    public String cum;

    public InsigniaScout (){};

    public InsigniaScout(String rel, String uidInsignia, String cum) {
        this.rel = rel;
        this.uidInsignia = uidInsignia;
        this.cum = cum;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getUidInsignia() {
        return uidInsignia;
    }

    public void setUidInsignia(String uidInsignia) {
        this.uidInsignia = uidInsignia;
    }

    public String getCum() {
        return cum;
    }

    public void setCum(String cum) {
        this.cum = cum;
    }
}
