package practica_uf4.model;

import java.io.*;
import java.net.*;

/**
 * Classe per enviar els diferents missatges entre el jugador i el servidor.
 */
public class Missatge implements Serializable {

    String[] array = new String[13];
    char[][] taulerUsuari = new char[10][10];
    String moviment;
    boolean finalitzat;

    /**
     * Constructor de la classe missatge
     * @param visible referent al tauler del jugador
     */
    public Missatge(char[][] visible) {
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                taulerUsuari[i][j] = visible[i][j];
            }
        }
    }

    /**
     * Per obtenir l'array amb les posicions inicials dels vaixells.
     * @return l'array de Strings amb les posicions.
     */
    public String[] getArray() {
        return array;
    }

    /**
     * Funcio per passar l'array de les posicions.
     * @param array referent a l'array amb les posicions
     */
    public void setArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }
    }

    /**
     * Funcio per obtenir el taulell.
     * @return char bidimensional del tauler de l'usuari
     */
    public char[][] getTaulerUsuari() {
        return taulerUsuari;
    }

    /**
     * Funcio per passar el tauler de l'usuari.
     * @param taulerUsuari referent a la taula propia de l'usuari.
     */
    public void setTaulerUsuari(char[][] taulerUsuari) {
        this.taulerUsuari = taulerUsuari;
    }

    /**
     * Funcio per obtenir el moviment que vol fer l'usuari.
     * @return un String amb la casella seleccionada de l'usuari.
     */
    public String getMoviment() {
        return moviment;
    }

    /**
     * Per passar el moviment que fa l'usuari.
     * @param moviment referent a la casella que vol bombardejar l'usuari.
     */
    public void setMoviment(String moviment) {
        this.moviment = moviment;
    }

    /**
     * Funcio per obtenir la variable per saber si ha finalitzat el joc.
     * @return retorna el boolea amb aquest resultat.
     */
    public boolean isFinalitzat() {
        return finalitzat;
    }

    /**
     * Funcio per definir la variable finalitzat.
     * @param finalitzat boolea referent a si ha finalitzat el joc.
     */
    public void setFinalitzat(boolean finalitzat) {
        this.finalitzat = finalitzat;
    }
}