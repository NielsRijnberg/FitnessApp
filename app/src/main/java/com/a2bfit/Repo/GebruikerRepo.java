package com.a2bfit.Repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.a2bfit.Model.Oefening;
import com.a2bfit.Model.Prestatie;
import com.a2bfit.Model.Schema;
import com.a2bfit.Model.Training;
import com.a2bfit.Model.TrainingsOefening;

import static com.a2bfit.Repo.DatabaseHelper.KEY_DATUM;
import static com.a2bfit.Repo.DatabaseHelper.KEY_SCHEMAID;
import static com.a2bfit.Repo.DatabaseHelper.TABLE_OEFENING;
import static com.a2bfit.Repo.DatabaseHelper.TABLE_PRESTATIE;
import static com.a2bfit.Repo.DatabaseHelper.TABLE_SCHEMA;
import static com.a2bfit.Repo.DatabaseHelper.TABLE_SCHEMA_OEFENING;
import static com.a2bfit.Repo.DatabaseHelper.TABLE_TRAINING;
import static com.a2bfit.Repo.DatabaseHelper.TABLE_TRAINING_OEFENING;

public class GebruikerRepo {
    private static DatabaseHelper databaseHelper;

    public GebruikerRepo(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public List<Prestatie> getPrestaties(){
        List<Prestatie> prestatieList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_PRESTATIE, null);

        while(result.moveToNext()) {
            int id = result.getInt(result.getColumnIndex("ID"));
            String naam = result.getString(result.getColumnIndex("prestatienaam"));
            String omschrijving = result.getString(result.getColumnIndex("prestatieomschrijving"));
            prestatieList.add(new Prestatie(id, naam, omschrijving));
        }
        return prestatieList;
    }

    public List<Oefening> getOefeningenVoorSchema(long schemaID){
        List<Oefening> oefeningList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_OEFENING + " as o " +
                "INNER JOIN schemas_oefeningen so ON so.oefeningID = o.ID " +
                "INNER JOIN schemas s ON s.ID = so.schemaID " +
                "INNER JOIN oefeningen_spiergroepen os ON os.oefeningID = o.ID " +
                "INNER JOIN spiergroepen sg ON sg.ID = os.spiergroepID " +
                "WHERE s.ID = ?", new String[] {""+schemaID});

        while (result.moveToNext()) {
            long oefeningID = result.getInt(result.getColumnIndex("oefeningID"));
            long spiergroepID = result.getInt(result.getColumnIndex("spiergroepID"));
            String naam = result.getString(result.getColumnIndex("oefeningnaam"));
            String foto = result.getString(result.getColumnIndex("oefeningfoto"));
            String omschrijving = result.getString(result.getColumnIndex("oefeningomschrijving"));
            oefeningList.add(new Oefening(oefeningID, naam, foto, omschrijving, spiergroepID));
        }
        return oefeningList;
    }

    public List<Schema> getSchemas(){
        List<Schema> schemaList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_SCHEMA, null);

        while (result.moveToNext()) {
            int id = result.getInt(result.getColumnIndex("ID"));
            String type = result.getString(result.getColumnIndex("schematype"));
            schemaList.add(new Schema(id, type));
        }
        return schemaList;
    }

    public Training startTraining(long schemaID, String datum){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SCHEMAID, schemaID);
        contentValues.put(KEY_DATUM, datum);

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long trainingID = db.insert(TABLE_TRAINING, null, contentValues);
        System.out.println("trainingID is: " + trainingID);
        return new Training(trainingID, schemaID, datum);
    }

    public void vinkOefeningAf(TrainingsOefening trainingsOefening){
        long trainingID = trainingsOefening.getTrainingID();
        long oefeningID = trainingsOefening.getOefeningID();
        int gewicht = trainingsOefening.getGewicht();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "INSERT INTO " + TABLE_TRAINING_OEFENING + " (oefeningID, trainingID, gewicht) VALUES (?, ?, ?)";
        db.execSQL(query, new String[]{""+oefeningID, ""+trainingID, ""+gewicht});
    }

    public List<Training> getTrainingen() {
        List<Training> trainingList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_TRAINING, null);

        while (result.moveToNext()) {
            int id = result.getInt(result.getColumnIndex("ID"));
            int schemaID = result.getInt(result.getColumnIndex("schemaID"));
            String datum = result.getString(result.getColumnIndex("datum"));
            trainingList.add(new Training(id, schemaID, datum));
        }
        return trainingList;
    }

    public List<TrainingsOefening> getOefeningenBijTraining(long trainingID) {
        List<TrainingsOefening> oefeningenVanTraining = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_TRAINING_OEFENING +
                " WHERE trainingID = ?", new String[] {""+trainingID});

        while (result.moveToNext()) {
            long oefeningID = result.getInt(result.getColumnIndex("oefeningID"));
            long trainingId = result.getInt(result.getColumnIndex("trainingID"));
            int gewicht = result.getInt(result.getColumnIndex("gewicht"));
            oefeningenVanTraining.add(new TrainingsOefening(oefeningID, trainingId, gewicht));
        }
        return oefeningenVanTraining;
    }

    public int[] getSetsEnReps(long oefeningID) {
        Cursor result = null;
        int[] setEnRep = null;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        try{
            result = db.rawQuery("select * from " + TABLE_SCHEMA_OEFENING +
                    " WHERE oefeningID = ?", new String[] {""+oefeningID});
            if (result.getCount()>0){
                result.moveToFirst();
                int aantalSets = result.getInt(result.getColumnIndex("aantalsets"));
                int aantalReps = result.getInt(result.getColumnIndex("aantalreps"));
                setEnRep = new int[]{aantalSets, aantalReps};
            }
            return setEnRep;
        }
        finally{
            result.close();
        }
    }
}
