package storage;

import application.model.Ordre;
import application.model.Prisliste;
import application.model.Produktkategori;

import java.util.ArrayList;

public class Storage {
    private static ArrayList<Produktkategori> produktkategorier = new ArrayList<>();
    private static ArrayList<Prisliste> prislister = new ArrayList<>();
    private static ArrayList<Ordre> ordre = new ArrayList<>();

    public static void storeProduktkategori(Produktkategori pk) {
        produktkategorier.add(pk);
    }

    public static void removeProduktkategori(Produktkategori pk) {
        produktkategorier.remove(pk);
    }

    public static ArrayList<Produktkategori> getProduktkategorier() {
        return new ArrayList<>(produktkategorier);
    }

    public static void storePrisliste(Prisliste pl) {
        prislister.add(pl);
    }

    public static void removePrisliste(Prisliste pl) {
        prislister.remove(pl);
    }

    public static ArrayList<Prisliste> getPrislister() {
        return new ArrayList<>(prislister);
    }

    public static void storeOrdre(Ordre o){
        ordre.add(o);
    }

    public static void removeOrdre(Ordre o) {
        ordre.remove(o);
    }

    public static ArrayList<Ordre> getOrdre() {
        return new ArrayList<>(ordre);
    }
}
