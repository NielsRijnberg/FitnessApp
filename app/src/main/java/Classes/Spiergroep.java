package Classes;

public class Spiergroep {
    int spiergroepID;
    String naam;

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
