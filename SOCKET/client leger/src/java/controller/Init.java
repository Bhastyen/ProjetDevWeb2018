package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import socket.SocketEnvoieDonnee;
import socket.SocketExecuteCommande;
import bdd.Connect;



/**
 * Servlet implementation class Init
 */
@WebServlet("/Init")
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Thread envoie;
	private Thread execute;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    public void init(ServletConfig config) throws ServletException {
    	/*try {
    		// creation de nouveaux thread permettant de gerer le client lourd
			envoie = new Thread(new SocketEnvoieDonnee());
			execute = new Thread(new SocketExecuteCommande());
			
			// lancement des sockets en parallele du serveur principale
			envoie.start();
			execute.start();
		} catch (IOException e) {
			System.out.println(e);
		}*/
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.sendRedirect("/ProjetWeb2018/Connexion");
	}
	
	public void destroy(){
		/*try {
			envoie.join();
			execute.join();
		} catch (InterruptedException e) {
			System.out.println(e);
		}*/
	}

}
