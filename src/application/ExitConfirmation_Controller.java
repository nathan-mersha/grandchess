package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ExitConfirmation_Controller extends AnchorPane {
	@FXML Button exit;
	@FXML Button no;
	@FXML Button save;
	
	public Stage stage = new Stage();
	public Scene scene = new Scene(this);
	
	public ExitConfirmation_Controller(){
		
		this.stage.setScene(this.scene);
		this.stage.initOwner(new Controler_Information().secondaryStage.getOwner());
		this.stage.initModality(Modality.APPLICATION_MODAL);
		this.stage.setResizable(false);
		this.stage.setTitle("Exit Tab"); //$NON-NLS-1$
		this.stage.initStyle(StageStyle.UTILITY);
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ExitConfirmation_GUI.fxml")); //$NON-NLS-1$
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.save.setDisable(Board_Controller.gameSaved==false ? false : true); 
	}
	public void showPar(){
		this.stage.show();
	}
	
	public void onButtonAction(ActionEvent e){
		if (e.getSource() == this.exit) {
			System.exit(0);
		}
		else if (e.getSource() == this.no) {
			this.stage.close();
		}
		else if (e.getSource() == this.save) {
			this.stage.close();
			Board_Controller.saveFile();
		}
	}

}
