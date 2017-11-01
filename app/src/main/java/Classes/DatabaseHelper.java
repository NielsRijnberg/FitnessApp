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
    public static final String TABLE_OEFENING = "oefeningen";
    public static final String TABLE_PRODUCT = "producten";
    public static final String TABLE_SCHEMA = "schemas";
    public static final String TABLE_SPIERGROEP = "spiergroepen";
    public static final String TABLE_SCHEMA_OEFENING = "schemas_oefeningen";
    public static final String TABLE_OEFENING_SPIERGROEP = "oefeningen_spiergroepen";
    //endregion

    //region Column Names
    //Comon column names
    public static final String KEY_ID = "ID";
    public static final String KEY_SCHEMAID = "schemaID";
    public static final String KEY_OEFENINGID = "oefeningID";
    public static final String KEY_SPIERGROEPID = "spiergroepID";

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
            KEY_SCHEMATYPE + " TEXT)";

    //spiergroep table create
    private static final String CREATE_TABLE_SPIERGROEP = "CREATE TABLE IF NOT EXISTS " + TABLE_SPIERGROEP +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_SPIERGROEPNAAM + " TEXT)";

    // schema_oefening table create
    private static final String CREATE_TABLE_SCHEMA_OEFENING = "CREATE TABLE IF NOT EXISTS " + TABLE_SCHEMA_OEFENING +
            "(" + KEY_SCHEMAID + " INTEGER REFERENCES " + TABLE_SCHEMA + " (" + KEY_ID + "), " +
            KEY_OEFENINGID + " INTEGER REFERENCES " + TABLE_OEFENING + " (" + KEY_ID + "))";

    // oefening_spiergroep table create
    private static final String CREATE_TABLE_OEFENING_SPIERGROEP = "CREATE TABLE IF NOT EXISTS " + TABLE_OEFENING_SPIERGROEP +
            "(" + KEY_OEFENINGID + " INTEGER REFERENCES " + TABLE_OEFENING + " (" + KEY_ID + "), " +
            KEY_SPIERGROEPID + " INTEGER REFERENCES " + TABLE_SPIERGROEP + " (" + KEY_ID + "))";

    //endregion

    //region Insert producten
    private static final String INSERT_SHAKEBEKER = "INSERT INTO " + TABLE_PRODUCT +
            " (" + KEY_ID + "," + KEY_PRODUCTNAAM + "," + KEY_PRODUCTKOSTEN  + "," + KEY_OMSCHRIJVING +
            ") VALUES (" + 1 + "," + "'Shakebeker'" + "," + 6.95 + "," + "''" + ")";

    private static final String INSERT_BIDON = "INSERT INTO " + TABLE_PRODUCT +
            " (" + KEY_ID + "," + KEY_PRODUCTNAAM + "," + KEY_PRODUCTKOSTEN  + "," + KEY_OMSCHRIJVING +
            ") VALUES (" + 2 + "," + "'Bidon'" + "," + 3.75 + "," + "'Inhoud 500mL'" + ")";

    private static final String INSERT_FITNESSHANDSCHOENEN = "INSERT INTO " + TABLE_PRODUCT +
            " (" + KEY_ID + "," + KEY_PRODUCTNAAM + "," + KEY_PRODUCTKOSTEN  + "," + KEY_OMSCHRIJVING +
            ") VALUES (" + 3 + "," + "'Fitnesshandschoenen'" + "," + 5.90 + "," + "'Voor goede grip en bescherming aan de hand'" + ")";

    private static final String INSERT_WHEYPERFECTION336 = "INSERT INTO " + TABLE_PRODUCT +
            " (" + KEY_ID + "," + KEY_PRODUCTNAAM + "," + KEY_PRODUCTKOSTEN  + "," + KEY_OMSCHRIJVING +
            ") VALUES (" + 4 + "," + "'Whey perfection 336 gram'" + "," + 6.90 + "," + "''" + ")";

    private static final String INSERT_WHEYPERFECTION750 = "INSERT INTO " + TABLE_PRODUCT +
            " (" + KEY_ID + "," + KEY_PRODUCTNAAM + "," + KEY_PRODUCTKOSTEN  + "," + KEY_OMSCHRIJVING +
            ") VALUES (" + 5 + "," + "'Whey perfection 750 gram'" + "," + 12.90 + "," + "''" + ")";

    private static final String INSERT_WHEYPERFECTION2270 = "INSERT INTO " + TABLE_PRODUCT +
            " (" + KEY_ID + "," + KEY_PRODUCTNAAM + "," + KEY_PRODUCTKOSTEN  + "," + KEY_OMSCHRIJVING +
            ") VALUES (" + 6 + "," + "'Whey perfection 2270 gram'" + "," + 34.90 + "," + "''" + ")";

    private static final String INSERT_WHEYPERFECTION4540 = "INSERT INTO " + TABLE_PRODUCT +
            " (" + KEY_ID + "," + KEY_PRODUCTNAAM + "," + KEY_PRODUCTKOSTEN  + "," + KEY_OMSCHRIJVING +
            ") VALUES (" + 7 + "," + "'Whey perfection 4540 gram'" + "," + 59.90 + "," + "''" + ")";

    private static final String INSERT_CREATINE = "INSERT INTO " + TABLE_PRODUCT +
            " (" + KEY_ID + "," + KEY_PRODUCTNAAM + "," + KEY_PRODUCTKOSTEN  + "," + KEY_OMSCHRIJVING +
            ") VALUES (" + 8 + "," + "'Creatine 500 gram'" + "," + 12.50 + "," + "''" + ")";

    private static final String INSERT_PROTEINBARS = "INSERT INTO " + TABLE_PRODUCT +
            " (" + KEY_ID + "," + KEY_PRODUCTNAAM + "," + KEY_PRODUCTKOSTEN  + "," + KEY_OMSCHRIJVING +
            ") VALUES (" + 9 + "," + "'Protein bars 12 repen'" + "," + 14.90 + "," + "''" + ")";
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

    private static final String INSERT_TRICEPS = "INSERT INTO " + TABLE_SPIERGROEP +
            " (" + KEY_ID + "," + KEY_SPIERGROEPNAAM +
            ") VALUES (" + 3 + "," + "'Triceps'" + ")";

    private static final String INSERT_BENEN = "INSERT INTO " + TABLE_SPIERGROEP +
            " (" + KEY_ID + "," + KEY_SPIERGROEPNAAM +
            ") VALUES (" + 4 + "," + "'Benen'" + ")";

    private static final String INSERT_RUG = "INSERT INTO " + TABLE_SPIERGROEP +
            " (" + KEY_ID + "," + KEY_SPIERGROEPNAAM +
            ") VALUES (" + 5 + "," + "'Rug'" + ")";

    private static final String INSERT_SCHOUDERS = "INSERT INTO " + TABLE_SPIERGROEP +
            " (" + KEY_ID + "," + KEY_SPIERGROEPNAAM +
            ") VALUES (" + 6 + "," + "'Schouders'" + ")";

    private static final String INSERT_BORST = "INSERT INTO " + TABLE_SPIERGROEP +
            " (" + KEY_ID + "," + KEY_SPIERGROEPNAAM +
            ") VALUES (" + 7 + "," + "'Borst'" + ")";
    //endregion

    //region Insert schema's
    private static final String INSERT_BORSTSCHEMA = "INSERT INTO " + TABLE_SCHEMA +
            " (" + KEY_ID + "," + KEY_SCHEMATYPE +
            ") VALUES (" + 1 + "," + "'Borstschema'" + ")";

    private static final String INSERT_BICEPSSCHEMA = "INSERT INTO " + TABLE_SCHEMA +
            " (" + KEY_ID + "," + KEY_SCHEMATYPE +
            ") VALUES (" + 2 + "," + "'Bicepsschema'" + ")";

    private static final String INSERT_BENENSCHEMA = "INSERT INTO " + TABLE_SCHEMA +
            " (" + KEY_ID + "," + KEY_SCHEMATYPE +
            ") VALUES (" + 3 + "," + "'Benenschema'" + ")";
    //endregion

    //region Insert oefeningen van schema
    private static final String INSERT_BENENSCHEMA_SQUATBACK = "INSERT INTO " + TABLE_SCHEMA_OEFENING +
            " (" + KEY_SCHEMAID + "," + KEY_OEFENINGID +
            ") VALUES (" + 3 + "," + 2 + ")";

    private static final String INSERT_BENENSCHEMA_LEGEXTENSION = "INSERT INTO " + TABLE_SCHEMA_OEFENING +
            " (" + KEY_SCHEMAID + "," + KEY_OEFENINGID +
            ") VALUES (" + 3 + "," + 3 + ")";

    private static final String INSERT_BENENSCHEMA_LYINGLEGCURL = "INSERT INTO " + TABLE_SCHEMA_OEFENING +
            " (" + KEY_SCHEMAID + "," + KEY_OEFENINGID +
            ") VALUES (" + 3 + "," + 4 + ")";

    private static final String INSERT_BENENSCHEMA_SEATEDLEGPRESS = "INSERT INTO " + TABLE_SCHEMA_OEFENING +
            " (" + KEY_SCHEMAID + "," + KEY_OEFENINGID +
            ") VALUES (" + 3 + "," + 5 + ")";
    //endregion



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTablesIfNotExists(db);
    }

    public void createTablesIfNotExists(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_OEFENING);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_SCHEMA);
        db.execSQL(CREATE_TABLE_SPIERGROEP);
        db.execSQL(CREATE_TABLE_SCHEMA_OEFENING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTables(db);

        onCreate(db);
    }

    public void dropTables(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OEFENING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEMA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPIERGROEP);
    }



    public void insertSampleData(SQLiteDatabase db){
        insertOefeningen(db);
        insertSpiergroepen(db);
        insertProducten(db);
        insertSchemas(db);
        insertOefeningenVanSchemas(db);
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
        db.execSQL(INSERT_BENEN);
        db.execSQL(INSERT_BORST);
        db.execSQL(INSERT_RUG);
        db.execSQL(INSERT_SCHOUDERS);
        db.execSQL(INSERT_TRICEPS);
    }

    private void insertProducten(SQLiteDatabase db){
        db.execSQL(INSERT_SHAKEBEKER);
        db.execSQL(INSERT_BIDON);
        db.execSQL(INSERT_FITNESSHANDSCHOENEN);
        db.execSQL(INSERT_WHEYPERFECTION336);
        db.execSQL(INSERT_WHEYPERFECTION750);
        db.execSQL(INSERT_WHEYPERFECTION2270);
        db.execSQL(INSERT_WHEYPERFECTION4540);
        db.execSQL(INSERT_CREATINE);
        db.execSQL(INSERT_PROTEINBARS);
    }

    private void insertSchemas(SQLiteDatabase db){
        db.execSQL(INSERT_BORSTSCHEMA);
        db.execSQL(INSERT_BICEPSSCHEMA);
        db.execSQL(INSERT_BENENSCHEMA);
    }

    private void insertOefeningenVanSchemas(SQLiteDatabase db){
        db.execSQL(INSERT_BENENSCHEMA_SQUATBACK);
        db.execSQL(INSERT_BENENSCHEMA_LEGEXTENSION);
        db.execSQL(INSERT_BENENSCHEMA_LYINGLEGCURL);
        db.execSQL(INSERT_BENENSCHEMA_SEATEDLEGPRESS);
    }



    public Cursor HaalAlleOefeningenOp(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_OEFENING, null);
        return result;
    }

    public Cursor HaalAlleProductenOp(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_PRODUCT, null);
        return result;
    }

    public Cursor HaalAlleSpiergroepenOp(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_SPIERGROEP, null);
        return result;
    }

    public Cursor HaalAlleSchemasOp() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_SCHEMA, null);
        return result;
    }
}
