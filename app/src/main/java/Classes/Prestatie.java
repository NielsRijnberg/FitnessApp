package Classes;


public class Prestatie {
    private long prestatieID;
    private String naam;
    private String omschrijving;

    public long getPrestatieID() {
        return prestatieID;
    }

    public String getNaam() {
        return naam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public Prestatie(long prestatieID, String naam, String omschrijving) {
        this.prestatieID = prestatieID;
        this.naam = naam;
        this.omschrijving = omschrijving;
    }

    @Override
    public String toString(){
        return naam;
    }
}
