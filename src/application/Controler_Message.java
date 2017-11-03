package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controler_Message implements Initializable {
	
	@FXML Button exit;
	@FXML Button rematch;
	
	@FXML Label lostPlayerName;
	
	public Main newInfo = new Main();
	
	public void onButtonAction(ActionEvent e){
		if (e.getSource()==this.exit) {
			System.exit(0);
			
		}
		if (e.getSource()==this.rematch) {
			MessageTab.secondaryStage.hide();
			Board_Controller.onNewGame();
			
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.lostPlayerName.setText(SharedVariables.lostPlayerName);
		
	}

}
