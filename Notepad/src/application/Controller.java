package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/***
 * <p> File name: Controller.java</p>
 * <p> Creation date: 10.04.2022</p>
 * <p> Last modification date: 22.04.2022</p>
 * <p> Purpose of the program: Handling all the functions of the application and interacting with the components.fxml file. Also lets application.css file to modify GUI.</p>
 * <p> Version history: 1.0 - pure code; 2.0 - refined code; 3.0 - commented code </p>
 * 
 * @author Samarthya Patel
 * @version 3.0
 */

public class Controller implements Initializable {

	private static final String UNSAVED_ALERT = "Would you like to save the changes before closing?";
	private static final String CLOSE_ALERT_HEADER = "You are about to close the file.";
	private static final String CLOSE_ALERT_CONTENT = "Are you sure you want to close?";
	private static final String ABOUT_CONTENT = "The Notepad-Editor is made by Samarthya Patel as a project in April 2022.\n\nThe rising developer currently studies at Swansea University.\n\nIn this application you can not only do basic operations of a notepad but also do Font editing, adding time-date stamps, and has other features too.\n\nHave a great time (.txt)ing.";

	private static final String LOGO_IMAGE = "images/logo.png";
	private static final String ABOUT_IMAGE = "images/aboutLogo.png";
	private static final String FILE_IMAGE = "images/file.png";
	private static final String EDIT_IMAGE = "images/edit.png";
	private static final String VIEW_IMAGE = "images/view.png";
	private static final String FONT_IMAGE = "images/font.png";
	private static final String HELP_IMAGE = "images/help.png";
	private static final String CLOCK_IMAGE = "images/clock.png";

	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM y");

	private static final String EMPTY_STRING = "";
	private static final String NEW_LINE = "\n";
	private static final int TWO = 2;
	private static final int SIXTEEN = 16;
	private static final int ONE_SECOND = 1000;

	private Stage stage;
	private int size = SIXTEEN;
	private String name = "Arial";
	private boolean bold = false;
	private boolean italic = false;
	private FontWeight bSyntax;
	private FontPosture iSyntax;
	private String time;
	private DialogPane dialog;

	//FXML injections to interact with the components.
	
	@FXML
	AnchorPane anchorPane;

	@FXML
	Menu fileMenu;

	@FXML
	Menu editMenu;

	@FXML
	Menu viewMenu;

	@FXML
	CheckMenuItem wrapCheck;

	@FXML
	Menu fontMenu;

	@FXML
	Menu fontSubMenu;

	@FXML
	ToggleGroup radioGroup;

	@FXML
	RadioMenuItem a, c, h, l, r, v;

	@FXML
	CheckMenuItem boldCheckBox;

	@FXML
	CheckMenuItem italicCheckBox;

	@FXML
	Menu helpMenu;

	@FXML
	TextArea textArea;

	@FXML
	ComboBox<String> comboBox;

	@FXML
	Label clockLabel;

	@FXML
	Label timeLabel;

	/***
	 * NewFile method takes care of making new file and saving the current file to avoid data loss. 
	 * @param e
	 * @throws IOException
	 */
	public void newFile(ActionEvent e) throws IOException {

		if (!textArea.getText().isEmpty()) {

			Alert unsaved = new Alert(AlertType.CONFIRMATION);
			unsaved.setTitle("Unsaved changes");
			unsaved.setContentText(UNSAVED_ALERT);

			if (unsaved.showAndWait().get() == ButtonType.OK) {

				saveFile(e);
				textArea.setText(EMPTY_STRING);

			} else {
				textArea.setText(EMPTY_STRING);
			}
		}

	}

