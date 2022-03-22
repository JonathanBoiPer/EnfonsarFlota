package practica_uf4.model.vaixells;

public class Portaavions {

    private int vidas;
    private String posicio;
    private char orientacio;

    public Portaavions (){
        vidas = 4;


    }


    /**
     *
     * @return boolean, True si el vaixell s'enfonsa, False si només l'han tocat
     */
    public boolean enfonsat (){
        if (vidas == 1){
            return true;
        }else {
            vidas--;
            return false;
        }
    }

}
