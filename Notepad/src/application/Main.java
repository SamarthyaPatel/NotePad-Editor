package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("components.fxml"));
			
			Scene scene = new Scene(root);
			
			stage.setTitle("Notepad");
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Close");
			alert.setHeaderText("You are about to close the file.");
			alert.setContentText("Are you sure you want to close?");
			stage.setOnCloseRequest(e -> {
				if(alert.showAndWait().get() == ButtonType.OK) {
					stage.getScene().getWindow();
					stage.close();
				}
			});
			
			stage.getIcons().add(new Image("images/logo.png"));
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
