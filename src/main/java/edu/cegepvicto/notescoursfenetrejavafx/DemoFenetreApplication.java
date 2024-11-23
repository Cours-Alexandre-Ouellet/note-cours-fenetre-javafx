package edu.cegepvicto.notescoursfenetrejavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DemoFenetreApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       new MenuController(stage, new DonneesSysteme());
    }

    public static void main(String[] args) {
        launch();
    }
}