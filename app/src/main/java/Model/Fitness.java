package Model;

import java.util.List;

import Repo.FitnessRepo;

public class Fitness {

    private final FitnessRepo fitnessRepo;

    public Fitness(FitnessRepo fitnessRepo){
        this.fitnessRepo = fitnessRepo;
    }

    public List<Oefening> getOefeningen(String spiergroepNaam){
        return fitnessRepo.getOefeningen(spiergroepNaam);
    }

    public List<Spiergroep> getSpiergroepen(){
        return fitnessRepo.getSpiergroepen();
    }
}
