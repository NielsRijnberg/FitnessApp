package Contexts;

import java.util.List;

import Classes.Training;

public interface ITrainingContext {

    List<Training> HaalAlleTrainingenOp();

    long StartTraining(Training training);
}
