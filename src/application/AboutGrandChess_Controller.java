package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AboutGrandChess_Controller extends AnchorPane {
	public Scene scene;
	public Stage secondaryStage = new Stage();
	
	public AboutGrandChess_Controller(){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutGrandChess_GUI.fxml")); //$NON-NLS-1$
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void show(){
		try {
			this.scene = new Scene(this);
			this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //$NON-NLS-1$
			this.secondaryStage.setScene(this.scene);
			this.secondaryStage.setTitle("About Grand-Chess."); //$NON-NLS-1$
			this.secondaryStage.initStyle(StageStyle.UTILITY);
			this.secondaryStage.initModality(Modality.APPLICATION_MODAL);
			this.secondaryStage.setResizable(false);
			this.secondaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

}
