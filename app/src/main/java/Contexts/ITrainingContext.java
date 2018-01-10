package Contexts;

import java.util.List;

import Model.Training;

public interface ITrainingContext {

    List<Training> HaalAlleTrainingenOp();

    long StartTraining(Training training);
}
