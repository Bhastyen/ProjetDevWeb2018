package client.model;

import java.net.*;

import java.io.*;

class testobject implements Serializable {
	int value;
	String id;

	public testobject(int v, String s) {
		this.value = v;
		this.id = s;
	}
}

public class SimpleClient {
	public static void main(String args[]) {
		try {
			System.out.println("lancement");
			Socket s = new Socket("localhost", 2002);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			testobject to = new testobject(1, "object from client");
			oos.writeObject(to);
			oos.writeObject(new String("another object from the client"));
			oos.close();
			os.close();
			s.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}