package Repositories;

import java.util.List;

import Classes.Oefening;
import Classes.Schema;
import Classes.Spiergroep;
import Contexts.IOefeningContext;

public class OefeningRepository {

    private IOefeningContext context;

    public OefeningRepository(IOefeningContext context){
        this.context = context;
    }

    public List<Oefening> HaalAlleOefeningenOp(){
        return context.HaalAlleOefeningenOp();
    }

    public List<Oefening> HaalOefeningenPerSpiergroepOp(Spiergroep spiergroep){
        return context.HaalOefeningenPerSpiergroepOp(spiergroep);
    }

    public List<Oefening> HaalOefeningenOpBijSchema(Schema schema){
        return context.HaalOefeningenOpBijSchema(schema);
    }
}
