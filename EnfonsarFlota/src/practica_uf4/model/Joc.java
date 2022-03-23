package practica_uf4.model;

/**
 * La classe que s'encarrega d'administra el joc en general, les jugades, els taules...
 */
public class Joc {

    /**
     * Mapa ocult = mapa que veurà el jugador contrincant, amb les caselles "tapades"
     * Mapa visible = un mapa per a fer les comprovacions més fàcilment, el client té un altre més clar per al jugador.
     */
    public static char [][] mapaOcultJug1 = new char [8][8];
    public static char [][] mapaOcultJug2 = new char [8][8];
    public static char [][] mapaVisibleJug1 = new char[8][8];
    public static char [][] mapaVisibleJug2 = new char[8][8];

    /**
     * Funcio per a posar el valor per defecte del taule ocult
     * @param mapa El taulell que es vol inicialitzar
     */
    public static void inicialitzarMapaOcult (char [][] mapa){
        for (int x = 0; x < mapa.length; x++){
            for (int y = 0; y < mapa[x].length; y++){
             mapa[x][y] = '·';
            }
        }
    }

    /**
     * Funcio per a posar el valor per defecte de la taula visible
     * @param mapa El taulell que es vol inicailitzar
     */
    public static void inicialitzarMapaVisible(char[][] mapa){
        for (int x = 0; x < mapa.length; x++){
            for (int y = 0; y < mapa[x].length; y++){
                mapa[x][y] = '=';
            }
        }
    }

    /**
     * Funcio per a imprimir el taulell visible (Funcio per a fer probes i comprovacions)
     * @param jugador Int, a quin jugador pertany el taulell
     */
    public static void imprimirMapa(int jugador){
        if (jugador == 1) {
            for (int x = 0; x < mapaVisibleJug1.length; x++) {
                for (int y = 0; y < mapaVisibleJug1[x].length; y++) {
                    System.out.print(" " + mapaVisibleJug1[x][y] + " ");
                }
                System.out.println();
            }
        }else if (jugador == 2){
            for (int x = 0; x < mapaVisibleJug2.length; x++) {
                for (int y = 0; y < mapaVisibleJug2[x].length; y++) {
                    System.out.print(" " + mapaVisibleJug2[x][y] + " ");
                }
                System.out.println();
            }
        }
        System.out.println("\n\n");

    }


}
