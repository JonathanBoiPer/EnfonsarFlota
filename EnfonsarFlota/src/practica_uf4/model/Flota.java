package practica_uf4.model;

import practica_uf4.model.vaixells.*;

/**
 * Classe Flota, administra els vaixells de cada jugador (Flota1 = jugador1 i Flota2 = jugador2)
 */
public class Flota {

    Vaixell[] Vaixells = new Vaixell[8];
    String[] estatVaixells = new String[8];
    String[] posicions;

    /**
     * Funcio que inicialitza els taules dels jugadors amb els vaixells colocats
     * @param posicions Array d'estrings que conté totes les posicions i orientacions dels vaixells d'un dels jugadors
     * @param jugador Int numero que indica quin jugador es.
     */
    public Flota(String[] posicions, int jugador){
        this.posicions = new String[13];
        this.posicions = posicions;
        inicialitzarVaixells();
        inicialitzarEstatVaixells();
        setVaixells(jugador);
    }

    /**
     * Funcio que posa els vaixells a la posicio indicada al String posicicions
     * @param jugador Int numero que indica quin jugador es.
     */
    public void setVaixells(int jugador){
        int posicio=3, orientacio=4;

        for (int x = 0; x < Vaixells.length ; x++) {
            //Els tres primes son les posicions dels submarins, que al ser d'una casella no tenen orientacio.
            if (x >= 3) {
                Vaixells[x].colocarVaixell(posicions[posicio], posicions[orientacio], (char) (x + 49), jugador);
                posicio += 2;
                orientacio += 2;
            }else{
                Vaixells[x].colocarVaixell(posicions[x], null, (char) (x + 49),jugador);
            }
        }
    }

    /**
     * Crea els objectes vaixells dins d'un array
     */
    public void inicialitzarVaixells(){
        for (int x = 0; x < 3 ; x++){ Vaixells[x] = new Submari() {}; }
        for (int x = 3; x < 5 ; x++){ Vaixells[x] = new Destructor() {}; }
        for (int x = 5; x < 7 ; x++){ Vaixells[x] = new Cuirassat() {}; }
        Vaixells[7] = new Portaavions() {};
    }

    /**
     * Inicialitza els vaixells com a "Intacte"
     */
    public void inicialitzarEstatVaixells(){
        for (int x = 0; x < 8 ; x++){
            estatVaixells[x] = "Intacte";
        }
    }

    /**
     * Cambia l'estat del vaixell dependent de quin vaixell sigui i quantes vidas tenia anteriorment
     * @param numVaixell el numero del vaixell corresponent a la poscicio de l'array i al numero del tauler
     * @return retorna True si tots els vaixells estan enfosats (Fi de joc);
     */
    public boolean setEstatVaixells(int numVaixell) {

        if (Vaixells[numVaixell].enfonsat()){
            estatVaixells[numVaixell] = "Enfonsat";
        }else {
            estatVaixells[numVaixell] = "Tocat";
        }

        for (int x = 0; x < estatVaixells.length; x++){
            if (!estatVaixells[x].equals("Enfonsat")){
                return false;
            }
        }
        return  true;
    }

}
