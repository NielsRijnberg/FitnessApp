package com.a2bfit.Model;

public class Spiergroep {
    private long spiergroepID;
    private String naam;

    public long getSpiergroepID() {
        return spiergroepID;
    }

    public String getNaam() {
        return naam;
    }

    public Spiergroep(long spiergroepID, String naam) {
        this.spiergroepID = spiergroepID;
        this.naam = naam;
    }

    @Override
    public String toString(){
        return naam;
    }
}
