package Repositories;

import java.util.List;
import Model.Spiergroep;
import Contexts.ISpiergroepContext;

public class SpiergroepRepository {

    private ISpiergroepContext context;

    public SpiergroepRepository(ISpiergroepContext context){
        this.context = context;
    }

    public List<Spiergroep> HaalSpiergroepenOp(){
        return context.HaalSpiergroepenOp();
    }
}
