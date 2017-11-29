package application.view;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button1;

    @FXML
    private Button button2;


    @FXML
    void addPersonne(ActionEvent event) {
    	Main main = new Main();
    	main.setScene("AddPersonne.fxml");
    }

    @FXML
    void administration(ActionEvent event) {
    	Main main = new Main();
    	main.setScene("ListeColab.fxml");
    }

    @FXML
    void initialize() {
        assert button1 != null : "fx:id=\"button1\" was not injected: check your FXML file 'Menu.fxml'.";
        assert button2 != null : "fx:id=\"button2\" was not injected: check your FXML file 'Menu.fxml'.";


    }

}