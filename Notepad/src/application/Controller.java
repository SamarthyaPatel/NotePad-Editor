package application;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

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
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Controller implements Initializable {
	
	private Stage stage;
	private int size = 16;
	private String name = "Arial";
	private boolean bold = false;
	private boolean italic = false;
	private FontWeight bSyntax;
	private FontPosture iSyntax;
	
	private String[] fontArray = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	private ObservableList<String> fonts = FXCollections.observableArrayList();
	
	@FXML
	ComboBox<String> comboBox;
	
	@FXML
	TextArea textArea;
	
	
	@FXML
	AnchorPane anchorPane;
	
	@FXML
	Menu fileMenu;
	
	@FXML
	ToggleButton boldToggle;
	
	@FXML
	ToggleButton italicToggle;
	
	
	public void newFile(ActionEvent e) {
		textArea.setText("");
	}
	
	public void open(ActionEvent e) throws FileNotFoundException {
		FileChooser choose = new FileChooser();
		choose.setTitle("Open file");
		choose.setInitialDirectory(new File("c:\\users\\"));
		choose.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		File file = choose.showOpenDialog(stage);
		
		String text = "";
		Scanner scan = new Scanner(file);
		while(scan.hasNext()) {
			text += scan.nextLine() + "\n";
		}
		scan.close();
		textArea.setText(text);
		
	}
	
	public void save(ActionEvent e) throws IOException {
		FileChooser choose = new FileChooser();
		choose.setTitle("Save file");
		choose.setInitialDirectory(new File("c:\\users\\"));
		choose.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		File file = choose.showSaveDialog(stage);
		
		FileWriter pen = new FileWriter(file.getPath());
		pen.write(textArea.getText());
		pen.close();
		
	}
	
	public void close(ActionEvent e) throws IOException {
		
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
		
		setToggleButtons();
		
		textArea.setFont(Font.font(name, bSyntax, iSyntax, size));
		
	}
	
	public void zoomOut(ActionEvent e) {
		size -= 1;
		
		setToggleButtons();
		
		textArea.setFont(Font.font(name, bSyntax, iSyntax, size));
	}
	
	public void changeFont(ActionEvent e) {
		name = comboBox.getValue();

		setToggleButtons();
		
		textArea.setFont(Font.font(name, bSyntax, iSyntax, size));
	}
	
	public void boldText(ActionEvent e) {
		
		setToggleButtons();
		
		textArea.setFont(Font.font(name, bSyntax, iSyntax, size));
		
	}
	
	public void italicText(ActionEvent e) {
		
		setToggleButtons();
		
		textArea.setFont(Font.font(name, bSyntax, iSyntax, size));
		
	}
	
	public void setToggleButtons() {
		
		bold = boldToggle.isSelected();
		italic = italicToggle.isSelected();
		
		if(bold == true) {
			bSyntax = FontWeight.BOLD;
		} else {
			bSyntax = FontWeight.NORMAL;
		}
		if(italic == true) {
			iSyntax = FontPosture.ITALIC;
		} else {
			iSyntax = FontPosture.REGULAR;
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		fileMenu.setGraphic(new ImageView("file:/C:/Users/SAMARTHYA/PhotoEditor/logo/file.png"));
		
		for(String s : fontArray) {
			fonts.add(s);
		}
		
		comboBox.getItems().addAll(fonts);
	}
	
}
