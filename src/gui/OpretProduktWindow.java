package gui;

import application.controller.Controller;
import application.model.Prisliste;
import application.model.Produkt;
import application.model.Produktkategori;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import storage.Storage;

import java.util.ArrayList;
import java.util.HashMap;

public class OpretProduktWindow extends Stage {
    private Produktkategori produktkategori;
    private Produkt produkt;
    private HashMap<Prisliste, Double> priserToAdd = new HashMap<>();

    public OpretProduktWindow(String title, Produktkategori produktkategori, Produkt produkt) {
        this.produktkategori = produktkategori;
        this.produkt = produkt;
        this.initStyle(StageStyle.UNIFIED);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public OpretProduktWindow(String title) {
        this(title, null, null);
    }

    private Label lblNavn, lblStoerrelse, lblLagerAntal, lblPk, lblPl1, lblPl2, lblPris;
    private TextField txfNavn, txfStoerrelse, txfLagerAntal, txfPris;
    private ListView<Prisliste> lwPl1, lwPl2;
    private ListView<Produkt> lwvProdukter;
    private Button btnAdd, btnRemove, btnOk, btnCancel;
    private ComboBox<Produktkategori> cbPk;
    private HBox hbox, hb2;
    private VBox vb1, vb2,vb3,vb4,vb5;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(20);

        lblNavn = new Label("Angiv navn:");
        lblStoerrelse = new Label("Angiv størrelse:");
        lblPk = new Label("Vælg produktkategori:");
        lblLagerAntal = new Label("Angiv lagerantal:");
        lblPl1 = new Label("Mulige prislister:");
        lblPl2 = new Label("Valgte prislister:");
        lblPris = new Label("Angiv pris (DKK):");
        txfNavn = new TextField();
        txfStoerrelse = new TextField();
        txfLagerAntal = new TextField();
        txfPris = new TextField();
        cbPk = new ComboBox<>();
        cbPk.getItems().addAll(Storage.getProduktkategorier());
        lwPl1 = new ListView<>();
        lwPl1.getItems().addAll(Storage.getPrislister());
        lwPl2 = new ListView<>();
        //lwvProdukter = new ListView<>();

        btnAdd = new Button("Tilføj");
        btnAdd.setOnAction(event -> this.addAction());
        btnAdd.setMaxWidth(Double.MAX_VALUE);

        btnRemove = new Button("Fjern");
        btnRemove.setOnAction(event -> this.removeAction());
        btnRemove.setMaxWidth(Double.MAX_VALUE);

        btnOk = new Button("Ok");
        btnOk.setOnAction(event -> this.okAction());
        btnOk.setMaxWidth(Double.MAX_VALUE);

        btnCancel = new Button("Cancel");
        btnCancel.setOnAction(event -> this.cancelAction());
        btnCancel.setMaxWidth(Double.MAX_VALUE);

        vb1 = new VBox(10);
        pane.add(vb1,0,0,1,1);
        vb1.getChildren().addAll(lblNavn,txfNavn,lblStoerrelse,txfStoerrelse);

        vb2 = new VBox(10);
        pane.add(vb2,3,0,1,1);
        vb2.getChildren().addAll(lblLagerAntal,txfLagerAntal,lblPk,cbPk);

        vb3 = new VBox(10);
        vb3.getChildren().addAll(lblPl1,lwPl1);

        vb4 = new VBox(10);
        vb4.getChildren().addAll(lblPris,txfPris,btnAdd,btnRemove);

        vb5 = new VBox(10);
        vb5.getChildren().addAll(lblPl2,lwPl2);

        hb2 = new HBox(20);
        pane.add(hb2,0,2);
        hb2.getChildren().addAll(vb3,vb4,vb5);

        hbox = new HBox(20);
        pane.add(hbox,0,5);
        hbox.getChildren().addAll(btnOk, btnCancel);
        hbox.setAlignment(Pos.CENTER);
        hbox.setHgrow(btnOk, Priority.ALWAYS);
        hbox.setHgrow(btnCancel, Priority.ALWAYS);

        ChangeListener<Prisliste> l1 = (op, oldObj, newObj) -> this.selectedPrislisteChanged();
        lwPl2.getSelectionModel().selectedItemProperty().addListener(l1);

        initControls();
    }

