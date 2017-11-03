package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Promoter {
	public String releasedPrisoner = null;
	ArrayList<String> filteredPrisoners = new ArrayList<>();
	
	public GridPane prisonContainer = new GridPane();
	public Stage primaryStage = new Stage();
	public boolean promoted = false;
	
	public Promoter(ArrayList<String> prison,String newPossition,Board_Controller bController){
		this.prisonContainer.setPadding(new Insets(5,5,5,5));
		this.prisonContainer.setHgap(10);
		
		Scene scene = new Scene(this.prisonContainer,500,75);
		this.primaryStage.setTitle("Promote Pawn"); //$NON-NLS-1$
		this.primaryStage.setResizable(false);
		this.primaryStage.setScene(scene);
//		primaryStage.initOwner(Main.secondaryStage.getOwner());
		this.primaryStage.initModality(Modality.APPLICATION_MODAL);
		this.primaryStage.initStyle(StageStyle.UNDECORATED);
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				event.consume();
			}
		});
		
		for (int i = 0; i < prison.size(); i++) {
			if (!(prison.get(i).equals("BLACK_PAWN") || prison.get(i).equals("WHITE_PAWN"))) {  //$NON-NLS-1$//$NON-NLS-2$
				String prisonerName = prison.get(i);
				if (!(this.filteredPrisoners.contains(prisonerName))) {
					this.filteredPrisoners.add(prisonerName);
				}
			}
		}
		///
		for (int i = 0; i < this.filteredPrisoners.size(); i++) {
			Button b = new Button();
			b.setId(this.filteredPrisoners.get(i));
			b.setPrefSize(50, 50);
			b.setGraphic(new ImageView(new Image("PRISONERS/"+this.filteredPrisoners.get(i)+".png")));  //$NON-NLS-1$ //$NON-NLS-2$
			b.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Promoter.this.releasedPrisoner = b.getId();
					
	            	Board_Controller.pieceMoves.theBoard.put(newPossition, Promoter.this.releasedPrisoner);
	            	Board_Controller.removePiecesFromBoard();
	            	Board_Controller.addPieceToBoard(newPossition, Promoter.this.releasedPrisoner);
					Board_Controller.populateFromHashMap(Board_Controller.pieceMoves.theBoard);
					
					Board_Controller.whitePrisonArray.remove(Promoter.this.releasedPrisoner);
	            	bController.populatePrisonFromArray();
	            	
	            	bController.blackLock.setImage(new Image("Icons/Lock_Locked.png")); //$NON-NLS-1$
	            	bController.whiteLock.setImage(new Image("Icons/Lock_Locked.png")); //$NON-NLS-1$
					Promoter.this.primaryStage.close();
				}
			});
			this.prisonContainer.add(b, i, 0);
		}
		///
		this.primaryStage.show();
	}

	public String getReleasedPrisoner() {
		return this.releasedPrisoner;
	}
	

}
