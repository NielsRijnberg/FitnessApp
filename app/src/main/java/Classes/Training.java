package Classes;

public class Training {

    private long trainingID;
    private long schemaID;
    private String datum;

    public Training(long trainingID, long schemaID, String datum) {
        this.trainingID = trainingID;
        this.schemaID = schemaID;
        this.datum = datum;
    }

    public Training(long schemaID, String datum) {
        this.schemaID = schemaID;
        this.datum = datum;
    }

    public long getTrainingID() {
        return trainingID;
    }

    public String getDatum() {
        return datum;
    }

    public long getSchemaID() {
        return schemaID;
    }

    @Override
    public String toString(){
        return datum;
    }
}
