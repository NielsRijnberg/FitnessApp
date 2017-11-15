package com.application.niels.a2bfit.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.application.niels.a2bfit.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Adapters.MyLayoutAdapter;
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

        getSupportActionBar().setTitle("Producten");

        getProducten();
        bekijkProduct();
    }

    public void getProducten(){
        List<Product> productList = db.HaalAlleProductenOp();
        ListAdapter listAdapter = new MyLayoutAdapter<Product>(this, android.R.layout.simple_list_item_1, android.R.id.text1, productList);
        listView.setAdapter(listAdapter);

        if (productList.isEmpty()){
            showMessage("Error", "Geen oefeningen gevonden");
        }
    }

    public void bekijkProduct(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Product selectedProduct = (Product) listView.getItemAtPosition(position);

                int productID = selectedProduct.getProductID();
                String naam = selectedProduct.getNaam();
                double kosten = selectedProduct.getKosten();
                String omschrijving = selectedProduct.getOmschrijving();

                Intent productDetailsIntent = new Intent(ProductActivity.this, ProductDetailsActivity.class);

                productDetailsIntent.putExtra("ID", productID);
                productDetailsIntent.putExtra("naam", naam);
                productDetailsIntent.putExtra("kosten", kosten);
                productDetailsIntent.putExtra("omschrijving", omschrijving);
                startActivity(productDetailsIntent);
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
