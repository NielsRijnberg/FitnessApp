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
    public List<Oefening> HaalOefeningenPerSpiergroepOp(Spiergroep spiergroep) {
        return null;
    }

    @Override
    public List<Oefening> HaalOefeningenOpBijSchema(Schema schema) {
        return null;
    }
}
