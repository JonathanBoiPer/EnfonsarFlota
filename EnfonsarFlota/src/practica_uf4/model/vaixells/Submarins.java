package practica_uf4.model.vaixells;

import practica_uf4.model.Joc;

public class Submarins extends vaixell{

    public Submarins(){
        vides = 1;
    }


    @Override
    public void colocarVaixell(String posicio, String orientacio, char num) {
        char direccio;
        int fila,columna;
        fila = (posicio.charAt(0) - 64);
        columna = (posicio.charAt(1)-47);

        Joc.mapaOcultJug1[fila][columna] = num;

    }


}
