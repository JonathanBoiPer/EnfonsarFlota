package sockets.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
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

        while(!str.equals("close")){

            System.out.print("Client: ");
            str = br.readLine();
            out.writeUTF(str);
            out.flush();
            str2 = in.readUTF();
            System.out.println("Servidor: " + str2);
        }

        out.close();
    }
}