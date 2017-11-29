package application;

	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;


public class Main extends Application {
	@FXML
	private AnchorPane content;
	
	private static Scene scene;
	private static Stage stage;
	
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/Menu.fxml"));
			this.stage = stage;
			scene = new Scene(root, 500, 275);
	        this.stage.setTitle("Secret Santa");
	        this.stage.setScene(scene);
	        this.stage.show();
	        this.stage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	 public void setScene(String lien){
	    	
	        try {
	        	Parent root = FXMLLoader.load(getClass().getResource("view/"+lien));
	            //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/ListeOverview.fxml"));
	            
	             // Show the scene containing the root layout.
	           this.scene = new Scene(root);
	             this.stage.setScene(scene);
	             this.stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("Changement de scene impossible");
	        }
	 }
}