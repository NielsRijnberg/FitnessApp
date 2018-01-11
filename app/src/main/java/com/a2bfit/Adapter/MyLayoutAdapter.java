package com.a2bfit.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyLayoutAdapter<T> extends ArrayAdapter<T> {

    private int textSize;
    private int textColor;

    public MyLayoutAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
        this.textSize = 20;
        this.textColor = Color.WHITE;
    }

    public MyLayoutAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List objects, int textSize, int textColor) {
        super(context, resource, textViewResourceId, objects);
        this.textSize = textSize;
        this.textColor = textColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = super.getView(position, convertView, parent);
        TextView textview = (TextView) view.findViewById(android.R.id.text1);
        textview.setTextColor(textColor);
        textview.setTextSize(textSize);
        return textview;
    }

}
