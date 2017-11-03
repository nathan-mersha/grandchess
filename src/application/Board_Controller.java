package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import champ.Champ;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * <h1>Description</h1>
 * The Board Controller class is responsible for controlling the main game environment, namely the board, the pieces and all the buttons.
 * The class's constructor is responsible for creating the GUI from a Hash map previously defined in another class, <code>PieceMoves.java</code>
 * Class extends from BorderPane, Which is the root pane in it's Board_GUI fxml file.
 * @author Nathan
 * @version 1.0.0
 * @category GUI
 * @see PieceMoves responsible for the model behind the scenes
 */
public class Board_Controller extends BorderPane implements Initializable {
	// Linkage of the panes with the fxml file See the fxml file "BoardController_GUI.fxml"
	
	@FXML public Pane p00_00; @FXML public Pane p00_01; @FXML public Pane p00_02; @FXML public Pane p00_03; @FXML public Pane p00_04; 
	@FXML public Pane p00_05; @FXML public Pane p00_06; @FXML public Pane p00_07; @FXML public Pane p00_08; @FXML public Pane p00_09; 
	
	@FXML public Pane p01_00; @FXML public Pane p01_01; @FXML public Pane p01_02; @FXML public Pane p01_03; @FXML public Pane p01_04; 
	@FXML public Pane p01_05; @FXML public Pane p01_06; @FXML public Pane p01_07; @FXML public Pane p01_08; @FXML public Pane p01_09; 
	
	@FXML public Pane p02_00; @FXML public Pane p02_01; @FXML public Pane p02_02; @FXML public Pane p02_03; @FXML public Pane p02_04; 
	@FXML public Pane p02_05; @FXML public Pane p02_06; @FXML public Pane p02_07; @FXML public Pane p02_08; @FXML public Pane p02_09; 
	
	@FXML public Pane p03_00; @FXML public Pane p03_01; @FXML public Pane p03_02; @FXML public Pane p03_03; @FXML public Pane p03_04; 
	@FXML public Pane p03_05; @FXML public Pane p03_06; @FXML public Pane p03_07; @FXML public Pane p03_08; @FXML public Pane p03_09; 
	
	@FXML public Pane p04_00; @FXML public Pane p04_01; @FXML public Pane p04_02; @FXML public Pane p04_03; @FXML public Pane p04_04; 
	@FXML public Pane p04_05; @FXML public Pane p04_06; @FXML public Pane p04_07; @FXML public Pane p04_08; @FXML public Pane p04_09; 
	
	@FXML public Pane p05_00; @FXML public Pane p05_01; @FXML public Pane p05_02; @FXML public Pane p05_03; @FXML public Pane p05_04; 
	@FXML public Pane p05_05; @FXML public Pane p05_06; @FXML public Pane p05_07; @FXML public Pane p05_08; @FXML public Pane p05_09; 
	
	@FXML public Pane p06_00; @FXML public Pane p06_01; @FXML public Pane p06_02; @FXML public Pane p06_03; @FXML public Pane p06_04; 
	@FXML public Pane p06_05; @FXML public Pane p06_06; @FXML public Pane p06_07; @FXML public Pane p06_08; @FXML public Pane p06_09; 
	
	@FXML public Pane p07_00; @FXML public Pane p07_01; @FXML public Pane p07_02; @FXML public Pane p07_03; @FXML public Pane p07_04; 
	@FXML public Pane p07_05; @FXML public Pane p07_06; @FXML public Pane p07_07; @FXML public Pane p07_08; @FXML public Pane p07_09; 
	
	@FXML public Pane p08_00; @FXML public Pane p08_01; @FXML public Pane p08_02; @FXML public Pane p08_03; @FXML public Pane p08_04; 
	@FXML public Pane p08_05; @FXML public Pane p08_06; @FXML public Pane p08_07; @FXML public Pane p08_08; @FXML public Pane p08_09; 
	
	@FXML public Pane p09_00; @FXML public Pane p09_01; @FXML public Pane p09_02; @FXML public Pane p09_03; @FXML public Pane p09_04; 
	@FXML public Pane p09_05; @FXML public Pane p09_06; @FXML public Pane p09_07; @FXML public Pane p09_08; @FXML public Pane p09_09; 
	
	// File Menu items
	@FXML MenuItem openGame;
	@FXML MenuItem saveGame;
	@FXML MenuItem close;
	// Game Menu items
	@FXML MenuItem singlePlayer;
	@FXML MenuItem multiPlayer;
	@FXML MenuItem resetTimer; // Change name to new game
	@FXML MenuItem setTimer;
	@FXML CheckMenuItem showPossibleMoves;
	@FXML MenuItem undo;
	// Help Menu item
	@FXML MenuItem aboutGrandChess;
	@FXML MenuItem credit;
	// Player Name Labels
	@FXML Label blackName;
	@FXML Label whiteName;
	//Progress bars
	@FXML ProgressBar blackProgress;
	@FXML ProgressBar whiteProgress;
	//player Timer Labels
	@FXML Label blackTimer;
	@FXML Label whiteTimer;
	// PrisonLock imageView
	@FXML ImageView blackLock;
	@FXML ImageView whiteLock;
	//Prison GridPane
	@FXML GridPane blackPrison;
	@FXML GridPane whitePrison;
	//Other Controllers
	@FXML ScrollPane history;
	//Buttons
	@FXML Button start;
	@FXML Button newGame;
	//Single player game modes
	@FXML RadioMenuItem easyMode;
	@FXML RadioMenuItem mediumMode;
	@FXML RadioMenuItem hardMode;
	//Player Side
	@FXML RadioMenuItem playerBlackSide;
	@FXML RadioMenuItem playerWhiteSide;
	//////Variables for counter
	public Thread t1;
	public Thread t2;
	public static int secStandard = 59;
	public static int second;
	public  int minute;
	public Label labelCounter;
	//////
	public int blackgameTime = 15;
	public int whitegameTime = 15;
	//////
	public boolean flag = true;
	//////
	public int totalGameTimeSecBlack = 0;
	public int totalGameTimeSecWhite = 0;
	//////
	public static final Color BLACK_ORIGINAL_BACKGROUND = Color.GRAY;
	public static final Color WHITE_ORIGINAL_BACKGROUND = Color.DARKGRAY;
	// White Moves First
	public static boolean isWhiteTurn = true;
	public static boolean isBlackTurn = false;
	///
	public static HashMap<String, Pane> paneCollection = new HashMap<>();
	public static PieceMoves pieceMoves = new PieceMoves();
	ArrayList<Pane> possibleMoves = new ArrayList<>();
	///
	public static Color blackSideColor = Color.GRAY;
	public static Color whiteSideColor = Color.DARKGRAY;
	
