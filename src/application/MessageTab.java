package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MessageTab extends Application {

	public static Stage secondaryStage = new Stage();
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MessageTab.fxml")); //$NON-NLS-1$
		Scene scene = new Scene(root);
		secondaryStage.setResizable(false);
		secondaryStage.setScene(scene);	
		secondaryStage.setTitle("Message Tab"); //$NON-NLS-1$
		secondaryStage.show();

	}
	

}
