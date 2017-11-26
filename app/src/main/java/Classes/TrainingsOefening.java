package Classes;

public class TrainingsOefening {
    int trainingID;
    int oefeningID;
    int gewicht;

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
