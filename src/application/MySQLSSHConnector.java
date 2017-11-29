package application;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

@SuppressWarnings("unused")
public class MySQLSSHConnector {
	public static Connection connexion() { 
		try {
			ResourceBundle rb = ResourceBundle.getBundle("application.propertie");
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");

			String url = "jdbc:postgresql://"+rb.getString("ip_database")+":"+rb.getString("port")+"/secret_santa";
			String user = rb.getString("user_db");
			String passwd = rb.getString("pwd_db");

			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");       
			return conn;

		} catch (Exception e) {
			e.printStackTrace();
		}      
		return null;
	}

}