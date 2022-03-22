package practica_uf4.model;

import practica_uf4.model.vaixells.vaixell;

public class Flota {
    vaixell[] vaixells= new vaixell[8];
    String[] estatVaixells = new String[8];
    String[] posicions = new String[16];


    public Flota(){

    }


    public void setPosiccions(String[] posiccions) { this.posicions = posiccions; }

    public void setVaixells(){
        int posicio=0, orientacio=1;

        for (int x = 0; x < vaixells.length ; x++) {

            vaixells[x].colocarVaixell(posicions[posicio],posicions[orientacio]);
            posicio += 2;
            orientacio += 2;

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
