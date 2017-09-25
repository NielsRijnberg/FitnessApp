package Contexts;

import java.util.List;

import Classes.Oefening;
import Classes.Schema;
import Classes.Spiergroep;

public interface IOefeningContext {

    List<Oefening> HaalAlleOefeningenOp();

    void VoegOefeningToeAanSchema(Oefening oefening, Schema schema);

    void VerwijderOefeningUitSchema(Oefening oefening, Schema schema);

    List<Oefening> HaalOefeningenPerSpiergroepOp(Spiergroep spiergroep);
}
