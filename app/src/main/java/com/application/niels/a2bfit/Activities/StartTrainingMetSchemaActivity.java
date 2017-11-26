package com.application.niels.a2bfit.Activities;

import android.app.DatePickerDialog;
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
    EditText etDatum;
    Calendar calendar;
    DatabaseHelper db;
    ListView lvOefeningenVanSchema;
    Button btnOefeningAfvinken;
    int selectedIndex;
    List<Oefening> oefeningenVanSchema;

    int[] VINKJESFOTO = {R.drawable.checkmark};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_training_met_schema);

        tvSchemaType = (TextView) findViewById(R.id.tvSchemaType);
        etGewicht = (EditText) findViewById(R.id.etGewicht);
        etDatum = (EditText) findViewById(R.id.etDatum);
        etDatum.setFocusable(false);
        etDatum.setClickable(true);
        calendar = Calendar.getInstance();
        db = new DatabaseHelper(this);
        lvOefeningenVanSchema = (ListView) findViewById(R.id.lvOefeningenVanSchema);
        btnOefeningAfvinken = (Button) findViewById(R.id.btnOefeningAfvinken);

        setOefeningenListbox();
        getClickedOefening();
        goToDatePicker();
        VinkOefeningAf();
    }

    public void getClickedOefening(){
        lvOefeningenVanSchema.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 selectedIndex = i;
            }
        });
    }

    public void setOefeningenListbox(){
        int schemaID = getIntent().getExtras().getInt("schemaID");
        oefeningenVanSchema = db.HaalOefeningenOpBijSchema(schemaID);
        ListAdapter listAdapter = new MyLayoutAdapter<Oefening>(this, android.R.layout.simple_list_item_1, android.R.id.text1, oefeningenVanSchema);
        lvOefeningenVanSchema.setAdapter(listAdapter);

        if (oefeningenVanSchema.isEmpty()){
            showMessage("Error", "Geen oefeningen gevonden");
        }
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    public void goToDatePicker(){
        etDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(StartTrainingMetSchemaActivity.this,R.style.DatePickerTheme, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void updateLabel(){
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.GERMANY);

        etDatum.setText(sdf.format(calendar.getTime()));
    }

    public void VinkOefeningAf(){
        btnOefeningAfvinken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gewichtAsString = etGewicht.getText().toString();
                if (gewichtAsString.length() != 0){
                    int gewicht = Integer.valueOf(gewichtAsString);

                    if (selectedIndex > -1){
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
                    Toast.makeText(StartTrainingMetSchemaActivity.this, "Vul gewicht en datum in", Toast.LENGTH_LONG).show();
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
