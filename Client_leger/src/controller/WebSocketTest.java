package controller;

import client.util.UtilisateurUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;

import model.Utilisateur;
 
public class WebSocketTest implements Runnable{
    static final int port = 9999;
    ServerSocket s;
    
    public WebSocketTest() throws IOException{   
        s = new ServerSocket(port);
    }
    
    public void run(){
    	int resultat = 0;
    	
        System.out.println("Socket serveur: " + s);
   
        while(true){
        	Socket soc;
        	try {
				soc = s.accept();
	
		        System.out.println("Serveur a accepte connexion: " + soc);
		 
		        ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
		        out.flush();
		 
		        ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
		 
		        Object objetRecu = in.readObject();
		        UtilisateurUtil utilisateur = (UtilisateurUtil) objetRecu;
		 
		        System.out.println("Serveur recoit: " + utilisateur.getPseudo());
		        //Si utilisateur.type==0 alors on test si pseudo et mdp sont bon, sinon inscription
		        
		        if (utilisateur.type == 0){
		        	if (bdd.Connect.valideUtilisateur(utilisateur.getEmail(), utilisateur.getMotPasse())){
		        		resultat = 1;
		        	}else{
		        		resultat = 0;
		        	}
		        }else{
		        	if (bdd.Connect.insertUtilisateur(utilisateur.getPseudo(), utilisateur.getEmail(), new Date(), utilisateur.getMotPasse()) != 0){
		        		resultat = 1;
			        }else{
			        	resultat = 0;
			        }
		        }
		 
		        System.out.println("Resultat de la requete: "+resultat);
		        System.out.println("Serveur a cree les flux");
		        Utilisateur usercomplet = new Utilisateur(utilisateur.getPseudo(),utilisateur.getMotPasse());
		        
		        usercomplet.setEmail(utilisateur.getEmail());
		        
		        UtilisateurUtil complet = new UtilisateurUtil(usercomplet, resultat);
		        //La, il faudrait renvoyer l'utilisateur complet (new UtilisateurUtil(utilisateurcomplet)
		        //et un user avec 0 a la place de 1 si c'est pas bon
		        
		        //On renvoie un truc pour dire si cest bon
		        out.writeObject(complet);
		        out.flush();
		 
		        System.out.println("Serveur: donnees emises");
		 
		        in.close();
		        out.close();
		  
		        soc.close();
        	} catch (IOException e) {
        		e.printStackTrace();
        	} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        }
    
    }
    
}