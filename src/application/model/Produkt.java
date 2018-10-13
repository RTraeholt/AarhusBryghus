package application.model;

public class Produkt {
    private String navn;
    private int lagerAntal;
    private double stoerrelse;

    public Produkt(String navn, int lagerAntal, double stoerrelse) {
        this.navn = navn;
        this.lagerAntal = lagerAntal;
        this.stoerrelse = stoerrelse;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getLagerAntal() {
        return lagerAntal;
    }

    public void setLagerAntal(int lagerAntal) {
        this.lagerAntal = lagerAntal;
    }

    public double getStoerrelse() {
        return stoerrelse;
    }

    public void setStoerrelse(double stoerrelse) {
        this.stoerrelse = stoerrelse;
    }

    @Override
    public String toString() {
        return navn + " " + stoerrelse;
    }
}


