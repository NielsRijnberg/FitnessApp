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

    DatabaseHelper myDb;
    EditText editNaam, editMail, editWachtwoord, editLeeftijd;
    Button btnRegistreer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreer);

        myDb = new DatabaseHelper(this);

        editNaam = (EditText) findViewById(R.id.etNaam);
        editMail = (EditText) findViewById(R.id.etMail);
        editWachtwoord = (EditText) findViewById(R.id.etWachtwoord);
        editLeeftijd = (EditText) findViewById(R.id.etLeeftijd);
        btnRegistreer = (Button) findViewById(R.id.btnRegistreer);

        RegistreerAbonnee();
    }

    public void RegistreerAbonnee() {
        btnRegistreer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData(editNaam.getText().toString(),
                                                        editWachtwoord.getText().toString(),
                                                        editLeeftijd.getText().toString(),
                                                        editMail.getText().toString());
                if (isInserted = true){
                    Toast.makeText(RegistreerActivity.this, "Geregistreerd", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(RegistreerActivity.this, "Database functie werkt niet", Toast.LENGTH_LONG).show();
                }



                /*Intent registreerIntent = new Intent(RegistreerActivity.this, LoginActivity.class);
                RegistreerActivity.this.startActivity(registreerIntent);*/
            }
        });
    }
}
