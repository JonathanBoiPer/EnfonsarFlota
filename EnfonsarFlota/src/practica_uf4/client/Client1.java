package practica_uf4.client;

import practica_uf4.model.Missatge;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Classe client pels diferents jugadors.
 */
public class Client1 {
    static Scanner scan = new Scanner(System.in);

    static int files;
    static int columnes;
    static boolean finalitzat;
    static char [][] ocult;
    static char [][] visible;
    static ObjectOutputStream out;
    static ObjectInputStream in;
    static BufferedReader br;

    static char userfila = ' ', userFilaTemp = ' ';
    static String filacol = "", usercolumna = "", orientacio = "", filacolTemp = "", userColTemp = "";
    static int columna = 0, fila = 0, colTemp = 0;
    static boolean valid;
    static String[] caselles = new String[16];

    /**
     * Inicialitzar les diferents funcions del jugador
     * @param args
     * @throws IOException per tractar errors
     */
    public static void main(String[] args) throws IOException {

        String ip = obtenirConnexio();
        int port = obtenirPort(ip);
        ip = separarPort(ip);


        Socket connexio = new Socket(ip, port);
        System.out.println("Connexió establerta.");

        out = new ObjectOutputStream(connexio.getOutputStream());
        in = new ObjectInputStream(new BufferedInputStream(connexio.getInputStream()));
        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Canals de comunicació creats.");

        sleep(2);

        menu();
        assignarVaixells();
        connexio.close();
    }
    public static String obtenirConnexio(){
        String connexio;
        Scanner scan = new Scanner(System.in);
        System.out.print("Digues connexió al servidor: ");
        connexio = scan.nextLine();
        return connexio;
    }
    public static int obtenirPort(String connexio) {
        int delimiter = connexio.indexOf(':');
        String port = connexio.substring(delimiter + 1);
        return Integer.parseInt(port);
    }

    public static String separarPort(String connexio){
        int delimiter = connexio.indexOf(':');
        connexio = connexio.substring(0,delimiter);
        return connexio;
    }

    public static void menu() {

            System.out.println("\nBenvingut al joc Enfonsar la Flota\n");
            sleep(2);
            System.out.println("En aquesta edició hi hauran 4 tipus de vaixell:");
            System.out.println("3 - Submarins de 1 casella");
            System.out.println("2 - Destructors de 2 caselles");
            System.out.println("2 - Cuirassats de 3 caselles");
            System.out.println("1 - Portaavions de 4 caselles\n");
            sleep(6);
            System.out.println("Aquest serà el taulell:\n");
            Client1.inizialitzarMapa();
            sleep(2);
        }

        public static void assignarVaixells() throws IOException{

            String rebut = "";
            String[] posicions = new String[13];


            int i = 0;
            int c = 0;

            sleep(1);

            System.out.println("\nAssigna els 3 submarins de 1.");
            while(i < 3) {
                do {
                    valid = true;
                    System.out.println("\nEn quina posició el vols situar el submarí numero " + (c + 1) + " (Exemple: A1)");
                    try {
                        filacol = scan.nextLine();
                        comprovarLiniesColum();
                    } catch (Exception e){
                        System.out.println("ERROR. No has introduït correctament les dades.");
                        valid = false;
                    }
                } while (!verificarFC(fila, columna) || !valid);
                posicions[i] = filacol;
                caselles[i] = filacol;
                i++; c++;
            }


            c = 0;
            System.out.println("\nAssigna els 2 destructors de 2.");
            while (i < 7) {
                do {
                    valid = true;
                    System.out.println("\nEn quina posició el vols situar el destructor numero " + (c + 1) + " (Exemple: A1)");
                    try {
                        filacol = scan.nextLine();
                        comprovarLiniesColum();
                    } catch (Exception e){
                        System.out.println("ERROR. No has introduït correctament les dades.");
                        valid = false;
                    }
                } while (!verificarFC(fila, columna) || !valid);
                caselles[i] = filacol;
                posicions[i] = filacol;
                i++;c++;
                filacolTemp = filacol;
                userFilaTemp = userfila;
                userColTemp = usercolumna;
                colTemp = columna;
                do {
                    int k = i;
                    orientacio(k, i);
                } while (!verificarFC(fila, columna) || !valid);
                caselles[i] = filacol;
                posicions[i] = orientacio;
                i++;
            }

            c = 0;
            System.out.println("\nAssigna els 2 cuirassats de 3.");
            while (i < 11) {
                do {
                    valid = true;
                    System.out.println("\nEn quina posició el vols situar el cuirassat numero " + (c + 1) + " (Exemple: A1)");
                    try {
                        filacol = scan.nextLine();
                        comprovarLiniesColum();
                    } catch (Exception e){
                        System.out.println("ERROR. No has introduït correctament les dades.");
                        valid = false;
                    }
                } while (!verificarFC(fila, columna) || !valid);
                posicions[i] = filacol;
                caselles[i] = filacol;
                i++;c++;
                filacolTemp = filacol;
                userFilaTemp = userfila;
                userColTemp = usercolumna;
                colTemp = columna;
                do {
                    for (int k = i; k < i + 2; k++) {
                        orientacio(k, i);
                        caselles[k] = filacol;
                        posicions[i] = orientacio;
                        if (!verificarFC(fila, columna) || !valid){
                            valid = false;
                            break;
                        }
                    }
                } while (!valid);
                i++;
            }

            System.out.println("\nAssigna el portaavió de 4.");
            while (i < 13) {
                do {
                    valid = true;
                    System.out.println("\nEn quina posició el vols situar el portaavió (Exemple: A1)");
                    try {
                        filacol = scan.nextLine();
                        comprovarLiniesColum();
                    } catch (Exception e){
                        System.out.println("ERROR. No has introduït correctament les dades.");
                        valid = false;
                    }
                } while (!verificarFC(fila, columna) || !valid);
                posicions[i] = filacol;
                caselles[i] = filacol;
                i++;
                filacolTemp = filacol;
                userFilaTemp = userfila;
                userColTemp = usercolumna;
                colTemp = columna;
                do {
                    for (int k = i; k < i + 3; k++) {
                        orientacio(k, i);
                        caselles[k] = filacol;
                        posicions[i] = orientacio;
                        if (!verificarFC(fila, columna) || !valid){
                            valid = false;
                            break;
                        }
                    }
                } while (!valid);
                i++;
            }

            sleep(2);


            Missatge taulell = new Missatge(visible);
            taulell.setArray(posicions);
            out.writeObject(taulell);


            out.flush();
            rebut = in.readUTF();
            System.out.println("\nServidor: " + rebut);
    }

