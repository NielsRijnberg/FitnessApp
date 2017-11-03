package Classes;

public class Product {
    private int productID;
    private String naam;
    private double kosten;
    private String omschrijving;

    public int getProductID() {
        return productID;
    }

    public String getNaam() {
        return naam;
    }

    public double getKosten() {
        return kosten;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    //Constructor zonder omschrijving
    public Product(int productID, String naam, double kosten) {
        this.productID = productID;
        this.naam = naam;
        this.kosten = kosten;
    }

    //Constructor met omschrijving
    public Product(int productID, String naam, double kosten, String omschrijving) {
        this.productID = productID;
        this.naam = naam;
        this.kosten = kosten;
        this.omschrijving = omschrijving;
    }

    @Override
    public String toString(){
        return naam;
    }
}
