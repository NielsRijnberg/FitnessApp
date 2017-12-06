package Classes;

public class TrainingsOefening {
    private int trainingID;
    private int oefeningID;
    private int gewicht;

    public TrainingsOefening(int trainingID, int oefeningID, int gewicht) {
        this.trainingID = trainingID;
        this.oefeningID = oefeningID;
        this.gewicht = gewicht;
    }

    public int getTrainingID() {
        return trainingID;
    }

    public int getOefeningID() {
        return oefeningID;
    }

    public int getGewicht() {
        return gewicht;
    }
}
