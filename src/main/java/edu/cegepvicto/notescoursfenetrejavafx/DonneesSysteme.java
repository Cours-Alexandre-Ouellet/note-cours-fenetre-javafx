package edu.cegepvicto.notescoursfenetrejavafx;

/**
 * Représente les données du système partagées entre les vues.
 *
 * Cette classe peut devenir très complexes selon la nature de l'application
 * et agir comme médiateur entre des parties du système.
 */
public class DonneesSysteme {

    /**
     * Le paramètre passé par le système.
     */
    private String parametre;

    /**
     * Retourne la valeur du paramètre.
     * @return la valeur du paramètre.
     */
    public String getParametre() {
        return parametre;
    }

    /**
     * Modifie la valeur du paramètre.
     * @param parametre la valeur du paramètre.
     */
    public void setParametre(String parametre) {
        this.parametre = parametre;
    }
}
