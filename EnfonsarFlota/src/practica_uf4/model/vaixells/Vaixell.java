package practica_uf4.model.vaixells;

public abstract class Vaixell {

    int vides;
    String posicio,orientacio;

    public abstract void colocarVaixell(String posicio, String orientacio, char num, int jugador);

    /**
     *
     * @return boolean, True si el vaixell s'enfonsa, False si només l'han tocat
     */
    public boolean enfonsat (){
        vides--;
        return vides == 0;
    }


}
