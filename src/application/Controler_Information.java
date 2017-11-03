package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controler_Information extends AnchorPane implements Initializable {
	
	@FXML Button ok;
	@FXML Button cancel;
	
	@FXML TextField blackField;
	@FXML TextField whiteField;
	
	@FXML TextField whiteGameTime;
	@FXML TextField blackGameTime;
	
	@FXML ComboBox<String> gameType;
	ObservableList<String> list = FXCollections.observableArrayList("Normal Mode","Quick Chess");  //$NON-NLS-1$//$NON-NLS-2$
	
	public Counter newCounter = new Counter();
	
	public Stage secondaryStage = new Stage();
	public Scene scene;
	
	public Controler_Information(){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("InformationTab.fxml")); //$NON-NLS-1$
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public void onComboSelection(){
		String selected  = this.gameType.getSelectionModel().getSelectedItem();
		if (selected.equals("Normal Mode")) { //$NON-NLS-1$
			this.whiteGameTime.setDisable(true);
			this.blackGameTime.setDisable(true);
		}
		else if (selected.equals("Quick Chess")) { //$NON-NLS-1$
			this.whiteGameTime.setDisable(false);
			this.blackGameTime.setDisable(false);
		}
		
	}
	public void buttonContoler(ActionEvent e){
		if (e.getSource()==this.ok) {
			
			SharedVariables.blackName = this.blackField.getText().equals("") ? "Black Player"  : this.blackField.getText(); //$NON-NLS-1$ //$NON-NLS-2$
			SharedVariables.whiteName = this.whiteField.getText().equals("") ? "White Player"  : this.whiteField.getText(); //$NON-NLS-1$ //$NON-NLS-2$
			if (this.gameType.getSelectionModel().getSelectedItem() == null) {
				SharedVariables.isNormalGame = true;
			}
			
			else if (this.gameType.getSelectionModel().getSelectedItem().equals("Quick Chess")) { //$NON-NLS-1$
				SharedVariables.isNormalGame = false;
				try {
					SharedVariables.blackgameTime  = Integer.parseInt(this.blackGameTime.getText());
					SharedVariables.whitegameTime  = Integer.parseInt(this.whiteGameTime.getText());
					
				} catch (NumberFormatException e2) {
					e2.printStackTrace();
					SharedVariables.blackgameTime = 15;
					SharedVariables.whitegameTime = 15;
				}
			}
			else if (this.gameType.getSelectionModel().getSelectedItem().equals("Normal Mode")) { //$NON-NLS-1$
				SharedVariables.isNormalGame = true;
			}
			
			this.secondaryStage.close();
			Board_Controller bc = new Board_Controller();
			bc.showBoard();
			
		}
		if (e.getSource()==this.cancel) {
			System.exit(0);
		}
	}
	public void showInformation(){
		try {
			this.scene = new Scene(this);
			this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //$NON-NLS-1$
			this.secondaryStage.setScene(this.scene);
			this.secondaryStage.setTitle("Players Info."); //$NON-NLS-1$
			this.secondaryStage.initStyle(StageStyle.UNDECORATED);
			this.secondaryStage.setResizable(false);
			this.secondaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.gameType.setItems(this.list);
		this.whiteGameTime.setDisable(true);
		this.blackGameTime.setDisable(true);
		
	}

}
