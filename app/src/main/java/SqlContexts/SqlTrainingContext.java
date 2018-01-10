package SqlContexts;

import java.util.List;

import Model.Training;
import Contexts.ITrainingContext;

public class SqlTrainingContext implements ITrainingContext{

    @Override
    public List<Training> HaalAlleTrainingenOp() {
        return null;
    }

    @Override
    public long StartTraining(Training training) {
        return 0;
    }
}
