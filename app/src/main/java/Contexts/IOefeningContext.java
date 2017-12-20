package Contexts;

import java.util.List;

import Classes.Oefening;
import Classes.Schema;
import Classes.Spiergroep;

public interface IOefeningContext {

    List<Oefening> HaalOefeningenOpBijSchema(int schemaID);

    List<Oefening> HaalOefeningenOpBijSpiergroep(String spiergroepNaam);
}
