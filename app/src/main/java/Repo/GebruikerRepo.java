package Repo;

import java.util.List;

import Classes.DatabaseHelper;
import Model.Oefening;
import Model.Prestatie;
import Model.Schema;
import Model.Training;
import Model.TrainingsOefening;

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
