package practica_uf4.model;

import java.io.*;
import java.net.Socket;

public class Missatge implements Serializable {

    String[] array = new String[13];
    char[][] taulerUsuari = new char[10][10];

    public Missatge(char[][] visible) {
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                taulerUsuari[i][j] = visible[i][j];
            }
        }
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }
    }

    public char[][] getTaulerUsuari() {
        return taulerUsuari;
    }

    public void setTaulerUsuari(char[][] taulerUsuari) {
        this.taulerUsuari = taulerUsuari;
    }
}
