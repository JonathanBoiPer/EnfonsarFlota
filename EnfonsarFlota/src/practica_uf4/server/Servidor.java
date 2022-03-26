package practica_uf4.server;

import practica_uf4.model.Flota;
import practica_uf4.model.Missatge;
import practica_uf4.model.Joc;
import java.io.*;
import java.net.*;

/**
 * Classe servidor per rebre les connexions i dades dels clients.
 */
public class Servidor {

    static boolean finalitzat;
    static char[][] taulerUsuari1 = new char[10][10];
    static char[][] taulerUsuari2 = new char[10][10];
    static ObjectOutputStream out;
    static ObjectInputStream in;
    static ObjectOutputStream out2;
    static ObjectInputStream in2;


    /**
     * Funcio main per inicialitzar el servidor
     * @param args Per poder fer Strings
     * @throws IOException per llançar les excepcions.
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


        rebudaVaixells();
        rebudaVaixells();
        enviar();
        in.close();
        in2.close();
        socket.close();
        server.close();
    }

    /**
     * Funcio per rebre les posicions inicials dels vaixells.
     * @throws IOException per llançar les excepcions.
     */
    public static void rebudaVaixells() throws IOException {

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

                out.writeUTF("Les dades han sigut rebudes amb exitosament.");
                out.flush();

                out2.writeUTF("Les dades han sigut rebudes amb exitosament.");
                out2.flush();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    /**
     * Funcio per enviar el tauler i rebre les posicions que demani bombardejar els jugadors
     * @throws IOException per llançar les excepcions.
     */
    public static void enviar() throws IOException {
        try {
            while (finalitzat) {
                //Enviar el taulell del contrincant
                out.writeUTF("Tauler del contrincant:\n");
                Missatge tauler1 = new Missatge(taulerUsuari2);
                out.writeObject(tauler1);
                out.flush();

                out2.writeUTF("Tauler del contrincant:\n");
                Missatge tauler2 = new Missatge(taulerUsuari1);
                out2.writeObject(tauler2);
                out2.flush();


                // Esperar i mostrar missatge
                System.out.println("\nEsperant missatge del client...");
                Missatge moviment = (Missatge) in.readObject(); //Aquí arriba l'array amb el moviment
                System.out.println(moviment.getMoviment());
                System.out.println("Missatge rebut del jugador 1.");
                finalitzat = moviment.isFinalitzat();

                System.out.println("\nEsperant missatge del client...");
                Missatge moviment2 = (Missatge) in2.readObject(); //Aquí arriba l'array amb el moviment
                System.out.println(moviment2.getMoviment());
                System.out.println("Missatge rebut del jugador 2.");
                finalitzat = moviment2.isFinalitzat();

                // Respondre al client
                out.writeUTF("Posició rebuda.");
                out.flush();

                out2.writeUTF("Posició rebuda.");
                out2.flush();

                //Enviar taulell del jugador propi
                out.writeUTF("El teu tauler actualitzat:\n");
                Missatge taulerJug1 = new Missatge(taulerUsuari2);
                out.writeObject(taulerJug1);
                out.flush();

                out2.writeUTF("El teu tauler actualitzat:\n");
                Missatge taulerJug2 = new Missatge(taulerUsuari1);
                out2.writeObject(taulerJug2);
                out2.flush();
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcio per mostrar la IP i el port amb el qual treballa el servidor
     * @throws IOException per llançar les excepcions.
     */
    public static void mostrarIP() throws IOException {
        String ip;
        Socket s = new Socket("www.google.com",80);
        ip = s.getLocalAddress().getHostAddress();
        System.out.println("La ip del servidor per realitzar la connexió del client 1 és: " + ip + ":5000");
    }
}
