package SqlContexts;

import java.util.List;

import Model.Spiergroep;
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
