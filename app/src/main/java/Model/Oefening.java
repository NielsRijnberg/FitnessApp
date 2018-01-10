package Model;


public class Oefening {
    private long oefeningID;
    private String naam;
    private String foto;
    private String omschrijving;
    private long spiergroepID;

    public long getSpiergroep() {
        return spiergroepID;
    }

    public long getOefeningID() {
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

    public Oefening(long oefeningID, String naam, String foto, String omschrijving, long spiergroepID) {
        this.oefeningID = oefeningID;
        this.naam = naam;
        this.foto = foto;
        this.omschrijving = omschrijving;
        this.spiergroepID = spiergroepID;
    }

    @Override
    public String toString(){
        return naam;
    }
}
