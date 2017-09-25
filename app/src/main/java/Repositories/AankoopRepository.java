package Repositories;

import java.util.List;

import Classes.Aankoop;
import Contexts.IAankoopContext;

public class AankoopRepository {

    private IAankoopContext context;

    public AankoopRepository(IAankoopContext context){
        this.context = context;
    }

    public List<Aankoop> HaalAlleAankopenOp(){
        return  context.HaalAlleAankopenOp();
    }

    public void VoegAankoopToe(Aankoop aankoop){
        context.VoegAankoopToe(aankoop);
    }
}
