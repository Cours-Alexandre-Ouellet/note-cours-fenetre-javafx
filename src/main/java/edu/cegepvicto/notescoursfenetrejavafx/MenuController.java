package edu.cegepvicto.notescoursfenetrejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Contrôleur principal du menu de la démonstration.
 */
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

    /**
     * Saisie une donnée qui est passée aux autres interfaces.
     */
    @FXML
    private TextField saisieDonnees;

    /**
     * Texte de confirmation pour la fenêtre modale.
     */
    @FXML
    private Label texteConfirmation;

    /**
     *
     */
    @FXML
    private Label nomFichier;

    /**
     * Crée un nouveau contrôleur pour la fenêtre du menu.
     * @param stage le stage qui affiche le menu.
     * @param donneesSysteme les données du système qui
     * @throws IOException si le fichier de vue ne peut être chargé.
     */
    public MenuController(Stage stage, DonneesSysteme donneesSysteme) throws IOException {
        super(stage, donneesSysteme);
    }

    /**
     * Initialise les contrôles de la fenêtre
     */
    @FXML
    private void initialize() {
        saisieDonnees.textProperty().addListener((valeur, ancienne, nouvelle) -> {
            contexte.setParametre(nouvelle);
        });
    }

    /**
     * Crée un nouvelle fenêtre flottante
     */
    @FXML
    private void nouvelleFenetre()
    {
        ouvrirFenetre(NouvelleFenetreIndepControleur.class);
    }

    /**
     * Remplace le contenu de la fenêtre actuelle.
     */
    @FXML
    private void changerContenu()
    {
        remplacerFenetre(NouvelleFenetreDepControleur.class);
    }

    /**
     * Affiche une fenêtre modale de confirmation.
     */
    @FXML
    private void fenetreConfirmation() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Êtes-vous sûr ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> resultat = confirmation.showAndWait();
        // On a appuyé sur le bouton
        if(resultat.isPresent()) {
            if(resultat.get() == ButtonType.YES) {
                texteConfirmation.setText("Oui");
            } else if (resultat.get() == ButtonType.NO) {
                texteConfirmation.setText("Non");
            }
        }
    }

    /**
     * Permet de choisir un fichier.
     */
    @FXML
    private void choisirFichier() {
        FileChooser selectionFichier = new FileChooser();
        selectionFichier.setTitle("Choississez un fichier");
        // Pointe sur Mes Documents de l'utilisateur actuel
        selectionFichier.setInitialDirectory(new File(System.getProperty("user.home") + File.separator + "documents"));
        selectionFichier.getExtensionFilters().addAll(
                new ExtensionFilter("Word", "*.doc", "*.docx"),
                new ExtensionFilter("Excel", "*.xls", "*.xlsx"),
                new ExtensionFilter("Word", "*.ppt", "*.pptx"),
                new ExtensionFilter("Tout les fichiers", "*.*")
        );

        nomFichier.setText(selectionFichier.showOpenDialog(stageUtilise).getAbsolutePath());

    }
}