package com.a2bfit.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.niels.a2bfit.R;

public class OefeningDetailsActivity extends AppCompatActivity {

    long oefeningID;
    String naam;
    String foto;
    String omschrijving;
    TextView tvOefeningNaam;
    TextView tvOefeningOmschrijving;
    ImageView ivOefeningFoto;
    EditText content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening_details);

        tvOefeningNaam = (TextView) findViewById(R.id.tvOefeningNaam);
        tvOefeningOmschrijving = (TextView) findViewById(R.id.tvOefeningOmschrijving);
        ivOefeningFoto = (ImageView) findViewById(R.id.OefeningImageView);

        getSupportActionBar().setTitle("Oefening details");

        getOefeningDetails();
        setTextBoxes();
    }

    private void getOefeningDetails(){
        oefeningID = getIntent().getExtras().getLong("ID");
        naam = getIntent().getExtras().getString("naam");
        foto = getIntent().getExtras().getString("oefeningfoto");
        omschrijving = getIntent().getExtras().getString("oefeningomschrijving");
    }

    private void setTextBoxes(){
        tvOefeningNaam.setText(naam);
        tvOefeningOmschrijving.setText(omschrijving);
        ivOefeningFoto.setImageResource(getResources().getIdentifier(foto.replace(".png", ""), "drawable", this.getPackageName()));
    }
}
