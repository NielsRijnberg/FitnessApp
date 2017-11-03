package com.application.niels.a2bfit.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.application.niels.a2bfit.R;

public class OefeningDetailsActivity extends AppCompatActivity {

    int oefeningID;
    String naam;
    int aantalSets;
    int aantalReps;
    TextView tvOefeningNaam;
    TextView tvAantalSets;
    TextView tvAantalReps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening_details);

        tvOefeningNaam = (TextView) findViewById(R.id.tvOefeningNaam);
        tvAantalSets = (TextView) findViewById(R.id.tvAantalSets);
        tvAantalReps = (TextView) findViewById(R.id.tvAantalReps);

        getOefeningDetails();
        setTextBoxes();
    }

    private void getOefeningDetails(){
        oefeningID = getIntent().getExtras().getInt("ID");
        naam = getIntent().getExtras().getString("naam");
        aantalSets = getIntent().getExtras().getInt("aantalSets");
        aantalReps = getIntent().getExtras().getInt("aantalReps");
    }

    private void setTextBoxes(){
        tvOefeningNaam.setText(naam);
        tvAantalSets.setText("Sets: " + aantalSets);
        tvAantalReps.setText("Reps: " + aantalReps);
    }
}
