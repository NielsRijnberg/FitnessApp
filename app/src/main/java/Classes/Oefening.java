package Classes;

import java.util.List;

public class Oefening {
    int oefeningID;
    String naam;
    int aantalSets;
    int aantalReps;

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

    public Oefening(int oefeningID, String naam, int aantalSets, int aantalReps) {
        this.oefeningID = oefeningID;
        this.naam = naam;
        this.aantalSets = aantalSets;
        this.aantalReps = aantalReps;
    }

    @Override
    public String toString(){
        return naam;
    }
}
