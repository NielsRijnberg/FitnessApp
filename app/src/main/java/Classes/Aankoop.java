package Classes;

import java.util.Date;

public class Aankoop {
    private int aankoopID;
    private int aantal;
    private Date datum;
    private Product product;

    public int getAankoopID() {
        return aankoopID;
    }

    public int getAantal() {
        return aantal;
    }

    public Date getDatum() {
        return datum;
    }

    public Product getProduct() {
        return product;
    }

    public Aankoop(int aankoopID, int aantal, Date datum, Product product) {

        this.aankoopID = aankoopID;
        this.aantal = aantal;
        this.datum = datum;
        this.product = product;
    }
}
