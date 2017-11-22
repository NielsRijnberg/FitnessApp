package Adapters;


import android.content.Context;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.niels.a2bfit.Activities.StartTrainingMetSchemaActivity;
import com.application.niels.a2bfit.R;

import java.util.zip.Inflater;

public class MyListAdapter extends BaseAdapter{
    public MyListAdapter() {
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //TODO fixen jonguuuhhh
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        /*LayoutInflater inflater = (LayoutInflater)
        view = getLayoutInflater().inflate(R.layout.list_layout_starttrainingmetschema);*/


        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textViewOefeningnaam);
        return null;
    }
}
