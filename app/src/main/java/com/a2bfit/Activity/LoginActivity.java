package com.a2bfit.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.a2bfit.Repo.DatabaseHelper;
import com.application.niels.a2bfit.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etGebruikersNaam = (EditText) findViewById(R.id.etGebruikersNaam);
        final EditText etWachtwoord = (EditText) findViewById(R.id.etWachtwoord);
        final Button btnLogIn = (Button) findViewById(R.id.btnLogIn);
        final TextView tvRegistreer = (TextView) findViewById(R.id.tvRegistreer);

        getSupportActionBar().setTitle("Log in");

        initDatabase();

        tvRegistreer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registreerIntent = new Intent(LoginActivity.this, RegistreerActivity.class);
                LoginActivity.this.startActivity(registreerIntent);
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logInIntent = new Intent(LoginActivity.this, OverzichtschermActivity.class);
                LoginActivity.this.startActivity(logInIntent);
            }
        });
    }

    private void initDatabase() {
        DatabaseHelper db = new DatabaseHelper(this);
        SQLiteDatabase sqlDb = db.getWritableDatabase();
        db.dropTables(sqlDb);
        db.createTablesIfNotExists(sqlDb);
        db.insertSampleData(sqlDb);
        db.close();
    }
}
