package practica_uf4.model;

import practica_uf4.model.vaixells.*;

public class Flota {
    vaixell[] vaixells= new vaixell[8];
    String[] estatVaixells = new String[8];
    String[] posicions = new String[16];


    public Flota(String[] posicions, int jugador){
        if (jugador == 1) {
            Joc.inicialitzarMapaVisible(Joc.mapaVisibleJug1);
        }else{
            Joc.inicialitzarMapaVisible(Joc.mapaVisibleJug2);
        }

        this.posicions = posicions;
        inicialitzarVaixells();
        inicialitzarEstatVaixells();
        setVaixells(jugador);
    }


    public void setVaixells(int jugador){
        int posicio=3, orientacio=4;

        for (int x = 0; x < vaixells.length ; x++) {
            if (x >= 3) {
                vaixells[x].colocarVaixell(posicions[posicio], posicions[orientacio], (char) (x + 49), jugador);
                posicio += 2;
                orientacio += 2;
            }else{
                vaixells[x].colocarVaixell(posicions[x], null, (char) (x + 49),jugador);
            }
        }
    }

    public void inicialitzarVaixells(){
        for (int x = 0; x < 3 ; x++){ vaixells[x] = new Submarins() {}; }
        for (int x = 3; x < 5 ; x++){ vaixells[x] = new Destructors() {}; }
        for (int x = 5; x < 7 ; x++){ vaixells[x] = new Cuirassats() {}; }
        vaixells[7] = new Portaavions() {};
    }

    public void inicialitzarEstatVaixells(){
        for (int x = 0; x < 8 ; x++){
            estatVaixells[x] = "Intacte";
        }
    }

    public void setEstatVaixells(int numVaixell) {
        if (vaixells[numVaixell].enfonsat()){
            estatVaixells[numVaixell] = "Enfonsat";
        }else {
            estatVaixells[numVaixell] = "Tocat";
        }
    }

    public String[] getEstat(){
        return estatVaixells;
    }


}
