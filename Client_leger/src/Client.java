package testSocket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Client{
	public static final int PORT1 = 8889;
	public static final int PORT2 = 8888;
    
	
	public Client(){
	
	}
	
	public static void main(String[] args){
		ObjectInputStream in;
		PrintWriter out;
		Utilisateur util = null;
				
		try{
			Socket socket = new Socket("127.0.0.1", PORT1);
	        out = new PrintWriter(socket.getOutputStream());
	        in = new ObjectInputStream(socket.getInputStream());
			
			out.write("connexion bastien.duraj@live.fr rtva1234\n");
			out.write("fin\n");
			out.close();
			
			util = (Utilisateur) in.readObject();
			in.close();
			
			socket.close();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(util);
	}
	
}
