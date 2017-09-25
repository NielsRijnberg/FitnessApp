package com.application.niels.a2bfit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.application.niels.a2bfit.R;

public class RegistreerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreer);

        final EditText etNaam = (EditText) findViewById(R.id.etNaam);
        final EditText etMail = (EditText) findViewById(R.id.etMail);
        final EditText etWachtwoord = (EditText) findViewById(R.id.etWachtwoord);
        final EditText etLeeftijd = (EditText) findViewById(R.id.etLeeftijd);

        final Button btnRegistreer = (Button) findViewById(R.id.btnRegistreer);

        btnRegistreer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registreerIntent = new Intent(RegistreerActivity.this, LoginActivity.class);
                RegistreerActivity.this.startActivity(registreerIntent);
            }
        });
    }
}
