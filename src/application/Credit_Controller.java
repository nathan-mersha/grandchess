package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Credit_Controller extends AnchorPane {
	public @FXML Hyperlink christianPage;
	public @FXML Hyperlink awraDesign_Software;
	public @FXML Hyperlink awraDesign_Graphics;
	public @FXML Hyperlink awra_MainPage;
	
	public Scene scene;
	public Stage secondaryStage = new Stage();
	
	
	
	public Credit_Controller(){
		this.scene = new Scene(this);
		this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //$NON-NLS-1$
		this.secondaryStage.setScene(this.scene);
		this.secondaryStage.setTitle("Credits"); //$NON-NLS-1$
		this.secondaryStage.initStyle(StageStyle.UTILITY);
		this.secondaryStage.initModality(Modality.APPLICATION_MODAL);
		this.secondaryStage.setResizable(false);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Credit_GUI.fxml")); //$NON-NLS-1$
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void show(){
		try {
			
			this.secondaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void onHyperLinkClick(ActionEvent e){
		if (e.getSource() == this.christianPage) {
			String url = this.christianPage.getText();
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
			
		}
		else if (e.getSource() == this.awraDesign_Software) {
			String url = this.awraDesign_Software.getText();
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
			
		}
		else if (e.getSource() == this.awraDesign_Graphics) {
			String url = this.awraDesign_Graphics.getText();
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
			
		}
		else if (e.getSource() == this.awra_MainPage) {
			String url = this.awra_MainPage.getText();
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
			
	
		}
	}


}
