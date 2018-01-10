package SqlContexts;

import java.util.List;

import Model.Oefening;
import Contexts.IOefeningContext;

public class SqlOefeningContext implements IOefeningContext {

    @Override
    public List<Oefening> HaalOefeningenOpBijSchema(int schemaID) {
        //DatabaseHelper db = new DatabaseHelper()
        return null;
    }

    @Override
    public List<Oefening> HaalOefeningenOpBijSpiergroep(String spiergroepNaam) {
        return null;
    }
}
