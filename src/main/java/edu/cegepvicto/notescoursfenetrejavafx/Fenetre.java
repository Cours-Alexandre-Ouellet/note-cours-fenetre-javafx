package edu.cegepvicto.notescoursfenetrejavafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

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
     * Crée une nouvelle fenêtre qui s'affiche dans un Stage déjà existant.
     * @param stage le Stage à utiliser.
     * @param contexte Le conteneur de données du système.
     */
    public Fenetre(Stage stage, DonneesSysteme contexte) throws IOException{
        if(stage == null) {
            stage = new Stage();
        }

        // Assigne le stage
        stageUtilise = stage;
        this.contexte = contexte;

        // Création de la vue
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getNomFichierVue() + ".fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load(), getLargeur(), getHauteur());

        // Paramètres du stage
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

    /**
     * Charge une fenêtre et l'affiche. Si la fenêtre ne peut être chargée, alors un message d'erreur
     * est enregistré et la fenêtre retournée est nulle.
     * @param controleur le contrôleur de la fenêtre à charger.
     * @param stageUtilise le stage utilisé pour rendre la scene associé au contrôleur
     * @return l'instance du contrôleur créé.
     */
    private Fenetre chargerFenetre(Class<? extends Fenetre> controleur, Stage stageUtilise) {
        try {
            Constructor<? extends Fenetre> constructeur = controleur.getConstructor(Stage.class, DonneesSysteme.class);
            Fenetre fenetre = constructeur.newInstance(stageUtilise, contexte);
            return fenetre;
        } catch (InvocationTargetException exceptionControleur) {
            if(exceptionControleur.getCause() instanceof IOException exceptionFichier) {
                System.err.println("Impossible de charger le fichier de vue. \n" + exceptionFichier.getMessage());
            } else {
                System.err.println("Exception lors de l'exécution du contrôleur. Cause possible : mauvais nom de la vue." +
                        "\n" + exceptionControleur.getMessage());
            }
        } catch (NoSuchMethodException | SecurityException exceptionControleur) {
            System.err.println("Le controleur ne contient pas de constructeur qui accepte les paramètres de type 'Stage'" +
                    "et 'DonneesSysteme'.\n" + exceptionControleur.getMessage());
        } catch (InstantiationException | IllegalArgumentException | IllegalAccessException exceptionCreation) {
            System.err.println("Impossible d'instancier le contrôleur demandé.\n" + exceptionCreation.getMessage());
        }

        return null;
    }
}
