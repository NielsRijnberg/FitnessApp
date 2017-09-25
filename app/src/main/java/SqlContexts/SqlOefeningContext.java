package SqlContexts;

import java.util.List;
import Classes.Oefening;
import Classes.Schema;
import Classes.Spiergroep;
import Contexts.IOefeningContext;

public class SqlOefeningContext implements IOefeningContext {
    @Override
    public List<Oefening> HaalAlleOefeningenOp() {
        return null;
    }

    @Override
    public void VoegOefeningToeAanSchema(Oefening oefening, Schema schema) {

    }

    @Override
    public void VerwijderOefeningUitSchema(Oefening oefening, Schema schema) {

    }

    @Override
    public List<Oefening> HaalOefeningenPerSpiergroepOp(Spiergroep spiergroep) {
        return null;
    }
}
