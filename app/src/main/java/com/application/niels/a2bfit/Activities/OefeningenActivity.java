package com.application.niels.a2bfit.Activities;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.application.niels.a2bfit.R;
import java.util.List;
import Classes.DatabaseHelper;
import Classes.Oefening;
import Classes.Spiergroep;

public class OefeningenActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button btnBekijkOefening;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefeningen);

        db = new DatabaseHelper(this);

        btnBekijkOefening = (Button) findViewById(R.id.btnBekijkOefening);

        getOefeningen();
    }

    public void getOefeningen(){
        btnBekijkOefening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = db.HaalAlleOefeningenOp();

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
