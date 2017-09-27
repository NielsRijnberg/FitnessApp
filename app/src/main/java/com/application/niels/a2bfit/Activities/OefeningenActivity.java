package com.application.niels.a2bfit.Activities;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.application.niels.a2bfit.R;

import Classes.DatabaseHelper;
import Classes.DatabaseHelperOefeningen;

public class OefeningenActivity extends AppCompatActivity {

    DatabaseHelperOefeningen myDb;

    Button btnBekijkOefening;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefeningen);

        myDb = new DatabaseHelperOefeningen(this);

        btnBekijkOefening = (Button) findViewById(R.id.btnBekijkOefening);

        HaalAlleOefeningenOp();
    }

    public void HaalAlleOefeningenOp(){
        btnBekijkOefening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = myDb.HaalAlleOefeningenOp();

                if (result.getCount() == 0){
                    showMessage("Error", "Geen oefeningen gevonden");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()){
                    buffer.append("OefeningID :"+ result.getString(0)+ "\n");
                    buffer.append("Naam :"+ result.getString(1)+ "\n");
                    buffer.append("AantalSets :"+ result.getString(2)+ "\n");
                    buffer.append("AantalReps :"+ result.getString(3)+ "\n\n");
                }
                showMessage("Data", buffer.toString());
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
