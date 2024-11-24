package edu.cegepvicto.notescoursfenetrejavafx;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Fenêtre conçu pour remplacer le contenu d'une autre et permettre de revenir au menu principal.
 */
public class NouvelleFenetreDepControleur extends Fenetre {

    @Override
    protected String getNomFichierVue() {
        return "nouvelleFenetreDep";
    }

    @Override
    protected String getNomFenetre() {
        return "Fenêtre dépendante";
    }

    @Override
    protected int getLargeur() {
        return 300;
    }

    @Override
    protected int getHauteur() {
        return 150;
    }

    @Override
    protected boolean estMaximisee() {
        return false;
    }

    /**
     * Crée une nouvelle fenêtre dépendante qui contient seulement un bouton de retour.
     * @param stage la fenêtre à modifier.
     * @param contexte les données du système.
     */
    public NouvelleFenetreDepControleur(Stage stage, DonneesSysteme contexte) throws IOException {
        super(stage, contexte);
    }

    /**
     * Recharge le menu prinicipal de l'application.
     */
    @FXML
    private void retourMenu() {
        remplacerFenetre(MenuController.class);
    }
}
