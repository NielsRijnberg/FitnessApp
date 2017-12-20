package SqlContexts;

import android.content.Context;

import java.util.List;

import Classes.DatabaseHelper;
import Classes.Oefening;
import Classes.Schema;
import Classes.Spiergroep;
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