    private void selectedPrislisteChanged() {
        Prisliste selectedPl = lwPl2.getSelectionModel().getSelectedItem();
        if (selectedPl != null) {
            txfPris.setText("" + selectedPl.getProduktPriser().get(produkt));
            //lwvProdukter.getItems().addAll(selectedPl.getProduktPriser().keySet());
        }
    }

    private void okAction() {
        Produktkategori pk = cbPk.getSelectionModel().getSelectedItem();
        String navn = txfNavn.getText();
        double stoerrelse = -1;
        int lagerAntal = -1;

        if (navn.length() < 1) {
            MainApp.createErrAlert("Der skal angives et produktnavn", (Stage)this.getScene().getWindow());
            return;
        }

        try {
            stoerrelse = Double.parseDouble(txfStoerrelse.getText().trim());
        } catch (NumberFormatException e) {
            MainApp.createErrAlert("Størrelse skal angives decimal eller heltal", (Stage)this.getScene().getWindow());
            return;
        }

        try {
            lagerAntal = Integer.parseInt(txfLagerAntal.getText().trim());
        } catch (NumberFormatException e) {
            MainApp.createErrAlert("Lagerantal skal angives som heltal",(Stage)this.getScene().getWindow());
            return;
        }

        if (pk == null) {
            MainApp.createErrAlert("Der skal angives en produktkategori",(Stage)this.getScene().getWindow());
            return;
        }

        if (produkt != null) {
            for (Prisliste pl : Controller.getPrislister()) {
                Controller.removeProduktPris(pl,produkt);
            }
            for (Prisliste key: priserToAdd.keySet()) {
                Controller.addProduktPris(key,produkt,priserToAdd.get(key));
            }
            Controller.updateProdukt(produkt,pk,navn,lagerAntal,stoerrelse);

            this.hide();
        } else {
            Produkt nytProdukt = Controller.opreProdukt(pk,navn,lagerAntal,stoerrelse);
            for (Prisliste key: priserToAdd.keySet()) {
                Controller.addProduktPris(key,nytProdukt,priserToAdd.get(key));
            }

            this.hide();
        }

    }

    private void cancelAction() {
        this.hide();
    }

    private void addAction() {
        double pris = -1;
        Prisliste pl = lwPl1.getSelectionModel().getSelectedItem();

        if (pl != null) {
            try {
                pris = Double.parseDouble(txfPris.getText().trim());
            } catch (NumberFormatException e) {
                MainApp.createErrAlert("Pris skal angives som decimal eller heltal",(Stage)this.getScene().getWindow());
                return;
            }
            if (!priserToAdd.containsKey(pl)) {
                priserToAdd.put(pl, pris);
                lwPl2.getItems().add(pl);
            } else {
                MainApp.createErrAlert("Produktet eksistere allerede på denne prisliste",(Stage)this.getScene().getWindow());
            }


        } else {
            MainApp.createErrAlert("Ups - der er ikke valgt en prisliste",(Stage)this.getScene().getWindow());
        }
    }

    private void removeAction() {
        Prisliste pl = lwPl2.getSelectionModel().getSelectedItem();

        if (pl != null) {
            priserToAdd.remove(pl);
            lwPl2.getItems().remove(pl);
        } else {
            MainApp.createErrAlert("Ups - der er ikke valgt en prisliste",(Stage)this.getScene().getWindow());
        }
    }

    private void initControls() {
        if (produkt != null) {
            txfNavn.setText(produkt.getNavn());
            txfStoerrelse.setText("" + produkt.getStoerrelse());
            txfLagerAntal.setText("" + produkt.getLagerAntal());
            cbPk.setValue(produktkategori);
            for (Prisliste pl : Controller.getPrislister()) {
                if (pl.getProduktPriser().containsKey(produkt)) {
                    priserToAdd.put(pl,pl.getProduktPriser().get(produkt));
                    lwPl2.getItems().add(pl);
                }
            }
        }
    }
}


