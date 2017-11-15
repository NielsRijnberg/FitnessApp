package SqlContexts;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.application.niels.a2bfit.Activities.OefeningenActivity;

import java.util.ArrayList;
import java.util.List;

import Classes.DatabaseHelper;
import Classes.Spiergroep;
import Contexts.ISpiergroepContext;

public class SqlSpiergroepContext implements ISpiergroepContext{



    @Override
    public List<Spiergroep> HaalSpiergroepenOp() {
        /*db.getReadableDatabase();
        Cursor result = db.HaalAlleSpiergroepenOp();
        List<Spiergroep> spiergroepList = new ArrayList<Spiergroep>();

        while (result.moveToNext()) {
            int id = result.getInt(result.getColumnIndex("ID"));
            String naam = result.getString(result.getColumnIndex("spiergroepnaam"));
            spiergroepList.add(new Spiergroep(id, naam));
        }
        return spiergroepList;*/
        return null;
    }
}
