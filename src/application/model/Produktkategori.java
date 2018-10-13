package application.model;

import java.util.ArrayList;

public class Produktkategori {
    private String navn;
    private Maalbar metrik;
    private ArrayList<Produkt> produkter = new ArrayList<>();

    public Produktkategori(String navn, Maalbar metrik) {
        this.navn = navn;
        this.metrik = metrik;
    }

    public Produkt createProdukt(String navn, int lagerAntal, double stoerrelse) {
        Produkt produkt = new Produkt(navn,lagerAntal,stoerrelse);
        this.addProdukt(produkt);
        return produkt;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Maalbar getMetrik() {
        return metrik;
    }

    public String getMetrikNavn() {
        return metrik.name();
    }

    public void setMetrik(Maalbar metrik) {
        this.metrik = metrik;
    }

    public ArrayList<Produkt> getProdukter() {
        return produkter;
    }

    public void setProdukter(ArrayList<Produkt> produkter) {
        this.produkter = produkter;
    }

    public void addProdukt(Produkt produkt) {
        this.produkter.add(produkt);
    }

    @Override
    public String toString() {
        return navn + " " + getMetrikNavn();
    }
}


