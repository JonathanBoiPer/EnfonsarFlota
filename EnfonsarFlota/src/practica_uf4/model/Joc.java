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
     * Funcio per a imprimir el taulell visible (Funcio per a fer proves i comprovacions)
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

    /**
     * Mostra el mapa ocult, amb les caselles tapades (Funcio principalment per a proves i comprovacions)
     * @param jugador int, el numero del jugador que te aquest mapa ocult
     */
    public static void imprimirMapaOcult(int jugador){
        if (jugador == 1) {
            for (int x = 0; x < mapaOcultJug1.length; x++) {
                for (int y = 0; y < mapaOcultJug1[x].length; y++) {
                    System.out.print(" " + mapaOcultJug1[x][y] + " ");
                }
                System.out.println();
            }
        }else if (jugador == 2){
            for (int x = 0; x < mapaOcultJug2.length; x++) {
                for (int y = 0; y < mapaOcultJug2[x].length; y++) {
                    System.out.print(" " + mapaOcultJug2[x][y] + " ");
                }
                System.out.println();
            }
        }
        System.out.println("\n\n");

    }

    /**
     * LA funcio mes important, que agafa el moviment, actualitza els mapes i diu si la partida ha acabar
     * @param moviment String, la casella a la que el jugador ha decidit disparar
     * @param jugador Int, el numero del jugador que ha fet la jugada
     * @param flota Flota, la flota corresponent al jugador contrincant
     * @return Boolean, true si la flota contrincaria s'ha enfonsat
     */
    public static boolean procesarMoviment(String moviment, int jugador, Flota flota){
        int fila  = (moviment.charAt(0) - 65);
        int columna = (moviment.charAt(1)-49);
        boolean finalitzat = false;

        if (jugador == 1){
            if (mapaVisibleJug2[fila][columna] != 61) {
                int numVaixell =  mapaVisibleJug2[fila][columna]-49;
                System.out.println(mapaVisibleJug2[fila][columna] + " " + numVaixell);
                finalitzat = flota.setEstatVaixells(numVaixell);
                mapaVisibleJug2[fila][columna] = 'X';
                mapaOcultJug2[fila][columna] = 'X';

            }else{
                mapaOcultJug2[fila][columna] = '=';
            }
        }else if (jugador == 2){
            if (mapaVisibleJug1[fila][columna] != 61) {

                int numVaixell = (int) mapaVisibleJug1[fila][columna]-49;
                finalitzat =  flota.setEstatVaixells(numVaixell);
                mapaVisibleJug1[fila][columna] = 'X';
                mapaOcultJug1[fila][columna] = 'X';
            }else{
                mapaOcultJug1[fila][columna] = '=';
            }

        }

        return  finalitzat;
    }


}
