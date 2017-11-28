package com.application.niels.a2bfit.Activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.niels.a2bfit.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Adapters.MyLayoutAdapter;
import Classes.DatabaseHelper;
import Classes.Oefening;
import Classes.Training;
import Classes.TrainingsOefening;

public class StartTrainingMetSchemaActivity extends AppCompatActivity {

    TextView tvSchemaType;
    EditText etGewicht;
    DatabaseHelper db;
    ListView lvOefeningenVanSchema;
    Button btnOefeningAfvinken;
    Button btnTrainingBeeindigen;
    int selectedIndex;
    List<Oefening> oefeningenVanSchema;
    ListAdapter listAdapter;
    boolean clickedListItem = false;

    int[] VINKJESFOTO = {R.drawable.checkmark};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_training_met_schema);

        tvSchemaType = (TextView) findViewById(R.id.tvSchemaType);
        etGewicht = (EditText) findViewById(R.id.etGewicht);
        db = new DatabaseHelper(this);
        lvOefeningenVanSchema = (ListView) findViewById(R.id.lvOefeningenVanSchema);
        btnOefeningAfvinken = (Button) findViewById(R.id.btnOefeningAfvinken);
        btnTrainingBeeindigen = (Button) findViewById(R.id.btnTrainingBeeindigen);

        setOefeningenListbox();
        getClickedOefening();
        VinkOefeningAf();
        TrainingBeeindigen();
    }

    public void getClickedOefening(){
        lvOefeningenVanSchema.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 selectedIndex = i;
                clickedListItem = true;
            }
        });
    }

    public void setOefeningenListbox(){
        int schemaID = getIntent().getExtras().getInt("schemaID");
        oefeningenVanSchema = db.HaalOefeningenOpBijSchema(schemaID);
        listAdapter = new MyLayoutAdapter<Oefening>(this, android.R.layout.simple_list_item_1, android.R.id.text1, oefeningenVanSchema);
        lvOefeningenVanSchema.setAdapter(listAdapter);

        if (oefeningenVanSchema.isEmpty()){
            showMessage("Error", "Geen oefeningen gevonden");
        }
    }


    //TODO zorgen dat ifstatement die checkt op gewicht het doet
    public void VinkOefeningAf(){
        btnOefeningAfvinken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gewichtAsString = etGewicht.getText().toString();
                if (!gewichtAsString.isEmpty()){
                    int gewicht = Integer.valueOf(gewichtAsString);

                    if (clickedListItem){
                        int trainingID = getIntent().getExtras().getInt("trainingID");
                        int oefeningID = ((Oefening)lvOefeningenVanSchema.getItemAtPosition(selectedIndex)).getOefeningID();

                        TrainingsOefening currentTrainingsOefening = new TrainingsOefening(trainingID, oefeningID, gewicht);
                        db.VinkTrainingAf(currentTrainingsOefening);
                        Toast.makeText(StartTrainingMetSchemaActivity.this, "Training afgevinkt", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(StartTrainingMetSchemaActivity.this, "Selecteer een oefening", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(StartTrainingMetSchemaActivity.this, "Vul een gewicht in", Toast.LENGTH_LONG).show();
                }
                oefeningenVanSchema.remove(selectedIndex);
                lvOefeningenVanSchema.invalidateViews();
            }
        });
    }

    public void TrainingBeeindigen(){
        btnTrainingBeeindigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oefeningenVanSchema.size() > 0){
                    AlertDialog.Builder adb=new AlertDialog.Builder(StartTrainingMetSchemaActivity.this);
                    adb.setTitle("Training beëindigen");
                    adb.setMessage("Weet je zeker dat je de training wilt beëindigen, er zijn nog oefeningen over");
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent overzichtSchermIntent = new Intent(StartTrainingMetSchemaActivity.this, OverzichtschermActivity.class);
                            startActivity(overzichtSchermIntent);
                        }});
                    adb.show();
                }
                else{
                    Intent overzichtSchermIntent = new Intent(StartTrainingMetSchemaActivity.this, OverzichtschermActivity.class);
                    startActivity(overzichtSchermIntent);
                    Toast.makeText(StartTrainingMetSchemaActivity.this ,"Training beëindigd", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
