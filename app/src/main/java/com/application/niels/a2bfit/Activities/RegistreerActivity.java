package com.application.niels.a2bfit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.niels.a2bfit.R;

import Classes.DatabaseHelper;

public class RegistreerActivity extends AppCompatActivity {

    EditText editNaam, editMail, editWachtwoord, editLeeftijd;
    Button btnRegistreer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreer);

        editNaam = (EditText) findViewById(R.id.etNaam);
        editMail = (EditText) findViewById(R.id.etMail);
        editWachtwoord = (EditText) findViewById(R.id.etWachtwoord);
        editLeeftijd = (EditText) findViewById(R.id.etLeeftijd);
        btnRegistreer = (Button) findViewById(R.id.btnRegistreer);

        getSupportActionBar().setTitle("Registreer");

        RegistreerAbonnee();
    }

    public void RegistreerAbonnee() {
        btnRegistreer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