	public boolean pieceClicked = false;
	public Pane clickedPane = null;
	
	public static ArrayList<String> blackPrisonArray = new ArrayList<>();
	public static ArrayList<String> whitePrisonArray = new ArrayList<>();
	
	public static Stage boardStage = new Stage();
	public static Scene boardScene;
	public boolean showPossibleMoves_bool = true;
	/////////
	public boolean singlePlayerMode = true;
	public String gameModeType = Champ.GAMEMODE_EASY;
	public String champSide = PieceMoves.BLACK_SIDE;
	/////////
	/**
	 * <h1>Description</h1>
	 * <p>Method is responsible for implementing and handling events that come from, the new game button and the start button.</p>
	 * @param e {@link ActionEvent}
	 * @return {@link Void}
	 */
	@SuppressWarnings("deprecation")
	public void onButtonAction(ActionEvent e){
		if (e.getSource()==this.start) {
			this.start.setDisable(true);
			this.t2.resume();
		}
		else if (e.getSource()==this.newGame) {
			onNewGame();
		}
	}
	public void onGameModeSelection(ActionEvent e){
		if (e.getSource() == easyMode) {
			gameModeType = Champ.GAMEMODE_EASY;
		}
		else if (e.getSource() == mediumMode) {
			gameModeType = Champ.GAMEMODE_MEDIUM;
		}
		else if (e.getSource() == hardMode) {
			gameModeType = Champ.GAMEMODE_HARD;
		}
	}
	/**
	 * <h1>Description</h1>
	 * Method executed when the new game button is clicked.It clears the current session of game and prepares the environment.
	 * <h2>Note</h2>
	 * The board is not restarted all over again but it's original conditions are restored to default.However the controller information 
	 * is initiated again for collecting personal datas.
	 * @return void
	 * @see Controler_Information
	 */
	public static void onNewGame(){
		blackPrisonArray.clear();
		whitePrisonArray.clear();
		boardStage.close();
		boardStage.hide();
		pieceMoves.intialChessPiecesSet();
		isWhiteTurn = true; 
		isBlackTurn = false;
		Controler_Information ci = new Controler_Information();
		ci.showInformation();
	}
	public static FileChooser fileDialog = new FileChooser();
	public static boolean gameSaved = false;
	/**
	 * <h1>Description</h1>
	 * Method executed when the save game is invoked either from the file menu or from the accelerator.
	 * Game is saved with an extension of .gcf for "Grand Chess File".Player names and whose turn it is, is also
	 * included with the game saving.
	 * @return void
	 */
	public static void saveFile() {
		fileDialog.setTitle("Save Game"); //$NON-NLS-1$
		fileDialog.setInitialDirectory(new File("C:/Users/Nathan/Documents")); //$NON-NLS-1$
		fileDialog.getExtensionFilters().add(new ExtensionFilter("Grand Chess File", "*.gcf")); //$NON-NLS-1$ //$NON-NLS-2$
		fileDialog.setInitialFileName("SavedGame.gcf"); //$NON-NLS-1$
		File file = fileDialog.showSaveDialog(boardStage); 
		if (file == null) {
			return;
		}
		try {
			Path target = file.toPath();
			Charset charSet = Charset.forName("US-ASCII"); //$NON-NLS-1$
			
			try(BufferedWriter writer = Files.newBufferedWriter(target,charSet)) {
				Set<String> set = pieceMoves.theBoard.keySet();
				Iterator<String> iterator = set.iterator();
				String whoseTurn = isWhiteTurn ? "WHITE" : "BLACK"; //$NON-NLS-1$ //$NON-NLS-2$
				writer.write(whoseTurn);
				writer.newLine();
				writer.write(SharedVariables.blackName);
				writer.newLine();
				writer.write(SharedVariables.whiteName);
				writer.newLine();
				while (iterator.hasNext()) {
					String possition = iterator.next();
					String pieceName = pieceMoves.theBoard.get(possition);
					writer.write(possition+"-"+pieceName, 0, possition.length()+pieceName.length()+1); //$NON-NLS-1$
					writer.newLine();
				}
				writer.close();
			} catch (Exception e) {
				e.getStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		gameSaved = true;
	}
	/**
	 * <h1>Description</h1>
	 * <p>The method executed at opening a file. This method is responsible for restoring the default data first saved by the players.
	 * The file extension for the grand chess games is ".gcf". The method is also responsible for stoping the time counting treads
	 * if they are found to be not null.</p>
	 * @return null
	 * 
	 */
	@SuppressWarnings("deprecation")
	public void openFile() {
		fileDialog.setTitle("Open"); //$NON-NLS-1$
		fileDialog.setInitialDirectory(new File("C:\\Users")); //$NON-NLS-1$
		fileDialog.getExtensionFilters().add(new ExtensionFilter("Grand Chess File", "*.gcf")); //$NON-NLS-1$ //$NON-NLS-2$
		File file = fileDialog.showOpenDialog(boardStage);
		Path readingFrom = Paths.get(file.getPath());
		Charset charSet = Charset.forName("US-ASCII"); //$NON-NLS-1$
		HashMap<String, String> savedMap = new HashMap<>();
		
		try(BufferedReader reader = Files.newBufferedReader(readingFrom,charSet)) {
			String line = null;
			String whoseTurn = reader.readLine();
			String savedBlackName = reader.readLine();
			String savedWhiteName = reader.readLine();
			this.blackName.setText(savedBlackName);
			this.whiteName.setText(savedWhiteName);
			if (whoseTurn.equals("BLACK")) { //$NON-NLS-1$
				isWhiteTurn = false;
				isBlackTurn = true;
			}
			else if (whoseTurn.equals("WHITE")) { //$NON-NLS-1$
				isWhiteTurn = true;
				isBlackTurn = false;
			}
			while ((line = reader.readLine()) != null) {
				String mapPossiton = line.substring(0, 5);
				String mapPieceName = line.substring(6, line.length());
				savedMap.put(mapPossiton, mapPieceName);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pieceMoves.theBoard.clear();
		pieceMoves.theBoard.putAll(savedMap);
		this.possibleMoves.clear();
		removePiecesFromBoard();
		populateFromHashMap(pieceMoves.theBoard);
		pane_Enabler_Disabler();
		this.v.getChildren().clear();
		
		blackPrisonArray.clear();
		whitePrisonArray.clear();
		populatePrisonFromArray();
		///
		if (t1 != null || t2 != null) {
			t1.stop();
			t2.stop();
		}
	}
	/**
	 * <h1>Description</h1>
	 * Method is responsible for reading which menu item generated the action event.And consequently apply the predefined methods thereof.
	 * @param e ActionEvent
	 * @return <code>null</code>
	 * @category EventSorter
	 */
	public void onMenuAction(ActionEvent e){
		
		if (e.getSource() == this.openGame) {
			openFile();
		}
		else if (e.getSource() == this.saveGame) {
			saveFile();
		}
		else if (e.getSource() == this.close) {
			closeBoard();
		}
		else if (e.getSource() == this.singlePlayer) {
			singlePlayerMode = true;
		}
		else if (e.getSource() == this.multiPlayer) {
			singlePlayerMode = false;
		}
		else if (e.getSource() == this.resetTimer) {
			onNewGame();
		}
		else if (e.getSource() == this.setTimer) {
			onNewGame();
		}
		else if (e.getSource() == this.showPossibleMoves) {
			this.showPossibleMoves_bool = this.showPossibleMoves.isSelected();
		}
		else if (e.getSource() == this.aboutGrandChess) {
			new AboutGrandChess_Controller().show();
		}
		else if (e.getSource() == this.credit) {
			new Credit_Controller().show();
		}
		else if (e.getSource() == this.undo) {
			undoMove();
		}
	}
	/**
	 * <h1>Description</h1>
	 * Method responsible for undoing move and switching to the right side.
	 * The method is also responsible for clearing any history related to any capture or movement.
	 * @return void
	 */
	public void undoMove(){
		turnSwitcher();
		String[] recentHistory = this.historyHandler.getLattestHistory();
		pieceMoves.theBoard.put(recentHistory[0], recentHistory[1]);
		pieceMoves.theBoard.put(recentHistory[2], recentHistory[3]);
		if (!(recentHistory[3].equals(PieceMoves.UNOCCUPIED))) {
			String side = recentHistory[3].substring(0, 5);
			if (side.equals("BLACK")) {
				blackPrisonArray.remove(blackPrisonArray.size()-1);
				populatePrisonFromArray();
			}
			else if (side.equals("WHITE")) {
				whitePrisonArray.remove(whitePrisonArray.size()-1);
				populatePrisonFromArray();
			}
		}
		this.undo.setDisable(this.historyHandler.anyHistoryLeft() ? false : true);
		this.v.getChildren().remove(this.v.getChildren().size()-1);
		removePiecesFromBoard();
		populateFromHashMap(pieceMoves.theBoard);
	}
	/**
	 * <h1>Description</h1>
	 * This method is responsible for showing the playing board, and assigns accelerators for undo and save actions.
	 * @return void
	 * @see #assignAccelerators()
	 */
	public void showBoard(){
		boardScene = new Scene(this);
		boardStage.setScene(boardScene);
		boardStage.setTitle("Grand-Chess V 1.0.0"); //$NON-NLS-1$
		boardStage.setResizable(false);
		boardStage.getIcons().addAll(new Image("Icons/icon3232.png"),new Image("Icons/icon6464.png")); //$NON-NLS-1$ //$NON-NLS-2$
		boardStage.show();
		assignAccelerators();
	}
	/**
	 * <h1>Description</h1>
	 * Method called upon exit request.
	 * @return void
	 * @see ExitConfirmation_Controller
	 */
	public static void closeBoard(){
		new ExitConfirmation_Controller().showPar();
	}
	/**
	 * <h1>Description</h1>
	 * Method responsible for switching the counter.Resumes the thread that was suspended, and suspends the thread that was alive
	 * @return void
	 */
	@SuppressWarnings("deprecation")
	public void swithGame_Counter() {
		if (this.flag==true) {
			this.t2.suspend();
			this.t1.resume();
			this.flag = !this.flag;
		}
		else if (this.flag==false) {
			this.t1.suspend();
			this.t2.resume();
			this.flag = !this.flag;
		}
	}
	/**
	 * <h1>Description</h1>
	 * Method called when the exit confirmation tab returns ok.
	 * @return void
	 */
	public static void onCloseGame(){
		System.exit(0);
	}
	/**
	 * <h1>Description</h1>
	 * This method assigns accelerator for undo and save game.
	 * @return void
	 */
	private void assignAccelerators(){
		// Assigning accelerator for undo.
		KeyCombination keyComb = new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				undoMove();
			}
		};
		boardScene.getAccelerators().put(keyComb, runnable);
		//Assigning accelerator for save
		KeyCombination keyComb2 = new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN);
		Runnable runnable2 = new Runnable() {
			@Override
			public void run() {
				saveFile();
			}
		};
		boardScene.getAccelerators().put(keyComb2, runnable2);
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ToggleGroup groupGameMode = new ToggleGroup();
		easyMode.setToggleGroup(groupGameMode);
		mediumMode.setToggleGroup(groupGameMode);
		hardMode.setToggleGroup(groupGameMode);
		ToggleGroup groupPlayerSide = new ToggleGroup();
		playerBlackSide.setToggleGroup(groupPlayerSide);
		playerWhiteSide.setToggleGroup(groupPlayerSide);
		
		this.undo.setDisable(true);
		
		this.blackName.setText(SharedVariables.blackName);
		this.whiteName.setText(SharedVariables.whiteName);
		
		if (SharedVariables.isNormalGame == true) {
			this.start.setDisable(true);
			this.blackTimer.setText("No Time Limit"); //$NON-NLS-1$
			this.whiteTimer.setText("No Time Limit"); //$NON-NLS-1$
		}
		if (SharedVariables.isNormalGame == false) {
			this.totalGameTimeSecBlack = SharedVariables.blackgameTime*60;
			this.totalGameTimeSecWhite = SharedVariables.whitegameTime*60;
			
			this.blackTimer.setText(SharedVariables.blackgameTime + "min."); //$NON-NLS-1$
			this.whiteTimer.setText(SharedVariables.whitegameTime + "min."); //$NON-NLS-1$
			
			this.blackgameTime = SharedVariables.blackgameTime;
			this.whitegameTime = SharedVariables.whitegameTime;
			
			Platform.runLater(new  Runnable() {
				@Override
				@SuppressWarnings("deprecation")
				public void run() {
					QuickChessTimeCounter blackGamer = new QuickChessTimeCounter(Board_Controller.this.blackgameTime-1,SharedVariables.blackName);
					QuickChessTimeCounter whiteGamer = new QuickChessTimeCounter(Board_Controller.this.whitegameTime-1,SharedVariables.whiteName);
					blackGamer.timeInfo.addListener(new ChangeListener<Object>() {
						@Override
						public void changed(ObservableValue<? extends Object> observable, Object oldValue,
								Object newValue) {
							Platform.runLater(new  Runnable() {
								@Override
								public void run() {
									Board_Controller.this.blackTimer.setText(blackGamer.timeInfo.get());
								}
							});
						}
					});
					blackGamer.remainingGameTimeSec.addListener(new ChangeListener<Object>() {
						@Override
						public void changed(ObservableValue<? extends Object> observable, Object oldValue,
								Object newValue) {
							
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									Board_Controller.this.blackProgress.setProgress(fractionReturner(Board_Controller.this.totalGameTimeSecBlack, blackGamer.remainingGameTimeSec.get()));
								}
							});
						}
					});
					whiteGamer.remainingGameTimeSec.addListener(new ChangeListener<Object>() {

						@Override
						public void changed(ObservableValue<? extends Object> observable, Object oldValue,
								Object newValue) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									Board_Controller.this.whiteProgress.setProgress(fractionReturner(Board_Controller.this.totalGameTimeSecWhite, whiteGamer.remainingGameTimeSec.get()));
								}
							});
						}
					});
					whiteGamer.timeInfo.addListener(new ChangeListener<Object>() {
						@Override
						public void changed(ObservableValue<? extends Object> observable, Object oldValue,
								Object newValue) {
							
							Platform.runLater(new  Runnable() {
								@Override
								public void run() {
									Board_Controller.this.whiteTimer.setText(whiteGamer.timeInfo.get());
								}
							});
						}
					});
					Board_Controller.this.t1 = new Thread(blackGamer);
					Board_Controller.this.t2 = new Thread(whiteGamer);
					Board_Controller.this.t1.setDaemon(true);
					Board_Controller.this.t2.setDaemon(true);
					
					Board_Controller.this.t1.start();
					Board_Controller.this.t2.start();
					Board_Controller.this.t1.suspend();
					Board_Controller.this.t2.suspend();
				}
			});
		}
	}
	/**
	 * <h1>Description</h1>
	 * Returns Fraction.
	 * @param full
	 * @param fraction
	 * @return
	 */
	public static double fractionReturner(double full,double fraction){return fraction/full;}
	/**
	 * <h1>Description</h1>
	 * Constructor responsible for connecting the hash map from the {@link PieceMoves} to the Gui
	 * of the panes.
	 */
	public Board_Controller(){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Board_GUI.fxml")); //$NON-NLS-1$
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setIdToPanes();
		init_paneCollection();
		populateFromHashMap(pieceMoves.theBoard);
		disableAllSide(PieceMoves.BLACK);
		
		boardStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				event.consume();
				new ExitConfirmation_Controller().showPar();
			}
		});
	}
	/**
	 * <h1>Description</h1>
	 * Method remove all pieces from the Board.
	 * @return void
	 */
	public static void removePiecesFromBoard(){
		Set<String> set = paneCollection.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String s = iterator.next();
			Pane pane = paneCollection.get(s);
			if (pane.getChildren().size() == 1) {
				pane.getChildren().remove(0);
			}
		}
	}
	/**
	 * <h1>Description</h1>
	 * Method responsible for populating the GUI Board from the data hash map provided on the parameter.
	 * @return void
	 * @see #addPieceToBoard(String, String)
	 * @param theHashMap
	 */
	public static void populateFromHashMap(HashMap<String, String> theHashMap) {
		HashMap<String, String> map = theHashMap;
		Set<String> set = map.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String coordinate = iterator.next();
			String piece = map.get(coordinate);
			addPieceToBoard(coordinate, piece);
		}
	}
	/**
	 * <h1>Description</h1>
	 * Method responsible for populating the black and white prison gui from a black and white prison array.
	 * @return void
	 */
	public void populatePrisonFromArray(){
		this.blackPrison.getChildren().clear();
		this.whitePrison.getChildren().clear();
		int rowB = 0;
		int columnB = 0;
		for (int i = 0; i < blackPrisonArray.size(); i++) {
			String prisonerName = blackPrisonArray.get(i);
			Image img = new Image("Captured/"+prisonerName +".png"); //$NON-NLS-1$ //$NON-NLS-2$
			ImageView view = new ImageView(img);
			if (columnB>6) {
				rowB++;
				columnB = 0;
			}
			this.blackPrison.add(view, columnB, rowB);
			columnB++;
		}
		int rowW = 0;
		int columnW = 0;
		for (int i = 0; i < whitePrisonArray.size(); i++) {
			String prisonerName = whitePrisonArray.get(i);
			Image img = new Image("Captured/"+prisonerName +".png"); //$NON-NLS-1$ //$NON-NLS-2$
			ImageView view = new ImageView(img);
			
			if (columnW>6) {
				rowW++;
				columnW = 0;
			}
			this.whitePrison.add(view, columnW, rowW);
			columnW++;
		}
	}
	/**
	 * <h1>Description</h1>
	 * Method puts the first initial positions of the grand chess pieces in place
	 * @return void
	 */
	public void init_paneCollection(){
		
		paneCollection.put("p00_00", this.p00_00); paneCollection.put("p00_01", this.p00_01); paneCollection.put("p00_02", this.p00_02); paneCollection.put("p00_03", this.p00_03); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p00_04", this.p00_04); paneCollection.put("p00_05", this.p00_05); paneCollection.put("p00_06", this.p00_06); paneCollection.put("p00_07", this.p00_07); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p00_08", this.p00_08); paneCollection.put("p00_09", this.p00_09); //$NON-NLS-1$ //$NON-NLS-2$
		
		paneCollection.put("p01_00", this.p01_00); paneCollection.put("p01_01", this.p01_01); paneCollection.put("p01_02", this.p01_02); paneCollection.put("p01_03", this.p01_03); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p01_04", this.p01_04); paneCollection.put("p01_05", this.p01_05); paneCollection.put("p01_06", this.p01_06); paneCollection.put("p01_07", this.p01_07); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p01_08", this.p01_08); paneCollection.put("p01_09", this.p01_09); //$NON-NLS-1$ //$NON-NLS-2$
		
		paneCollection.put("p02_00", this.p02_00); paneCollection.put("p02_01", this.p02_01); paneCollection.put("p02_02", this.p02_02); paneCollection.put("p02_03", this.p02_03); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p02_04", this.p02_04); paneCollection.put("p02_05", this.p02_05); paneCollection.put("p02_06", this.p02_06); paneCollection.put("p02_07", this.p02_07); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p02_08", this.p02_08); paneCollection.put("p02_09", this.p02_09); //$NON-NLS-1$ //$NON-NLS-2$
		
		paneCollection.put("p03_00", this.p03_00); paneCollection.put("p03_01", this.p03_01); paneCollection.put("p03_02", this.p03_02); paneCollection.put("p03_03", this.p03_03); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p03_04", this.p03_04); paneCollection.put("p03_05", this.p03_05); paneCollection.put("p03_06", this.p03_06); paneCollection.put("p03_07", this.p03_07); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p03_08", this.p03_08); paneCollection.put("p03_09", this.p03_09); //$NON-NLS-1$ //$NON-NLS-2$
		
		paneCollection.put("p04_00", this.p04_00); paneCollection.put("p04_01", this.p04_01); paneCollection.put("p04_02", this.p04_02); paneCollection.put("p04_03", this.p04_03); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p04_04", this.p04_04); paneCollection.put("p04_05", this.p04_05); paneCollection.put("p04_06", this.p04_06); paneCollection.put("p04_07", this.p04_07); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p04_08", this.p04_08); paneCollection.put("p04_09", this.p04_09); //$NON-NLS-1$ //$NON-NLS-2$
		
		paneCollection.put("p05_00", this.p05_00); paneCollection.put("p05_01", this.p05_01); paneCollection.put("p05_02", this.p05_02); paneCollection.put("p05_03", this.p05_03); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p05_04", this.p05_04); paneCollection.put("p05_05", this.p05_05); paneCollection.put("p05_06", this.p05_06); paneCollection.put("p05_07", this.p05_07); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p05_08", this.p05_08); paneCollection.put("p05_09", this.p05_09); //$NON-NLS-1$ //$NON-NLS-2$
		
		paneCollection.put("p06_00", this.p06_00); paneCollection.put("p06_01", this.p06_01); paneCollection.put("p06_02", this.p06_02); paneCollection.put("p06_03", this.p06_03); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p06_04", this.p06_04); paneCollection.put("p06_05", this.p06_05); paneCollection.put("p06_06", this.p06_06); paneCollection.put("p06_07", this.p06_07); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p06_08", this.p06_08); paneCollection.put("p06_09", this.p06_09); //$NON-NLS-1$ //$NON-NLS-2$
		
		paneCollection.put("p07_00", this.p07_00); paneCollection.put("p07_01", this.p07_01); paneCollection.put("p07_02", this.p07_02); paneCollection.put("p07_03", this.p07_03); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p07_04", this.p07_04); paneCollection.put("p07_05", this.p07_05); paneCollection.put("p07_06", this.p07_06); paneCollection.put("p07_07", this.p07_07); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p07_08", this.p07_08); paneCollection.put("p07_09", this.p07_09); //$NON-NLS-1$ //$NON-NLS-2$
		
		paneCollection.put("p08_00", this.p08_00); paneCollection.put("p08_01", this.p08_01); paneCollection.put("p08_02", this.p08_02); paneCollection.put("p08_03", this.p08_03); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p08_04", this.p08_04); paneCollection.put("p08_05", this.p08_05); paneCollection.put("p08_06", this.p08_06); paneCollection.put("p08_07", this.p08_07); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p08_08", this.p08_08); paneCollection.put("p08_09", this.p08_09); //$NON-NLS-1$ //$NON-NLS-2$
		
		paneCollection.put("p09_00", this.p09_00); paneCollection.put("p09_01", this.p09_01); paneCollection.put("p09_02", this.p09_02); paneCollection.put("p09_03", this.p09_03); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p09_04", this.p09_04); paneCollection.put("p09_05", this.p09_05); paneCollection.put("p09_06", this.p09_06); paneCollection.put("p09_07", this.p09_07); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		paneCollection.put("p09_08", this.p09_08); paneCollection.put("p09_09", this.p09_09); //$NON-NLS-1$ //$NON-NLS-2$
		
		
	}
	/**
	 * <h1>Description</h1>
	 * Method responsible for creating a proper chess movement language including both capture and non capture moves.The method also integrates
	 * a picture of which piece was responsible for the movement.
	 * @param theMovingPiece
	 * @param initialPosition
	 * @param secondPosition
	 * @return {@link Pane}
	 */
	public static Pane history(String theMovingPiece,String initialPosition,String secondPosition){
		Pane pane = new Pane();
		class historyHelper{
			/**
			 * <h1>Description</h1>
			 * Inner method is responsible for parsing the x and y coordinate to a chess movement language.
			 * @param pos
			 * @return String
			 */
			public String toChessString_Conv(String pos){
				String[] letters = {"a","b","c","d","e","f","g","h","i","j"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$
				int x = Integer.parseInt(pos.substring(0, 2));
				int y = Integer.parseInt(pos.substring(3, 5));
				String chessX = letters[x];
				String chessY = Integer.toString(y+1);
				String converted = chessX+chessY;
				return converted;
			}
			/**
			 * <h1>Description</h1>
			 * Method evaluates the second position provided on the parameter and if it's any thing but <code>UNOCCUPIED</code> 
			 * returns that it is in fact a captured move.
			 * @param secPos
			 * @return boolean
			 */
			public boolean isCaptureMove(String secPos){
				boolean b = false;
				if (!(pieceMoves.theBoard.get(secPos).equals(PieceMoves.UNOCCUPIED))) {
					b = true;
				}
				return b;
			}
		}
		historyHelper helper = new historyHelper();
		// Defines a capture move
		if (helper.isCaptureMove(secondPosition)) {
			Label l = new Label();
			l.setFont(new Font(12));
		
			l.setText(" " + helper.toChessString_Conv(initialPosition) +" x "+ helper.toChessString_Conv(secondPosition)); //$NON-NLS-1$ //$NON-NLS-2$
			HBox hb = new HBox();
			ImageView img1 = new ImageView(new Image("Captured/" + theMovingPiece + ".png")); //$NON-NLS-1$ //$NON-NLS-2$
			ImageView img2 = new ImageView(new Image("Captured/" + pieceMoves.theBoard.get(secondPosition)+".png")); //$NON-NLS-1$ //$NON-NLS-2$
			
			img1.setScaleX(0.7);
			img1.setScaleY(0.7);
			img2.setScaleX(0.7);
			img2.setScaleY(0.7);
			
			hb.getChildren().addAll(img1,new Label("x"),img2,new Label(":"),l); //$NON-NLS-1$ //$NON-NLS-2$
			pane.getChildren().add(hb);
		}
		// Defines a non-capture move
		else if (!(helper.isCaptureMove(secondPosition))) {
			Label l = new Label();
			l.setFont(new Font(12));
			
			l.setText("  " + helper.toChessString_Conv(initialPosition) + " - " + helper.toChessString_Conv(secondPosition)); //$NON-NLS-1$ //$NON-NLS-2$
			HBox hb = new HBox();
			ImageView img1 = new ImageView(new Image("Captured/" + theMovingPiece + ".png")); //$NON-NLS-1$ //$NON-NLS-2$
			img1.setScaleX(0.7);
			img1.setScaleY(0.7);
			img1.setOpacity(70);
			hb.getChildren().addAll(img1,new Label(" : "),l); //$NON-NLS-1$
			pane.getChildren().add(hb);
		}
		pane.setOpacity(50);
		return pane;
	}
	VBox v = new VBox(7);
	HistoryHandler historyHandler = new HistoryHandler(pieceMoves.theBoard);
	/**
	 * <h1>Description</h1>
	 * Method listens any click happening on the playing area, and acts accordingly.Method identifies all combination of appropriate and inappropriate moves
	 * and acts accordingly.
	 * @param event
	 */
	@SuppressWarnings({ "deprecation" })
	public void clickOnArea(MouseEvent event){
		this.start.setDisable(true);
		if (this.pieceClicked == false) {
			Pane pane = (Pane) event.getSource();
			this.clickedPane = pane;
			String paneId = pane.getId();
			
			String piecePossition = paneId.substring(1, 6);
			String piece = pieceMoves.theBoard.get(piecePossition);
			
			ArrayList<String> possMoves = PieceMoves.filterCheckMoves(pieceMoves.piecePossibleMoves(piece, piecePossition), piece, piecePossition, pieceMoves);
			ArrayList<String> possMoves_Captured = PieceMoves.filterCheckMoves(pieceMoves.piecePossibleMoves_Captured(), piece, piecePossition, pieceMoves);
			
			for (int i = 0; i < possMoves.size(); i++) {
				Pane p = paneCollection.get("p"+possMoves.get(i)); //$NON-NLS-1$
				this.possibleMoves.add(p);
			}
			boolean bol = pieceMoves.pawnForPromotion;
			
			if (this.showPossibleMoves_bool) {
				glowPossitions(possMoves,bol,Color.VIOLET);
				glowPossitions(possMoves_Captured,true,Color.INDIANRED);
			}
			else if (this.showPossibleMoves_bool == false) {
				this.clickedPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(2), new Insets(3))));
			}
			
			this.pieceClicked = true;
			enableAllSide(PieceMoves.BLACK);
        	enableAllSide(PieceMoves.WHITE);
		}
		else if (this.pieceClicked == true) {
			///
			if (isBlackTurn == true) {
     			enableAllSide(PieceMoves.BLACK);
     			disableAllSide(PieceMoves.WHITE);
     		}
             else if (isWhiteTurn == true) {
     			enableAllSide(PieceMoves.WHITE);
     			disableAllSide(PieceMoves.BLACK);
     		}
        	///
			Pane secondClickedPane = (Pane) event.getSource();
			String secondPossition = secondClickedPane.getId().substring(1,6);
			String paneId = this.clickedPane.getId();
			String piecePossition = paneId.substring(1, 6);
			String piece = pieceMoves.theBoard.get(piecePossition);

			boardToOriginalLook();
			
            if (this.possibleMoves.contains(secondClickedPane)) {
            	gameSaved = false;
            	this.undo.setDisable(false);
            	String secondPositionsPreviousPiece = pieceMoves.theBoard.get(secondPossition);
            	
            	moving_FootPrint(this.clickedPane, secondClickedPane);
            	pieceMoves.removePiece(piecePossition);
            	
            	Pane pae = history(piece, piecePossition, secondPossition);
            	this.v.getChildren().addAll(pae);
            	this.history.setPadding(new Insets(5));
            	this.history.setContent(this.v);
            	this.historyHandler.addHistory(piecePossition, piece, secondPossition, secondPositionsPreviousPiece);
            	
            	removePiecesFromBoard();
            	String toMoved_Piece = pieceMoves.theBoard.get(secondPossition);
            	
            	if (toMoved_Piece.substring(0, 5).equals(PieceMoves.BLACK)) {
        			blackPrisonArray.add(toMoved_Piece);
        			if ( !(toMoved_Piece.equals(PieceMoves.BLACK_PAWN))) {
						pieceMoves.blackPrison.add(toMoved_Piece);
					}
        		}
        		else if (toMoved_Piece.subSequence(0, 5).equals(PieceMoves.WHITE)) {
        			whitePrisonArray.add(toMoved_Piece);
        			if ( !(toMoved_Piece.equals(PieceMoves.WHITE_PAWN))) {
						pieceMoves.whitePrison.add(toMoved_Piece);
					}
        		}
            	// piece mover
            	pieceMoves.theBoard.put(secondPossition, piece);
            	this.pieceClicked = false;
    			this.possibleMoves.clear();
            	//Refresh and Populate
    			populateFromHashMap(pieceMoves.theBoard);
            	populatePrisonFromArray();
            	/////
            	turnSwitcher();
                
                if (piece.equals(PieceMoves.WHITE_PAWN)) {
                	if (secondPossition.substring(3,5).equals("00")) { //$NON-NLS-1$
                		new Promoter(whitePrisonArray,secondPossition,this);
    					this.whiteLock.setImage(new Image("Icons/Lock_Unlocked.png")); //$NON-NLS-1$
					}
                	else if ((secondPossition.substring(3,5).equals("01")) || (secondPossition.substring(3,5).equals("02"))) { //$NON-NLS-1$ //$NON-NLS-2$
                		if (pieceMoves.pawnForPromotion) {
                			new ConfirmPromotion_Controller(whitePrisonArray, secondPossition, this).showConfirmPromotion();
    						this.whiteLock.setImage(new Image("Icons/Lock_Unlocked.png")); //$NON-NLS-1$
						}
					}
				}
                else if (piece.equals(PieceMoves.BLACK_PAWN)) {
                	if (secondPossition.substring(3,5).equals("09")) { //$NON-NLS-1$
                		
                		new Promoter(blackPrisonArray,secondPossition,this);
                        this.blackLock.setImage(new Image("Icons/Lock_Unlocked.png")); //$NON-NLS-1$
						
					}
                	else if ((secondPossition.substring(3,5).equals("07")) || (secondPossition.substring(3,5).equals("08"))) { //$NON-NLS-1$ //$NON-NLS-2$
						if (pieceMoves.pawnForPromotion) {
							new ConfirmPromotion_Controller(blackPrisonArray, secondPossition, this).showConfirmPromotion();
							this.whiteLock.setImage(new Image("Icons/Lock_Unlocked.png")); //$NON-NLS-1$
						}
					}
				}
			}
            ///////
            else if (!(this.possibleMoves.contains(secondClickedPane))) {
				this.possibleMoves.clear();
				this.pieceClicked = false;
			}
            if (!(pieceMoves.isKingInCheck(PieceMoves.WHITE_SIDE)) && !(pieceMoves.isKingInCheck(PieceMoves.BLACK_SIDE))) {
            	if (pieceMoves.isStaleGame()) {
            		new CheckMate_Controller(CheckMate_Controller.STALEMATE).showPar();
            		this.t1.suspend();
					this.t2.suspend();
				}
			}
            if (pieceMoves.isKingInCheck(PieceMoves.WHITE_SIDE)) {
				String pos = pieceMoves.getPiecePossition(PieceMoves.WHITE_KING);
				String paneName = "p"+pos; //$NON-NLS-1$
				Pane p = paneCollection.get(paneName);
				p.setEffect(new Glow(0.5));
				p.setBackground(new Background(new BackgroundFill(Color.INDIANRED, new CornerRadii(2), new Insets(3))));
				if (pieceMoves.isKingCheckMate(PieceMoves.WHITE_SIDE,pieceMoves.theBoard) == true) {
					new CheckMate_Controller(CheckMate_Controller.CHECKMATE).showPar();
					this.t1.suspend();
					this.t2.suspend();
				}
			}
            else if (pieceMoves.isKingInCheck(PieceMoves.BLACK_SIDE)) {
				String pos = pieceMoves.getPiecePossition(PieceMoves.BLACK_KING);
				String paneName = "p"+pos; //$NON-NLS-1$
				Pane p = paneCollection.get(paneName);
				p.setEffect(new Glow(0.7));
				p.setBackground(new Background(new BackgroundFill(Color.INDIANRED, new CornerRadii(2), new Insets(3))));
				
				if (pieceMoves.isKingCheckMate(PieceMoves.BLACK_SIDE,pieceMoves.theBoard) == true) {
					new CheckMate_Controller(CheckMate_Controller.CHECKMATE).showPar();
					this.t1.suspend();
					this.t2.suspend();
				}
			}
		}
		if (singlePlayerMode == true && isChampsTurn()) {
			disableAllSide(PieceMoves.BLACK_SIDE);
			disableAllSide(PieceMoves.WHITE_SIDE);
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					Champ champ = new Champ(champSide, pieceMoves.theBoard, gameModeType);
					String[] proposedMove = champ.bestProposedMove();
					
					
					System.out.println("Proposed move is : Piece name : " + proposedMove[0] + " initial position : " + proposedMove[1] + " final position : " + proposedMove[2] );
					
					
					//Refresh and Populate
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							removePiecesFromBoard();
							pieceMoves.movePiece(proposedMove[2], proposedMove[1], proposedMove[0]);
							populateFromHashMap(pieceMoves.theBoard);
				        	populatePrisonFromArray();
				        	turnSwitcher();
						}
					});
				}
			});
			System.out.println("<><><><><><><>Thread started<><><><><><><>");
			t.start();
		}
		
	}
	public boolean isChampsTurn(){
		boolean b = false;
		if (champSide.equals(PieceMoves.BLACK_SIDE) && isBlackTurn == true) {
			b = true;
		}
		else if (champSide.equals(PieceMoves.WHITE_SIDE) && isWhiteTurn == true) {
			b = true;
		}
		return b;
	}
	/**
	 * <h1>Description</h1>
	 * Method responsible for switching the value of the boolean flags used to monitor pane enabler and disabler
	 * @return void
	 * @see #pane_Enabler_Disabler() 
	 */
	public void turnSwitcher() {
		isBlackTurn = !isBlackTurn;
		isWhiteTurn = !isWhiteTurn;
		pane_Enabler_Disabler();
	}
	/**
	 * <h1>Description</h1>
	 * Method responsible for enabling and disabling piece resting panes.At one point either all black pieces resting panes will be enabled or the whites,
	 * with the exception of those places being a capture move by the opponents piece.
	 * @return void
	 * @see #swithGame_Counter()
	 * @see #enableAllSide(String)
	 * @see #disableAllSide(String)
	 */
	public void pane_Enabler_Disabler() {
		if (isBlackTurn == true) {
			enableAllSide(PieceMoves.BLACK);
			disableAllSide(PieceMoves.WHITE);
			if (SharedVariables.isNormalGame == false) {
				swithGame_Counter();
			}
		}
        else if (isWhiteTurn == true) {
			enableAllSide(PieceMoves.WHITE);
			disableAllSide(PieceMoves.BLACK);
			if (SharedVariables.isNormalGame == false) {
				swithGame_Counter();
			}
		}
	}
	/**
	 * <h1>Description</h1>
	 * Method responsible for showing the foot print of the pieces movement.
	 * from - the piece from which it began moving
	 * to - the piece from which it ended/rested moving
	 * @return void
	 * @param from {@link Pane}
	 * @param to {@link Pane}
	 */
	public static void moving_FootPrint(Pane from, Pane to){
		from.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(15), new Insets(2))));
		to.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(15), new Insets(2))));
	}
	/**
	 * <h1>Description</h1>
	 * Method responsible for returning the board to the original look, executed when the board has changed color due showing possible moves.
	 * @return void
	 */
	public static void boardToOriginalLook() {
		//TODO -  Find a better algo. This method takes to much memory
		Set<String> set = paneCollection.keySet();
		Iterator<String> str = set.iterator();
		while (str.hasNext()) {
			Pane changePane = paneCollection.get(str.next());
			
			changePane.setEffect(new Glow(0));
			String possition = changePane.getId().substring(1, 6);
			int[] intPossition = PieceMoves.stringToArray(possition);
			int xP = intPossition[0];
			int yP = intPossition[1];
			
			if ( ((xP%2 == 0) && (yP%2 == 0)) || ((xP%2 != 0) && (yP%2 != 0)) ) {
				changePane.setBackground(new Background(new BackgroundFill(whiteSideColor, new CornerRadii(0), new Insets(0))));
			}
			else {
				changePane.setBackground(new Background(new BackgroundFill(blackSideColor, new CornerRadii(0), new Insets(0))));
			}
		}
	}
	/**
	 * <h1>Description</h1>
	 * Method responsible for disabling all panes except the ones that are appropriate in moving to or from.
	 * @return void
	 * @see #clickOnArea(MouseEvent)
	 */
	public void disableAllExceptPossibleMoves(){
		Set<String> set = paneCollection.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String s = iterator.next();
			Pane pane = paneCollection.get(s);
			pane.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					// Disable's listener
				}
			});
		}
		for (int i = 0; i < this.possibleMoves.size(); i++) {
			Pane p = this.possibleMoves.get(i);
			p.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					clickOnArea(event);
				}
			});
			this.clickedPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					clickOnArea(event);
				}
			});
		}
	}
	/**
	 * <h1>Description</h1>
	 * Method is responsible for changing the effect of panes to glow.The method identifies if the position is a capture or normal moves
	 * and glows accordingly.
	 * @param listofAreas
	 * @param captured
	 * @param color
	 * @return void
	 */
	public static void glowPossitions(ArrayList<String> listofAreas,boolean captured,Color color){
		for (int i = 0; i < listofAreas.size(); i++) {
			String s = "p"+listofAreas.get(i); //$NON-NLS-1$
			Pane p = paneCollection.get(s);
			p.setEffect(new Glow(0.8));
			if (captured == true) {
				p.setBackground(new Background(new BackgroundFill(color, new CornerRadii(2), new Insets(3))));
			}
			 
		}
	}
	/**
	 * <h1>Description</h1>
	 * Method responsible for setting id to all the panes in the playing arena.
	 * @return void
	 */
	public static void setIdToPanes(){
		Set<String> set = paneCollection.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String s = iterator.next();
			Pane pane = paneCollection.get(s);
			pane.setId(s);
		}
	}
	/**
	 * <h1>Description</h1>
	 * Method adds the given piece name - chessPiece to the given coordinate by querying the proper image from the chessPiece name.
	 * @return void
	 * @param coordinate
	 * @param chessPiece
	 */
	public static void addPieceToBoard(String coordinate,String chessPiece){
		ImageView imgView = new ImageView();
		Pane pane = paneCollection.get("p"+coordinate); //$NON-NLS-1$
		try {
			if (chessPiece.equals("UNOCCUPIED")) { //$NON-NLS-1$
				imgView = new ImageView();
				pane.getChildren().add(imgView);
			}
			else if (!(chessPiece.equals("UNOCCUPIED"))) { //$NON-NLS-1$
				String imgResource = "Pieces/" + chessPiece + ".png"; //$NON-NLS-1$ //$NON-NLS-2$
				Image img = new Image(imgResource);
				imgView.setImage(img);
				
				double insetX = (64-img.getWidth())/2;
				double insetY = (64-img.getHeight())/2;
				
				imgView.setX(insetX);
				imgView.setY(insetY);
				
				if (pane.getChildren().size() == 1) {
					pane.getChildren().remove(0);
					pane.getChildren().add(imgView);
				}
				else if (pane.getChildren().size() == 0) {
					pane.getChildren().add(imgView);
				}
			}
			
		} catch (Exception e) {
			System.err.println("invalid url"); //$NON-NLS-1$
			e.printStackTrace();
		}
		
	}
	/**
	 * <h1>Description</h1>
	 * Method disables all panes which side the parameter takes
	 * side may be either "BLACKSIDE" or "WHITESIDE"
	 * @return void
	 * @param side
	 */
	public static void disableAllSide(String side){
		ArrayList<String> allPos = pieceMoves.getAllPositionOfSide(side);
		for (int i = 0; i < allPos.size(); i++) {
			String s = "p"+allPos.get(i); //$NON-NLS-1$
			Pane p = paneCollection.get(s);
			p.setDisable(true);
		}
	}
	/**
	 * <h1>Description</h1>
	 * Method enables all panes which side the parameter takes
	 * side may be either "BLACKSIDE" or "WHITESIDE"
	 * @return void
	 * @param side
	 */
	public static void enableAllSide(String side){
		ArrayList<String> allPos = pieceMoves.getAllPositionOfSide(side);
		for (int i = 0; i < allPos.size(); i++) {
			String s = "p"+allPos.get(i); //$NON-NLS-1$
			Pane p = paneCollection.get(s);
			p.setDisable(false);
		}
	}
	
}
