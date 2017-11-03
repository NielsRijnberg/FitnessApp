package com.application.niels.a2bfit.Activities;

import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.application.niels.a2bfit.R;

import java.util.ArrayList;
import java.util.List;

import Classes.DatabaseHelper;
import Classes.Oefening;
import Classes.Product;

public class ProductActivity extends AppCompatActivity {

    DatabaseHelper db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        db = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.listViewProducten);

        getProducten();
    }

    public void getProducten(){
        Cursor result = db.HaalAlleProductenOp();
        List<Product> productList = new ArrayList<Product>();

        if (result.getCount() == 0){
            showMessage("Error", "Geen oefeningen gevonden");
            return;
        }
        else {
            while (result.moveToNext()) {
                int id = result.getInt(result.getColumnIndex("ID"));
                String naam = result.getString(result.getColumnIndex("productnaam"));
                double kosten = result.getDouble(result.getColumnIndex("productkosten"));
                String omschrijving = result.getString(result.getColumnIndex("omschrijving"));

                productList.add(new Product(id, naam, kosten, omschrijving));

                ListAdapter listAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, android.R.id.text1, productList) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
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

    public void bekijkProduct(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

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
