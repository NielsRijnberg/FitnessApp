package com.a2bfit.Repo;

import java.util.List;

import com.a2bfit.Model.Oefening;
import com.a2bfit.Model.Prestatie;
import com.a2bfit.Model.Schema;
import com.a2bfit.Model.Training;
import com.a2bfit.Model.TrainingsOefening;

public class GebruikerRepo {
    private static DatabaseHelper db;

    public GebruikerRepo(DatabaseHelper db) {
        this.db = db;
    }

    public List<Prestatie> getPrestaties(){
        return db.HaalAllePrestatiesOp();
    }

    public List<Oefening> getOefeningenVoorSchema(long schemaID){
        return db.HaalOefeningenOpBijSchema(schemaID);
    }

    public List<Schema> getSchemas(){
        return db.HaalAlleSchemasOp();
    }

    public Training startTraining(long schemaID, String datum){
        return db.StartTraining(new Training(schemaID, datum));
    }

    public void vinkOefeningAf(TrainingsOefening trainingsOefening){
        db.VinkOefeningAf(trainingsOefening);
    }

    public List<Training> getTrainingen() {
        return db.HaalAlleTrainingenOp();
    }

    public List<TrainingsOefening> getOefeningenBijTraining(long trainingID) {
        return db.HaalOefeningenOpBijTraining(trainingID);
    }

    public int[] getSetsEnReps(long oefeningID) {
        return db.HaalSetsEnRepsOp(oefeningID);
    }
}
