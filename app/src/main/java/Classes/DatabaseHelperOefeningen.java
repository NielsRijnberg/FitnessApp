package Classes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperOefeningen extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Sportschool.db";
    public static final String TABLE_NAME = "Oefening_Table";
    public static final String COLUMN_OEFENINGID = "OefeningID";
    public static final String COLUMN_NAAM = "Naam";
    public static final String COLUMN_AANTALSETS = "AantalSets";
    public static final String COLUMN_AANTALREPS = "AantalReps";

    public DatabaseHelperOefeningen(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_OEFENING_TABLE = "create table if not exists "+TABLE_NAME+" ("+COLUMN_OEFENINGID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_NAAM+" TEXT, "+COLUMN_AANTALSETS+" INTEGER, "+COLUMN_AANTALREPS+" INTEGER)";
        db.execSQL(CREATE_OEFENING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public Cursor HaalAlleOefeningenOp(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from Oefening_Table", null);
        return  result;
    }
}
