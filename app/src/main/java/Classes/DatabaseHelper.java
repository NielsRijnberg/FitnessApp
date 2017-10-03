package Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database version and name
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Sportschool";

    //Table names
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




    //aankoop table create
    private static final String CREATE_TABLE_AANKOOP = "CREATE TABLE " + TABLE_AANKOOP +
            "("+ KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_ABONNEEID + " INTEGER, " +
            KEY_AANTAL + " INTEGER, " +
            KEY_DATUM + " TEXT";

    //abonnee table create
    private static final String CREATE_TABLE_ABONNEE = "CREATE TABLE " + TABLE_ABONNEE +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_ABONNEENAAM + " TEXT, " +
            KEY_WACHTWOORD + " TEXT, " +
            KEY_LEEFTIJD + " INTEGER, " +
            KEY_MAILADRES + " TEXT";

    //abonnement table create
    private static final String CREATE_TABLE_ABONNEMENT = "CREATE TABLE " + TABLE_ABONNEMENT +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_ABONNEMENTKOSTEN + " DECIMAL(8,4)";

    //oefening table create
    private static final String CREATE_TABLE_OEFENING = "CREATE TABLE " + TABLE_OEFENING +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_OEFENINGNAAM + " TEXT, " +
            KEY_AANTALSETS + " INTEGER, " +
            KEY_AANTALREPS + " INTEGER";

    //product table create
    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_PRODUCTNAAM + " TEXT, " +
            KEY_PRODUCTKOSTEN + " DECIMAL(8,4), " +
            KEY_OMSCHRIJVING + " TEXT";

    //schema table create
    private static final String CREATE_TABLE_SCHEMA = "CREATE TABLE " + TABLE_SCHEMA +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_ABONNEEID + " INTEGER, " +
            KEY_SCHEMATYPE + " TEXT";

    //spiergroep table create
    private static final String CREATE_TABLE_SPIERGROEP = "CREATE TABLE " + TABLE_SPIERGROEP +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_SPIERGROEPNAAM + " TEXT";

    // abonnee_abonnement table create


    // schema_oefening table create


    // oefening_spiergroep table create


    // product_aankoop table create


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_AANKOOP);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_ABONNEE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_ABONNEMENT);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_OEFENING);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_SCHEMA);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_SPIERGROEP);

        onCreate(db);
    }

    /*public boolean insertData(String Naam, String Wachtwoord, String Leeftijd, String Mailadres){
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
    }*/
}
