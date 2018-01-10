package Repositories;

import java.util.List;

import Model.Training;
import Contexts.ITrainingContext;

public class TrainingRepository {

    private ITrainingContext context;

    public TrainingRepository(ITrainingContext context){
        this.context = context;
    }

    public List<Training> HaalAlleTrainingenOp(){
        return context.HaalAlleTrainingenOp();
    }

    public long StartTraining(Training training){
        return context.StartTraining(training);
    }
}

