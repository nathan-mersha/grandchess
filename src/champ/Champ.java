package champ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import application.PieceMoves;

public class Champ {
	////// Game Type
	public static final String GAMEMODE_EASY = "Easy";
	public static final String GAMEMODE_MEDIUM = "Medium";
	public static final String GAMEMODE_HARD = "Hard";
	///////
	/////// Piece Values
	private static final int QUEEN_VALUE = 30; // Properly evaluated value have not yet been set
	private static final int MARSHAL_VALUE = 20; // Properly evaluated value have not yet been set
	private static final int CARDINAL_VALUE = 20; // Properly evaluated value have not yet been set
	private static final int BISHOP_VALUE = 15; // Properly evaluated value have not yet been set
	private static final int KNIGHT_VALUE = 15; // Properly evaluated value have not yet been set
	private static final int CASTLE_VALUE = 15; // Properly evaluated value have not yet been set
	private static final int PAWN_VALUE = 7; // Properly evaluated value have not yet been set
	///////
	/////// Move type
	private final static String FIVE_MOVE_TYPE = "Five Moves";
	private final static String TWO_MOVE_TYPE = "Two Moves";
	///////
	
	String champSide = null;
	String gameMode = null;
	PieceMoves pieceMoves = new PieceMoves();
	ArrayList<String[]> firstCandidates = new ArrayList<>(); // The first candidates are the total possible non-filtered moves
	
