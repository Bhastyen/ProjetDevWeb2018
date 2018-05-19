package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import model.Utilisateur;



public class SocketExecuteCommande extends ServerSocket implements Runnable{

	public static final int PORT = 8889;
	public static final int LIMITE = 1;
	
	
    public SocketExecuteCommande() throws IOException {
		super();
	}
	
    
	public void run(){
		String ligne, commande;
		Utilisateur util;
		Socket s;
		ObjectOutputStream out;
		BufferedReader in;
		String[] info = new String[2];
		int reponse = 0;
		
		try {
			ServerSocket socket = new ServerSocket(PORT, LIMITE);
			System.out.println("En attente de client ...");
			
			while (true){
				s = socket.accept();
				out = new ObjectOutputStream(s.getOutputStream());
			    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				
				while((ligne = in.readLine()) != null && (ligne = in.readLine()) != "fin"){
					info = ligne.split(" ");
					if (info[0].equals("connexion")){
					    // verifie la connexion
					    if (bdd.Connect.valideUtilisateur(info[1], info[2]))
					        reponse = 1;
					    else reponse = 2;
					}
				}
				
				if (reponse == 1){
					// renvoie la reponse apropriee
					util = new Utilisateur();
					util.setMail(info[1]);
					util.setPass(info[2]);
				}else{
					util = null;
				}

				out.writeObject(util);
				
				// fermeture de la socket et des voies de communication
				in.close();
				out.close();
				s.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
