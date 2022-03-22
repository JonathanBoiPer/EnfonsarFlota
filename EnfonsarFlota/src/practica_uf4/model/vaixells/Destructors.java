package practica_uf4.model.vaixells;

import practica_uf4.model.Joc;

public class Destructors extends vaixell{

    public Destructors(){
        vides = 2;
    }


    @Override
    public void colocarVaixell(String posicio, String orientacio, char num, int jugador) {
        char direccio;
        int fila,columna,x = 0, y = 0;
        fila  = (posicio.charAt(0) - 65);
        columna = (posicio.charAt(1)-49);
        direccio = orientacio.charAt(0);

        if (direccio == 'N'){
            y = -1;
        }else if (direccio == 'S'){
            y = 1;
        }else if (direccio == 'E'){
            x = 1;
        }else{
            x = -1;
        }

        if(jugador == 1) {
            Joc.mapaVisibleJug1[fila][columna] = num;
            Joc.mapaVisibleJug1[fila + y][columna + x] = num;
        }else{
            Joc.mapaVisibleJug2[fila][columna] = num;
            Joc.mapaVisibleJug2[fila + y][columna + x] = num;
        }
    }

}
