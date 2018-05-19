package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class SocketEnvoieDonnee extends ServerSocket implements Runnable{
	public static final int PORT = 8888;
	public static final int LIMITE = 50;
	
	// canaux d'envoie
	List<PrintWriter> outs = new ArrayList<>();
	
	
    public SocketEnvoieDonnee() throws IOException {
		super();
	}
	
	public void run() {
		String ligne;
		Socket s;
		
		try {
			ServerSocket socket = new ServerSocket(PORT, LIMITE);
			System.out.println("En attente de client ...");
			
			while (true){
				s = socket.accept();
				outs.add(new PrintWriter(s.getOutputStream()));
				// comment arreter la connexion?
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void envoieDonnee(String type, Object objet){
		String reponse = "";
		
		/*if (type.equals("groupe") && objet instanceof Groupe){
			for (int i = 0; i<outs.size(); i++){
				outs.get(i).print("demande nomGroupe fin");
			}
			
			// attent une reponse du client associe
			do{
			    reponse = ints.get(i).readLine();
			}while (reponse == "" || reponse == null);
			
			// recupere l'id contenu dans l'objet et compare avec la reponse
			if (((Groupe) objet).getId() == reponse){
				// TODO streamer l'objet
			}else{
			    // TODO envoie un message d'erreur
			}
		}else if (type.equals("utilisateur") && objet instanceof Utilisateur){
			for (int i = 0; i<outs.size(); i++){
				outs.get(i).print("demande pseudo fin");
			}
			
			// attent une reponse du client associe
			do{
			    reponse = ints.get(i).readLine();
			}while (reponse == "" || reponse == null);
			
			// recupere l'id contenu dans l'objet et compare avec la reponse
			if (((Utilisateur) objet).getId() == reponse){
				// TODO streamer l'objet
			}
		}else if (type.equals("document") && objet instanceof DocumentPerso){
			for (int i = 0; i<outs.size(); i++){
				outs.get(i).print("demande nomDoc fin");
			}
			
			// attent une reponse du client associe
			do{
			    reponse = ints.get(i).readLine();
			}while (reponse == "" || reponse == null);
			
			// recupere l'id contenu dans l'objet et compare avec la reponse
			if (((DocumentPerso) objet).getId() == reponse){
				// TODO streamer l'objet
			}
			
			
		}*/
		
	}

}
