package edu.cegepvicto.notescoursfenetrejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SousFenetreControleur {

    /**
     * Nombre de clic sur le bouton
     */
    private int nombreClic;

    /**
     * Boîte qui affiche le message
     */
    @FXML
    private Label compteur;

    /**
     * Change l'affichage du message
     */
    @FXML
    private void incrementerCompteur() {
        nombreClic++;
        compteur.setText(nombreClic + "");
    }

}
