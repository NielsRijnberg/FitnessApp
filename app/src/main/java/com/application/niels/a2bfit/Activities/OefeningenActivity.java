package com.application.niels.a2bfit.Activities;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.application.niels.a2bfit.R;

import java.util.ArrayList;

import Classes.DatabaseHelper;

public class OefeningenActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button btnBekijkOefening;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefeningen);

        db = new DatabaseHelper(this);

        btnBekijkOefening = (Button) findViewById(R.id.btnBekijkOefening);
        listView = (ListView) findViewById(R.id.listViewOefeningen);

        getOefeningen();
    }

    public void getOefeningen() {
        Cursor result = db.HaalAlleOefeningenOp();
        ArrayList<String> oefeningList = new ArrayList<>();

        if (result.getCount() == 0) {
            showMessage("Error", "Geen oefeningen gevonden");
            return;
        } else {
            while (result.moveToNext()) {
                oefeningList.add(result.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, oefeningList){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){
                        View view = super.getView(position, convertView, parent);
                        TextView textview = (TextView) view.findViewById(android.R.id.text1);
                        textview.setTextColor(Color.WHITE);
                        return textview;
                    }
                };
                listView.setAdapter(listAdapter);
            }
        }
    }

   /* public void bekijkOefening(){
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
    }*/

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
