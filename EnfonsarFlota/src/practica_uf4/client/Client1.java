package practica_uf4.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client1 {

    static int files;
    static int columnes;
    static boolean finalitzat;
    static char [][] ocult;
    static char [][] visible;

    public static void main(String[] args) throws IOException {

        String ip = obtenirConnexio();
        int port = obtenirPort(ip);
        ip = separarPort(ip);

        Socket connexio = new Socket(ip, port);
        System.out.println("Connexió establerta.");

        missatgeria(connexio);
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

    public static void missatgeria(Socket socket) throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str="", str2="";

        while(finalitzat){

            int i = 0;

            System.out.print("Benvingut al joc Enfonsar la Flota");
            System.out.print("En aquesta edició hi hauran 4 tipus de vaixell:");
            System.out.print("3 - Submarins de 1 casella");
            System.out.print("2 - Destructors de 2 caselles");
            System.out.print("2 - Cuirassats de 3 caselles");
            System.out.print("1 - Portaavions de 4 caselles");
            System.out.print("Aquest serà el taulell:");
            Client1.inizialitzarMapa();

            while(i <= 8) {
                i++;
                System.out.print("Quin vaixell vols posar?");
                System.out.print("Submarí = 'S'");
                System.out.print("Destructor = 'D'");
                System.out.print("Cuirassats = 'C'");
                System.out.print("Portaavions = 'P'");
                str = br.readLine();

                switch (str){
                    case "S":
                        System.out.println("En quina posició el vols situar");

                        System.out.println("Amb quina orientació?");

                        break;
                    case "D":
                        System.out.println("En quina posició el vols situar");

                        System.out.println("Amb quina orientació?");

                        break;
                    case "C":
                        System.out.println("En quina posició el vols situar");

                        System.out.println("Amb quina orientació?");

                        break;
                    case "P":
                        System.out.println("En quina posició el vols situar");

                        System.out.println("Amb quina orientació?");

                        break;
                    default:
                        System.out.println("ERROR!. No es reconeix el tipus de vaixell.");
                }

                out.writeUTF(str);
                out.flush();
                str2 = in.readUTF();
                System.out.println("Servidor: " + str2);
            }
        }

        out.close();
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
}