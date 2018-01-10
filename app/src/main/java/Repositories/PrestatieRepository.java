package Repositories;

import java.util.List;

import Model.Prestatie;
import Contexts.IPrestatieContext;

public class PrestatieRepository {

    private IPrestatieContext context;

    public PrestatieRepository(IPrestatieContext context){
        this.context = context;
    }

    public List<Prestatie> HaalPrestatiesOp(){
        return context.HaalPrestatiesOp();
    }
}
