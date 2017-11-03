package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CheckMate_Controller extends AnchorPane implements Initializable {
	@FXML Button rematch;
	@FXML Button exit;
	@FXML Label messageL;
	
	public static String CHECKMATE = "Check-Mate"; //$NON-NLS-1$
	public static String STALEMATE = "Stale-Mate"; //$NON-NLS-1$
	public String inputMessage = null;
	
	public Stage stage = new Stage();
	public Scene scene = new Scene(this);
	public CheckMate_Controller(String message){
		this.stage.setScene(this.scene);
		this.stage.initOwner(new Controler_Information().secondaryStage.getOwner());
		this.stage.initModality(Modality.APPLICATION_MODAL);
		this.stage.setTitle("Check Mate"); //$NON-NLS-1$
		this.stage.setResizable(false);
		this.stage.initStyle(StageStyle.UNDECORATED);
		this.inputMessage = message;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckMate_GUI.fxml")); //$NON-NLS-1$
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public void onButtonAction(ActionEvent e){
		if (e.getSource().equals(this.rematch)) {
			this.stage.close();
			Board_Controller.onNewGame();
		}
		else if (e.getSource().equals(this.exit)) {
			System.exit(0);
		}
	}
	public void showPar(){
		
		this.stage.show();
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.messageL.setText(this.inputMessage);
	}

}
