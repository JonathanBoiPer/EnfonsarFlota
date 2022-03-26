package practica_uf4.model.vaixells;

import practica_uf4.model.Joc;

/**
 * Classe submarins, d'una casella. N'hi ha 3.
 */
public class Submari extends Vaixell {

    public Submari(){
        vides = 1;
    }


    @Override
    public void colocarVaixell(String posicio, String orientacio, char num, int jugador) {
        int fila,columna;
        fila  = (posicio.charAt(0) - 65);
        columna = (posicio.charAt(1)-49);

        if (jugador == 1) {
            Joc.mapaVisibleJug1[fila][columna] = num;
        }else{
            Joc.mapaVisibleJug2[fila][columna] = num;
        }

    }


}
