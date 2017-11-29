package dao;

public class ColaboDonner {
	private Integer id;
	
	public ColaboDonner(Integer id, String nom, String prenom, String email, String aDonner) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.prenom2 = aDonner;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String nom;

	private String prenom;

	private String email;
	
	private String prenom2;

	public ColaboDonner(Integer id, String nom, String prenom, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public ColaboDonner() {
		super();
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

	public String getPrenom2() {
		return prenom2;
	}

	public void setPrenom2(String aDonner) {
		this.prenom2 = aDonner;
	}
}
