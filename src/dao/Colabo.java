package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.MySQLSSHConnector;

public class Colabo {
	private Integer id;

	private String nom;

	public Colabo(Integer id) {
		super();
		this.id = id;
	}

	private String prenom;

	private String email;

	private Integer id_colabo;

	public Colabo() {
		super();
	}

	public Colabo(Integer id, String nom, String prenom, String email, Integer id_colabo) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.id_colabo = id_colabo;
	}

	public Colabo(Integer id, String nom, String prenom, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Colabo(String nom, String prenom, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId_colabo() {
		return id_colabo;
	}

	public void setId_colabo(Integer id_colabo) {
		this.id_colabo = id_colabo;
	}

	public void insertColab() throws SQLException {
		Connection con = MySQLSSHConnector.connexion();
		String query = "INSERT INTO colabo( " + 
				"	prenom, nom, email) " + 
				"	VALUES (?, ?, ?)";
		PreparedStatement state = con.prepareStatement(query);
		state.setString(1, getNom());
		state.setString(2, getPrenom());
		state.setString(3, getEmail());
		try {
			state.execute();
		}
		catch (SQLException e ) {

		} finally {
			if (con != null) { con.close(); }
			if (state != null ) {
				state.close();
			}
		}
	}
	
	public static ArrayList<Colabo> findAll(){
		ArrayList<Colabo> listColab = new ArrayList<Colabo>();
		Colabo colabo = null;
		Connection con = MySQLSSHConnector.connexion();
		Statement stmt = null;
		String query = "SELECT * FROM colabo";
		try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	colabo = new Colabo(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getInt("id_colab_cadeau"));
	        	listColab.add(colabo);
	        }
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    } finally {
	    	if (con != null) { try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} }
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} }
	    }
		return listColab;
	}
	
	public Colabo findById(){
		Colabo colabo = null;
		Connection con = MySQLSSHConnector.connexion();
		Statement stmt = null;
		String query = "SELECT * FROM colabo WHERE id =  " + getId_colabo();
		try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	colabo = new Colabo(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getInt("id_colab_cadeau"));
	        	return colabo;
	        }
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    } finally {
	    	if (con != null) { try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} }
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} }
	    }
		return colabo;
	}
	
	public void supp(Integer id) throws SQLException{
		Connection con = MySQLSSHConnector.connexion();
		String query = "UPDATE colabo SET id_colab_cadeau = null WHERE id_colab_cadeau = ? ";
		PreparedStatement state = con.prepareStatement(query);
		state.setInt(1, id);
		
		String query2 = "DELETE FROM colabo WHERE id = ? ";
		PreparedStatement state2 = con.prepareStatement(query2);
		state2.setInt(1, id);
		try {
			state.execute();
			state2.execute();
		}
		catch (SQLException e ) {
			e.printStackTrace();
		} finally {
			if (con != null) { con.close(); }
			if (state != null ) {
				state.close();
			}
		}
	}
	
	public void updateAdonner(Integer id, Integer id2) throws SQLException{
		Connection con = MySQLSSHConnector.connexion();
		String query = "UPDATE colabo SET id_colab_cadeau = ? WHERE id = ? ";
		PreparedStatement state = con.prepareStatement(query);
		state.setInt(1, id);
		state.setInt(2, id2);
		try {
			state.execute();
		}
		catch (SQLException e ) {
			e.printStackTrace();
		} finally {
			if (con != null) { con.close(); }
			if (state != null ) {
				state.close();
			}
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_colabo == null) ? 0 : id_colabo.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Colabo other = (Colabo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
