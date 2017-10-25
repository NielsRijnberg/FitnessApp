package Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.os.Build.ID;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";

    //Database version and name
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Sportschool";

    //region Table names
    public static final String TABLE_AANKOOP = "aankopen";
    public static final String TABLE_ABONNEE = "abonnees";
    public static final String TABLE_ABONNEMENT = "abonnementen";
    public static final String TABLE_OEFENING = "oefeningen";
    public static final String TABLE_PRODUCT = "producten";
    public static final String TABLE_SCHEMA = "schemas";
    public static final String TABLE_SPIERGROEP = "spiergroepen";
    public static final String TABLE_ABONNEE_ABONNEMENT = "abonnees_abonnementen";
    public static final String TABLE_SCHEMA_OEFENING = "schemas_oefeningen";
    public static final String TABLE_OEFENING_SPIERGROEP = "oefeningen_spiergroepen";
    public static final String TABLE_PRODUCT_AANKOOP = "producten_aankopen";
    //endregion

    //region Column Names
    //Comon column names
    public static final String KEY_ID = "ID";
    public static final String KEY_ABONNEEID = "abonneeID";
    public static final String KEY_ABONNEMENTID = "abonnementID";
    public static final String KEY_SCHEMAID = "schemaID";
    public static final String KEY_OEFENINGID = "oefeningID";
    public static final String KEY_SPIERGROEPID = "spiergroepID";
    public static final String KEY_AANKOOPID = "aankoopID";
    public static final String KEY_PRODUCTID = "productID";

    //aankopen column names
    public static final String KEY_AANTAL = "aantal";
    public static final String KEY_DATUM = "datum";

    //abonnee column names
    public static final String KEY_ABONNEENAAM = "abonneenaam";
    public static final String KEY_WACHTWOORD = "wachtwoord";
    public static final String KEY_LEEFTIJD = "leeftijd";
    public static final String KEY_MAILADRES = "mailadres";

    //abonnement column names
    public static final String KEY_ABONNEMENTKOSTEN = "abonnementkosten";

    //oefening column names
    public static final String KEY_OEFENINGNAAM = "oefeningnaam";
    public static final String KEY_AANTALSETS = "aantalsets";
    public static final String KEY_AANTALREPS = "aantalreps";

    //product column names
    public static final String KEY_PRODUCTNAAM = "productnaam";
    public static final String KEY_PRODUCTKOSTEN = "productkosten";
    public static final String KEY_OMSCHRIJVING = "omschrijving";

    //schema column names
    public static final String KEY_SCHEMATYPE = "schematype";

    //spiergroep column names
    public static final String KEY_SPIERGROEPNAAM = "spiergroepnaam";

    //endregion

    //region Create Tables
    //aankoop table create
    private static final String CREATE_TABLE_AANKOOP = "CREATE TABLE IF NOT EXISTS " + TABLE_AANKOOP +
            "("+ KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_ABONNEEID + " INTEGER, " +
            KEY_AANTAL + " INTEGER, " +
            KEY_DATUM + " TEXT)";

    //abonnee table create
    private static final String CREATE_TABLE_ABONNEE = "CREATE TABLE IF NOT EXISTS " + TABLE_ABONNEE +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_ABONNEENAAM + " TEXT, " +
            KEY_WACHTWOORD + " TEXT, " +
            KEY_LEEFTIJD + " INTEGER, " +
            KEY_MAILADRES + " TEXT)";

    //abonnement table create
    private static final String CREATE_TABLE_ABONNEMENT = "CREATE TABLE IF NOT EXISTS " + TABLE_ABONNEMENT +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_ABONNEMENTKOSTEN + " REAL)";

    //oefening table create
    private static final String CREATE_TABLE_OEFENING = "CREATE TABLE IF NOT EXISTS " + TABLE_OEFENING +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_OEFENINGNAAM + " TEXT, " +
            KEY_AANTALSETS + " INTEGER, " +
            KEY_AANTALREPS + " INTEGER)";

    //product table create
    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCT +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_PRODUCTNAAM + " TEXT, " +
            KEY_PRODUCTKOSTEN + " REAL, " +
            KEY_OMSCHRIJVING + " TEXT)";

    //schema table create
    private static final String CREATE_TABLE_SCHEMA = "CREATE TABLE IF NOT EXISTS " + TABLE_SCHEMA +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_ABONNEEID + " INTEGER, " +
            KEY_SCHEMATYPE + " TEXT)";

    //spiergroep table create
    private static final String CREATE_TABLE_SPIERGROEP = "CREATE TABLE IF NOT EXISTS " + TABLE_SPIERGROEP +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_SPIERGROEPNAAM + " TEXT)";

    // abonnee_abonnement table create
    /*private static final String CREATE_TABLE_ABONNEE_ABONNEMENT = "CREATE TABLE " + TABLE_ABONNEE_ABONNEMENT +
            "(" + KEY_ABONNEEID + " INTEGER NOT NULL REFERENCES " + TABLE_ABONNEE(KEY_ID) +*/



    // schema_oefening table create


    // oefening_spiergroep table create


    // product_aankoop table create
    //endregion

    //region Insert oefeningen
    //Inserts van oefeningen
    private static final String INSERT_BENCHPRESS= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS +
            ") VALUES (" + 1 + "," + "'Benchpress'" + "," + 5 + "," + 5 + ")";

    private static final String INSERT_SQUAT_BACK= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS +
            ") VALUES (" + 2 + "," + "'Squat back'" + "," + 4 + "," + 12 + ")";

    private static final String INSERT_LEG_EXTENSION= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS +
            ") VALUES (" + 3 + "," + "'Leg extension'" + "," + 4 + "," + 10 + ")";

    private static final String INSERT_LYING_LEG_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS +
            ") VALUES (" + 4 + "," + "'Lying leg curl'" + "," + 4 + "," + 10 + ")";

    private static final String INSERT_SEATED_LEG_PRESS= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS +
            ") VALUES (" + 5 + "," + "'Seated leg press'" + "," + 4 + "," + 10 + ")";
    //endregion

    //region Insert spiergroepen
    private static final String INSERT_BUIK = "INSERT INTO " + TABLE_SPIERGROEP +
            " (" + KEY_ID + "," + KEY_SPIERGROEPNAAM +
            ") VALUES (" + 1 + "," + "'Buik'" + ")";

    private static final String INSERT_BICEPS = "INSERT INTO " + TABLE_SPIERGROEP +
            " (" + KEY_ID + "," + KEY_SPIERGROEPNAAM +
            ") VALUES (" + 2 + "," + "'Biceps'" + ")";
    //endregion

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTablesIfNotExists(db);
    }

    public void createTablesIfNotExists(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_AANKOOP);
        db.execSQL(CREATE_TABLE_ABONNEE);
        db.execSQL(CREATE_TABLE_ABONNEMENT);
        db.execSQL(CREATE_TABLE_OEFENING);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_SCHEMA);
        db.execSQL(CREATE_TABLE_SPIERGROEP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTables(db);

        onCreate(db);
    }

    public void dropTables(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AANKOOP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ABONNEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ABONNEMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OEFENING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEMA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPIERGROEP);
    }

    public void insertSampleData(SQLiteDatabase db){
        insertOefeningen(db);
        insertSpiergroepen(db);
    }

    private void insertOefeningen(SQLiteDatabase db){
        db.execSQL(INSERT_BENCHPRESS);
        db.execSQL(INSERT_SQUAT_BACK);
        db.execSQL(INSERT_LEG_EXTENSION);
        db.execSQL(INSERT_LYING_LEG_CURL);
        db.execSQL(INSERT_SEATED_LEG_PRESS);
    }

    private void insertSpiergroepen(SQLiteDatabase db){
        db.execSQL(INSERT_BUIK);
        db.execSQL(INSERT_BICEPS);
    }

    public Cursor HaalAlleOefeningenOp(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_OEFENING, null);
        return  result;
    }
}
