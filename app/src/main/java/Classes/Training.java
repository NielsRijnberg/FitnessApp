package Classes;

public class Training {

    int trainingID;
    String oefeningNaam;
    int gewicht;
    String datum;

    public int getTrainingID() {
        return trainingID;
    }

    public String getOefeningNaam() {
        return oefeningNaam;
    }

    public int getGewicht() {
        return gewicht;
    }

    public String getDatum() {
        return datum;
    }

    public Training(String oefeningNaam, int gewicht, String datum) {
        this.oefeningNaam = oefeningNaam;
        this.gewicht = gewicht;
        this.datum = datum;
    }

    public Training(int trainingID, String oefeningNaam, int gewicht, String datum) {
        this.trainingID = trainingID;
        this.oefeningNaam = oefeningNaam;
        this.gewicht = gewicht;
        this.datum = datum;
    }


}
