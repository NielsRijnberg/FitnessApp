package com.application.niels.a2bfit.Activities;


import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.application.niels.a2bfit.R;
import java.util.List;
import Adapters.MyLayoutAdapter;
import Classes.DatabaseHelper;
import Classes.Oefening;
import Classes.Spiergroep;
import Classes.Training;

public class TrainingDetailsActivity extends AppCompatActivity {

    Spinner TrainingSpinner;
    DatabaseHelper db;
    EditText etTotaalAantalOefeningen;
    EditText etTotaalAantalHerhalingen;
    EditText etTotaalGewicht;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_details);

        TrainingSpinner = (Spinner) findViewById(R.id.TrainingSpinner);
        etTotaalAantalOefeningen = (EditText) findViewById(R.id.etTotaalAantalOefeningen);
        etTotaalAantalHerhalingen = (EditText) findViewById(R.id.etTotaalAantalHerhalingen);
        etTotaalGewicht = (EditText) findViewById(R.id.etTotaalGewicht);
        db = new DatabaseHelper(this);

        VulSpinner();
        setAantalOefeningen();
        setTotaalAantalHerhalingen();
    }

    public void VulSpinner(){
        List<Training> trainingList = db.HaalAlleTrainingenOp();
        SpinnerAdapter spinnerAdapter = new MyLayoutAdapter<Spiergroep>(this, android.R.layout.simple_list_item_1, android.R.id.text1, trainingList, 20, Color.BLUE);
        TrainingSpinner.setAdapter(spinnerAdapter);

        if (trainingList.isEmpty()) {
            showMessage("Error", "Geen trainingen gevonden");
        }
    }

    public void setAantalOefeningen(){
        Training selectedTraining = (Training) TrainingSpinner.getSelectedItem();
        int schemaID = selectedTraining.getSchemaID();
        List<Oefening> oefeningenVanTraining = db.HaalOefeningenOpBijSchema(schemaID);
        int aantalOefeningen = oefeningenVanTraining.size();
        etTotaalAantalOefeningen.setText("" + aantalOefeningen);
    }

    public void setTotaalAantalHerhalingen(){
        int totaalAantalHerhalingen = 0;

        Training selectedTraining = (Training) TrainingSpinner.getSelectedItem();
        int schemaID = selectedTraining.getSchemaID();
        List<int[]> setsEnReps = db.HaalHerhalingenEnSetsOp(schemaID);

        for (int[] setEnRep : setsEnReps){
            totaalAantalHerhalingen += (setEnRep[0] * setEnRep[1]);
        }
        etTotaalAantalHerhalingen.setText("" + totaalAantalHerhalingen);
    }

    public void setTotaalGewicht(){
        etTotaalGewicht.setText(" kg");
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
