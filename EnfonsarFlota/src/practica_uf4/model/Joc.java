package practica_uf4.model;

public class Joc {

    public static char [][] mapaOcultJug1 = new char [8][8];
    public char [][] mapaOcultJug2 = new char [8][8];
    public char [][] mapaVisibleJug1;
    public char [][] mapaVisibleJug2;


    private void inicialitzarMapaOcult (char [][] mapa){
        for (int x = 0; x < mapa.length; x++){
            for (int y = 0; y < mapa[x].length; y++){
             mapa[x][y] = '·';
            }
        }
    }

    private void inicialitzarMapaVisible (char [][] mapa){
        for (int x = 0; x < mapa.length; x++){
            for (int y = 0; y < mapa[x].length; y++){
                mapa[x][y] = 'A';
            }
        }
    }


}
