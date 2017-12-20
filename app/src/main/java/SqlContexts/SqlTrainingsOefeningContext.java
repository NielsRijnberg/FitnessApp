package SqlContexts;

import java.util.List;

import Classes.TrainingsOefening;
import Contexts.ITrainingsOefeningContext;

public class SqlTrainingsOefeningContext implements ITrainingsOefeningContext{

    @Override
    public List<TrainingsOefening> HaalOefeningenOpBijTraining(int trainingID) {
        return null;
    }

    @Override
    public void VinkTrainingAf(TrainingsOefening trainingsOefening) {

    }
}
