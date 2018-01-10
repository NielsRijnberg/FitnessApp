package Contexts;

import java.util.List;

import Model.Oefening;

public interface IOefeningContext {

    List<Oefening> HaalOefeningenOpBijSchema(int schemaID);

    List<Oefening> HaalOefeningenOpBijSpiergroep(String spiergroepNaam);
}
