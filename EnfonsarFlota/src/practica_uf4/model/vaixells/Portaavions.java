package practica_uf4.model.vaixells;

public class Portaavions {

    private int vides;
    private String posicio,orientacio;

    public Portaavions (){
        vides = 4;


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
