package Classes;

import java.util.List;

public class Oefening {
    int oefeningID;
    String naam;
    int aantalSets;
    int aantalReps;
    List<Spiergroep> spiergroepen;

    public int getOefeningID() {
        return oefeningID;
    }

    public String getNaam() {
        return naam;
    }

    public int getAantalSets() {
        return aantalSets;
    }

    public int getAantalReps() {
        return aantalReps;
    }

    public List<Spiergroep> getSpiergroepen() {
        return spiergroepen;
    }

    public Oefening(int oefeningID, String naam, int aantalSets, int aantalReps, List<Spiergroep> spiergroepen) {
        this.oefeningID = oefeningID;
        this.naam = naam;
        this.aantalSets = aantalSets;
        this.aantalReps = aantalReps;
        this.spiergroepen = spiergroepen;
    }

    public Oefening(int oefeningID, String naam, int aantalSets, int aantalReps) {
        this.oefeningID = oefeningID;
        this.naam = naam;
        this.aantalSets = aantalSets;
        this.aantalReps = aantalReps;
    }
}
