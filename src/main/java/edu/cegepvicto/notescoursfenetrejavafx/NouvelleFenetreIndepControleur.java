package edu.cegepvicto.notescoursfenetrejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur d'une nouvelle fenêtre indépendante (avec son propre stage)
 */
public class NouvelleFenetreIndepControleur extends Fenetre {

    @Override
    protected String getNomFichierVue() {
        return "nouvelleFenetreIndep";
    }

    @Override
    protected String getNomFenetre() {
        return "Nouvelle fenêtre";
    }

    @Override
    protected int getLargeur() {
        return 200;
    }

    @Override
    protected int getHauteur() {
        return 200;
    }

    @Override
    protected boolean estMaximisee() {
        return false;
    }

    /**
     * Affiche le contenu du contexte.
     */
    @FXML
    private Label affichageParametre;

    /**
     * Crée une nouvelle fenêtre secondaire
     * @param stage la fenêtre à modifier.
     * @param contexte les données du système.
     * @throws IOException si le fichier de vue n'est pas trouvé.
     */
    public NouvelleFenetreIndepControleur(Stage stage, DonneesSysteme contexte) throws IOException {
        super(stage, contexte);
    }

    /**
     * Intialise le contenu de la fenêtre.
     */
    @FXML
    private void initialize() {
        affichageParametre.setText(contexte.getParametre());
    }

    /**
     * Action du bouton de fermer la fenêtre
     */
    @FXML
    private void fermerFenetre() {
        stageUtilise.close();
    }
}