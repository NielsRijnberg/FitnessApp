package Classes;

import java.util.List;

public class Schema {
    int schemaID;
    String type;

    public int getSchemaID() {
        return schemaID;
    }

    public String getType() {
        return type;
    }

    public Schema(int schemaID, String type) {
        this.schemaID = schemaID;
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }
}
