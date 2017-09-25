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

    public void VoegOefeningToeAanSchema(Oefening oefening, Schema schema) {
        context.VoegOefeningToeAanSchema(oefening, schema);
    }

    public void VerwijderOefeningUitSchema(Oefening oefening, Schema schema){
        context.VerwijderOefeningUitSchema(oefening, schema);
    }

    public List<Oefening> HaalOefeningenPerSpiergroepOp(Spiergroep spiergroep){
        return context.HaalOefeningenPerSpiergroepOp(spiergroep);
    }
}
