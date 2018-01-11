package com.a2bfit.Repo;

import java.util.List;

import com.a2bfit.Model.Oefening;
import com.a2bfit.Model.Spiergroep;

public class FitnessRepo {
    private static DatabaseHelper db;

    public FitnessRepo(DatabaseHelper db) {
        this.db = db;
    }

    public List<Oefening> getOefeningen(String spiergroepNaam){
        return db.HaalOefeningenOpBijSpiergroep(spiergroepNaam);
    }

    public List<Spiergroep> getSpiergroepen(){
        return db.HaalAlleSpiergroepenOp();
    }
}
