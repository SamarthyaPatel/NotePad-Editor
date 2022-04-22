package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/***
 * <p> File name: Main.java</p>
 * <p> Creation date: 10.04.2022</p>
 * <p> Last modification date: 22.04.2022</p>
 * <p> Purpose of the program: Handling the GUI of the application by using components.fxml file and doing the needful requested by the user.</p>
 * <p> Version history: 1.0 - pure code; 2.0 - close request code updated; 3.0 - commented code</p>
 * 
 * @author Samarthya Patel
 * @version 3.0
 */

public class Main extends Application {
	
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("components.fxml"));
			
			Scene scene = new Scene(root);
			
			stage.setTitle("Notepad");
			
			stage.setMinWidth(600);
			stage.setMinHeight(65);
			
			stage.getIcons().add(new Image("images/logo.png"));
			
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent e) {
			     Platform.exit();
			     System.exit(0);
			    }
			  });
			
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
