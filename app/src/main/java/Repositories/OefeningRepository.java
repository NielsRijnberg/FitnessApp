package Repositories;

import java.util.List;

import Model.Oefening;
import Contexts.IOefeningContext;

public class OefeningRepository {

    private IOefeningContext context;

    public OefeningRepository(IOefeningContext context){
        this.context = context;
    }

    public List<Oefening> HaalOefeningenOpBijSchema(int schemaID){
        return context.HaalOefeningenOpBijSchema(schemaID);
    }

    public List<Oefening> HaalOefeningenOpBijSpiergroep(String spiergroepNaam){
        return context.HaalOefeningenOpBijSpiergroep(spiergroepNaam);
    }
}
