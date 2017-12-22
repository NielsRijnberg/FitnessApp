package Classes;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

public class Gebruiker {

    private String naam;

    public Gebruiker(String naam){
        this.naam = naam;
    }

    public static List<Schema> getSchemas(Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        return db.HaalAlleSchemasOp();
    }

    public static List<Training> getTrainingen(Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        throw new UnsupportedOperationException("jullie mam");
    }

    public static Training startTraining(long schemaID, String datum, Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        Training training = new Training(schemaID, datum);
        return db.StartTraining(training);
    }
}
