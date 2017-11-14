package Contexts;

import java.util.List;

import Classes.Oefening;
import Classes.Schema;
import Classes.Spiergroep;

public interface IOefeningContext {

    List<Oefening> HaalAlleOefeningenOp();

    List<Oefening> HaalOefeningenPerSpiergroepOp(Spiergroep spiergroep);

    List<Oefening> HaalOefeningenOpBijSchema(Schema schema);
}
