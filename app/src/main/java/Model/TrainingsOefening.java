package Model;

import android.content.Context;

public class TrainingsOefening {
        private long trainingID;
        private long oefeningID;
        private int gewicht;
        private Context context;

    public TrainingsOefening(long trainingID, long oefeningID, int gewicht) {
            this.trainingID = trainingID;
            this.oefeningID = oefeningID;
            this.gewicht = gewicht;
        }

    public void setContext(Context context){
        this.context = context;
    }

    public long getTrainingID() {
        return trainingID;
    }

    public long getOefeningID() {
        return oefeningID;
    }

    public int getGewicht() {
        return gewicht;
    }

    /*public void VinkTrainingAf(){
        DatabaseHelper db = new DatabaseHelper(context);
        db.VinkOefeningAf(this);
    }*/
}
