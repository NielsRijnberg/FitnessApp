package Contexts;

import java.util.List;

import Model.TrainingsOefening;

public interface ITrainingsOefeningContext {

    List<TrainingsOefening> HaalOefeningenOpBijTraining(int trainingID);

    void VinkTrainingAf(TrainingsOefening trainingsOefening);
}
