package gui;

import application.controller.Controller;
import application.model.Maalbar;
import application.model.Produktkategori;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OpretProduktkategoriWindow extends Stage {
    private Produktkategori produktkategori;

    public OpretProduktkategoriWindow(String title, Produktkategori produktkategori) {
        this.produktkategori = produktkategori;
        this.initStyle(StageStyle.UNIFIED);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public OpretProduktkategoriWindow(String title) {
        this(title, null);
    }

    private Label lblNavn, lblMetrik;
    private TextField txfNavn;
    private ComboBox<Maalbar> cbxMetrik;
    private Button btnOk, btnCancel;
    private HBox hb1, hb2, hb3;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(20);

        lblNavn = new Label("Angiv navn:");
        lblMetrik = new Label("Vælg metrik:");
        txfNavn = new TextField();
        cbxMetrik = new ComboBox<>();
        cbxMetrik.getItems().addAll(Maalbar.values());

        btnOk = new Button("Ok");
        btnOk.setMaxWidth(Double.MAX_VALUE);
        btnOk.setOnAction(event -> this.okAction());

        btnCancel = new Button("Annuller");
        btnCancel.setMaxWidth(Double.MAX_VALUE);
        btnCancel.setOnAction(event -> this.cancelAction());

        hb1 = new HBox(5);
        pane.add(hb1,0,0);
        hb1.getChildren().addAll(lblNavn,txfNavn);
        hb1.setAlignment(Pos.CENTER_LEFT);

        hb2 = new HBox(5);
        pane.add(hb2,0,1);
        hb2.getChildren().addAll(lblMetrik,cbxMetrik);
        hb2.setAlignment(Pos.CENTER_LEFT);

        hb3 = new HBox(20);
        pane.add(hb3,0,4);
        hb3.getChildren().addAll(btnOk,btnCancel);
        hb3.setAlignment(Pos.CENTER_LEFT);

        initControls();
    }

    private void okAction() {
        String navn = txfNavn.getText();
        Maalbar metrik = cbxMetrik.getSelectionModel().getSelectedItem();

        if (navn.length() < 1) {
            MainApp.createErrAlert("OBS! Der skal angives et navn",(Stage)this.getScene().getWindow());
            return;
        }

        if (metrik == null) {
            MainApp.createErrAlert("OBS! Der skal vælges en metrik",(Stage)this.getScene().getWindow());
            return;
        }

        if (produktkategori != null) {
            Controller.updateProduktkategori(produktkategori,navn,metrik);
            this.hide();
        } else {
            Controller.opretProduktkategori(navn,metrik);
            this.hide();
        }
    }
    private void cancelAction() {
        this.hide();
    }

    private void initControls() {
        if (produktkategori != null) {
            txfNavn.setText(produktkategori.getNavn());
            cbxMetrik.setValue(produktkategori.getMetrik());
        }
    }
}


