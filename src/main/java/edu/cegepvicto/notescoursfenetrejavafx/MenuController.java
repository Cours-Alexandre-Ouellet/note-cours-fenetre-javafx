package edu.cegepvicto.notescoursfenetrejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController extends Fenetre {

    @Override
    protected String getNomFichierVue() {
        return "menu";
    }

    @Override
    protected String getNomFenetre() {
        return "Menu de la démonstration";
    }

    @Override
    protected int getLargeur() {
        return 400;
    }

    @Override
    protected int getHauteur() {
        return 600;
    }

    @Override
    protected boolean estMaximisee() {
        return false;
    }

    @FXML
    private TextField saisieDonnees;

    public MenuController(Stage stage, DonneesSysteme donneesSysteme) throws IOException {
        super(stage, donneesSysteme);
    }

    @FXML
    private void nouvelleFenetre()
    {

    }

    @FXML
    private void nouvelleFenetreDessus()
    {

    }

    @FXML
    private void changerContenu()
    {

    }


}