package application;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@SuppressWarnings("unused")
public class MySQLSSHConnector {
	public static Connection connexion() { 
		Properties prop = new Properties();
		InputStream  input = null;
		try {
			input = new FileInputStream(MySQLSSHConnector.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"\\application\\propertie.properties");
			prop.load(input);
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");

			String url = "jdbc:postgresql://"+prop.getProperty("ip_database")+":5432/secret_santa";
			String user = prop.getProperty("user_db");
			String passwd = prop.getProperty("pwd_db");

			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");       
			return conn;

		} catch (Exception e) {
			e.printStackTrace();
		}      
		return null;
	}

}