    public static void comprovarLiniesColum() {
        userfila = filacol.charAt(0);
        fila = userfila - 'A' + 1;
        usercolumna = filacol.substring(1);
        columna = Integer.parseInt(usercolumna);
        for (int j = 0; j < caselles.length; j++) {
            if (filacol.equals(caselles[j])) {
                System.out.println("ERROR. La casella en direcció a la orientació que has escollit, ja ha sigut utilitzada o té algun error.");
                valid = false;
                break;
            }
        }
    }

    public static void orientacio(int k, int i) throws IOException {
        if (k < i + 1) {
            filacol = filacolTemp;
            userfila = userFilaTemp;
            usercolumna = userColTemp;
            columna = colTemp;

            valid = true;

            do {
                System.out.println("Amb quina orientació(N,S,E,O)?");

                orientacio = br.readLine();

            } while (!(orientacio.equals("N") || orientacio.equals("S") || orientacio.equals("E") || orientacio.equals("O")));
        }
        switch (orientacio) {
            case "N":
                userfila--;
                filacol = userfila + usercolumna;
                comprovarLiniesColum();
                break;
            case "S":
                userfila++;
                filacol = userfila + usercolumna;
                comprovarLiniesColum();
                break;
            case "E":
                columna++;
                filacol = userfila + String.valueOf(columna);
                comprovarLiniesColum();
                break;
            case "O":
                columna--;
                filacol = userfila + String.valueOf(columna);
                comprovarLiniesColum();
                break;
        }
    }

    public static void rebre() throws IOException, ClassNotFoundException {
        while (true) {
            // Demanar i enviar missatge
            System.out.print("\nEscriu la casella que vols bombardejar: ");
            String missatge = scan.nextLine();
            out.writeObject(missatge);

            // Esperar i mostrar resposta
            Object obj = in.readObject();
            System.out.println("Resposta del servidor: " + obj);
        }
    }

    public static boolean verificarFC(int fila, int columna) {

        if (fila >= files -1 || fila < 1 || columna >= columnes - 1 || columna < 1) {
            System.out.println("Error. La columna i/o la fila es incorrecte.");
            return false;
        }
        else return true;
    }

    public static void inizialitzarMapa() {
        files = 10;
        columnes = 10;
        ocult = new char [files][columnes];
        visible = new char [files][columnes];

        inizialitzarCamp(ocult, ' ');
        inizialitzarCamp(visible, '.');

        mostrarCamp(visible);
    }

    static void inizialitzarCamp(char[][] camp, char c) {
        for (int i = 1; i < files -1; i++) {
            for (int j = 1; j < columnes -1; j++) {
                camp[i][j] = c;
            }
        }
    }

    public static void mostrarCamp(char[][] camp) {
        char lletra = 'A';
        char numero = '1';
        int l = 0;
        int n = 0;
        for (int i = 0; i < camp.length -1; i++) {
            for (int j = 0; j < camp[i].length -1; j++) {
                if (i == 0 && j > 0) {
                    camp[i][j] = (char) (numero + n);
                    n++;
                } else if (j == 0 && i > 0) {
                    camp[i][j] = (char) (lletra + l);
                    l++;
                }
                System.out.print("  " + camp[i][j]);
            }
            System.out.println();
        }
    }

    public static void sleep(int segons) {
        try {
            for (int i = 0; i < segons; i++) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
