package Repositories;

import java.util.List;

import Classes.TrainingsOefening;
import Contexts.ITrainingsOefeningContext;

public class TrainingsOefeningRepository {

    private ITrainingsOefeningContext context;

    public TrainingsOefeningRepository(ITrainingsOefeningContext context){
        this.context = context;
    }

    public List<TrainingsOefening> HaalOefeningenOpBijTraining(int trainingID){
        return context.HaalOefeningenOpBijTraining(trainingID);
    }

    public void VinkTrainingAf(TrainingsOefening trainingsOefening){
        context.VinkTrainingAf(trainingsOefening);
    }
}
