package practica_uf4.server;

import practica_uf4.model.Flota;
import practica_uf4.model.Missatge;
import practica_uf4.model.Joc;
import java.io.*;
import java.net.*;

public class Servidor {

    static boolean finalitzat;
    static char[][] taulerUsuari1 = new char[10][10];
    static char[][] taulerUsuari2 = new char[10][10];
    static ObjectOutputStream out;
    static ObjectInputStream in;
    static ObjectOutputStream out2;
    static ObjectInputStream in2;


    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        mostrarIP();
        //Realitzar connexió
        ServerSocket server = new ServerSocket(5000);

        Socket socket = server.accept();
        System.out.println("S'ha connectat un client...");
        Socket socket2 = server.accept();
        System.out.println("Connexió Establerta.");

        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        out2 = new ObjectOutputStream(socket2.getOutputStream());
        in2 = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));

        System.out.println("Canals de comunicació oberts...");


        rebudaVaixells(socket);
        rebudaVaixells(socket2);

        server.close();
    }

    /**
     *
     * @param socket
     * @throws IOException
     */
    public static void rebudaVaixells(Socket socket) throws IOException {

            try {
                Missatge inici = (Missatge) in.readObject(); //Aquí arriba l'array amb les posicions i les orientacions
                String[] posicions = inici.getArray();
                Flota jug1 = new Flota(posicions,1);
                Joc.imprimirMapa(1);

                Missatge inici2 = (Missatge) in2.readObject(); //Aquí arriba l'array amb les posicions i les orientacions del segon jugador.
                String[] posicions2 = inici2.getArray();
                Flota jug2 = new Flota(posicions2,2);
                Joc.imprimirMapa(2);


                for (int i = 1; i < 9; i++) {
                    for (int j = 1; j < 9; j++) {
                        taulerUsuari1[i][j] = inici.getTaulerUsuari()[i][j];
                    }
                }

                for (int i = 1; i < 9; i++) {
                    for (int j = 1; j < 9; j++) {
                        taulerUsuari2[i][j] = inici2.getTaulerUsuari()[i][j];
                    }
                }

                System.out.println("\nJugador 1");
                for (int i = 0; i < posicions.length; i++) {
                    if (i < 3) System.out.println("Posició submarí: " + posicions[i]);
                    if (i > 2 && i < 7) System.out.println("Posició destructor: " + posicions[i]);
                    if (i > 6 && i < 11) System.out.println("Posició cuirassat: " + posicions[i]);
                    if (i > 10 && i < 13) System.out.println("Posició portaavions: " + posicions[i]);
                }

                System.out.println("\nJugador 2");
                for (int i = 0; i < posicions2.length; i++) {
                    if (i < 3) System.out.println("Posició submarí: " + posicions2[i]);
                    if (i > 2 && i < 7) System.out.println("Posició destructor: " + posicions2[i]);
                    if (i > 6 && i < 11) System.out.println("Posició cuirassat: " + posicions2[i]);
                    if (i > 10 && i < 13) System.out.println("Posició portaavions: " + posicions2[i]);
                }

                out.writeUTF("Les dades han sigut rebudes amb exitosament.");
                out.flush();

                out2.writeUTF("Les dades han sigut rebudes amb exitosament.");
                out2.flush();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        in.close();
        in2.close();
        socket.close();
    }

    public static void enviar() throws IOException, ClassNotFoundException {
        while (true) {
            // Esperar i mostrar missatge
            System.out.println("\nEsperant missatge del client...");
            Object obj = in.readObject();
            System.out.println("Missatge rebut del client: " + obj);

            // Respondre al client
            out.writeObject("He rebut el missatge ---> " + obj);
        }
    }

    /**
     *
     * @throws IOException
     */
    public static void mostrarIP() throws IOException {
        String ip;
        Socket s = new Socket("www.google.com",80);
        ip = s.getLocalAddress().getHostAddress();
        System.out.println("La ip del servidor per realitzar la connexió del client 1 és: " + ip + ":5000");
    }
}
