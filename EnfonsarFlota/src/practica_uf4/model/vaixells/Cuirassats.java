package practica_uf4.model.vaixells;

import practica_uf4.model.Joc;

public class Cuirassats extends vaixell{


    public Cuirassats(){
        vides = 3;
    }


    @Override
    public void colocarVaixell(String posicio, String orientacio, char num, int jugador) {
        char direccio;
        int fila,columna,x = 0, y = 0, n = 0, m = 0;
        fila  = (posicio.charAt(0) - 65);
        columna = (posicio.charAt(1)-49);
        direccio = orientacio.charAt(0);

        if (direccio == 'N'){
            m = -1;
        }else if (direccio == 'S'){
            m = 1;
        }else if (direccio == 'E'){
            n = 1;
        }else{
            n = -1;
        }

        for(int q = 0; q < 3; q++){
            if (jugador == 1) {
                Joc.mapaVisibleJug1[fila + y][columna + x] = num;
            }else {
                Joc.mapaVisibleJug2[fila + y][columna + x] = num;
            }
                x += n;
                y += m;
        }

    }


}
