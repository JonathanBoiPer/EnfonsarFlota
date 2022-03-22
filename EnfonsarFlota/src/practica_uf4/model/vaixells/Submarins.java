package practica_uf4.model.vaixells;

public class Submarins {

    private int vides;
    private String posicio,orientacio;

    public Submarins(){
        vides = 1;
    }

    /**
     *
     * @return boolean, True si el vaixell s'enfonsa, False si només l'han tocat
     */
    public boolean enfonsat (){
        if (vides == 1){
            return true;
        }else {
            vides--;
            return false;
        }
    }
}
