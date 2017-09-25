package Classes;

import java.util.List;

public class Abonnee {
    private int abonneeID;
    private String naam;
    private int leeftijd;
    private String mailAdres;
    private String wachtWoord;
    private List<Aankoop> aankopen;
    private Abonnement abonnement;
    private List<Schema> schemas;

    public int getAbonneeID() {
        return abonneeID;
    }

    public String getNaam() {
        return naam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public String getMailAdres() {
        return mailAdres;
    }

    public String getWachtWoord() {
        return wachtWoord;
    }

    public List<Aankoop> getAankopen() {
        return aankopen;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public List<Schema> getSchemas() {
        return schemas;
    }

    public Abonnee(int abonneeID, String naam, int leeftijd, String mailAdres, String wachtWoord, List<Aankoop> aankopen, Abonnement abonnement, List<Schema> schemas) {
        this.abonneeID = abonneeID;
        this.naam = naam;
        this.leeftijd = leeftijd;
        this.mailAdres = mailAdres;
        this.wachtWoord = wachtWoord;
        this.aankopen = aankopen;
        this.abonnement = abonnement;
        this.schemas = schemas;
    }
}
