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
    static ObjectOutputStream out;
    static ObjectInputStream in;
    static ObjectOutputStream out2;
    static ObjectInputStream in2;
    static Flota jug1;
    static Flota jug2;


    /**
     * Funcio main per inicialitzar el servidor
     * @param args Per poder fer Strings
     * @throws IOException per llançar les excepcions.
     */
    public static void main(String[] args) throws IOException {
        mostrarIP();
        //Realitzar connexió
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Esperant jugadors...\n");

        Socket socket = server.accept();
        System.out.println("Jugador 1 connectat");
        Joc.inicialitzarMapaVisible(Joc.mapaVisibleJug1);
        Joc.inicialitzarMapaOcult(Joc.mapaOcultJug1);
        
        Socket socket2 = server.accept();
        System.out.println("Jugador 2 connectat");
        Joc.inicialitzarMapaVisible(Joc.mapaVisibleJug2);
        Joc.inicialitzarMapaOcult(Joc.mapaOcultJug2);

        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        out2 = new ObjectOutputStream(socket2.getOutputStream());
        in2 = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));

        System.out.println("Canals de comunicació oberts...");


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
                jug1 = new Flota(posicions,1);
                Joc.imprimirMapa(1);

                Missatge inici2 = (Missatge) in2.readObject(); //Aquí arriba l'array amb les posicions i les orientacions del segon jugador.
                String[] posicions2 = inici2.getArray();
                jug2 = new Flota(posicions2,2);
                Joc.imprimirMapa(2);

                
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
        String movimentJug1,movimentJug2;
        try {
            while (!finalitzat) {
                //Enviar el taulell del contrincant
                out.writeUTF("Tauler del contrincant:\n");
                out.reset();
                Missatge tauler1 = new Missatge(Joc.mapaOcultJug2);
                out.writeObject(tauler1);
                out.flush();

                out2.writeUTF("Tauler del contrincant:\n");
                out2.reset();
                Missatge tauler2 = new Missatge(Joc.mapaOcultJug1);
                out2.writeObject(tauler2);
                out2.flush();


                // Esperar i mostrar missatge
                System.out.println("\nEsperant missatge del client...");
                Missatge moviment = (Missatge) in.readObject(); //Aquí arriba l'array amb el moviment
                System.out.println(moviment.getMoviment());
                movimentJug1 = moviment.getMoviment();
                finalitzat = Joc.procesarMoviment(movimentJug1,1,jug1);
                System.out.println("Moviment del jugador 1 rebut.");

                System.out.println("\nEsperant missatge del client...");
                Missatge moviment2 = (Missatge) in2.readObject(); //Aquí arriba l'array amb el moviment
                System.out.println(moviment2.getMoviment());
                movimentJug2 = moviment2.getMoviment();
                if (!finalitzat) {
                    finalitzat = Joc.procesarMoviment(movimentJug2, 2, jug2);
                }
                System.out.println("Moviment del jugador 2 rebut.");

                // Respondre al client
                out.writeUTF("Posició rebuda.");
                out.flush();

                out2.writeUTF("Posició rebuda.");
                out2.flush();

                //Enviar taulell del jugador propi
                out.writeUTF("El teu tauler actualitzat:");
                Missatge taulerJug1 = new Missatge(Joc.mapaVisibleJug1);
                taulerJug1.setFinalitzat(finalitzat);
                out.reset();
                out.writeObject(taulerJug1);
                out.flush();

                out2.writeUTF("El teu tauler actualitzat:");
                Missatge taulerJug2 = new Missatge(Joc.mapaVisibleJug2);
                taulerJug1.setFinalitzat(finalitzat);
                out2.reset();
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
        System.out.println("La ip del servidor és: " + ip + ":5000");
    }


    
}
