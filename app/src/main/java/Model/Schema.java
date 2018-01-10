package Model;

import java.util.ArrayList;
import java.util.List;

import Model.Oefening;

public class Schema {

    private long schemaID;
    private String type;
    private List<Oefening> oefeningen;

    public long getSchemaID() {
        return schemaID;
    }

    public String getType() {
        return type;
    }

    public Schema(long schemaID, String type, List<Oefening> oefeningen) {
        this.schemaID = schemaID;
        this.type = type;
        this.oefeningen = oefeningen;
    }

    public Schema(long schemaID, String type) {
        this.schemaID = schemaID;
        this.type = type;
        this.oefeningen = new ArrayList<>();
    }

    @Override
    public String toString(){
        return type;
    }
}
