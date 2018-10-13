package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Ordre {
    private static int ordreNr;
    private boolean isBetalt;
    private LocalDate oprettelsesDato;
    private double samletOrdrePris;
    private Sellable betalingsmetode;
    private ArrayList<Ordrelinje> ordrelinjer = new ArrayList<>();

    public Ordre(boolean isBetalt, LocalDate oprettelsesDato) {
        this.isBetalt = isBetalt;
        this.oprettelsesDato = oprettelsesDato;
    }

    public Ordrelinje createOrdrelinje(Produkt produkt, double produktPris, int antal) {
        Ordrelinje ol = new Ordrelinje(produkt, produktPris, antal, this);
        this.addOrdrelinje(ol);
        return ol;
    }

    public static int getOrdreNr() {
        return ordreNr;
    }

    public static void setOrdreNr(int ordreNr) {
        Ordre.ordreNr = ordreNr;
    }

    public boolean isBetalt() {
        return isBetalt;
    }

    public void setBetalt(boolean betalt) {
        isBetalt = betalt;
    }

    public LocalDate getOprettelsesDato() {
        return oprettelsesDato;
    }

    public void setOprettelsesDato(LocalDate oprettelsesDato) {
        this.oprettelsesDato = oprettelsesDato;
    }

    public double getSamletOrdrePris() {
        return samletOrdrePris;
    }

    public void setSamletOrdrePris(double samletOrdrePris) {
        this.samletOrdrePris = samletOrdrePris;
    }

    public Sellable getBetalingsmetode() {
        return betalingsmetode;
    }

    public void setBetalingsmetode(Sellable betalingsmetode) {
        this.betalingsmetode = betalingsmetode;
    }

    public ArrayList<Ordrelinje> getOrdrelinjer() {
        return ordrelinjer;
    }

    public void setOrdrelinjer(ArrayList<Ordrelinje> ordrelinjer) {
        this.ordrelinjer = ordrelinjer;
    }

    public void addOrdrelinje(Ordrelinje ordrelinje) {
        this.ordrelinjer.add(ordrelinje);
    }
}

