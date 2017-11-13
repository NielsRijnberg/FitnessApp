package com.application.niels.a2bfit.Activities;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.niels.a2bfit.R;

import java.io.InputStream;

public class OefeningDetailsActivity extends AppCompatActivity {

    int oefeningID;
    String naam;
    int aantalSets;
    int aantalReps;
    String foto;
    TextView tvOefeningNaam;
    TextView tvAantalSets;
    TextView tvAantalReps;
    ImageView ivOefeningFoto;
    EditText content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening_details);

        tvOefeningNaam = (TextView) findViewById(R.id.tvOefeningNaam);
        tvAantalSets = (TextView) findViewById(R.id.tvAantalSets);
        tvAantalReps = (TextView) findViewById(R.id.tvAantalReps);
        ivOefeningFoto = (ImageView) findViewById(R.id.OefeningImageView);

        getSupportActionBar().setTitle("Oefening details");

        getOefeningDetails();
        setTextBoxes();
    }

    private void getOefeningDetails(){
        oefeningID = getIntent().getExtras().getInt("ID");
        naam = getIntent().getExtras().getString("naam");
        aantalSets = getIntent().getExtras().getInt("aantalSets");
        aantalReps = getIntent().getExtras().getInt("aantalReps");
        foto = getIntent().getExtras().getString("oefeningfoto");
    }

    private void setTextBoxes(){
        tvOefeningNaam.setText(naam);
        tvAantalSets.setText("Sets: " + aantalSets);
        tvAantalReps.setText("Reps: " + aantalReps);


        int id = getResources().getIdentifier("com.application.niels.a2bfit:drawable/" + foto, null, null);
        ivOefeningFoto.setImageResource(id);

        /*int id = getResources().getIdentifier(foto, "drawable", getPackageName());
        ivOefeningFoto.setImageResource(id);*/
    }
}
