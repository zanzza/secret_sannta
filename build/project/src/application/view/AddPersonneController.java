package application.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.Main;
import dao.Colabo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class AddPersonneController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField email;

	@FXML
	private Label error;

	@FXML
	private TextField nom;

	@FXML
	private TextField prenom;

	@FXML
	private Button retour;

	@FXML
	private Button validate;


	@FXML
	void retour(ActionEvent event) {
		Main main = new Main();
		main.setScene("Menu.fxml");
	}

	@FXML
	void validate(ActionEvent event) {

		String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
				+ "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(this.email.getText());
		if (controler.matches() && !this.nom.getText().isEmpty() && !this.prenom.getText().isEmpty()){
			Colabo col = new Colabo(nom.getText(), prenom.getText(), email.getText());
			try {
				col.insertColab();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.error.setText("Personne Ajouter");
			this.error.setTextFill(Color.web("#00FF00"));
		}else{
			this.error.setText("Erreur d'enregistrement");
			this.error.setTextFill(Color.web("#FF0000"));
		}
	}

	@FXML
	void initialize() {
		assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'AddPersonne.fxml'.";
		assert error != null : "fx:id=\"error\" was not injected: check your FXML file 'AddPersonne.fxml'.";
		assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'AddPersonne.fxml'.";
		assert prenom != null : "fx:id=\"prenom\" was not injected: check your FXML file 'AddPersonne.fxml'.";
		assert retour != null : "fx:id=\"retour\" was not injected: check your FXML file 'AddPersonne.fxml'.";
		assert validate != null : "fx:id=\"validate\" was not injected: check your FXML file 'AddPersonne.fxml'.";


	}

}