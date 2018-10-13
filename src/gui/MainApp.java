package gui;

import application.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) { launch(args); }

    @Override
    public void init() {
        Controller.initStorage();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Aarhus Bryghus Betalingssystem");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static Alert createAlert(String title, String headerText, Stage owner) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.initOwner(owner);
        alert.showAndWait();
        return alert;
    }

    public static Alert createWarAlert(String headerText, Stage owner) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(headerText);
        alert.initOwner(owner);
        alert.showAndWait();
        return alert;
    }

    public static Alert createErrAlert(String headerText, Stage owner) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(headerText);
        alert.initOwner(owner);
        alert.showAndWait();
        return alert;
    }

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab produktTab = new Tab("Produkt håndtering");
        ProduktPane produktPane = new ProduktPane();
        produktTab.setContent(produktPane);
        produktTab.setOnSelectionChanged(event -> produktPane.updateControls());


        tabPane.getTabs().addAll(produktTab);

    }
}


