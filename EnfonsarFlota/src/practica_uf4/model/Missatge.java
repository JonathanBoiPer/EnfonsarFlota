package practica_uf4.model;

import java.io.Serializable;

public class Missatge implements Serializable {

    String accio;
    Object objecte;

    public Missatge(String _missatge, Object o) {
        accio = _missatge;
        objecte = o;
    }

    @Override
    public String toString() {
        return "Missatge: " + "accio = '" + accio + '\'' + ", objecte = " + objecte;
    }
}
