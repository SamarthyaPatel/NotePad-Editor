package application;

import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Controller implements Initializable {
	
	String[] fontArray = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	ObservableList<String> fonts = FXCollections.observableArrayList();
	
	@FXML
	ComboBox<String> comboBox;
	
	Stage stage;
	
	@FXML
	TextArea textArea;
	int size = 16;
	String name = "Arial";
	
	@FXML
	AnchorPane anchorPane;
	
	@FXML
	Menu fileMenu;
	
	public void newFile(ActionEvent e) {
		System.out.println("Open new file.");
	}
	
	public void open(ActionEvent e) {
		System.out.println("Open file.");
	}
	
	public void save(ActionEvent e) {
		System.out.println("Save the file.");
	}
	
	public void close(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Close");
		alert.setHeaderText("You are about to close the file.");
		alert.setContentText("Are you sure you want to close?");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			stage = (Stage) anchorPane.getScene().getWindow();
			System.out.println("Closed.");
			stage.close();
		}
	}
	
	public void zoomIn(ActionEvent e) throws IOException {
		size += 1;
		textArea.setFont(new Font(name, size));
		
	}
	
	public void zoomOut(ActionEvent e) {
		size -= 1;
		textArea.setFont(new Font(name, size));
	}
	
	public void changeFont(ActionEvent e) {
		name = comboBox.getValue();
		textArea.setFont(new Font(name, size));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		fileMenu.setGraphic(new ImageView("file:///C:/Users/SAMARTHYA/PhotoEditor/logo/file.png"));
		
		for(String s : fontArray) {
			fonts.add(s);
		}
		comboBox.getItems().addAll(fonts);
		//textArea.setFont(new Font(name, size));
	}
	
}
