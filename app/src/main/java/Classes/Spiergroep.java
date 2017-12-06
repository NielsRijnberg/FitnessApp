package Classes;

public class Spiergroep {
    private int spiergroepID;
    private String naam;

    public int getSpiergroepID() {
        return spiergroepID;
    }

    public String getNaam() {
        return naam;
    }

    public Spiergroep(int spiergroepID, String naam) {
        this.spiergroepID = spiergroepID;
        this.naam = naam;
    }

    @Override
    public String toString(){
        return naam;
    }
}
