package practica_uf4.server;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) throws IOException {
        mostrarIP();
        //Realitzar connexió
        ServerSocket server = new ServerSocket(5000);
        //ServerSocket server2 = new ServerSocket(5001);

        Socket socket = server.accept();
        //Socket socket2 = server.accept();
        System.out.println("Connexió Establerta. Per finalitzar xat escriu close");

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

        /*ObjectOutputStream out2 = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in2 = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));*/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str="", str2="";

        while(!str.equals("close")){

            str = in.readUTF();
            System.out.println("Client: " + str);
            System.out.print("Servidor: ");
            str2=br.readLine();
            out.writeUTF(str2);
            out.flush();
        }
/*
        while(!str.equals("close")){

            str = in.readUTF();
            System.out.println("Client: " + str);
            System.out.print("Servidor: ");
            str2=br.readLine();
            //out2.writeUTF(str2);
            //out2.flush();
        }
*/
        in.close();
        socket.close();
        server.close();
    }
    public static void mostrarIP() throws IOException {
        String ip;
        Socket s = new Socket("www.google.com",80);
        ip = s.getLocalAddress().getHostAddress();
        System.out.println("La ip del servidor per realitzar la connexió és: " + ip + ":5000");
    }

    /*
    Socket socket;
    ServerSocket server;
    ObjectInputStream in;

    public Servidor(int port){
        try {
        Socket s = new Socket("www.google.com",80);
        String serverIP = s.getLocalAddress().getHostAddress();
        s.close();

        server = new ServerSocket(port);
            System.out.println("Servidor iniciat");

    } catch (IOException e) {
            e.printStackTrace();
        }
    }

     */
}
