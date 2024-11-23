package edu.cegepvicto.notescoursfenetrejavafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Assure la gestion d'un fenêtre en permettant de l'ouvrir, de la fermer
 * et de transmettre des données vers une autre fenêtre.
 */
public abstract class Fenetre {

    /**
     * Le stage dans lequelle la fenêtre s'affiche.
     */
    protected Stage stageUtilise;

    /**
     * Le contexte de données du système
     */
    protected DonneesSysteme contexte;

    /**
     * Retourne le nom du fichier de vue utilisée pour rendre cette fenêtre sans l'extension
     * du nom de fichier.
     *
     * @return le nom du fichier de vue à partir du nom de package de la classe
     */
    protected abstract String getNomFichierVue();

    /**
     * Retourne le nom de la fenêtre.
     *
     * @return le nom de la fenêtre.
     */
    protected abstract String getNomFenetre();

    /**
     * Retourne la largeur à utiliser pour la fenêtre.
     *
     * @return la largeur de la fenêtre.
     */
    protected abstract int getLargeur();

    /**
     * Retourne la hauteur à utiliser pour la fenêtre.
     *
     * @return la hauteur de la fenêtre.
     */
    protected abstract int getHauteur();

    /**
     * Indique si la fenêtre doit être ouverte en mode maximiser ou non.
     *
     * @return si la fenêtre est ouverte maximisée ou non.
     */
    protected abstract boolean estMaximisee();

    /**
     * Crée une nouvelle fenêtre qui s'affiche dans un nouveau Stage.
     * @param contexte Le conteneur de données du système.
     */
    public Fenetre(DonneesSysteme contexte) throws IOException {
        this(null, contexte);
    }

    /**
     * Crée une nouvelle fenêtre qui s'affiche dans un Stage déjà existant.
     * @param stage le Stage à utiliser.
     * @param contexte Le conteneur de données du système.
     */
    public Fenetre(Stage stage, DonneesSysteme contexte) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getNomFichierVue() + ".fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load(), getLargeur(), getHauteur());

        if(stage == null) {
            stage = new Stage();
        }

        // Assigne le stage
        stageUtilise = stage;
        this.contexte = contexte;

        stage.setMaximized(estMaximisee());
        stage.setTitle(getNomFenetre());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Ouvre une nouvelle fenêtre liée au contrôleur spécifié.
     * @param controleur le contrôleur du nouveau contenu à afficher.
     */
    protected Fenetre ouvrirFenetre(Class<? extends Fenetre> controleur) {
        return chargerFenetre(controleur, null);
    }

    /**
     * Remplace le contenu de la fenêtre avec celui lié au contrôleur spécifié.
     * @param controleur le contrôleur du nouveau contenu à afficher.
     */
    protected Fenetre remplacerFenetre(Class<? extends Fenetre> controleur) {
        return chargerFenetre(controleur, stageUtilise);
    }

    private Fenetre chargerFenetre(Class<? extends Fenetre> controleur, Stage stageUtilise) {
        try {
            Constructor<? extends Fenetre> constructeur = controleur.getConstructor(Stage.class, DonneesSysteme.class);
            Fenetre fenetre = constructeur.newInstance(stageUtilise, contexte);
            return fenetre;
        } catch (InvocationTargetException exceptionFichierInnexistant) {
            if(exceptionFichierInnexistant.getCause() instanceof IOException) {

            } else {

            }
        } catch (NoSuchMethodException | SecurityException exceptionControleur) {

        } catch (InstantiationException | IllegalArgumentException | IllegalAccessException exceptionCreation) {

        }

        return null;
    }
}
