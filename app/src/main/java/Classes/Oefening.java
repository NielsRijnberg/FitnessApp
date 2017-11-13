package Classes;

import java.util.List;

public class Oefening {
    int oefeningID;
    String naam;
    int aantalSets;
    int aantalReps;
    String foto;

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

    public String getFoto() {
        return foto;
    }

    public Oefening(int oefeningID, String naam, int aantalSets, int aantalReps, String foto) {
        this.oefeningID = oefeningID;
        this.naam = naam;
        this.aantalSets = aantalSets;
        this.aantalReps = aantalReps;
        this.foto = foto;
    }

    @Override
    public String toString(){
        return naam;
    }
}
