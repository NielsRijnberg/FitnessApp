package Classes;

import java.util.List;

public class Schema {
    int schemaID;
    String type;
    List<Oefening> oefeningen;

    public int getSchemaID() {
        return schemaID;
    }

    public String getType() {
        return type;
    }

    public List<Oefening> getOefeningen() {
        return oefeningen;
    }

    public Schema(int schemaID, String type, List<Oefening> oefeningen) {
        this.schemaID = schemaID;
        this.type = type;
        this.oefeningen = oefeningen;
    }
}
