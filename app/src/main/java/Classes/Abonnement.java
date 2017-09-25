package Classes;


public class Abonnement {
    private int abonnementID;
    private double kosten;

    public int getAbonnementID() {
        return abonnementID;
    }

    public double getKosten() {
        return kosten;
    }

    public Abonnement(int abonnementID, double kosten) {

        this.abonnementID = abonnementID;
        this.kosten = kosten;
    }
}
