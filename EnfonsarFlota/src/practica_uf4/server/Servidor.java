package practica_uf4.server;

import practica_uf4.model.Missatge;

import java.io.*;
import java.net.*;


public class Servidor {

    static boolean finalitzat;
    static char[][] taulerUsuari1 = new char[10][10];
    static char[][] taulerUsuari2 = new char[10][10];

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        mostrarIP();
        //Realitzar connexió
        ServerSocket server = new ServerSocket(5000);
        //ServerSocket server2 = new ServerSocket(5001);

        Socket socket = server.accept();
        //Socket socket2 = server.accept();
        System.out.println("Connexió Establerta.");

        rebudaVaixells(socket);
        //enviar(socket);
        server.close();
    }

    /**
     *
     * @param socket
     * @throws IOException
     */
    public static void rebudaVaixells(Socket socket) throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String rebre="", enviar="";

            try {
                Missatge inici = (Missatge) in.readObject(); //Aquí arriba l'array amb les posicions i les orientacions
                String[] posicions = inici.getArray();
                for (int i = 1; i < 9; i++) {
                    for (int j = 1; j < 9; j++) {
                        taulerUsuari1[i][j] = inici.getTaulerUsuari()[i][j];
                    }
                }
                for (int i = 1; i < 9; i++) {
                    for (int j = 1; j < 9; j++) {
                        taulerUsuari2[i][j] = inici.getTaulerUsuari()[i][j];
                    }
                }

                for (int i = 0; i < posicions.length; i++) {
                    if (i < 3) System.out.println("Posició submarí: " + posicions[i]);
                    if (i > 2 && i < 7) System.out.println("Posició destructor: " + posicions[i]);
                    if (i > 6 && i < 11) System.out.println("Posició cuirassat: " + posicions[i]);
                    if (i > 10 && i < 13) System.out.println("Posició portaavions: " + posicions[i]);
                }

                out.writeUTF("Les dades han sigut rebudes amb exitosament.");
                out.flush();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        in.close();
        socket.close();
    }

    public static void enviar(Socket socket) throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String rebre="", enviar="";

        while (finalitzat) {
            out.writeObject(new Missatge(taulerUsuari1));
            out.flush();

        }
        in.close();
        socket.close();

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
        System.out.println("La ip del servidor per realitzar la connexió del client 2 és: " + ip + ":5001");
    }
}
