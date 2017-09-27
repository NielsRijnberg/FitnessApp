package Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Sportschool.db";
    public static final String TABLE_NAME = "Abonnee_Table";
    public static final String COLUMN_ABONNEEID = "AbonneeID";
    public static final String COLUMN_NAAM = "Naam";
    public static final String COLUMN_WACHTOORD = "Wachtwoord";
    public static final String COLUMN_LEEFTIJD = "Leeftijd";
    public static final String COLUMN_MAILADRES = "Mailadres";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //van de int kosten moet nog een decimal gemaakt worden
        db.execSQL("create table " + TABLE_NAME + " (AbonneeID INTEGER PRIMARY KEY AUTOINCREMENT, Naam TEXT, Wachtwoord TEXT, Leeftijd INTEGER, Mailadres TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Naam, String Wachtwoord, String Leeftijd, String Mailadres){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAAM, Naam);
        contentValues.put(COLUMN_WACHTOORD, Wachtwoord);
        contentValues.put(COLUMN_LEEFTIJD, Leeftijd);
        contentValues.put(COLUMN_MAILADRES, Mailadres);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }
}