	/**
	 * <h1>Description</h1>
	 * Constructor method, initializes champ
	 * @param side
	 * @param theMap
	 * @param gameMode
	 */
	public Champ(String side,HashMap<String, String> theMap,String gameMode){
		System.out.println("Game mode is : " + gameMode);
		this.gameMode = gameMode;
		if (side.equals(PieceMoves.BLACK_SIDE) || side.equals(PieceMoves.WHITE_SIDE)) {
			this.champSide = side;
			pieceMoves.setTheBoard(theMap);
		}
		else {
			System.err.println("Wrong side name");
		}
	}
	/**
	 * <h1>Description</h1>
	 * The first candidates are the total possible non-filtered moves.
	 * @return Unordered First wave list.
	 */
	public ArrayList<String[]> firstWave(){
		System.out.println("First wave begins");
		ArrayList<String[]> firstWaveCollection = new ArrayList<>();
		HashMap<String, String> board = pieceMoves.theBoard;
		Set<String> set = board.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String position = it.next();
			String pieceName = board.get(position);
			String side = null;
			try {
				side = pieceName.substring(0, 5);
			} catch (Exception e) {
				side = PieceMoves.UNOCCUPIED;
			}
			///////
			if (champSide.equals(PieceMoves.BLACK_SIDE) && side.equals("BLACK")) {
				ArrayList<String> piecePosMoves = pieceMoves.piecePossibleMoves(pieceName, position);
				ArrayList<String> filteredPieceMoves = PieceMoves.filterCheckMoves(piecePosMoves, pieceName, position, pieceMoves);
				for (int i = 0; i < filteredPieceMoves.size(); i++) {
					String[] pieceMoveCandidate = {pieceName,position,filteredPieceMoves.get(i)};
					firstWaveCollection.add(pieceMoveCandidate);
				}
			}
			else if (champSide.equals(PieceMoves.WHITE_SIDE) && side.equals("WHITE")) {
				ArrayList<String> piecePosMoves = pieceMoves.piecePossibleMoves(pieceName, position);
				ArrayList<String> filteredPieceMoves = PieceMoves.filterCheckMoves(piecePosMoves, pieceName, position, pieceMoves);
				
				for (int i = 0; i < filteredPieceMoves.size(); i++) {
					String[] pieceMoveCandidate = {pieceName,position,filteredPieceMoves.get(i)};
					firstWaveCollection.add(pieceMoveCandidate);
				}
			}
		}
		System.out.println("First wave ends");
		return firstWaveCollection;
	}
	/**
	 * <h1>Description</h1>
	 * The second wave holds all possible moves that result in opponents checkMate.
	 * List may be empty, in which case the third wave will execute.
	 * @return second wave lists(Check mate by one move) {@link ArrayList}
	 */
	public ArrayList<String[]> secondWave(ArrayList<String[]> firstWave,HashMap<String, String> theBoard){
		System.out.println("Second wave starts....");
		
		ArrayList<String[]> secondWaveCollection = new ArrayList<>();
		PieceMoves simulation = null;
		for (int i = 0; i < firstWave.size(); i++) {
			simulation = new PieceMoves();
			String pieceName = firstWave.get(i)[0];
			String initialPosition = firstWave.get(i)[1];
			String finalPosition = firstWave.get(i)[2];
			String pieceSide = pieceName.substring(0, 5);
			String opponentSide = pieceSide.equals(PieceMoves.WHITE) ? PieceMoves.BLACK_SIDE : PieceMoves.WHITE_SIDE;
			
			simulation.theBoard.putAll(theBoard);
			simulation.movePiece(finalPosition, initialPosition, pieceName);
			
			if (simulation.isKingInCheck(opponentSide) && simulation.isKingCheckMate(opponentSide, simulation.theBoard)) {
				System.out.println("Is " + opponentSide + " king in check mate : " + simulation.isKingCheckMate(opponentSide, simulation.theBoard));
				String[] checkMateMove = {pieceName,initialPosition,finalPosition};
				secondWaveCollection.add(checkMateMove);
			}
		}
		System.out.println("Second wave ends .... ");
		System.out.println("Can check mate in any of the first wave lists : " + !(secondWaveCollection.isEmpty()));
	
		return secondWaveCollection;
	}
	/**
	 * <h1>Description</h1>
	 * Method calculates the over all standing position of Side side.
	 * While all positions have a value of 1, the surrounding positions of the opponents king have a value of 3.
	 * Marking it's importance.
	 * @param side
	 * @return int represents the overall holding position of side.(0-118)
	 */
	public int controlledArea_Single(String side,PieceMoves pm){
		class KingArea {
			public int kingArea_CrossChecker(ArrayList<String> opp_kingsArea,ArrayList<String> myOverAllPositon){
				int factor = 3; // Exact factor have not been calculated to the exact
				int controlledArea = 0;
				for (int i = 0; i < opp_kingsArea.size(); i++) {
					String pos = opp_kingsArea.get(i).toString();
					for (int j = 0; j < myOverAllPositon.size(); j++) {
						if (pos.equals(myOverAllPositon.get(j))) {
							controlledArea ++ ;
						}
					}
				}
				int ans = factor * controlledArea;
				return ans;
			}
			
			public ArrayList<String> area(String kingName){
				ArrayList<String> surrounding = new ArrayList<>();
				if (kingName.equals(PieceMoves.BLACK_KING) || kingName.equals(PieceMoves.WHITE_KING)) {
					String currentKingPosition = pm.getPiecePossition(kingName);
					ArrayList<String> potential_current_Kingpos =  pm.kingMoves(currentKingPosition); // opponent's King position
					potential_current_Kingpos.add(currentKingPosition); 
					surrounding.addAll(potential_current_Kingpos);
				}
				else {
					System.err.println("King name representation error");
				}
				return surrounding;
		}
		}
		int contPositions = 0; // Value could range from 0 - 118
		int kingArea_value = 0;
		KingArea ka = new KingArea();
		ArrayList<String> controlledPositions = new ArrayList<>();
		if (side.equals(PieceMoves.BLACK_SIDE)) {
			
			HashMap<String, String> board = pm.theBoard;
			Set<String> set = board.keySet();
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				String position = it.next();
				String pieceName = board.get(position);
				String pieceside = pieceName.substring(0, 5);
				
				if (side.equals(PieceMoves.BLACK_SIDE) && pieceside.equals("BLACK")) {
					ArrayList<String> allPossibleMoves = pm.allPossibleMoves(PieceMoves.BLACK_SIDE);
					kingArea_value = ka.kingArea_CrossChecker(ka.area(PieceMoves.WHITE_KING), allPossibleMoves);
					
					for (int i = 0; i < allPossibleMoves.size(); i++) {
						if (!(controlledPositions.contains(allPossibleMoves.get(i)))) {
							controlledPositions.add(allPossibleMoves.get(i));
						}	
					}
					contPositions = controlledPositions.size() + kingArea_value;
				}
			}
		}
		else if (side.equals(PieceMoves.WHITE_SIDE)) {
			HashMap<String, String> board = pm.theBoard;
			Set<String> set = board.keySet();
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				String position = it.next();
				String pieceName = board.get(position);
				String pieceside = pieceName.substring(0, 5);
				
				if (side.equals(PieceMoves.WHITE_SIDE) && pieceside.equals("WHITE")) {
					ArrayList<String> allPossibleMoves = pm.allPossibleMoves(PieceMoves.WHITE_SIDE);
					////
					kingArea_value = ka.kingArea_CrossChecker(ka.area(PieceMoves.BLACK_KING), allPossibleMoves);
					////
					for (int i = 0; i < allPossibleMoves.size(); i++) {
						if (!(controlledPositions.contains(allPossibleMoves.get(i)))) {
							controlledPositions.add(allPossibleMoves.get(i));
						}	
					}
					contPositions = controlledPositions.size() + kingArea_value;
				}
			}
		}
		else {
			System.err.println("Side representation error");
		}
		return contPositions;
	}
	/**
	 * <h1>Description</h1>
	 * Method describes the over all controlling area of side s.
	 * <h2>Example</h2>
	 * If side s controls 80 percent of all the possible controlling position and if the opponents side controls 90 percent
	 * of all  controlling position, assuming that overlapping control position is present, side so now controls -10 position
	 * @param side {@link String}
	 * @return Overall controlling position
	 */
	public int sideOverallControllingArea(String side,PieceMoves pm){
		int overallControlling = 0;
		if (side.equals(PieceMoves.WHITE_SIDE) || side.equals(PieceMoves.BLACK_SIDE)) {
			int sideControlling = controlledArea_Single(side,pm);
			int opponentControlling = side.equals(PieceMoves.BLACK_SIDE) ? controlledArea_Single(PieceMoves.WHITE_SIDE,pm) : controlledArea_Single(PieceMoves.BLACK_SIDE,pm);
			overallControlling = sideControlling - opponentControlling;
		}
		else {
			System.err.println("Side representation error");
		}
		return overallControlling;
	}
	/**
	 * <h1>Description</h1>
	 * Method will be executed if there isn't a single entry in the second wave which constitutes of all checkmate moves.
	 * The third wave is the constitutes of all the moves that alter the overall controlling area of both the opposing and the ally side in favor of the allys side.
	 * There is not a limit to the number of constituents, however the arraylist will constitute at most 30 possible moves.
	 * @param secondWave
	 * @return
	 */
	public ArrayList<String[]> thirdWave(ArrayList<String[]> firstWave){
		ArrayList<String[]> thirdWaveProposals = new ArrayList<>();
		ArrayList<String[]> proposalTempHolder = new ArrayList<>();
		
		for (int i = 0; i < firstWave.size(); i++) {
			String pieceName = firstWave.get(i)[0];
			String initialPosition = firstWave.get(i)[1];
			String finalPosition = firstWave.get(i)[2];
			String sideName = pieceName.substring(0, 5);
			
			PieceMoves simulation = new PieceMoves();
			simulation.theBoard.putAll(pieceMoves.theBoard);
			simulation.movePiece(finalPosition, initialPosition, pieceName);
				
			int overAllControllingArea = sideOverallControllingArea(sideName.equals("BLACK") ? PieceMoves.BLACK_SIDE : PieceMoves.WHITE_SIDE,simulation);
			String[] move = {Integer.toString(overAllControllingArea),pieceName,initialPosition,finalPosition};
				
			proposalTempHolder.add(move);
		
		}
		thirdWaveProposals = sorter(proposalTempHolder);
		return thirdWaveProposals; // thirdWaveProposals
	}
	/**
	 * <h1>Description</h1>
	 * Method Sorts unordered list by grade in ascending order.
	 * @param unordered list
	 * @return sorted listed by grade
	 */
	private ArrayList<String[]> sorter(ArrayList<String[]> unordered){
		ArrayList<String[]> holder = new ArrayList<>();
		TreeSet<Integer> treeholder = new TreeSet<>();
		
		for (int i = 0; i < unordered.size(); i++) {
			int grade = Integer.parseInt(unordered.get(i)[0]);
			treeholder.add(grade);
		}
		while (!(treeholder.isEmpty())) {
			for (int i = 0; i < unordered.size(); i++) {
				if (Integer.parseInt(unordered.get(i)[0]) == treeholder.last()) {
					holder.add(unordered.get(i));
				}
			}
			treeholder.remove(treeholder.last());
		}
		return holder;
	}
	/**
	 * <h1>Description</h1>
	 * Method is responsible for returning the best proposed move after proper analysis of all the three waves
	 * @return The best proposed move in form of {pieceName,pieceInitial_Possition,pieceFinal_Position}
	 */
	public String[] bestProposedMove(){
		String[] proposedMove = null;
		ArrayList<String[]> firstWave = firstWave(); // All possible filtered moves
		ArrayList<String[]> secondWave = secondWave(firstWave,pieceMoves.theBoard); // Checkmate move
		ArrayList<String[]> thirdWave = thirdWave(firstWave); // Moves ordered by maximum area occupation,moves are not filtered by the 4 conditions.
		
		if (!(secondWave.isEmpty())) {
			proposedMove = secondWave.get(0);
			System.out.println("Can Check mate in one move...second wave is not empty");
		}
		else {
			if (gameMode.equals(GAMEMODE_EASY)) {
				PieceMoves ppm = new PieceMoves();
				ppm.theBoard.putAll(pieceMoves.theBoard);
				ArrayList<String[]> best25ProposedMoves = new ArrayList<>();
				ArrayList<String[]> listUnderThreat = listUnderThreat(ppm, champSide);
				System.out.println("list under threat size is : " + listUnderThreat.size());

				for (int i = 0; i < thirdWave.size(); i++) {
					String[] move = {thirdWave.get(i)[1],thirdWave.get(i)[2],thirdWave.get(i)[3]};
					
					// There is a piece under immediate threat
					if (listUnderThreat.size() > 0) {
						System.out.println("There is a piece under threat");
						if ((condition1_captured(move, GAMEMODE_EASY, ppm) == true) && (condition2_checkMated(GAMEMODE_EASY, ppm) == true) && condition3_staleMate(GAMEMODE_EASY, ppm) && true) {
							best25ProposedMoves.add(move);
							if (i > 25) {break;}
						}
						
					}
					//There is not a piece under immediate threat
					else if (listUnderThreat.size() == 0) {
						if ((condition1_captured(move, GAMEMODE_EASY, ppm) == true) && (condition2_checkMated(GAMEMODE_EASY, ppm) == true) && condition3_staleMate(GAMEMODE_EASY, ppm) && true) {
							System.out.println("Found a move...");
							proposedMove = move;
							break;
						}
					}
				}
				if (listUnderThreat.size() > 0) {
					System.out.println(" LIst under threat size is greater than 0 ( what ever tha");
					proposedMove = threatList_proposedMoves_analyzer(best25ProposedMoves, listUnderThreat);
				}
			}
//			else if (gameMode.equals(GAMEMODE_MEDIUM)) {
//				ArrayList<String[][]> canCheckMate_13 = canCheckMate_1_3mvs(pieceMoves.theBoard);
//				if (canCheckMate_13.isEmpty() == false) {
//					proposedMove = canCheckMate_13.get(0)[0];
//				}
//				else if (canCheckMate_13.isEmpty() == true) {
//					for (int i = 0; i < thirdWave.size(); i++) {
//						String[] move = {thirdWave.get(i)[1],thirdWave.get(i)[2],thirdWave.get(i)[3]};
//						PieceMoves ppm = new PieceMoves();
//						ppm.theBoard.putAll(pieceMoves.theBoard);
//						ppm.movePiece(move[2], move[1], move[0]);
//						if ((condition1_captured(move, GAMEMODE_MEDIUM, ppm) == true) && (condition2_checkMated(GAMEMODE_MEDIUM, ppm) == true) && condition3_staleMate(GAMEMODE_MEDIUM, ppm) && true) {
//							proposedMove = move;
//							break;
//						}
//					}
//				}
//			}
			
//			else if (gameMode.equals(GAMEMODE_HARD)) {
//				
//				ArrayList<String[][]> canCheckMate_13 = canCheckMate_1_3mvs(pieceMoves.theBoard);
//				if (canCheckMate_13.isEmpty() == false) {
//					proposedMove = canCheckMate_13.get(0)[0];
//				}
//				else {
//					ArrayList<String[][]> canCheckMate_15 = canCheckMate_1_5mvs(pieceMoves.theBoard);
//					if (canCheckMate_15.isEmpty() == false) {
//						proposedMove = canCheckMate_15.get(0)[0];
//					}
//					else if (canCheckMate_15.isEmpty() == true) {
//						for (int i = 0; i < thirdWave.size(); i++) {
//							String[] move = {thirdWave.get(i)[1],thirdWave.get(i)[2],thirdWave.get(i)[3]};
//							PieceMoves ppm = new PieceMoves();
//							ppm.theBoard.putAll(pieceMoves.theBoard);
//							ppm.movePiece(move[2], move[1], move[0]);
//							if ((condition1_captured(move, GAMEMODE_HARD, ppm) == true) && (condition2_checkMated(GAMEMODE_HARD, ppm) == true) && condition3_staleMate(GAMEMODE_MEDIUM, ppm) && true) {
//								proposedMove = move;
//								break;
//							}
//						}
//					}
//				}
//			}
		}
		return proposedMove;
	}
	/**
	 * <h1>Description</h1>
	 * Method
	 * @param bestProposedMoves
	 * @param threatList
	 * @return
	 */
	public String[] threatList_proposedMoves_analyzer(ArrayList<String[]> bestProposedMoves,ArrayList<String[]> threatList){
		String[] filteredProposedMove = bestProposedMoves.get(0);
		for (int i = 0; i < threatList.size(); i++) {
			String[] holder = threatList.get(i);
			String pieceName = holder[0];
			String piecePossition = holder[1];
			for (int j = 0; j < bestProposedMoves.size(); j++) {
				String[] holder1 = bestProposedMoves.get(j);
				String pieceName1 = holder1[0];
				String piecePossition1 = holder1[1];
				if ((pieceName.equals(pieceName1) && piecePossition.equals(piecePossition1))) {
					filteredProposedMove = holder1;
					System.out.println("Rescued my piece");
					break;
				}
			}
		}
		return filteredProposedMove;
	}
	/**
	 * <h1>Description</h1>
	 * Method returns all list of side under threat
	 * String[0] = piece name
	 * String[1] = initial position	
	 * @param pm PieceMoves
	 * @param side
	 * @return All pieces under threat and their initial position.
	 */	
	public ArrayList<String[]> listUnderThreat(PieceMoves pm,String side){
		ArrayList<String[]> underThreat = new ArrayList<>();
		if (side.equals(PieceMoves.BLACK_SIDE) || side.equals(PieceMoves.WHITE_SIDE)) {
			ArrayList<String> opponentsPossibleMove = pm.allPossibleMoves(side.equals(PieceMoves.WHITE_SIDE) ? PieceMoves.BLACK_SIDE  : PieceMoves.WHITE_SIDE);
			ArrayList<String> allysCurrentPosition = pm.getAllPositionOfSide(side.equals(PieceMoves.BLACK_SIDE) ? "BLACK" : "WHITE");
			
			for (int i = 0; i < allysCurrentPosition.size(); i++) {
				if (opponentsPossibleMove.contains(allysCurrentPosition.get(i))) {
					String piecePossition = allysCurrentPosition.get(i);
					String pieceName = pm.theBoard.get(piecePossition);
					String[] underThreat_piece = {pieceName,piecePossition};
					underThreat.add(underThreat_piece);
					System.out.println("Under threat piece's list : " + pieceName + " on position : " + piecePossition ); 
				}
			}
		}
		else {
			System.err.println("Side representation error");
		}
		return underThreat;
	}
	////
	// 4th wave conditionals begin here
	////
	/**
	 * <h1>Description</h1>
	 * Method returns true if the proposed move puts the piece in immediate threat, a captured move.
	 * @param proposedMove
	 * @return
	 */
	public boolean movePutsOnThreat(String[] proposedMove){
		boolean b = false;
		PieceMoves simulation = new PieceMoves();
		simulation.theBoard.putAll(pieceMoves.theBoard);
		
		String pieceName = proposedMove[1];
		String initialPosition = proposedMove[2];
		String finalPosition = proposedMove[3];
//		
		simulation.movePiece(finalPosition, initialPosition, pieceName);
		
		String side = pieceName.substring(0,5);
		ArrayList<String> opponentsPossibleMoves = simulation.allPossibleMoves(side.equals("BLACK") ? PieceMoves.WHITE_SIDE : PieceMoves.BLACK_SIDE);
		
		if (opponentsPossibleMoves.contains(finalPosition)) {
			b = true;
		}
		System.out.println("That move will put you on immediate threat");
		return b;
	}
	/**
	 * <h1>Description</h1>
	 * Method returns true if the proposed move can aquire an overall of 30 points or more to ally.
	 * Method return false otherwise.
	 * @param proposedMove 
	 * @return true if the proposed move can aquire an overall 30 point to ally.
	 */
	public boolean canAquire_TeritorialValue(String[] proposedMove){
		boolean b = false;
		PieceMoves simulation = new PieceMoves();
		simulation.theBoard.putAll(pieceMoves.theBoard);
		//////////
		int initial_ControlValue = sideOverallControllingArea(champSide, simulation);
		/////////
		String pieceName = proposedMove[0];
		String initialPosition = proposedMove[1];
		String finalPosition = proposedMove[2];
		simulation.movePiece(finalPosition, initialPosition, pieceName);
		int final_ControlValue = 0;
		
		String opponentSide = champSide.equals(PieceMoves.WHITE_SIDE) ? PieceMoves.BLACK_SIDE : PieceMoves.WHITE_SIDE;
		ArrayList<String> opponentsPossibleMoves = simulation.allPossibleMoves(opponentSide);
		if (opponentsPossibleMoves.contains(finalPosition)) {
			System.out.println("Piece could be captured...");
			ArrayList<String> allPossition = simulation.getAllPositionOfSide(opponentSide);
			for (int i = 0; i < allPossition.size(); i++) {
				String opponentPossition = allPossition.get(i);
				String opponentPieceName = simulation.theBoard.get(opponentPossition);
				ArrayList<String> opponentPiecePossibleMoves = simulation.piecePossibleMoves(opponentPieceName,opponentPossition);
				if (opponentPiecePossibleMoves.contains(finalPosition)) {
					simulation.movePiece(finalPosition, opponentPossition, opponentPieceName);
					/////////////////////
					final_ControlValue = sideOverallControllingArea(champSide, simulation);
					break;
				}
			}
		}
		
		if ((pieceName.equals(PieceMoves.BLACK_QUEEN) || pieceName.equals(PieceMoves.WHITE_QUEEN)) && (final_ControlValue - initial_ControlValue) >= QUEEN_VALUE) {
			b = true;}
		else if ((pieceName.equals(PieceMoves.BLACK_MARSHAL) || pieceName.equals(PieceMoves.WHITE_MARSHAL)) && (final_ControlValue - initial_ControlValue) >= MARSHAL_VALUE) {
			b = true;}
		else if ((pieceName.equals(PieceMoves.BLACK_CARDINAL) || pieceName.equals(PieceMoves.WHITE_CARDINAL)) && (final_ControlValue - initial_ControlValue) >= CARDINAL_VALUE) {
			b = true;}
		else if ((pieceName.equals(PieceMoves.BLACK_BISHOP) || pieceName.equals(PieceMoves.WHITE_BISHOP)) && (final_ControlValue - initial_ControlValue) >= BISHOP_VALUE) {
			b = true;}
		else if ((pieceName.equals(PieceMoves.BLACK_KNIGHT) || pieceName.equals(PieceMoves.WHITE_KNIGHT)) && (final_ControlValue - initial_ControlValue) >= KNIGHT_VALUE) {
			b = true;}
		else if ((pieceName.equals(PieceMoves.BLACK_CASTLE) || pieceName.equals(PieceMoves.BLACK_CASTLE)) && (final_ControlValue - initial_ControlValue) >= CASTLE_VALUE) {
			b = true;}
		else if ((pieceName.equals(PieceMoves.BLACK_PAWN) || pieceName.equals(PieceMoves.BLACK_PAWN)) && (final_ControlValue - initial_ControlValue) >= PAWN_VALUE) {
			b = true;}
		
		return b;
	}
	/**
	 * <h1>Description</h1>
	 * Return true if the proposed move could end the game in check mate.
	 * Return false otherwise.
	 * @param proposedMove
	 * @return whether or not the proposed move stale mates.
	 */
	public boolean moveStaleMates(String[] proposedMove){
		boolean b = false;
		PieceMoves simulation = new PieceMoves();
		simulation.theBoard.putAll(pieceMoves.theBoard);
		
		String pieceName = proposedMove[1];
		String initialPosition = proposedMove[2];
		String finalPosition = proposedMove[3];
		
		simulation.movePiece(finalPosition, initialPosition, pieceName);
		if (simulation.isStaleGame()) {
			b = true;
		}
		return b;
	}
	/**
	 * <h1>Description</h1>
	 * Method returns true if the champs side can checkmate the opponent in one to max moves(three before testing) and no more
	 * (The maximum move will be decided after memory analysis.)
	 * Move counting will begin on the Champs move.Move number does not include opponents move.
	 * @return a proposed moves in string[] value containing
	 * <p>
	 * string[0] represents the number of moves it will take to checkMate the opponent.
	 * string[1] the piece name.
	 * string[2] it's initial position.
	 * string[3] it's final position.
	 * </p>
	 * <h2>Note</h2>
	 * If the returned arraylist is empty then the champ can't check mate in the maximum itteration allowed(which is no greater than 6) //specify the exact num.
	 */
	public ArrayList<String[][]> canCheckMate_1_3mvs(HashMap<String, String> theMap){
		boolean inevitableCheckMate = false;
		ArrayList<String[][]> checkMate_moves = new ArrayList<>();
		ArrayList<String[]> thirdWave_list = thirdWave(firstWave());
		ArrayList<String[]> opponent2ndLayer_allPossibleMoves = new ArrayList<>();
		
		boolean foundMove_ch = false;
			for (int i = 0; i < thirdWave_list.size(); i++) {
				String pieceName = thirdWave_list.get(i)[1];
				String initialPosition = thirdWave_list.get(i)[2];
				String finalPosition = thirdWave_list.get(i)[3];
				
				PieceMoves simulation = new PieceMoves();
				simulation.theBoard.putAll(theMap);
				simulation.movePiece(finalPosition, initialPosition, pieceName);
				ArrayList<String[]> oneMove_CheckMate = secondWave(firstWave(), theMap);
				
				if (oneMove_CheckMate.isEmpty()) {
					Champ simulation_opponent = new Champ(champSide.equals(PieceMoves.BLACK_SIDE) ? PieceMoves.WHITE_SIDE : PieceMoves.BLACK_SIDE, simulation.theBoard,gameMode);
					ArrayList<String[]> opponent_FirstWave = simulation_opponent.firstWave();
					opponent2ndLayer_allPossibleMoves.addAll(opponent_FirstWave);
					///////////
					for (int l1 = 0; l1 < opponent_FirstWave.size(); l1++) {
						String[] move = opponent_FirstWave.get(l1);
						boolean contains = false;
						for (int m1 = 0; m1 < opponent2ndLayer_allPossibleMoves.size(); m1++) {
							if (move[0].equals(opponent2ndLayer_allPossibleMoves.get(m1)[0]) && move[1].equals(opponent2ndLayer_allPossibleMoves.get(m1)[1]) && move[2].equals(opponent2ndLayer_allPossibleMoves.get(m1)[2])) {
								contains = true;
							}
						}
						if (contains == false) {
							opponent2ndLayer_allPossibleMoves.add(move);
						}
					}
					
					if (foundMove_ch == false) {
						for (int j = 0; j < opponent_FirstWave.size(); j++) {
							String pieceName_opp = opponent_FirstWave.get(j)[0];
							String initialPosition_opp = opponent_FirstWave.get(j)[1];
							String finalPosition_opp = opponent_FirstWave.get(j)[2];
							//
							PieceMoves simulation2 = new PieceMoves();
							simulation2.theBoard.putAll(simulation.theBoard);
							simulation2.movePiece(finalPosition_opp, initialPosition_opp, pieceName_opp);
							Champ simulation_ally = new Champ(champSide, simulation2.theBoard,gameMode);
							ArrayList<String[]> second_secondWave = simulation_ally.secondWave(simulation_ally.firstWave(),simulation2.theBoard);//third move
							
							if (!(second_secondWave.isEmpty())) {
								
								String[] firstMove = {thirdWave_list.get(i)[1],thirdWave_list.get(i)[2],thirdWave_list.get(i)[3]};
								String[] secondMove = {opponent_FirstWave.get(j)[0],opponent_FirstWave.get(j)[1],opponent_FirstWave.get(j)[2]};
								String[] thirdMove = {second_secondWave.get(0)[0],second_secondWave.get(0)[1],second_secondWave.get(0)[2]};
								String[][] combinedProposal = {firstMove,secondMove,thirdMove};
								
								checkMate_moves.add(combinedProposal);
								foundMove_ch = true;
							}
						}
						boolean inevetibleCheckMate = comparer(checkMate_moves, opponent2ndLayer_allPossibleMoves, new ArrayList<>(),TWO_MOVE_TYPE);
						if (inevetibleCheckMate == true) {
							inevitableCheckMate = true;
							break;
						}
					}
					if (foundMove_ch == true) {
						break;
					} 
				}
				else if (!(oneMove_CheckMate.isEmpty())) {
					for (int j = 0; j < oneMove_CheckMate.size(); j++) {
						String pieceName_oneMove = oneMove_CheckMate.get(j)[0];
						String initialPosition_oneMove = oneMove_CheckMate.get(j)[1];
						String finalPosition_oneMove = oneMove_CheckMate.get(j)[2];
						String[] proposed_oneMove_checkMate = {"1",pieceName_oneMove,initialPosition_oneMove,finalPosition_oneMove};
						String[][] proposed_oneMove_checkMate2 = {proposed_oneMove_checkMate};
						checkMate_moves.add(proposed_oneMove_checkMate2);
					}
					break;
				}
			}
			return inevitableCheckMate ? checkMate_moves : new ArrayList<String[][]>();
	}
	public ArrayList<String[][]> canCheckMate_1_5mvs(HashMap<String, String> theMap){
		boolean inevitableCheckMate = false;
		
		ArrayList<String[][]> checkMate_moves = new ArrayList<>();
		ArrayList<String[]> thirdWave_list = thirdWave(firstWave());
		/////////
		ArrayList<String[]> opponent2ndLayer_allPossibleMoves = new ArrayList<>();
		ArrayList<String[]> opponent4thLayer_allPossibleMoves = new ArrayList<>();
		
		boolean foundMove_ch = false;
			for (int i = 0; i < thirdWave_list.size(); i++) {
				String pieceName = thirdWave_list.get(i)[1];
				String initialPosition = thirdWave_list.get(i)[2];
				String finalPosition = thirdWave_list.get(i)[3];
				
				PieceMoves simulation = new PieceMoves();
				simulation.theBoard.putAll(theMap);
				simulation.movePiece(finalPosition, initialPosition, pieceName);
				ArrayList<String[]> oneMove_CheckMate = secondWave(firstWave(), theMap);
				if (oneMove_CheckMate.isEmpty()) {
					Champ simulation_opponent = new Champ(champSide.equals(PieceMoves.BLACK_SIDE) ? PieceMoves.WHITE_SIDE : PieceMoves.BLACK_SIDE, simulation.theBoard,gameMode);
					ArrayList<String[]> opponent_FirstWave = simulation_opponent.firstWave();
					opponent2ndLayer_allPossibleMoves.addAll(opponent_FirstWave);
					//////
					for (int l1 = 0; l1 < opponent_FirstWave.size(); l1++) {
						String[] move = opponent_FirstWave.get(l1);
						boolean contains = false;
						for (int m1 = 0; m1 < opponent2ndLayer_allPossibleMoves.size(); m1++) {
							if (move[0].equals(opponent2ndLayer_allPossibleMoves.get(m1)[0]) && move[1].equals(opponent2ndLayer_allPossibleMoves.get(m1)[1]) && move[2].equals(opponent2ndLayer_allPossibleMoves.get(m1)[2])) {
								contains = true;
							}
						}
						if (contains == false) {
							opponent2ndLayer_allPossibleMoves.add(move);
						}
					}
					/////
					if (foundMove_ch == false) {
						for (int j = 0; j < opponent_FirstWave.size(); j++) {
							String pieceName_opp = opponent_FirstWave.get(j)[0];
							String initialPosition_opp = opponent_FirstWave.get(j)[1];
							String finalPosition_opp = opponent_FirstWave.get(j)[2];
							///////////
							PieceMoves simulation2 = new PieceMoves();
							simulation2.theBoard.putAll(simulation.theBoard);
							simulation2.movePiece(finalPosition_opp, initialPosition_opp, pieceName_opp);
							Champ simulation_ally = new Champ(champSide, simulation2.theBoard,gameMode);
							///////////
							ArrayList<String[]> second_thirdWave = simulation_ally.thirdWave(simulation_ally.firstWave());
							for (int k = 0; k < second_thirdWave.size(); k++) {
								String second_pieceName = second_thirdWave.get(k)[1];
								String second_initialPosition = second_thirdWave.get(k)[2];
								String second_finalPosition = second_thirdWave.get(k)[3];
								
								PieceMoves simulation3 = new PieceMoves();
								simulation3.theBoard.putAll(simulation2.theBoard);
								simulation3.movePiece(second_finalPosition, second_initialPosition, second_pieceName);
								
								Champ simulation3_opponent = new Champ(champSide.equals(PieceMoves.BLACK_SIDE) ? PieceMoves.WHITE_SIDE : PieceMoves.BLACK_SIDE, simulation3.theBoard,gameMode);
								ArrayList<String[]> opponentsecond_FirstWave = simulation3_opponent.firstWave();
								/////////// Adding all 4th layer moves for latter comparison
								for (int l = 0; l < opponentsecond_FirstWave.size(); l++) {
									String[] move = opponentsecond_FirstWave.get(l);
									boolean contains = false;
									for (int m = 0; m < opponent4thLayer_allPossibleMoves.size(); m++) {
										if (move[0].equals(opponent4thLayer_allPossibleMoves.get(m)[0]) && move[1].equals(opponent4thLayer_allPossibleMoves.get(m)[1]) && move[2].equals(opponent4thLayer_allPossibleMoves.get(m)[2])) {
											contains = true;
										}
									}
									if (contains == false) {
										opponent4thLayer_allPossibleMoves.add(move);
									}
								}
								//////////////
								for (int l = 0; l < opponentsecond_FirstWave.size(); l++) {
									String second_pieceName_opp = opponentsecond_FirstWave.get(l)[0];
									String second_initialPosition_opp = opponentsecond_FirstWave.get(l)[1];
									String second_finalPosition_opp = opponentsecond_FirstWave.get(l)[2];
									
									PieceMoves simulation4 = new PieceMoves();
									simulation4.theBoard.putAll(simulation3.theBoard);
									simulation4.movePiece(second_finalPosition_opp, second_initialPosition_opp, second_pieceName_opp);
									
									Champ second_simulation_ally = new Champ(champSide, simulation4.theBoard,gameMode);
									ArrayList<String[]> third_secondWave = second_simulation_ally.secondWave(second_simulation_ally.firstWave(), simulation4.theBoard);
									
									if (!(third_secondWave.isEmpty())) {
										String[] firstMove = {thirdWave_list.get(i)[1],thirdWave_list.get(i)[2],thirdWave_list.get(i)[3]}; 
										String[] secondMove = {opponent_FirstWave.get(j)[0],opponent_FirstWave.get(j)[1],opponent_FirstWave.get(j)[2]};
										String[] thirdMove = {second_thirdWave.get(k)[1],second_thirdWave.get(k)[2],second_thirdWave.get(k)[3]};
										String[] fourthMove = {opponentsecond_FirstWave.get(l)[0],opponentsecond_FirstWave.get(l)[1],opponentsecond_FirstWave.get(l)[2]};
										String[] fifthMove = {third_secondWave.get(0)[0],third_secondWave.get(0)[1],third_secondWave.get(0)[2]};
										String[][] combinedProposal = {firstMove,secondMove,thirdMove,fourthMove,fifthMove};
										
										checkMate_moves.add(combinedProposal);
										foundMove_ch = true;
									}
								}
								boolean inevetibleCheckMate = comparer(checkMate_moves, opponent2ndLayer_allPossibleMoves, opponent4thLayer_allPossibleMoves,FIVE_MOVE_TYPE);
								if (inevetibleCheckMate == true) {
									inevitableCheckMate = true;
									break;
								}
							}
						}
					}
				}
				else if (!(oneMove_CheckMate.isEmpty())) {
					for (int j = 0; j < oneMove_CheckMate.size(); j++) {
						String pieceName_oneMove = oneMove_CheckMate.get(j)[0];
						String initialPosition_oneMove = oneMove_CheckMate.get(j)[1];
						String finalPosition_oneMove = oneMove_CheckMate.get(j)[2];
						String[][] proposed_oneMove_checkMate = {{pieceName_oneMove,initialPosition_oneMove,finalPosition_oneMove}};
						checkMate_moves.add(proposed_oneMove_checkMate);
						inevitableCheckMate = true;
					}
					break;
				}
			}
		return inevitableCheckMate ? checkMate_moves : new ArrayList<String[][]>();
	}
	/**
	 * <h1>Description</h1>
	 * Method is compares a set of collection to the correspondent opponents
	 * possible move, and returns true if all the opponents possible move is the 
	 * subset of the larger collection.
	 * @param allCollection
	 * @param secondWaveCollection
	 * @param fourthWaveCollection
	 * @param type
	 * @return
	 */
	private boolean comparer(ArrayList<String[][]> allCollection, ArrayList<String[]> secondWaveCollection, ArrayList<String[]> fourthWaveCollection,String type){
		boolean allisContained = true; 
		ArrayList<String[]> extracted_collection_secondWave = new ArrayList<>();
		ArrayList<String[]> extracted_collection_fourthWave = null;
		if (type.equals(FIVE_MOVE_TYPE)) {
			extracted_collection_fourthWave = new ArrayList<>();
			for (int i = 0; i < allCollection.size(); i++) {
				extracted_collection_fourthWave.add(allCollection.get(i)[3]);
			}
		}
		///// Initializing extracted Collection for second and third wave.
		for (int i = 0; i < allCollection.size(); i++) {
			extracted_collection_secondWave.add(allCollection.get(i)[1]);
		}
		///////////// for fourth wave
		if (type.equals(FIVE_MOVE_TYPE)) {
			for (int i = 0; i < fourthWaveCollection.size(); i++) {
				boolean foundMatch = false;
				for (int j = 0; j < extracted_collection_fourthWave.size(); j++) {
					String[] testExt = extracted_collection_fourthWave.get(j);
					String[] testCol = fourthWaveCollection.get(i);
					if (testExt[0].equals(testCol[0]) && testExt[1].equals(testCol[1]) && testExt[2].equals(testCol[2])) {
						foundMatch = true;
					}
				}
				if (foundMatch == false) {
					allisContained = false;
				}
				else if (foundMatch == true) {
					allisContained = true;
				}
			}
		}
		////////////// for second wave
		for (int i = 0; i < secondWaveCollection.size(); i++) {
			boolean foundMatch = false;
			for (int j = 0; j < extracted_collection_secondWave.size(); j++) {
				String[] testExt = extracted_collection_secondWave.get(j);
				String[] testCol = secondWaveCollection.get(i);
				if (testExt[0].equals(testCol[0]) && testExt[1].equals(testCol[1]) && testExt[2].equals(testCol[2])) {
					foundMatch = true;
				}
			}
			if (foundMatch == false) {
				allisContained = false;
			}
			else if (foundMatch == true) {
				allisContained = true;
			}
		}
		return allisContained;
	}
	
	/**
	 * <h1>Description</h1>
	 * Method returns captured condition value.
	 * True : Move passes this condition.
	 * False : Move fails this condition.
	 * @param theMove, game mode , Piecemoves pm
	 * @return boolean
	 */
	public boolean condition1_captured(String[] theMove,String gameMode,PieceMoves pm){
		boolean b = false;
		//////////
		PieceMoves ppm = new PieceMoves();
		ppm.setTheBoard(pm.theBoard);
		ppm.movePiece(theMove[2], theMove[1], theMove[0]);
		/////////
		String[] ar = {" ",theMove[0],theMove[1],theMove[2]};
		boolean onThreat = movePutsOnThreat(ar);
		/////
		if (onThreat == false) {
			System.out.println("On threat is false");
			b = true;
		}
		else if (onThreat == true) {
			//////////
			ArrayList<String[]> secondWave = secondWave(firstWave(),pm.theBoard);
			if (gameMode.equals(GAMEMODE_EASY) && (!(secondWave.isEmpty()))) {
				for (int i = 0; i < secondWave.size(); i++) {
					System.out.println("Check mate move is : Piece name : " + secondWave.get(i)[0] + " Initial position : " + secondWave.get(i)[1] + "Final position : " + secondWave.get(i)[2]);
				}
				System.out.println("Can check mate in one move");
//				b = true;
			}
			else if (gameMode.equals(GAMEMODE_MEDIUM) && (canCheckMate_1_3mvs(pm.theBoard).isEmpty() == false)) {
				b = true;
			}
			else if (gameMode.equals(GAMEMODE_HARD) && (canCheckMate_1_5mvs(pm.theBoard).isEmpty() == false)) {
				b = true;
			}
			//////////
			if (canAquire_TeritorialValue(theMove)) {
				System.out.println("Can aquire territorial value");
				b = true;
			}
		}
		return b;
	}
	
	/**
	 * <h1>Description</h1>
	 * Method returns checkmated condition value
	 * @param gameMode
	 * @param theBoard
	 * @return
	 */
	public boolean condition2_checkMated(String gameMode,PieceMoves pm){
		boolean b = true;
		Champ ch = new Champ(champSide.equals(PieceMoves.BLACK_SIDE) ? PieceMoves.WHITE_SIDE : PieceMoves.BLACK_SIDE, pm.theBoard,gameMode);
		if ((gameMode.equals(GAMEMODE_EASY)) && (ch.secondWave(ch.firstWave(), pm.theBoard).isEmpty() == false)) {
			b = false;
		}
		else if (gameMode.equals(GAMEMODE_MEDIUM) && (ch.canCheckMate_1_3mvs(pm.theBoard).isEmpty() == false)) {
			b = false;
		}
		else if (gameMode.equals(GAMEMODE_HARD) && (ch.canCheckMate_1_5mvs(pm.theBoard).isEmpty() == false)) {
			b = false;
			if (canCheckMate_1_3mvs(pm.theBoard).isEmpty() == false) {
				b = true;
			}
		}
		return b;
	}
	
	/**
	 * <h1>Description</h1>
	 * Method returns staleMated Condition.
	 * @param gameMode
	 * @param pm
	 * @return
	 */
	public boolean condition3_staleMate(String gameMode,PieceMoves pm){
		boolean b = true;
		Champ ch = new Champ(champSide.equals(PieceMoves.BLACK_SIDE) ? PieceMoves.WHITE_SIDE : PieceMoves.BLACK_SIDE, pm.theBoard,gameMode);
		if (pm.isStaleGame()) {
			b = false;
			if (gameMode.equals(GAMEMODE_EASY) && ch.secondWave(ch.firstWave(), pm.theBoard).isEmpty() == false) {
				b = true;
			}
			else if ((gameMode.equals(GAMEMODE_MEDIUM)) && ch.canCheckMate_1_3mvs(pm.theBoard).isEmpty() == false) {
				b = true;
			}
			else if ((gameMode.equals(GAMEMODE_HARD)) && ch.canCheckMate_1_5mvs(pm.theBoard).isEmpty() == false) {
				b = true;
			}
		}
		return b;
	}
}
