package com.a2bfit.Repo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.a2bfit.Model.Oefening;
import com.a2bfit.Model.Spiergroep;

import static com.a2bfit.Repo.DatabaseHelper.TABLE_SPIERGROEP;

public class FitnessRepo {
    private static DatabaseHelper databaseHelper;

    public FitnessRepo(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public List<Oefening> getOefeningen(String spiergroepNaam){
        List<Oefening> oefeningList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM oefeningen as o " +
                "INNER JOIN oefeningen_spiergroepen os ON os.oefeningID = o.ID " +
                "INNER JOIN spiergroepen s ON s.ID = os.spiergroepID " +
                "WHERE s.spiergroepnaam = ?", new String[] {spiergroepNaam});

        while (result.moveToNext()) {
            long id = result.getInt(result.getColumnIndex("ID"));
            long spiergroepID = result.getInt(result.getColumnIndex("spiergroepID"));
            String naam = result.getString(result.getColumnIndex("oefeningnaam"));
            String foto = result.getString(result.getColumnIndex("oefeningfoto"));
            String omschrijving = result.getString(result.getColumnIndex("oefeningomschrijving"));
            oefeningList.add(new Oefening(id, naam, foto, omschrijving, spiergroepID));
        }
        return oefeningList;
    }

    public List<Spiergroep> getSpiergroepen(){
        List<Spiergroep> spiergroepList = new ArrayList<Spiergroep>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_SPIERGROEP, null);

        while (result.moveToNext()) {
            int id = result.getInt(result.getColumnIndex("ID"));
            String naam = result.getString(result.getColumnIndex("spiergroepnaam"));
            spiergroepList.add(new Spiergroep(id, naam));
        }
        return spiergroepList;
    }
}
