package Classes;


public class Prestatie {
    private int prestatieID;
    private String naam;
    private String omschrijving;

    public int getPrestatieID() {
        return prestatieID;
    }

    public String getNaam() {
        return naam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public Prestatie(int prestatieID, String naam, String omschrijving) {
        this.prestatieID = prestatieID;
        this.naam = naam;
        this.omschrijving = omschrijving;
    }

    @Override
    public String toString(){
        return naam;
    }
}
