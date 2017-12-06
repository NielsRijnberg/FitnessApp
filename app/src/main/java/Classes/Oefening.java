package Classes;

import java.util.List;

public class Oefening {
    private int oefeningID;
    private String naam;
    private String foto;
    private String omschrijving;

    public int getOefeningID() {
        return oefeningID;
    }

    public String getNaam() {
        return naam;
    }

    public String getFoto() {
        return foto;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public Oefening(int oefeningID, String naam, String foto, String omschrijving) {
        this.oefeningID = oefeningID;
        this.naam = naam;
        this.foto = foto;
        this.omschrijving = omschrijving;
    }

    @Override
    public String toString(){
        return naam;
    }
}
