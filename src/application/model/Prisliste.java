package application.model;

import java.util.HashMap;

public class Prisliste {
    private String navn;
    private HashMap<Produkt, Double> produktPriser = new HashMap<>();

    public Prisliste(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public HashMap<Produkt, Double> getProduktPriser() {
        return new HashMap<>(produktPriser);
    }

    public void setProduktPriser(HashMap<Produkt, Double> produktPriser) {
        this.produktPriser = produktPriser;
    }

    public void addProduktPris(Produkt produkt, double pris) {
        this.produktPriser.put(produkt,pris);
    }

    @Override
    public String toString() {
        return navn;
    }
}


