package Model;

import java.util.List;

import Repo.GebruikerRepo;

public class Gebruiker {
    private final GebruikerRepo gebruikerRepo;

    public Gebruiker(GebruikerRepo gebruikerRepo) {
        this.gebruikerRepo = gebruikerRepo;
    }

    public List<Prestatie> getPrestaties(){
        return gebruikerRepo.getPrestaties();
    }

    public List<Oefening> getOefeningenVoorSchema(long schemaID){
        return gebruikerRepo.getOefeningenVoorSchema(schemaID);
    }

    public List<Schema> getSchemas(){
        return gebruikerRepo.getSchemas();
    }

    public Training startTraining(long schemaID, String datum){
        return gebruikerRepo.startTraining(schemaID, datum);
    }

    public void vinkOefeningAf(TrainingsOefening trainingsOefening){
        gebruikerRepo.vinkOefeningAf(trainingsOefening);
    }

    public List<Training> getTrainingen() {
        return gebruikerRepo.getTrainingen();
    }

    public List<TrainingsOefening> getOefeningenBijTraining(long trainingID) {
        return gebruikerRepo.getOefeningenBijTraining(trainingID);
    }

    public int[] getSetsEnReps(long oefeningID) {
        return gebruikerRepo.getSetsEnReps(oefeningID);
    }
}
