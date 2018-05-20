package client.util;

import client.model.Utilisateur;
import client.util.UtilisateurUtil;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
 
public class SocketTest {

    
    static final String serverName = "localhost";
    static final int serverPort = 9999;
 Socket socket;
 public  SocketTest() throws IOException, ClassNotFoundException{
        this.socket = new Socket(serverName, serverPort);
        System.out.println("Socket client: " + socket);
 }
 public UtilisateurUtil TestConnexion(Utilisateur user) throws IOException, ClassNotFoundException{
 
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
 
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
 
        System.out.println("Client a cree les flux");
 
        UtilisateurUtil usertosend=new UtilisateurUtil(user,0);
 
        out.writeObject(usertosend);
        out.flush();
 
        System.out.println("Client: donnees emises");
 
        Object objetRecu = in.readObject();
       UtilisateurUtil usercomplet= (UtilisateurUtil) objetRecu;
 
        System.out.println("Client recoit: " + usercomplet.getPseudo());
 
        in.close();
        out.close();
        socket.close();
        return usercomplet;
    }
}