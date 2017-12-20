package Repositories;

import java.util.List;

import Classes.Prestatie;
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
