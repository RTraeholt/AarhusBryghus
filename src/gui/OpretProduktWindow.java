package gui;

import application.model.Produkt;
import application.model.Produktkategori;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OpretProduktWindow extends Stage {
    private Produktkategori produktkategori;
    private Produkt produkt;

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

    private void initContent(GridPane pane) {

    }
}


