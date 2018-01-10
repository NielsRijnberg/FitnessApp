package Repositories;

import java.util.List;

import Model.Schema;
import Contexts.ISchemaContext;

public class SchemaRepository {

    private ISchemaContext context;

    public SchemaRepository(ISchemaContext context){
        this.context = context;
    }

    public List<Schema> HaalAlleSchemasOp(){
        return context.HaalAlleSchemasOp();
    }
}
