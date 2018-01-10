package Repo;

import java.util.List;

import Classes.DatabaseHelper;
import Model.Oefening;
import Model.Spiergroep;

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
