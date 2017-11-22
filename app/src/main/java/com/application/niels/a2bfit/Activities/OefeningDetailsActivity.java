package com.application.niels.a2bfit.Activities;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        oefeningID = getIntent().getExtras().getInt("ID");
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
