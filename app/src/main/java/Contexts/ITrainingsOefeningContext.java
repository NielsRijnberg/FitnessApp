package Contexts;

import java.util.List;

import Classes.TrainingsOefening;

public interface ITrainingsOefeningContext {

    List<TrainingsOefening> HaalOefeningenOpBijTraining(int trainingID);

    void VinkTrainingAf(TrainingsOefening trainingsOefening);
}
