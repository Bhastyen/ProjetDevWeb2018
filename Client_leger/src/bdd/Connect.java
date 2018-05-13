package bdd;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;




public class Connect {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/";    //"jdbc:mysql://mira2.univ-st-etienne.fr:3306/"
    private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	private String id;
    private String pwd;
    private String db;
    private Connection c;
    private Statement st;
    private PreparedStatement pst;
    
	public Connect(String id, String pwd, String db){
		// garde en memoire les infos de connexion
		this.setId(id);
		this.setPwd(pwd);
		this.setDb(db);
		
		// chargement du driver
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// connection vers la BDD
		try {
			c = (Connection) DriverManager.getConnection(URL+db, id, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			st = (Statement) c.createStatement();
			//pst = (PreparedStatement) c.prepareStatement("SELECT age FROM personne WHERE id_personne = ?");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static ResultSet processSelect(String query){
		Connect c = new Connect("root", "", "mind_map");
		ResultSet r = null;
		
		r = c.reqSQL(query, 's');

		try {
			c.getConnection().close();
			c.getStatement().close();
			c.getPreparedStatement().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	public static int processUpdate(String query){
		Connect c = new Connect("root", "", "mind_map");
		int n;
		
		n = c.reqUpdate(query);

		try {
			c.getConnection().close();
			c.getStatement().close();
			c.getPreparedStatement().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	public ResultSet reqSQL(String query, char type){
		ResultSet r = null;
		int up;
		
		if (type == 's'){
			try {
				r = st.executeQuery(query);
		        afficherRequete(r);
		    } catch (SQLException e) {
				System.out.println("Attention! Votre requete d'interrogation n'a pas marche, elle est sans doute mal ecrite.");
				e.printStackTrace();
			}
		}else if (type == 'm'){
			try {
				up = st.executeUpdate(query);
				System.out.println(up+" rows modified.");
			} catch (SQLException e) {
				System.out.println("Attention! Votre requete de manipulation n'a pas marche, elle est sans doute mal ecrite.");
				e.printStackTrace();
			}
		}else{
			System.out.println("Attention type de la requete inconnu: s = selection et m = manipulation");
		}
		
		return r;
		
	}
	
	public int reqUpdate(String query){
		int up = 0;
		
		try {
			up = st.executeUpdate(query);
			System.out.println(up+" rows modified.");
		} catch (SQLException e) {
			System.out.println("Attention! Votre requete de manipulation n'a pas marche, elle est sans doute mal ecrite.");
			System.out.println(e);
		}
		
		return up;
		
	}  
	
	public void afficherRequete(ResultSet r) {
		int compteur = 0, limite = 50;     // pour eviter l'affichage des 10000 utilisateurs dans le terminal
		ResultSetMetaData res;
		String ligne = "";
		
		if (r != null) {
			try {
				res = r.getMetaData();
				for(int i=1; i<=res.getColumnCount(); i++) {
					ligne += res.getColumnName(i)+" ";
				}
				System.out.println(ligne);
				while(r.next() && compteur < limite){
					ligne = "";
					for(int i=1; i<=res.getColumnCount(); i++) {
						ligne += r.getString(i)+" ";
					}
					System.out.println(ligne);
					compteur ++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	/*public void getAge(int id_personne){
		ResultSet r;
		
		try {
			r = st.executeQuery("SELECT age FROM personne Where id_personne = "+id_personne);
			if (r != null) {
				r.next();
				//System.out.println("Personne numero " + id_personne + " " + r.getInt("age"));
				r.close();
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getAgePreComp(int id_personne){
		ResultSet r;
		
		try {
			pst.setInt(1, id_personne);
			r = pst.executeQuery();
			r.next();
			//System.out.println("Personne numero " + id_personne + " " + r.getInt("age"));
			r.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	/*public void inscriptionsRandom() {
		char[] no, pr;
		int n = 10000;
		int i, t1, t2;
		int age;
		
		try {
			PreparedStatement p = (PreparedStatement) c.prepareStatement("INSERT INTO personne(nom, prenom, age, email) VALUES (?, ?, ?, \"b\")");
			for (i=0; i<n; i++) {
				t1 = (int) ((Math.random()*10)+3);
				t2 = (int) ((Math.random()*10)+3);
				
				no = new char[t1]; 
				pr = new char[t2];
				
				for (int j = 0; j<t1; j++) {
					no[j] = (char) (Math.random()*255);
				}
				
				for (int j = 0; j<t2; j++) {
					pr[j] = (char) (Math.random()*255);
				}
				
				age = (int) ((Math.random()*(2000-1940))+1940);
				
				p.setString(1, new String(no));
				p.setString(2, new String(pr));
				p.setInt(3, age);
				p.executeUpdate();
			}
			p.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}*/

	public String getId(char c){
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public Connection getConnection() {
		return c;
	}

	public Statement getStatement() {
		return st;
	}

	public PreparedStatement getPreparedStatement() {
		return pst;
	}
	
}
