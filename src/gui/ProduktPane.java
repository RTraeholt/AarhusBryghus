package gui;

import application.controller.Controller;
import application.model.Produkt;
import application.model.Produktkategori;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;


public class ProduktPane extends GridPane {
    private Label lblPk,lblP;
    private ListView<Produktkategori> lvwPk;
    private ListView<Produkt> lvwP;
    private Button btnOpretPk, btnRedigerPk, btnSletPk, btnOpretP,btnRedigerP,btnSletP;
    private VBox vbPk, vbP;


    public ProduktPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(20);

        ChangeListener<Produktkategori> listener = (ov,oldObj,newObj) -> this.selectedPkChanged();

        lblPk = new Label("Produktkategorier");
        lblP = new Label("Produkter");
        lvwPk = new ListView<>();
        lvwPk.getItems().addAll(Controller.getProduktkategorier());
        lvwPk.getSelectionModel().selectedItemProperty().addListener(listener);
        lvwP = new ListView<>();

        btnOpretPk = new Button("Opret");
        btnOpretPk.setMaxWidth(Double.MAX_VALUE);
        btnOpretPk.setOnAction(event -> this.createPkAction());

        btnRedigerPk = new Button("Rediger");
        btnRedigerPk.setMaxWidth(Double.MAX_VALUE);
        btnRedigerPk.setOnAction(event -> this.updatePkAction());

        btnSletPk = new Button("Slet");
        btnSletPk.setMaxWidth(Double.MAX_VALUE);
        btnSletPk.setOnAction(event -> this.deletePkAction());

        btnOpretP = new Button("Opret");
        btnOpretP.setMaxWidth(Double.MAX_VALUE);
        btnOpretP.setOnAction(event -> this.createPAction());

        btnRedigerP = new Button("Rediger");
        btnRedigerP.setMaxWidth(Double.MAX_VALUE);
        btnRedigerP.setOnAction(event -> this.updatePAction());

        btnSletP = new Button("Slet");
        btnSletP.setMaxWidth(Double.MAX_VALUE);
        btnSletP.setOnAction(event -> this.deletePAction());

        vbPk = new VBox(10);
        this.add(vbPk,0,0);
        vbPk.getChildren().addAll(lblPk,lvwPk,btnOpretPk,btnRedigerPk,btnSletPk);
        vbPk.setAlignment(Pos.CENTER_LEFT);

        vbP = new VBox(10);
        this.add(vbP,3,0);
        vbP.getChildren().addAll(lblP,lvwP,btnOpretP,btnRedigerP,btnSletP);
        vbP.setAlignment(Pos.CENTER_LEFT);

        this.updateControls();
    }

    public void updateControls() {
        lvwPk.getItems().clear();
        lvwPk.getItems().addAll(Controller.getProduktkategorier());
    }

    private void updateControlsFromSelected() {
        int selectedIndex = lvwPk.getSelectionModel().getSelectedIndex();
        lvwPk.getSelectionModel().select(selectedIndex);
        Produktkategori pk = lvwPk.getSelectionModel().getSelectedItem();
        lvwP.getItems().clear();;
        lvwP.getItems().addAll(Controller.getProdukter(pk));
    }

    private void selectedPkChanged() {
        Produktkategori pk = lvwPk.getSelectionModel().getSelectedItem();
        if(pk != null) {
            lvwP.getItems().addAll(Controller.getProdukter(pk));
            updateControlsFromSelected();
        }
    }

    private void createPkAction() {
        OpretProduktkategoriWindow win = new OpretProduktkategoriWindow("Opret ny produktkategori");
        win.showAndWait();
        updateControls();
    }
    private void updatePkAction() {
        Produktkategori pk = lvwPk.getSelectionModel().getSelectedItem();
        if (pk != null) {
            OpretProduktkategoriWindow win = new OpretProduktkategoriWindow("Opret ny produktkategori", pk);
            win.showAndWait();
            updateControls();
        } else {
            MainApp.createErrAlert("Err!",(Stage)this.getScene().getWindow());
        }

    }
    private void deletePkAction() {
        Produktkategori pk = lvwPk.getSelectionModel().getSelectedItem();
        String msg = "";
        if(pk != null) {
            Alert alert = MainApp.createWarAlert("Er du sikker på at du vil slette denne produktkategori: " + pk.getNavn() + "?", (Stage)this.getScene().getWindow());
            Optional<ButtonType> res = alert.showAndWait();

            if (res.isPresent() && res.get() == ButtonType.OK) {
                msg = Controller.deleteProduktkategori(pk);
                updateControls();
                MainApp.createWarAlert(msg,(Stage)this.getScene().getWindow());
            }
        } else {
            MainApp.createErrAlert("OBS! ingen produktkategori er valgt",(Stage)this.getScene().getWindow());
        }
    }
    private void createPAction() {}
    private void updatePAction() {}
    private void deletePAction() {}

}


