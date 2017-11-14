package Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.os.Build.ID;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";

    //DB version and name
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
    public static final String KEY_OEFENINGFOTO = "oefeningfoto";

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
            KEY_AANTALREPS + " INTEGER, " +
            KEY_OEFENINGFOTO + " TEXT)";

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
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 1 + "," + "'Benchpress'" + "," + 5 + "," + 5 + "," + "'benchpress.png'" + ")";

    private static final String INSERT_SQUAT_BACK= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 2 + "," + "'Squat back'" + "," + 4 + "," + 12 + "," + "'squatback.png'" + ")";

    private static final String INSERT_LEG_EXTENSION= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 3 + "," + "'Leg extension'" + "," + 4 + "," + 10 + "," + "'legextension.png'" + ")";

    private static final String INSERT_LYING_LEG_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 4 + "," + "'Lying leg curl'" + "," + 4 + "," + 10 + "," + "'lyinglegcurl.png'" + ")";

    private static final String INSERT_SEATED_LEG_PRESS= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 5 + "," + "'Seated leg press'" + "," + 4 + "," + 10 + "," + "'seatedlegpress.png'" + ")";

    private static final String INSERT_SEATED_LEG_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 6 + "," + "'Seated leg curl'" + "," + 4 + "," + 10 + "," + "'seatedlegcurl.png'" + ")";

    private static final String INSERT_PULL_DOWN_FRONT= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 7 + "," + "'Pull down front'" + "," + 4 + "," + 10 + "," + "'pulldownfront.png'" + ")";

    private static final String INSERT_BARBELL_ROWING= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 8 + "," + "'Barbell rowing'" + "," + 4 + "," + 12 + "," + "'barbellrowing.png'" + ")";

    private static final String INSERT_DUMBBELL_ROWING= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 9 + "," + "'Dumbbell rowing'" + "," + 4 + "," + 12 + "," + "'dumbbellrowing.png'" + ")";

    private static final String INSERT_DUAL_SEATED_ROW_WIDE= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 10 + "," + "'Dual seated row wide'" + "," + 4 + "," + 10 + "," + "'dualseatedrowwide.png'" + ")";

    private static final String INSERT_HORIZONTAL_LAT_ROW= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 11 + "," + "'Horizontal lat row'" + "," + 4 + "," + 12 + "," + "'horizontallatrow.png'" + ")";

    private static final String INSERT_DUMBBELL_PRESS= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 12 + "," + "'Dumbbell press'" + "," + 4 + "," + 12 + "," + "'dumbbellpress.png'" + ")";

    private static final String INSERT_FLYES= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 13 + "," + "'Flyes'" + "," + 4 + "," + 12 + "," + "'flyes.png'" + ")";

    private static final String INSERT_CABLE_CROSS_OVER= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 14 + "," + "'Cable cross over'" + "," + 4 + "," + 12 + "," + "'cablecrossover.png'" +")";

    private static final String INSERT_INCLINE_BENCH_PRESS= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 15 + "," + "'Incline bench press'" + "," + 4 + "," + 12 + "," + "'inclinebenchpress.png'" + ")";

    private static final String INSERT_INCLINE_DUMBBELL_PRESS= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 16 + "," + "'Incline dumbbell press'" + "," + 4 + "," + 12 + "," + "'inclinedumbbellpress.png'" + ")";

    private static final String INSERT_DUMBBELL_PULL_OVER= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 17 + "," + "'Dumbbell pull over'" + "," + 4 + "," + 12 + "," + "'dumbbellpullover.png'" + ")";

    private static final String INSERT_SEATED_CHEST_PRESS= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 18 + "," + "'Seated chest press'" + "," + 4 + "," + 12 + "," + "'seatedchestpress.png'" + ")";

    private static final String INSERT_FLY_MACHINE= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 19 + "," + "'Fly machine'" + "," + 4 + "," + 12 + "," + "'flymachine.png'" + ")";

    private static final String INSERT_FRONT_PRESS_STANDING= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 20 + "," + "'Front press standing'" + "," + 4 + "," + 12 + "," + "'frontpressstanding.png'" + ")";

    private static final String INSERT_SHOULDER_PRESS_MACHINE= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 21 + "," + "'Shoulder press machine'" + "," + 4 + "," + 12 + "," + "'shoulderpressmachine.png'" + ")";

    private static final String INSERT_FRONT_RAISE_STANDING= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 22 + "," + "'Front raise standing'" + "," + 4 + "," + 12 + "," + "'frontraisestanding.png'" + ")";

    private static final String INSERT_BEND_OVER_RAISE_SEATED= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 23 + "," + "'Bend over raise seated'" + "," + 4 + "," + 12 + "," + "'bendoverraiseseated.png'" + ")";

    private static final String INSERT_UPRIGHT_ROWING= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 24 + "," + "'Upright rowing'" + "," + 4 + "," + 12 + "," + "'uprightrowing.png'" + ")";

    private static final String INSERT_LATERAL_RAISE_STANDING= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 25 + "," + "'Lateral raise standing'" + "," + 4 + "," + 12 + "," + "'lateralraisestanding.png'" + ")";

    private static final String INSERT_TRICEPS_EXTENSION_DUMBBELL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 26 + "," + "'Triceps extension dumbbell'" + "," + 4 + "," + 12 + "," + "'tricepsextensiondumbbell.png'" + ")";

    private static final String INSERT_TRICEPS_PULL_DOWN= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 27 + "," + "'Triceps pull down'" + "," + 4 + "," + 12 + "," + "'tricepspulldown.png'" + ")";

    private static final String INSERT_TRICEPS_PULL_DOWN_ROPE= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 28 + "," + "'Triceps pull down rope'" + "," + 4 + "," + 12 + "," + "'tricepspulldownrope.png'" + ")";

    private static final String INSERT_KICK_BACKS_DUMBBELL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 29 + "," + "'Kick backs dumbbell'" + "," + 4 + "," + 12 + "," + "'kickbacksdumbbell.png'" + ")";

    private static final String INSERT_TRICEPS_EXTENSION= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 30 + "," + "'Triceps extension'" + "," + 4 + "," + 12 + "," + "'tricepsextension.png'" + ")";

    private static final String INSERT_BICEPS_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 31 + "," + "'Biceps curl'" + "," + 4 + "," + 12 + "," + "'bicepscurl.png'" + ")";

    private static final String INSERT_DUMBBELL_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 32 + "," + "'Dumbbell curl'" + "," + 4 + "," + 12 + "," + "'dumbbellcurlstandingalternated.png'" + ")";

    private static final String INSERT_CABLE_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 33 + "," + "'Cable curl'" + "," + 4 + "," + 12 + "," + "'cablecurl.png'" + ")";

    private static final String INSERT_PREACHER_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 34 + "," + "'Preacher curl'" + "," + 4 + "," + 12 + "," + "'preachercurl.png'" + ")";

    private static final String INSERT_HAMMER_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 35 + "," + "'Hammer curl'" + "," + 4 + "," + 12 + "," + "'hammercurl.png'" + ")";

    private static final String INSERT_SEATED_CALF_RAISE= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 36 + "," + "'Seated calf raise'" + "," + 4 + "," + 12 + "," + "'seatedcalfraise.png'" + ")";

    private static final String INSERT_CRUNCH= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 37 + "," + "'Crunch'" + "," + 4 + "," + 12 + "," + "'crunch.png'" + ")";

    private static final String INSERT_SIDE_CRUNCH= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 38 + "," + "'Side crunch'" + "," + 4 + "," + 12 + "," + "'sidecrunch.png'" + ")";

    private static final String INSERT_LEG_LIFT= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 39 + "," + "'Leg lift'" + "," + 4 + "," + 12 + "," + "'leglift.png'" + ")";

    private static final String INSERT_ABDOMINAL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 40 + "," + "'Abdominal'" + "," + 4 + "," + 12 + "," + "'abdominal.png'" + ")";

    private static final String INSERT_PLANK= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_AANTALSETS  + "," + KEY_AANTALREPS + "," + KEY_OEFENINGFOTO +
            ") VALUES (" + 41 + "," + "'Plank'" + "," + 4 + "," + 12 + "," + "'plank.png'" + ")";
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

    private static final String INSERT_BUIKSCHEMA = "INSERT INTO " + TABLE_SCHEMA +
            " (" + KEY_ID + "," + KEY_SCHEMATYPE +
            ") VALUES (" + 4 + "," + "'Buikschema'" + ")";

    private static final String INSERT_TRICEPSSCHEMA = "INSERT INTO " + TABLE_SCHEMA +
            " (" + KEY_ID + "," + KEY_SCHEMATYPE +
            ") VALUES (" + 5 + "," + "'Tricepsschema'" + ")";

    private static final String INSERT_RUGSCHEMA = "INSERT INTO " + TABLE_SCHEMA +
            " (" + KEY_ID + "," + KEY_SCHEMATYPE +
            ") VALUES (" + 6 + "," + "'Rugschema'" + ")";

    private static final String INSERT_SCHOUDERSSCHEMA = "INSERT INTO " + TABLE_SCHEMA +
            " (" + KEY_ID + "," + KEY_SCHEMATYPE +
            ") VALUES (" + 7 + "," + "'Schoudersschema'" + ")";
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

    //region Insert oefeningen van spiergroep
    private static final String INSERT_BENCHPRESS_BORST = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 1 + "," + 7 + ")";

    private static final String INSERT_SQUATBACK_BENEN = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 2 + "," + 4 + ")";

    private static final String INSERT_LEGEXTENSION_BENEN = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 3 + "," + 4 + ")";

    private static final String INSERT_LYINGLEGCURL_BENEN = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 4 + "," + 4 + ")";

    private static final String INSERT_SEATEDLEGPRESS_BENEN = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 5 + "," + 4 + ")";

    private static final String INSERT_SEATEDLEGCURL_BENEN = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 6 + "," + 4 + ")";

    private static final String INSERT_PULLDOWNFRONT_RUG = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 7 + "," + 5 + ")";

    private static final String INSERT_BARBELLROWING_RUG = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 8 + "," + 5 + ")";

    private static final String INSERT_DUMBBELLROWING_RUG = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 9 + "," + 5 + ")";

    private static final String INSERT_DUALSEATEDROWWIDE_RUG = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 10 + "," + 5 + ")";

    private static final String INSERT_HORIZONTALLATROW_RUG = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 11 + "," + 5 + ")";

    private static final String INSERT_DUMBBELLPRESS_BORST = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 12 + "," + 7 + ")";

    private static final String INSERT_FLYES_BORST = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 13 + "," + 7 + ")";

    private static final String INSERT_CABLECROSSOVER_BORST = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 14 + "," + 7 + ")";

    private static final String INSERT_INCLINEBENCHPRESS_BORST = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 15 + "," + 7 + ")";

    private static final String INSERT_INCLINEDUMBBELLPRESS_BORST = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 16 + "," + 7 + ")";

    private static final String INSERT_DUMBBELLPULLOVER_BORST = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 17 + "," + 7 + ")";

    private static final String INSERT_SEATEDCHESTPRESS_BORST = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 18 + "," + 7 + ")";

    private static final String INSERT_FLYMACHINE_BORST = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 19 + "," + 7 + ")";

    private static final String INSERT_FRONTPRESSSTANDING_SCHOUDERS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 20 + "," + 6 + ")";

    private static final String INSERT_SHOULDERPRESSMACHINE_SCHOUDERS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 21 + "," + 6 + ")";

    private static final String INSERT_FRONTRAISESTANDING_SCHOUDERS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 22 + "," + 6 + ")";

    private static final String INSERT_BENDOVERRAISESEATED_SCHOUDERS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 23 + "," + 6 + ")";

    private static final String INSERT_UPRIGHTROWING_SCHOUDERS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 24 + "," + 6 + ")";

    private static final String INSERT_LATERALRAISESTANDING_SCHOUDERS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 25 + "," + 6 + ")";

    private static final String INSERT_TRICEPSEXTENSIONDUMBBELL_TRICEPS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 26 + "," + 3 + ")";

    private static final String INSERT_TRICEPSPULLDOWN_TRICEPS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 27 + "," + 3 + ")";

    private static final String INSERT_TRICEPSPULLDOWNROPE_TRICEPS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 28 + "," + 3 + ")";

    private static final String INSERT_KICKBACKSDUMBBELL_TRICEPS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 29 + "," + 3 + ")";

    private static final String INSERT_TRICEPSEXTENSION_TRICEPS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 30 + "," + 3 + ")";

    private static final String INSERT_BICEPSCURL_BICEPS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 31 + "," + 2 + ")";

    private static final String INSERT_DUMBBELLCURL_BICEPS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 32 + "," + 2 + ")";

    private static final String INSERT_CABLECURL_BICEPS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 33 + "," + 2 + ")";

    private static final String INSERT_PREACHERCURL_BICEPS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 34 + "," + 2 + ")";

    private static final String INSERT_HAMMERCURL_BICEPS = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 35 + "," + 2 + ")";

    private static final String INSERT_SEATEDCALFRAISE_BENEN = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 36 + "," + 4 + ")";

    private static final String INSERT_CRUNCH_BUIK = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 37 + "," + 1 + ")";

    private static final String INSERT_SIDECRUNCH_BUIK = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 38 + "," + 1 + ")";

    private static final String INSERT_LEGLIFT_BUIK = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 39 + "," + 1 + ")";

    private static final String INSERT_ABDOMINAL_BUIK = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 40 + "," + 1 + ")";

    private static final String INSERT_PLANK_BUIK = "INSERT INTO " + TABLE_OEFENING_SPIERGROEP +
            " (" + KEY_OEFENINGID + "," + KEY_SPIERGROEPID +
            ") VALUES (" + 41 + "," + 1 + ")";

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
        db.execSQL(CREATE_TABLE_OEFENING_SPIERGROEP);
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
        insertOefeningenVanSpiergroep(db);
        insertOefeningenVanSchemas(db);
    }


    private void insertOefeningen(SQLiteDatabase db){
        db.execSQL(INSERT_BENCHPRESS);
        db.execSQL(INSERT_SQUAT_BACK);
        db.execSQL(INSERT_LEG_EXTENSION);
        db.execSQL(INSERT_LYING_LEG_CURL);
        db.execSQL(INSERT_SEATED_LEG_PRESS);
        db.execSQL(INSERT_SEATED_LEG_CURL);
        db.execSQL(INSERT_PULL_DOWN_FRONT);
        db.execSQL(INSERT_BARBELL_ROWING);
        db.execSQL(INSERT_DUMBBELL_ROWING);
        db.execSQL(INSERT_DUAL_SEATED_ROW_WIDE);
        db.execSQL(INSERT_HORIZONTAL_LAT_ROW);
        db.execSQL(INSERT_DUMBBELL_PRESS);
        db.execSQL(INSERT_FLYES);
        db.execSQL(INSERT_CABLE_CROSS_OVER);
        db.execSQL(INSERT_INCLINE_BENCH_PRESS);
        db.execSQL(INSERT_INCLINE_DUMBBELL_PRESS);
        db.execSQL(INSERT_DUMBBELL_PULL_OVER);
        db.execSQL(INSERT_SEATED_CHEST_PRESS);
        db.execSQL(INSERT_FLY_MACHINE);
        db.execSQL(INSERT_FRONT_PRESS_STANDING);
        db.execSQL(INSERT_SHOULDER_PRESS_MACHINE);
        db.execSQL(INSERT_FRONT_RAISE_STANDING);
        db.execSQL(INSERT_BEND_OVER_RAISE_SEATED);
        db.execSQL(INSERT_UPRIGHT_ROWING);
        db.execSQL(INSERT_LATERAL_RAISE_STANDING);
        db.execSQL(INSERT_TRICEPS_EXTENSION_DUMBBELL);
        db.execSQL(INSERT_TRICEPS_PULL_DOWN);
        db.execSQL(INSERT_TRICEPS_PULL_DOWN_ROPE);
        db.execSQL(INSERT_KICK_BACKS_DUMBBELL);
        db.execSQL(INSERT_TRICEPS_EXTENSION);
        db.execSQL(INSERT_BICEPS_CURL);
        db.execSQL(INSERT_DUMBBELL_CURL);
        db.execSQL(INSERT_CABLE_CURL);
        db.execSQL(INSERT_PREACHER_CURL);
        db.execSQL(INSERT_HAMMER_CURL);
        db.execSQL(INSERT_SEATED_CALF_RAISE);
        db.execSQL(INSERT_CRUNCH);
        db.execSQL(INSERT_SIDE_CRUNCH);
        db.execSQL(INSERT_LEG_LIFT);
        db.execSQL(INSERT_ABDOMINAL);
        db.execSQL(INSERT_PLANK);
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
        db.execSQL(INSERT_BUIKSCHEMA);
        db.execSQL(INSERT_RUGSCHEMA);
        db.execSQL(INSERT_SCHOUDERSSCHEMA);
        db.execSQL(INSERT_TRICEPSSCHEMA);
    }

    private void insertOefeningenVanSpiergroep(SQLiteDatabase db){
        db.execSQL(INSERT_SQUATBACK_BENEN);
        db.execSQL(INSERT_LEGEXTENSION_BENEN);
        db.execSQL(INSERT_LYINGLEGCURL_BENEN);
        db.execSQL(INSERT_SEATEDLEGPRESS_BENEN);
        db.execSQL(INSERT_SEATEDLEGCURL_BENEN);
        db.execSQL(INSERT_PULLDOWNFRONT_RUG);
        db.execSQL(INSERT_BARBELLROWING_RUG);
        db.execSQL(INSERT_DUMBBELLROWING_RUG);
        db.execSQL(INSERT_DUALSEATEDROWWIDE_RUG);
        db.execSQL(INSERT_HORIZONTALLATROW_RUG);
        db.execSQL(INSERT_BENCHPRESS_BORST);
        db.execSQL(INSERT_DUMBBELLPRESS_BORST);
        db.execSQL(INSERT_FLYES_BORST);
        db.execSQL(INSERT_CABLECROSSOVER_BORST);
        db.execSQL(INSERT_INCLINEBENCHPRESS_BORST);
        db.execSQL(INSERT_INCLINEDUMBBELLPRESS_BORST);
        db.execSQL(INSERT_DUMBBELLPULLOVER_BORST);
        db.execSQL(INSERT_SEATEDCHESTPRESS_BORST);
        db.execSQL(INSERT_FLYMACHINE_BORST);
        db.execSQL(INSERT_FRONTPRESSSTANDING_SCHOUDERS);
        db.execSQL(INSERT_SHOULDERPRESSMACHINE_SCHOUDERS);
        db.execSQL(INSERT_FRONTRAISESTANDING_SCHOUDERS);
        db.execSQL(INSERT_BENDOVERRAISESEATED_SCHOUDERS);
        db.execSQL(INSERT_UPRIGHTROWING_SCHOUDERS);
        db.execSQL(INSERT_LATERALRAISESTANDING_SCHOUDERS);
        db.execSQL(INSERT_TRICEPSEXTENSIONDUMBBELL_TRICEPS);
        db.execSQL(INSERT_TRICEPSPULLDOWN_TRICEPS);
        db.execSQL(INSERT_TRICEPSPULLDOWNROPE_TRICEPS);
        db.execSQL(INSERT_KICKBACKSDUMBBELL_TRICEPS);
        db.execSQL(INSERT_TRICEPSEXTENSION_TRICEPS);
        db.execSQL(INSERT_BICEPSCURL_BICEPS);
        db.execSQL(INSERT_DUMBBELLCURL_BICEPS);
        db.execSQL(INSERT_CABLECURL_BICEPS);
        db.execSQL(INSERT_PREACHERCURL_BICEPS);
        db.execSQL(INSERT_HAMMERCURL_BICEPS);
        db.execSQL(INSERT_SEATEDCALFRAISE_BENEN);
        db.execSQL(INSERT_CRUNCH_BUIK);
        db.execSQL(INSERT_SIDECRUNCH_BUIK);
        db.execSQL(INSERT_LEGLIFT_BUIK);
        db.execSQL(INSERT_ABDOMINAL_BUIK);
        db.execSQL(INSERT_PLANK_BUIK);
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

    public Cursor HaalAlleSchemasOp(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_SCHEMA, null);
        return result;
    }

    public Cursor HaalOefeningenOpBijSchema(String type){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM oefeningen as o " +
                "INNER JOIN schemas_oefeningen so ON so.oefeningID = o.ID " +
                "INNER JOIN schemas s ON s.ID = so.schemaID " +
                "WHERE s.schematype = ?", new String[] {type});
        return result;
    }

    public Cursor HaalOefeningenOpBijSpiergroep(String spiergroepnaam){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM oefeningen as o " +
                "INNER JOIN oefeningen_spiergroepen os ON os.oefeningID = o.ID " +
                "INNER JOIN spiergroepen s ON s.ID = os.spiergroepID " +
                "WHERE s.spiergroepnaam = ?", new String[] {spiergroepnaam});
        return result;
    }
    /*public Cursor HaalOefeningenOpBijSpiergroep(Spiergroep spiergroep){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM oefeningen INNER JOIN " + TABLE_OEFENING_SPIERGROEP + " ON oefeningID = oefeningen.oefeningID WHERE oefeningen_spiergroepen.spiergroepID = " + spiergroep.spiergroepID);

    }*/
}