	/***
	 * openFile method uses FileChooser to access and open text files from the user's system.
	 * @param e
	 * @throws FileNotFoundException
	 */
	public void openFile(ActionEvent e) throws FileNotFoundException {

		FileChooser choose = new FileChooser();
		choose.setTitle("Open file");
		choose.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));

		File file = choose.showOpenDialog(stage);
		String text = EMPTY_STRING;

		Scanner scan = new Scanner(file);

		while (scan.hasNext()) {
			text += scan.nextLine() + NEW_LINE;
		}
		scan.close();

		textArea.setText(text);
		textArea.end();
	}

	/***
	 * saveFile method helps to save text file in user's system using FileChooser.
	 * @param e
	 * @throws IOException
	 */
	public void saveFile(ActionEvent e) throws IOException {

		FileChooser choose = new FileChooser();
		choose.setTitle("Save file");
		choose.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));

		File file = choose.showSaveDialog(stage);

		FileWriter pen = new FileWriter(file.getPath());

		pen.write(textArea.getText());
		pen.close();

	}

	/***
	 * close function to close the application with an alert dialog window.
	 * @param e
	 * @throws IOException
	 */
	public void close(ActionEvent e) throws IOException {

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Close");
		alert.setHeaderText(CLOSE_ALERT_HEADER);
		alert.setContentText(CLOSE_ALERT_CONTENT);

		stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(LOGO_IMAGE));

		dialog = alert.getDialogPane();
		dialog.getStylesheets().add(getClass().getResource("application.css").toString());
		dialog.getStyleClass().add("dialog");

		alert.getButtonTypes().remove(ButtonType.OK);
		alert.getButtonTypes().add(ButtonType.YES);

		if (alert.showAndWait().get() == ButtonType.YES) {

			Platform.exit();
			System.exit(0);
		}
	}

	/***
	 * Method to zoom in the text area
	 * @param e
	 */
	public void zoomIn(ActionEvent e) {

		size += TWO;

		setCheckMenuItems();

		textArea.setFont(Font.font(name, bSyntax, iSyntax, size));

	}

	/***
	 * Method to zoom out the text area
	 * @param e
	 */
	public void zoomOut(ActionEvent e) {

		size -= TWO;

		setCheckMenuItems();

		textArea.setFont(Font.font(name, bSyntax, iSyntax, size));
		
	}

	/***
	 * Method to enable / disable text wrapping
	 * @param e
	 */
	public void wrapText(ActionEvent e) {

		textArea.setWrapText(wrapCheck.isSelected());

	}
	
	/***
	 * Method to change fonts
	 * @param e
	 */
	public void changeFont(ActionEvent e) {

		if (a.isSelected()) {
			name = a.getText();
		} else if (c.isSelected()) {
			name = c.getText();
		} else if (h.isSelected()) {
			name = h.getText();
		} else if (l.isSelected()) {
			name = l.getText();
		} else if (r.isSelected()) {
			name = r.getText();
		} else if (v.isSelected()) {
			name = v.getText();
		}

		textArea.setFont(Font.font(name, bSyntax, iSyntax, size));
		
		int length = textArea.getLength();
		
		textArea.replaceText(length - TWO, length, textArea.getText(length - TWO, length));;

	}

	/***
	 * Method to bold text
	 * @param e
	 */
	public void boldText(ActionEvent e) {

		setCheckMenuItems();

		textArea.setFont(Font.font(name, bSyntax, iSyntax, size));

	}

	/***
	 * Method to apply italic effect on text
	 * @param e
	 */
	public void italicText(ActionEvent e) {

		setCheckMenuItems();

		textArea.setFont(Font.font(name, bSyntax, iSyntax, size));

	}

	/***
	 * Set method to set the values of bold and italic effects
	 */
	public void setCheckMenuItems() {

		bold = boldCheckBox.isSelected();
		italic = italicCheckBox.isSelected();

		if (bold == true) {
			bSyntax = FontWeight.BOLD;
		} else {
			bSyntax = FontWeight.NORMAL;
		}

		if (italic == true) {
			iSyntax = FontPosture.ITALIC;
		} else {
			iSyntax = FontPosture.REGULAR;
		}
	}

	/***
	 * Method to add time and date stamps in the text area at desired position
	 * @param e
	 */
	public void addTimeDate(ActionEvent e) {

		int pos = textArea.getCaretPosition();

		String date = dateFormat.format(new Date());

		String timeDate = "[" + time + "  |  " + date + "]";

		textArea.replaceText(pos, pos, timeDate);

		textArea.positionCaret(pos + timeDate.length());
	}

	/***
	 * Method to undo the action
	 * @param e
	 */
	public void undo(ActionEvent e) {
		textArea.undo();
	}

	/***
	 * Method to redo the action
	 * @param e
	 */
	public void redo(ActionEvent e) {
		textArea.redo();
	}

	/***
	 * Method to select everything in the text area
	 * @param e
	 */
	public void selectAll(ActionEvent e) {
		textArea.selectAll();
	}

	/***
	 * Method to cut the selected text
	 * @param e
	 */
	public void cut(ActionEvent e) {
		textArea.cut();
	}

	/***
	 * Method to copy the selected text
	 * @param e
	 */
	public void copy(ActionEvent e) {
		textArea.copy();
	}

	/***
	 * Method to paste text at desired position
	 * @param e
	 */
	public void paste(ActionEvent e) {
		textArea.paste();
	}

	/***
	 * Method responsible to display about section using alert dialog window
	 * @param e
	 */
	public void displayAbout(ActionEvent e) {

		Alert about = new Alert(AlertType.INFORMATION);

		stage = (Stage) about.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(LOGO_IMAGE));

		dialog = about.getDialogPane();
		dialog.getStylesheets().add(getClass().getResource("application.css").toString());
		dialog.getStyleClass().add("dialog");

		about.setTitle("About");
		about.setHeight(500);
		about.setGraphic(new ImageView(ABOUT_IMAGE));
		about.setHeaderText("Notepad Editor");
		about.setContentText(ABOUT_CONTENT);
		about.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		fileMenu.setGraphic(new ImageView(FILE_IMAGE));
		editMenu.setGraphic(new ImageView(EDIT_IMAGE));
		viewMenu.setGraphic(new ImageView(VIEW_IMAGE));
		fontMenu.setGraphic(new ImageView(FONT_IMAGE));
		helpMenu.setGraphic(new ImageView(HELP_IMAGE));
		clockLabel.setGraphic(new ImageView(CLOCK_IMAGE));

		radioGroup.selectToggle(a);

		time();

	}

	/***
	 * Method responsible to display live clock using thread
	 */
	public void time() {

		Thread clock = new Thread(() -> {

			while (true) {

				try {
					Thread.sleep(ONE_SECOND);
				} catch (InterruptedException e) {
				}

				time = timeFormat.format(new Date());

				Platform.runLater(() -> {
					timeLabel.setText(time);
				});
			}
		});

		clock.start();
	}
}
