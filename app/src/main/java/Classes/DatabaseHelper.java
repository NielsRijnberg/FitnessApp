package Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
    public static final String TABLE_TRAINING = "trainingen";
    public static final String TABLE_TRAINING_OEFENING = "trainingen_oefeningen";
    //endregion

    //region Column Names
    //Common column names
    public static final String KEY_ID = "ID";
    public static final String KEY_SCHEMAID = "schemaID";
    public static final String KEY_OEFENINGID = "oefeningID";
    public static final String KEY_SPIERGROEPID = "spiergroepID";
    public static final String KEY_TRAININGID = "trainingID";

    //oefening column names
    public static final String KEY_OEFENINGNAAM = "oefeningnaam";
    public static final String KEY_AANTALSETS = "aantalsets";
    public static final String KEY_AANTALREPS = "aantalreps";
    public static final String KEY_OEFENINGOMSCHRIJVING = "oefeningomschrijving";
    public static final String KEY_OEFENINGFOTO = "oefeningfoto";

    //product column names
    public static final String KEY_PRODUCTNAAM = "productnaam";
    public static final String KEY_PRODUCTKOSTEN = "productkosten";
    public static final String KEY_OMSCHRIJVING = "omschrijving";

    //schema column names
    public static final String KEY_SCHEMATYPE = "schematype";

    //spiergroep column names
    public static final String KEY_SPIERGROEPNAAM = "spiergroepnaam";

    //training column names
    public static final String KEY_GEWICHT = "gewicht";
    public static final String KEY_DATUM = "datum";

    //endregion

    //region Create Tables
    //oefening table create
    private static final String CREATE_TABLE_OEFENING = "CREATE TABLE IF NOT EXISTS " + TABLE_OEFENING +
            "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_OEFENINGNAAM + " TEXT, " +
            KEY_OEFENINGFOTO + " TEXT, " +
            KEY_OEFENINGOMSCHRIJVING + " TEXT)";

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
            KEY_OEFENINGID + " INTEGER REFERENCES " + TABLE_OEFENING + " (" + KEY_ID + "), " +
            KEY_AANTALSETS + " INTEGER, " +
            KEY_AANTALREPS + " INTEGER)";

    // oefening_spiergroep table create
    private static final String CREATE_TABLE_OEFENING_SPIERGROEP = "CREATE TABLE IF NOT EXISTS " + TABLE_OEFENING_SPIERGROEP +
            "(" + KEY_OEFENINGID + " INTEGER REFERENCES " + TABLE_OEFENING + " (" + KEY_ID + "), " +
            KEY_SPIERGROEPID + " INTEGER REFERENCES " + TABLE_SPIERGROEP + " (" + KEY_ID + "))";

    //training table create
    private static final String CREATE_TABLE_TRAINING = "CREATE TABLE IF NOT EXISTS " + TABLE_TRAINING +
            "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_SCHEMAID + " INTEGER REFERENCES " + TABLE_SCHEMA + " (" + KEY_ID + "), " +
            KEY_DATUM + " TEXT)";

    private static final String CREATE_TABLE_TRAINING_OEFENING = "CREATE TABLE IF NOT EXISTS " + TABLE_TRAINING +
            "(" + KEY_OEFENINGID + " INTEGER REFERENCES " + TABLE_OEFENING + " (" + KEY_ID + "), " +
            KEY_TRAININGID + " INTEGER REFERENCES " + TABLE_TRAINING + " (" + KEY_ID + "), " +
            KEY_GEWICHT + " INTEGER)";
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
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 1 + "," + "'Benchpress'" + "," + "'benchpress.png'" + "," + "'Greep breder dan schouderbreedte, zak tot ellebogen lager dan schouders, armen uitstrekken en beweeg boven het midden van de borst'" + ")";

    private static final String INSERT_SQUAT_BACK= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 2 + "," + "'Squat back'" + "," + "'squatback.png'" + "," + "'Voeten op heupbreedte, barbell in de nek, borst naar voren, onderrug hol, zak niet dieper dan 90 graden'" + ")";

    private static final String INSERT_LEG_EXTENSION= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 3 + "," + "'Leg extension'" + "," + "'legextension.png'" + "," + "'Toestel instellen naar lichaamsmaten, instructie toesten opvolgen, instructie schema opvolgen'" + ")";

    private static final String INSERT_LYING_LEG_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 4 + "," + "'Lying leg curl'" + "," + "'lyinglegcurl.png'" + "," + "'Toestel instellen naar lichaamsmaten, instructie toesten opvolgen, instructie schema opvolgen'" + ")";

    private static final String INSERT_SEATED_LEG_PRESS= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 5 + "," + "'Seated leg press'" + "," + "'seatedlegpress.png'" + "," + "'Toestel instellen naar lichaamsmaten, instructie toesten opvolgen, instructie schema opvolgen'" + ")";

    private static final String INSERT_SEATED_LEG_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 6 + "," + "'Seated leg curl'" + "," + "'seatedlegcurl.png'" + "," + "'Toestel instellen naar lichaamsmaten, instructie toesten opvolgen, instructie schema opvolgen'" + ")";

    private static final String INSERT_PULL_DOWN_FRONT= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 7 + "," + "'Pull down front'" + "," + "'pulldownfront.png'" + "," + "'Pak stang op schuine delen wijd, trek omlaag tot sleutelbeen, strek tot je armen bijna gestrekt zijn'" + ")";

    private static final String INSERT_BARBELL_ROWING= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 8 + "," + "'Barbell rowing'" + "," + "'barbellrowing.png'" + "," + "'Pak barbell onderhands, handen op schouderbreedte, knieen licht gebogen, optrekken tot middenrif, laten zakken tot je armen bijna gestrekt zijn'" + ")";

    private static final String INSERT_DUMBBELL_ROWING= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 9 + "," + "'Dumbbell rowing'" + "," + "'dumbbellrowing.png'" + "," + "'Linker hand op bank, linker knie op bank, dumbbell in rechter hand, trek dumbbell verticaal tot naast het lichaam, terug tot arm bijna gestrekt is'" + ")";

    private static final String INSERT_DUAL_SEATED_ROW_WIDE= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 10 + "," + "'Dual seated row wide'" + "," + "'dualseatedrowwide.png'" + "," + "'Toestel instellen naar lichaamsmaten, instructie toesten opvolgen, instructie schema opvolgen'" + ")";

    private static final String INSERT_HORIZONTAL_LAT_ROW= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 11 + "," + "'Horizontal lat row'" + "," + "'horizontallatrow.png'" + "," + "'Toestel instellen naar lichaamsmaten, instructie toesten opvolgen, instructie schema opvolgen'" + ")";

    private static final String INSERT_DUMBBELL_PRESS= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 12 + "," + "'Dumbbell press'" + "," + "'dumbbellpress.png'" + "," + "'Greep met handpalmen naar voeten, zakken tot ellebogen lager dan schouders zijn, uitstrekken tot armen gestrekt zijn'" + ")";

    private static final String INSERT_FLYES= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 13 + "," + "'Flyes'" + "," + "'flyes.png'" + "," + "'Greep met handpalmen naar lichaam, armen licht gebogen, zak naar buiten tot dumbbells op schouderhoogte'" + ")";

    private static final String INSERT_CABLE_CROSS_OVER= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 14 + "," + "'Cable cross over'" + "," + "'cablecrossover.png'" + "," + "'Sta tussen pulleys, 1 voet voor, bovenlichaam licht voorover, beweeg in ruime boog tot voor je borst'" +")";

    private static final String INSERT_INCLINE_BENCH_PRESS= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 15 + "," + "'Incline bench press'" + "," + "'inclinebenchpress.png'" + "," + "'Greep breder dan schouderbreedte, zak tot ellebogen iets lager dan schouders zijn, uitstrekken tot armen bijna gestrekt zijn'" + ")";

    private static final String INSERT_INCLINE_DUMBBELL_PRESS= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 16 + "," + "'Incline dumbbell press'" + "," + "'inclinedumbbellpress.png'" + "," + "'Greep met handpalmen naar voeten, zakken tot ellebogen iets lager zijn dan schouders, uitstrekken tot armen bijna gestrekt zijn'" + ")";

    private static final String INSERT_DUMBBELL_PULL_OVER= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 17 + "," + "'Dumbbell pull over'" + "," + "'dumbbellpullover.png'" + "," + "'Greep tussen duimen en wijsvingers, armen licht gebogen, zak naar achter tot dumbbell achter je hoofd is'" + ")";

    private static final String INSERT_SEATED_CHEST_PRESS= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 18 + "," + "'Seated chest press'" + "," + "'seatedchestpress.png'" + "," + "'Toestel instellen naar lichaamsmaten, instructie toesten opvolgen, instructie schema opvolgen'" + ")";

    private static final String INSERT_FLY_MACHINE= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 19 + "," + "'Fly machine'" + "," + "'flymachine.png'" + "," + "'Toestel instellen naar lichaamsmaten, instructie toesten opvolgen, instructie schema opvolgen'" + ")";

    private static final String INSERT_FRONT_PRESS_STANDING= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 20 + "," + "'Front press standing'" + "," + "'frontpressstanding.png'" + "," + "'Sta met barbell op sleutelbenen, duw uit tot armen bijna gestrekt, houd rug recht en houdt romp stil'" + ")";

    private static final String INSERT_SHOULDER_PRESS_MACHINE= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 21 + "," + "'Shoulder press machine'" + "," + "'shoulderpressmachine.png'" + "," + "'Toestel instellen naar lichaamsmaten, instructie toesten opvolgen, instructie schema opvolgen'" + ")";

    private static final String INSERT_FRONT_RAISE_STANDING= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 22 + "," + "'Front raise standing'" + "," + "'frontraisestanding.png'" + "," + "'Sta met dumbbells voor dijbenen, hef dumbbells voorwaarts tot ooghoogte, armen licht gebogen, rug recht'" + ")";

    private static final String INSERT_BEND_OVER_RAISE_SEATED= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 23 + "," + "'Bend over raise seated'" + "," + "'bendoverraiseseated.png'" + "," + "'Zit stil met dumbbells naast kuiten, borst op knieen, hef dumbbells zijwaarts tot ellebogen op schouderhoogte, armen licht gebogen'" + ")";

    private static final String INSERT_UPRIGHT_ROWING= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 24 + "," + "'Upright rowing'" + "," + "'uprightrowing.png'" + "," + "'Sta met licht gebogen knieen, rechte rug en ez-bar voor heupen, armen bijna gestrekt, hef langs lichaam tot boven het borstbeen'" + ")";

    private static final String INSERT_LATERAL_RAISE_STANDING= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 25 + "," + "'Lateral raise standing'" + "," + "'lateralraisestanding.png'" + "," + "'Sta met dumbbells naast heupen, hef dumbbells zijwaarts tot ellebogen op schouderhoogte, armen licht gebogen'" + ")";

    private static final String INSERT_TRICEPS_EXTENSION_DUMBBELL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 26 + "," + "'Triceps extension dumbbell'" + "," + "'tricepsextensiondumbbell.png'" + "," + "'Pak dumbbell in hand, arm langs oor, buig tot hand lager dan elleboog, dumbbell achter hoofd'" + ")";

    private static final String INSERT_TRICEPS_PULL_DOWN= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 27 + "," + "'Triceps pull down'" + "," + "'tricepspulldown.png'" + "," + "'Sta met knieen licht gebogen, pak barbell in bovengreep, ellebogen in zij, beweeg van volledig gestrekt tot hand boven ellebooghoogte'" + ")";

    private static final String INSERT_TRICEPS_PULL_DOWN_ROPE= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 28 + "," + "'Triceps pull down rope'" + "," + "'tricepspulldownrope.png'" + "," + "'Sta rechtop, knieen licht gebogen, pak rope, ellebogen in zij, beweeg van volledig gestrekt tot hand boven ellebooghoogte'" + ")";

    private static final String INSERT_KICK_BACKS_DUMBBELL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 29 + "," + "'Kick backs dumbbell'" + "," + "'kickbacksdumbbell.png'" + "," + "'Linker hand en linker knie op bank, pak dumbbell in rechter hand, beweeg onderarm omhoog'" + ")";

    private static final String INSERT_TRICEPS_EXTENSION= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 30 + "," + "'Triceps extension'" + "," + "'tricepsextension.png'" + "," + "'Rugligging, armen gestrekt tot handen lager dan elleboog, ez-bar bij voorhoofd'" + ")";

    private static final String INSERT_BICEPS_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 31 + "," + "'Biceps curl'" + "," + "'bicepscurl.png'" + "," + "'Sta rechtop, knieen licht gebogen, pak ez-bar in ondergreep, beweeg van bijna gestrekt tot voor sleutelbeen'" + ")";

    private static final String INSERT_DUMBBELL_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 32 + "," + "'Dumbbell curl'" + "," + "'dumbbellcurlstandingalternated.png'" + "," + "'Sta met dumbbells naast heupen, handpalmen naar binnen, buig armen en draai duim naar buiten'" + ")";

    private static final String INSERT_CABLE_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 33 + "," + "'Cable curl'" + "," + "'cablecurl.png'" + "," + "'Sta rechtop met licht gebogen knieen, pak barbell in ondergreep, duw ellebogen in zij van bijna gestrekt tot naar voor'" + ")";

    private static final String INSERT_PREACHER_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 34 + "," + "'Preacher curl'" + "," + "'preachercurl.png'" + "," + "'Zit met bovenarmen op bank, pak ez-bar in ondergreep, beweeg van bijna gestrekt tot oor'" + ")";

    private static final String INSERT_HAMMER_CURL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 35 + "," + "'Hammer curl'" + "," + "'hammercurl.png'" + "," + "'Sta met dumbbell naast heup, handpalm naar binnen, buig arm met duim omhoog'" + ")";

    private static final String INSERT_SEATED_CALF_RAISE= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 36 + "," + "'Seated calf raise'" + "," + "'seatedcalfraise.png'" + "," + "'Toestel instellen naar lichaamsmaten, instructie toesten opvolgen, instructie schema opvolgen'" + ")";

    private static final String INSERT_CRUNCH= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 37 + "," + "'Crunch'" + "," + "'crunch.png'" + "," + "'Rugligging, voeten plat op de vloer, rol schouders van vloer, kijk naar plafond en ondersteun hoofd, zak terug en houd spanning op de buik'" + ")";

    private static final String INSERT_SIDE_CRUNCH= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 38 + "," + "'Side crunch'" + "," + "'sidecrunch.png'" + "," + "'Rugligging, voeten plat op de vloer, rol schouders, draai rechter elleboog naar linker knie, zak terug en houd spanning op de buik'" + ")";

    private static final String INSERT_LEG_LIFT= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 39 + "," + "'Leg lift'" + "," + "'leglift.png'" + "," + "'Benen gestrekt, armen langs lichaam op de vloer, hef benen verticaal'" + ")";

    private static final String INSERT_ABDOMINAL= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 40 + "," + "'Abdominal'" + "," + "'abdominal.png'" + "," + "'Toestel instellen naar lichaamsmaten, instructie toesten opvolgen, instructie schema opvolgen'" + ")";

    private static final String INSERT_PLANK= "INSERT INTO " + TABLE_OEFENING +
            " (" + KEY_ID + "," + KEY_OEFENINGNAAM + "," + KEY_OEFENINGFOTO + "," + KEY_OEFENINGOMSCHRIJVING +
            ") VALUES (" + 41 + "," + "'Plank'" + "," + "'plank.png'" + "," + "'Geen uitleg beschikbaar'" + ")";
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
            " (" + KEY_SCHEMAID + "," + KEY_OEFENINGID + "," + KEY_AANTALSETS + "," + KEY_AANTALREPS +
            ") VALUES (" + 3 + "," + 2 + "," + 4 + "," + 10 + ")";

    private static final String INSERT_BENENSCHEMA_LEGEXTENSION = "INSERT INTO " + TABLE_SCHEMA_OEFENING +
            " (" + KEY_SCHEMAID + "," + KEY_OEFENINGID + "," + KEY_AANTALSETS + "," + KEY_AANTALREPS +
            ") VALUES (" + 3 + "," + 3 + "," + 4 + "," + 10 + ")";

    private static final String INSERT_BENENSCHEMA_LYINGLEGCURL = "INSERT INTO " + TABLE_SCHEMA_OEFENING +
            " (" + KEY_SCHEMAID + "," + KEY_OEFENINGID + "," + KEY_AANTALSETS + "," + KEY_AANTALREPS +
            ") VALUES (" + 3 + "," + 4 + "," + 4 + "," + 10 + ")";

    private static final String INSERT_BENENSCHEMA_SEATEDLEGPRESS = "INSERT INTO " + TABLE_SCHEMA_OEFENING +
            " (" + KEY_SCHEMAID + "," + KEY_OEFENINGID + "," + KEY_AANTALSETS + "," + KEY_AANTALREPS +
            ") VALUES (" + 3 + "," + 5 + "," + 4 + "," + 10 + ")";
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
        db.execSQL(CREATE_TABLE_TRAINING);
        db.execSQL(CREATE_TABLE_TRAINING_OEFENING);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OEFENING_SPIERGROEP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEMA_OEFENING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAINING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAINING_OEFENING);
    }


    //region InsertData
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
    //endregion

    //region Queries
    public List<Product> HaalAlleProductenOp(){
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_PRODUCT, null);

        while (result.moveToNext()) {
            int id = result.getInt(result.getColumnIndex("ID"));
            String naam = result.getString(result.getColumnIndex("productnaam"));
            double kosten = result.getDouble(result.getColumnIndex("productkosten"));
            String omschrijving = result.getString(result.getColumnIndex("omschrijving"));
            productList.add(new Product(id, naam, kosten, omschrijving));
        }
        return productList;
    }

    public List<Spiergroep> HaalAlleSpiergroepenOp(){
        List<Spiergroep> spiergroepList = new ArrayList<Spiergroep>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_SPIERGROEP, null);

        while (result.moveToNext()) {
            int id = result.getInt(result.getColumnIndex("ID"));
            String naam = result.getString(result.getColumnIndex("spiergroepnaam"));
            spiergroepList.add(new Spiergroep(id, naam));
        }
        return spiergroepList;
    }

    public List<Schema> HaalAlleSchemasOp(){
        List<Schema> schemaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_SCHEMA, null);

        while (result.moveToNext()) {
            int id = result.getInt(result.getColumnIndex("ID"));
            String type = result.getString(result.getColumnIndex("schematype"));
            schemaList.add(new Schema(id, type));
        }
        return schemaList;
    }

    public List<Oefening> HaalOefeningenOpBijSchema(int schemaID){
        List<Oefening> oefeningList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM oefeningen as o " +
                "INNER JOIN schemas_oefeningen so ON so.oefeningID = o.ID " +
                "INNER JOIN schemas s ON s.ID = so.schemaID " +
                "WHERE s.ID = ?", new String[] {""+schemaID});

        while (result.moveToNext()) {
            int id = result.getInt(result.getColumnIndex("ID"));
            String naam = result.getString(result.getColumnIndex("oefeningnaam"));
            String foto = result.getString(result.getColumnIndex("oefeningfoto"));
            String omschrijving = result.getString(result.getColumnIndex("oefeningomschrijving"));
            oefeningList.add(new Oefening(id, naam, foto, omschrijving));
        }
        return oefeningList;
    }

    public List<Oefening> HaalOefeningenOpBijSpiergroep(String spiergroepnaam){
        List<Oefening> oefeningList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM oefeningen as o " +
                "INNER JOIN oefeningen_spiergroepen os ON os.oefeningID = o.ID " +
                "INNER JOIN spiergroepen s ON s.ID = os.spiergroepID " +
                "WHERE s.spiergroepnaam = ?", new String[] {spiergroepnaam});

        while (result.moveToNext()) {
            int id = result.getInt(result.getColumnIndex("ID"));
            String naam = result.getString(result.getColumnIndex("oefeningnaam"));
            String foto = result.getString(result.getColumnIndex("oefeningfoto"));
            String omschrijving = result.getString(result.getColumnIndex("oefeningomschrijving"));
            oefeningList.add(new Oefening(id, naam, foto, omschrijving));
        }
        return oefeningList;
    }

    public long StartTraining(Training training){
        ContentValues contentValues = new ContentValues();
        contentValues.put("schemaID", training.getSchemaID());
        contentValues.put("datum", training.getDatum());

        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(TABLE_TRAINING, null, contentValues);
    }

    public void VinkTrainingAf(TrainingsOefening trainingsOefening){
        int trainingID = trainingsOefening.getTrainingID();
        int oefeningID = trainingsOefening.getOefeningID();
        int gewicht = trainingsOefening.getGewicht();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO " + TABLE_TRAINING_OEFENING + " (oefeningID, trainingID, gewicht) VALUES (?, ?, ?)";
        db.execSQL(query, new String[]{""+oefeningID, ""+trainingID, ""+gewicht});

    }
    //endregion
}
