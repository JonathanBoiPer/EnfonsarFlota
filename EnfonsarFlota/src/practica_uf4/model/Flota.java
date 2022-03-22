package practica_uf4.model;

import practica_uf4.model.vaixells.*;

public class Flota {
    vaixell[] vaixells= new vaixell[8];
    String[] estatVaixells = new String[8];
    String[] posicions = new String[16];


    public Flota(){
        inicialitzarVaixells();
        inicialitzarEstatVaixells();
        setVaixells();
    }


    public void setPosiccions(String[] posiccions) { this.posicions = posiccions; }

    public void setVaixells(){
        int posicio=0, orientacio=1;

        for (int x = 0; x < vaixells.length ; x++) {

            vaixells[x].colocarVaixell(posicions[posicio],posicions[orientacio],(char)(x+49));
            posicio += 2;
            orientacio += 2;

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
