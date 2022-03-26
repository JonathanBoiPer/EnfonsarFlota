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

    static char [][] contrincant;
    static char [][] jugador;
    static ObjectOutputStream out;
    static ObjectInputStream in;
    static BufferedReader br;

    static char userfila = ' ', userFilaTemp = ' ';
    static String filacol = "", usercolumna = "", orientacio = "", filacolTemp = "", userColTemp = "";
    static int columna = 0, fila = 0, colTemp = 0;
    static boolean valid;
    static String[] caselles = new String[20];

    /**
     * Inicialitzar les diferents funcions del jugador
     * @param args per realitzar strings
     * @throws IOException per tractar errors.
     */
    public static void main(String[] args) throws IOException {

        String ip = obtenirConnexio();
        int port = obtenirPort(ip);
        ip = obtenirIP(ip);


        Socket connexio = new Socket(ip, port);
        System.out.println("Connexió establerta.");

        out = new ObjectOutputStream(connexio.getOutputStream());
        in = new ObjectInputStream(new BufferedInputStream(connexio.getInputStream()));
        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Canals de comunicació creats.");

        sleep(2);

        menu();
        assignarVaixells();
        rebre();
        connexio.close();
    }

    /**
     * Per escriure la connexio al servidor.
     * @return String amb la connexio(IP+Port).
     */
    public static String obtenirConnexio(){
        String connexio;
        Scanner scan = new Scanner(System.in);
        System.out.print("Digues connexió al servidor: ");
        connexio = scan.nextLine();
        return connexio;
    }

    /**
     * Funcio per dividir la variable connexio i obtenir el port.
     * @param connexio String en el qual estan les dues variables(IP+Port).
     * @return enter amb el numero del port.
     */
    public static int obtenirPort(String connexio) {
        int delimiter = connexio.indexOf(':');
        String port = connexio.substring(delimiter + 1);
        return Integer.parseInt(port);
    }

    /**
     * Funcio per obtenir la IP
     * @param connexio String en el qual estan les dues variables(IP+Port).
     * @return String amb la IP.
     */
    public static String obtenirIP(String connexio){
        int delimiter = connexio.indexOf(':');
        connexio = connexio.substring(0,delimiter);
        return connexio;
    }

    /**
     * Menu on s'explica el joc
     */
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
            inizialitzarMapa();
            mostrarCamp(jugador);
            sleep(2);
        }

    /**
     * Funcio on cada jugador assigna els seus vaixells
     * @throws IOException per tractar errors.
     */
    public static void assignarVaixells() throws IOException{

            String rebut = "";
            String[] posicions = new String[13];


            int i = 0;
            int c = 0;
            int k = i;

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
                    for (k = i + 2; k < i + 4; k++) {
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
                caselles[k] = filacol;
                i++;
                filacolTemp = filacol;
                userFilaTemp = userfila;
                userColTemp = usercolumna;
                colTemp = columna;
                do {
                    for (k = i + 2; k < i + 5; k++) {
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


            Missatge taulell = new Missatge(jugador);
            taulell.setArray(posicions);
            out.writeObject(taulell);


            out.flush();
            rebut = in.readUTF();
            System.out.println("\nServidor: " + rebut);
    }

    /**
     * Funcio per descompondre la posicio que ha escrit el jugador i per comprovar que no estigui repetida de cap manera.
     */
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

    /**
     * Funcio per comprovar que la orientacio que ha escollit l'usuari no col·lisioni amb cap vaixell ja posat.
     * @param k comptador enter
     * @param i comptador enter
     * @throws IOException per tractament d'errors.
     */
    public static void orientacio(int k, int i) throws IOException {
        if (k <= i + 2) {
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

    /**
     * Funcio on es rep els mapes i s'envien els moviments dels diferents jugadors.
     * @throws IOException per tractament d'errors.
     */
    public static void rebre() throws IOException {
        try {
            String rebut;
            while (finalitzat) {
                out.flush();
                rebut = in.readUTF();
                System.out.println("\nServidor: " + rebut);
                // Rebre el taulell del contrincant
                Missatge taulellContrincant = (Missatge) in.readObject();
                for (int i = 1; i < 9; i++) {
                    for (int j = 1; j < 9; j++) {
                        contrincant[i][j] = taulellContrincant.getTaulerUsuari()[i][j];
                    }
                }
                mostrarCamp(contrincant);

                // Demanar i enviar missatge
                do {
                    valid = true;
                    System.out.println("\nEscriu la casella que vols bombardejar: (Exemple: A1)");
                    try {
                        filacol = scan.nextLine();
                        userfila = filacol.charAt(0);
                        fila = userfila - 'A' + 1;
                        usercolumna = filacol.substring(1);
                        columna = Integer.parseInt(usercolumna);
                    } catch (Exception e) {
                        System.out.println("ERROR. No has introduït correctament les dades.");
                        valid = false;
                    }
                } while (!verificarFC(fila, columna) || !valid);
                Missatge moviment = new Missatge(jugador);
                moviment.setMoviment(filacol);
                finalitzat = moviment.isFinalitzat();
                out.writeObject(moviment);


                // Mostrar camp jugador
                Missatge taulellJugador = (Missatge) in.readObject();
                for (int i = 1; i < 9; i++) {
                    for (int j = 1; j < 9; j++) {
                        contrincant[i][j] = taulellJugador.getTaulerUsuari()[i][j];
                    }
                }
                mostrarCamp(jugador);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcio per comprovar que tant la linia com la columna que l'usuari hagi seleccionat estiguin dintre del rang estipulat.
     * @param fila enter referent a la fila del tauler.
     * @param columna enter referent a la columna.
     * @return boolean referent a si la posicio es valida o no.
     */
    public static boolean verificarFC(int fila, int columna) {

        if (fila >= files -1 || fila < 1 || columna >= columnes - 1 || columna < 1) {
            System.out.println("Error. La columna i/o la fila es incorrecte.");
            return false;
        }
        else return true;
    }

    /**
     * Funcio per iniciar el tauler del jugador
     */
    public static void inizialitzarMapa() {
        files = 10;
        columnes = 10;
        jugador = new char [files][columnes];
        contrincant = new char [files][columnes];

        inizialitzarCamp(contrincant,'.');
        inizialitzarCamp(jugador, '.');
    }

    /**
     * Funcio per omplir els camps dels usuaris.
     * @param camp char bidimensional referent al tauler.
     * @param c char referent al caracter amb el qual volem omplir el camp.
     */
    static void inizialitzarCamp(char[][] camp, char c) {
        for (int i = 1; i < files -1; i++) {
            for (int j = 1; j < columnes -1; j++) {
                camp[i][j] = c;
            }
        }
    }

    /**
     * Mostrar el tauler amb les lletres i numeros escalats.
     * @param camp char bidimensional referent al tauler.
     */
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

    /**
     * Funcio per fer que el programa esperi uns segons.
     * @param segons enter per escriure quants segons vol esperar-se la maquina.
     */
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
