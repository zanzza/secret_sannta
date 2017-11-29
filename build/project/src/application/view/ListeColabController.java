package application.view;


import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import application.Main;
import application.MySQLSSHConnector;
import dao.Colabo;
import dao.ColaboDonner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListeColabController {
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableColumn<ColaboDonner, String> email;

	@FXML
	private Button env_mail;

	@FXML
	private Label error;

	@FXML
	private TableColumn<ColaboDonner, String> nom;

	@FXML
	private TableColumn<ColaboDonner, String>prenom;

	@FXML
	private Button retour;

	@FXML
	private Button supp;

	@FXML
	private TableView<ColaboDonner> tableau;
	@FXML
	private TableColumn<ColaboDonner, String>cadeauA;


	@FXML
	void envMail(ActionEvent event) {
		ArrayList<Colabo> listColab = Colabo.findAll();
		ArrayList<Colabo> listColab2 = Colabo.findAll();
		ArrayList<Colabo> listColab3 = new ArrayList<Colabo>();
		Random rand = new Random();
		if(listColab.size() > 1) {
			for(Colabo colab : listColab){
				listColab2.remove(colab);
				while(true) {
					Colabo colab2 = listColab2.get(rand.nextInt(listColab2.size()));
					if (!colab2.equals(colab)) {
						try {
							colab.updateAdonner(colab2.getId(), colab.getId());
							listColab2.remove(colab2);
							listColab3.add(colab2);
							if(!listColab3.contains(colab)) {
								listColab2.add(colab);
							}
							Properties prop = new Properties();
							InputStream  input = null;
							try {
								input = new FileInputStream(MySQLSSHConnector.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"\\application\\propertie.properties");
								prop.load(input);
							}
							catch (Exception e) {
								e.printStackTrace();
							}
							String to = colab.getEmail();
							// Sender's email ID needs to be mentioned
							String from = colab.getEmail();

							// Assuming you are sending email from localhost
							String host = prop.getProperty("mail_smtp");

							// Get system properties
							Properties properties = System.getProperties();

							// Setup mail server
							properties.setProperty("mail.smtp.host", host);

							// Get the default Session object.
							Session session = Session.getDefaultInstance(properties);

							try {
								// Create a default MimeMessage object.
								MimeMessage message = new MimeMessage(session);

								// Set From: header field of the header.
								message.setFrom(new InternetAddress(from));

								// Set To: header field of the header.
								message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

								// Set Subject: header field
								message.setSubject("Cadeau de Noël CDS!");

								// Send the actual HTML message, as big as you like
								message.setContent("Bonjour,<br/>"
										+ "Vous devez offrir un cadeau de Noël à " + colab2.getPrenom() + " "
										+ colab2.getNom() + "<br/> Cordialement, Le Père Noël Secret.", "text/html");

								// Send message
								Transport.send(message);
								System.out.println("Sent message successfully....");
							} catch (MessagingException mex) {
								mex.printStackTrace();
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}

			find();
		}

	}

	@FXML
	void retour(ActionEvent event) {
		Main main = new Main();
		main.setScene("Menu.fxml");
	}

	@FXML
	void supp(ActionEvent event) {
		ColaboDonner colabo = tableau.getSelectionModel().getSelectedItem();
		try {
			Colabo colabo2 = new Colabo(colabo.getId());
			colabo2.supp(colabo.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		find();
	}

	public void select(){
		ColaboDonner cpt = tableau.getSelectionModel().getSelectedItem();
		if( cpt !=null ){
			supp.setDisable(false);
		}
		else{
			supp.setDisable(true);
		}
	}

	@FXML
	void initialize() {
        assert cadeauA != null : "fx:id=\"cadeauA\" was not injected: check your FXML file 'ListeColab.fxml'.";
		assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'ListeColab.fxml'.";
		assert env_mail != null : "fx:id=\"env_mail\" was not injected: check your FXML file 'ListeColab.fxml'.";
		assert error != null : "fx:id=\"error\" was not injected: check your FXML file 'ListeColab.fxml'.";
		assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'ListeColab.fxml'.";
		assert prenom != null : "fx:id=\"prenom\" was not injected: check your FXML file 'ListeColab.fxml'.";
		assert retour != null : "fx:id=\"retour\" was not injected: check your FXML file 'ListeColab.fxml'.";
		assert supp != null : "fx:id=\"supp\" was not injected: check your FXML file 'ListeColab.fxml'.";
		assert tableau != null : "fx:id=\"tableau\" was not injected: check your FXML file 'ListeColab.fxml'.";



		this.supp.setDisable(true);
		this.nom.setCellValueFactory(new PropertyValueFactory<ColaboDonner,String>("nom"));
		this.prenom.setCellValueFactory(new PropertyValueFactory<ColaboDonner,String>("prenom"));
		this.email.setCellValueFactory(new PropertyValueFactory<ColaboDonner,String>("email"));
		this.cadeauA.setCellValueFactory(new PropertyValueFactory<ColaboDonner,String>("prenom2"));
		find();

	}

	public void find() {
		this.tableau.getItems().removeAll(tableau.getItems());
		ArrayList<Colabo> listColab = Colabo.findAll();
		for(Colabo colab : listColab) {
			ColaboDonner colaboDonner = new ColaboDonner(colab.getId(), colab.getNom(), colab.getPrenom(), colab.getEmail(), "");
			if (colab.findById() != null) {
				Colabo colabo = colab.findById();
				colaboDonner.setPrenom2(colabo.getPrenom() + " " + colabo.getNom());
			}
			tableau.getItems().add(colaboDonner);
		}
	}
}
