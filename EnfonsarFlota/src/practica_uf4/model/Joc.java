package practica_uf4.model;

public class Joc {

    public static char [][] mapaOcultJug1 = new char [8][8];
    public static char [][] mapaOcultJug2 = new char [8][8];
    public static char [][] mapaVisibleJug1 = new char[8][8];
    public static char [][] mapaVisibleJug2 = new char[8][8];


    public void inicialitzarMapaOcult (char [][] mapa){
        for (int x = 0; x < mapa.length; x++){
            for (int y = 0; y < mapa[x].length; y++){
             mapa[x][y] = '·';
            }
        }
    }

    public static void inicialitzarMapaVisible(char[][] mapa){
        for (int x = 0; x < mapa.length; x++){
            for (int y = 0; y < mapa[x].length; y++){
                mapa[x][y] = '=';
            }
        }
    }


    public static void imprimirMapa(int jugador){
        for (int x = 0; x < mapaVisibleJug1.length; x++){
            for (int y = 0; y < mapaVisibleJug1[x].length;y++){
             System.out.print(" "+mapaVisibleJug1[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println("\n\n");

    }


}
