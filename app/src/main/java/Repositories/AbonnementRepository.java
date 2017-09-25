package Repositories;

import Classes.Abonnee;
import Contexts.IAbonnementContext;

public class AbonnementRepository {

    private IAbonnementContext context;

    public AbonnementRepository(IAbonnementContext context){
        this.context = context;
    }

    public void HaalAbonnementOp(Abonnee abonnee){
        context.HaalAbonnementOp(abonnee);
    }
}
