package application.model;

public class Ordrelinje {
    private Produkt produkt;
    private double produktPris;
    private int samletOrdrelinjePris;
    private int antal;
    private Ordre ordre;

    public Ordrelinje(Produkt produkt, double produktPris, int antal, Ordre ordre) {
        this.produkt = produkt;
        this.produktPris = produktPris;
        this.antal = antal;
        this.ordre = ordre;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public double getProduktPris() {
        return produktPris;
    }

    public void setProduktPris(int produktPris) {
        this.produktPris = produktPris;
    }

    public int getSamletOrdrelinjePris() {
        return samletOrdrelinjePris;
    }

    public void setSamletOrdrelinjePris(int samletOrdrelinjePris) {
        this.samletOrdrelinjePris = samletOrdrelinjePris;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public Ordre getOrdre() {
        return ordre;
    }

    public void setOrdre(Ordre ordre) {
        this.ordre = ordre;
    }

    public double beregnSamletOrdrelinjePris() {
        return antal * produktPris;
    }
}


