package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    public static Produktkategori opretProduktkategori(String navn, Maalbar metrik) {
        Produktkategori pk = new Produktkategori(navn, metrik);
        Storage.storeProduktkategori(pk);
        return pk;
    }

    public static void updateProduktkategori(Produktkategori pk, String navn, Maalbar metrik) {
        pk.setNavn(navn);
        pk.setMetrik(metrik);
    }

    public static String deleteProduktkategori(Produktkategori pk) {
        String msg = "Produktkategorien er slettet!";
        try {
            if(pk.getProdukter().isEmpty()) {
                Storage.removeProduktkategori(pk);
            } else {
                throw new Exception("Denne produktkategori indeholder produkter og kan ikke slettes");
            }
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    public static ArrayList<Produktkategori> getProduktkategorier() {
        return Storage.getProduktkategorier();
    }

    public static Produkt opreProdukt(Produktkategori pk, String navn, int antal, double stoerrelse) {
        Produkt p = pk.createProdukt(navn, antal,stoerrelse);
        return p;
    }

    public static void updateProdukt(Produkt produkt, Produktkategori pk, String navn, int antal, double stoerrelse) {
        produkt.setNavn(navn);
        produkt.setLagerAntal(antal);
        produkt.setStoerrelse(stoerrelse);
        produkt.getProduktkategori().getProdukter().remove(produkt);
        produkt.setProduktkategori(pk);
        pk.addProdukt(produkt);

    }

    public static ArrayList<Produkt> getProdukter(Produktkategori pk) {
        return pk.getProdukter();
    }
    //
    public static Prisliste opretPrisliste(String navn) {
        Prisliste pl = new Prisliste(navn);
        Storage.storePrisliste(pl);
        return pl;
    }

    public static ArrayList<Prisliste> getPrislister() {
        return Storage.getPrislister();
    }

    public static void setProduktPriser(HashMap<Produkt,Double> produktPriser, Prisliste pl) {
        pl.setProduktPriser(produktPriser);
    }

    public static void removeProduktPris(Prisliste pl, Produkt produkt) {
        if (pl.getProduktPriser().containsKey(produkt)) {
            pl.getProduktPriser().remove(produkt);
            System.out.println(produkt + " Removed from " + pl);
        }
    }

    public static String addProduktPris(Prisliste pl, Produkt produkt, double pris){
        String msg = "Succes!";

        try {
            if (!pl.getProduktPriser().containsKey(produkt)) {
                pl.addProduktPris(produkt,pris);
                System.out.println(produkt + "added to: " + pl);
            } else {
                throw new Exception("Dette produkt eksistere allerede på denne prisliste");
            }
        } catch (Exception e) {
            msg = e.getMessage();
        }

        return msg;
    }

    public static Ordre opretOrdre(boolean isBetalt, LocalDate oprettelsesDato) {
        Ordre o = new Ordre(isBetalt,oprettelsesDato);
        Storage.storeOrdre(o);
        return o;
    }

    public static Ordrelinje opretOrdrelinje(Produkt produkt, double produktPris, int antal, Ordre ordre) {
        Ordrelinje ol = ordre.createOrdrelinje(produkt,produktPris,antal);
        return ol;
    }

    public static void initStorage() {
        Produktkategori pk1 = Controller.opretProduktkategori("Flaskeøl", Maalbar.CL);
        Produktkategori pk2 = Controller.opretProduktkategori("Fadøl", Maalbar.CL);

        Produkt p1 = Controller.opreProdukt(pk1,"Klosterbryg",100,50);
        Produkt p2 = Controller.opreProdukt(pk1,"Pilsner Extra",100,50);
        Produkt p3 = Controller.opreProdukt(pk2,"Klosterbryg",100,40);
        Produkt p4 = Controller.opreProdukt(pk2,"Pilsner Extra",100,40);

        Prisliste pl1 = Controller.opretPrisliste("Butik");
        Prisliste pl2 = Controller.opretPrisliste("Fredagsbar");

        Controller.addProduktPris(pl1,p1,50);
        Controller.addProduktPris(pl1,p2,50);
        Controller.addProduktPris(pl2,p1,35);
        Controller.addProduktPris(pl2,p2,35);
        Controller.addProduktPris(pl2,p3,30);
        Controller.addProduktPris(pl2,p4,30);

        Ordre o1 = Controller.opretOrdre(false,LocalDate.now());

        Ordrelinje ol1 = Controller.opretOrdrelinje(p1,pl1.getProduktPriser().get(p1), 5, o1);
    }
}

