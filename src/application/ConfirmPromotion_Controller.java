package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ConfirmPromotion_Controller extends AnchorPane {
	
	@FXML Button promote;
	@FXML Button notYet;
	
	public Stage stage = new Stage();
	public Scene scene = new Scene(this);
	
	public ArrayList<String> prison;
	public String secondPosition;
	public Board_Controller bc;
	
	public ConfirmPromotion_Controller(ArrayList<String> prison,String secondPosition,Board_Controller bc){
		this.prison = prison;
		this.secondPosition = secondPosition;
		this.bc = bc;
		this.stage.setScene(this.scene);
		this.stage.initOwner(new Controler_Information().secondaryStage.getOwner());
		this.stage.initModality(Modality.APPLICATION_MODAL);
		this.stage.setResizable(false);
		this.stage.initStyle(StageStyle.UNDECORATED);
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmPromotion_GUI.fxml")); //$NON-NLS-1$
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void onButtonAction(ActionEvent e){
		if (e.getSource() == this.promote) {
			new Promoter(this.prison,this.secondPosition,this.bc);
			this.stage.hide();
		}
		else if (e.getSource() == this.notYet) {
			this.stage.hide();
		}
	}
	public void showConfirmPromotion(){
		this.stage.show();
	}
	

}
