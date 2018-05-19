package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class SocketExecuteCommande extends ServerSocket implements Runnable{

	public static final int PORT = 8889;
	public static final int LIMITE = 1;
	
	
    public SocketExecuteCommande() throws IOException {
		super();
	}
	
    
	public void run() {
		String ligne, commande;
		Socket s;
		PrintWriter out;
		BufferedReader in;
		
		try {
			ServerSocket socket = new ServerSocket(PORT, LIMITE);
			System.out.println("En attente de client ...");
			
			while (true){
				s = socket.accept();
				out = new PrintWriter(s.getOutputStream());
			    //in = new BufferedReader(s.getInputStream());
				
				//while((ligne = in.readLine()) == null){
					//if (ligne.split(" ")[0].equals("connexion")){
				        // verifie la connexion
				
				        // renvoie la reponse apropriee
						
			        //}
				//}
				
				// fermeture de la socket et des voies de communication
				s.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
