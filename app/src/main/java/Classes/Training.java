package Classes;

public class Training {

    private int trainingID;
    private int schemaID;
    private String datum;

    public Training(int trainingID, int schemaID, String datum) {
        this.trainingID = trainingID;
        this.schemaID = schemaID;
        this.datum = datum;
    }

    public Training(int schemaID, String datum) {
        this.schemaID = schemaID;
        this.datum = datum;
    }

    public int getTrainingID() {
        return trainingID;
    }

    public String getDatum() {
        return datum;
    }

    public int getSchemaID() {
        return schemaID;
    }

    @Override
    public String toString(){
        return datum;
    }
}
