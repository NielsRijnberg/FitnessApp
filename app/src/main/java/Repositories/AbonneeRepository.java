package Repositories;

import Classes.Abonnee;
import Contexts.IAbonneeContext;

public class AbonneeRepository {

    private IAbonneeContext context;

    public AbonneeRepository(IAbonneeContext context){
        this.context = context;
    }

    public void PasAbonneeAan(Abonnee abonnee){
        context.PasAbonneeAan(abonnee);
    }
}
