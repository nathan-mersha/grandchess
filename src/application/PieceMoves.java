package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class PieceMoves {
	
	public static final String BLACK_SIDE = "Black Side"; //$NON-NLS-1$
	public static final String WHITE_SIDE = "White Side"; //$NON-NLS-1$
	public static final String BLACK = "BLACK"; //$NON-NLS-1$
	public static final String WHITE = "WHITE"; //$NON-NLS-1$
	public static final String UNOCC = "UNOCC"; //$NON-NLS-1$
	
	public static  String UNOCCUPIED = "UNOCCUPIED"; //$NON-NLS-1$
	
	public static  String BLACK_KING = "BLACK_KING"; //$NON-NLS-1$
	public static  String BLACK_QUEEN = "BLACK_QUEEN"; //$NON-NLS-1$
	public static  String BLACK_CARDINAL = "BLACK_CARDINAL"; //$NON-NLS-1$
	public static  String BLACK_MARSHAL = "BLACK_MARSHAL"; //$NON-NLS-1$
	public static  String BLACK_BISHOP = "BLACK_BISHOP"; //$NON-NLS-1$
	public static  String BLACK_KNIGHT = "BLACK_KNIGHT"; //$NON-NLS-1$
	public static  String BLACK_CASTLE = "BLACK_CASTLE"; //$NON-NLS-1$
	public static  String BLACK_PAWN = "BLACK_PAWN"; //$NON-NLS-1$
	
	public static  String WHITE_KING = "WHITE_KING"; //$NON-NLS-1$
	public static  String WHITE_QUEEN = "WHITE_QUEEN"; //$NON-NLS-1$
	public static  String WHITE_CARDINAL = "WHITE_CARDINAL"; //$NON-NLS-1$
	public static  String WHITE_MARSHAL = "WHITE_MARSHAL"; //$NON-NLS-1$
	public static  String WHITE_BISHOP = "WHITE_BISHOP"; //$NON-NLS-1$
	public static  String WHITE_KNIGHT = "WHITE_KNIGHT"; //$NON-NLS-1$
	public static  String WHITE_CASTLE = "WHITE_CASTLE"; //$NON-NLS-1$
	public static  String WHITE_PAWN = "WHITE_PAWN"; //$NON-NLS-1$
	
	public HashMap<String, String> theBoard = new HashMap<>();
	
	public void setTheBoard(HashMap<String, String> theModel){
		this.theBoard = theModel;
	}
	public PieceMoves(){
		intialChessPiecesSet();
	}
	public ArrayList<String> piecePossibleMoves(String pieceName,String currentpossition){
		this.capturedMoves.clear();
		ArrayList<String> piecePossibleM = null;
		
		if (pieceName.equals("WHITE_KING") || pieceName.equals("BLACK_KING")) { //$NON-NLS-1$ //$NON-NLS-2$
			piecePossibleM = kingMoves(currentpossition);
		}
		else if (pieceName.equals("WHITE_QUEEN") || pieceName.equals("BLACK_QUEEN")) { //$NON-NLS-1$ //$NON-NLS-2$
			piecePossibleM = queenMoves(currentpossition);
		}
		else if (pieceName.equals("WHITE_CARDINAL") || pieceName.equals("BLACK_CARDINAL") ) { //$NON-NLS-1$ //$NON-NLS-2$
			piecePossibleM = cardinalMoves(currentpossition);
		}
		else if ( pieceName.equals("WHITE_MARSHAL") || pieceName.equals("BLACK_MARSHAL") ) { //$NON-NLS-1$ //$NON-NLS-2$
			piecePossibleM = marshalMoves(currentpossition);
		}
		else if (pieceName.equals("WHITE_BISHOP") || pieceName.equals("BLACK_BISHOP") ) { //$NON-NLS-1$ //$NON-NLS-2$
			piecePossibleM = bishopMoves(currentpossition);
		}
		else if (pieceName.equals("WHITE_KNIGHT") || pieceName.equals("BLACK_KNIGHT") ) { //$NON-NLS-1$ //$NON-NLS-2$
			piecePossibleM = knightMoves(currentpossition);
		}
		else if (pieceName.equals("WHITE_CASTLE") || pieceName.equals("BLACK_CASTLE") ) { //$NON-NLS-1$ //$NON-NLS-2$
			piecePossibleM = castleMoves(currentpossition);
		}
		else if (pieceName.equals("WHITE_PAWN")) { //$NON-NLS-1$
			piecePossibleM = pawnMoves(currentpossition,WHITE_SIDE);
		}
		else if (pieceName.equals("BLACK_PAWN")) { //$NON-NLS-1$
			piecePossibleM = pawnMoves(currentpossition,BLACK_SIDE);
		}
		else {
			System.err.println("Unknown piece"); //$NON-NLS-1$
		}
		
		return piecePossibleM;
	}
	
	public  void intialChessPiecesSet(){
//		Black piece initials
		this.theBoard.put(arrayToString(new int[]{0,0}) ,BLACK_CASTLE);
		this.theBoard.put(arrayToString(new int[]{9,0}) ,BLACK_CASTLE);
		
		this.theBoard.put(arrayToString(new int[]{1,1}) ,BLACK_KNIGHT);
		this.theBoard.put(arrayToString(new int[]{2,1}) ,BLACK_BISHOP);
		
		this.theBoard.put(arrayToString(new int[]{3,1}) ,BLACK_QUEEN);
		
		this.theBoard.put(arrayToString(new int[]{4,1}) ,BLACK_KING); //black king original place
		this.theBoard.put(arrayToString(new int[]{5,1}) ,BLACK_MARSHAL);
		this.theBoard.put(arrayToString(new int[]{6,1}) ,BLACK_CARDINAL);
		this.theBoard.put(arrayToString(new int[]{7,1}) ,BLACK_BISHOP);
		this.theBoard.put(arrayToString(new int[]{8,1}) ,BLACK_KNIGHT);
	
		for (int i = 0; i < 10; i++) {
			this.theBoard.put(arrayToString(new int[]{i,2}) ,BLACK_PAWN);
			
		}
		////
		//White piece initials
		this.theBoard.put(arrayToString(new int[]{0,9}) ,WHITE_CASTLE);
		this.theBoard.put(arrayToString(new int[]{9,9}) ,WHITE_CASTLE);
		
		this.theBoard.put(arrayToString(new int[]{1,8}) ,WHITE_KNIGHT);
		this.theBoard.put(arrayToString(new int[]{2,8}) ,WHITE_BISHOP);
		this.theBoard.put(arrayToString(new int[]{3,8}) ,WHITE_QUEEN);
		this.theBoard.put(arrayToString(new int[]{4,8}) ,WHITE_KING); // White king original place
		this.theBoard.put(arrayToString(new int[]{5,8}) ,WHITE_MARSHAL);
		this.theBoard.put(arrayToString(new int[]{6,8}) ,WHITE_CARDINAL);
		this.theBoard.put(arrayToString(new int[]{7,8}) ,WHITE_BISHOP);
		this.theBoard.put(arrayToString(new int[]{8,8}) ,WHITE_KNIGHT);
		
		for (int i = 0; i < 10; i++) {
			this.theBoard.put(arrayToString(new int[]{i,7}) ,WHITE_PAWN);
		}

		// Unoccupied area's
		for (int i = 0; i < 10; i++) {
			this.theBoard.put(arrayToString(new int[]{i,3}) ,UNOCCUPIED);
			this.theBoard.put(arrayToString(new int[]{i,4}) ,UNOCCUPIED);
			this.theBoard.put(arrayToString(new int[]{i,5}) ,UNOCCUPIED);
			this.theBoard.put(arrayToString(new int[]{i,6}) ,UNOCCUPIED);
		}
		for (int i = 1; i < 9; i++) {
			this.theBoard.put(arrayToString(new int[]{i,9}) ,UNOCCUPIED);
			this.theBoard.put(arrayToString(new int[]{i,0}) ,UNOCCUPIED);
		}
		this.theBoard.put(arrayToString(new int[]{0,1}) ,UNOCCUPIED);
		this.theBoard.put(arrayToString(new int[]{9,1}) ,UNOCCUPIED);
		this.theBoard.put(arrayToString(new int[]{0,8}) ,UNOCCUPIED);
		this.theBoard.put(arrayToString(new int[]{9,8}) ,UNOCCUPIED);
		
	}
	public void removePiece(String possition){
		this.theBoard.put(possition, UNOCCUPIED);
	}
	
	public void movePiece(String movingPosition,String initialPosition,String piece){
		this.theBoard.put(initialPosition, UNOCCUPIED);
		this.theBoard.put(movingPosition, piece);
	}
	
	public ArrayList<String> bishopMoves(String currentPosString){
		int[] currentPossition = stringToArray(currentPosString);
		ArrayList<int[]> possibleMoves = new ArrayList<>();
		int posX = currentPossition[0];
		int posY = currentPossition[1];
		
		for (int i = 1; i < 10; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX + i;
			newY = posY + i;
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		for (int i = 1; i < 10; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX + i;
			newY = posY - i;
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		for (int i = 1; i < 10; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX - i;
			newY = posY + i;
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		for (int i = 1; i < 10; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX - i;
			newY = posY - i;
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		ArrayList<String> possibleMovesString = new ArrayList<>();
		for (int i = 0; i < possibleMoves.size(); i++) {
			possibleMovesString.add(arrayToString(possibleMoves.get(i)));
		}
		return possibleMovesString;
	}
	public ArrayList<String> castleMoves(String currentPosString){
		int[] currentPossition = stringToArray(currentPosString);
		ArrayList<int[]> possibleMoves = new ArrayList<>();
		int posX = currentPossition[0];
		int posY = currentPossition[1];
		//Conditional
		for (int i = 1; i < 10; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX;
			newY = posY + i;
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		//Conditional
		for (int i = 1; i < 10; i++) {
			int newX = 0;
			int newY = 0;
			
			newX = posX;
			newY = posY - i;
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		//Conditional
		for (int i = 1; i < 10; i++) {
			int newX = 0;
			int newY = 0;
			
			newX = posX + i;
			newY = posY;
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		//Conditional
		for (int i = 1; i < 10; i++) {
			int newX = 0;
			int newY = 0;
			
			newX = posX - i;
			newY = posY;
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		//Conditional
		ArrayList<String> possibleMovesString = new ArrayList<>();
		for (int i = 0; i < possibleMoves.size(); i++) {
			possibleMovesString.add(arrayToString(possibleMoves.get(i)));
		}
		return possibleMovesString;
	}
	public ArrayList<String> knightMoves(String currentPosString){
		int[] currentPossition = stringToArray(currentPosString);
		ArrayList<int[]> possibleMoves = new ArrayList<>();
		int posX = currentPossition[0];
		int posY = currentPossition[1];
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX + 2;
			newY = posY - 1;
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX + 2;
			newY = posY + 1;
				
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX - 2;
			newY = posY - 1;
				
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX - 2;
			newY = posY + 1;
				
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX + 1;
			newY = posY - 2;
				
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX + 1;
			newY = posY + 2;
				
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX - 1;
			newY = posY - 2;
				
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX - 1;
			newY = posY + 2;
				
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		
		ArrayList<String> possibleMovesString = new ArrayList<>();
		for (int i = 0; i < possibleMoves.size(); i++) {
			possibleMovesString.add(arrayToString(possibleMoves.get(i)));
		}
		return possibleMovesString;
	}
	/**
	 * @param currentPosString
	 * @return ArrayList<String>
	 */
	public ArrayList<String> kingMoves(String currentPosString){
		int[] currentPossition = stringToArray(currentPosString);
		ArrayList<int[]> possibleMoves = new ArrayList<>();
		int posX = currentPossition[0];
		int posY = currentPossition[1];
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX + i;
			newY = posY;
			
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX + i;
			newY = posY - i;
			
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX + i;
			newY = posY + i;
			
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX - i;
			newY = posY;
			
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX - i;
			newY = posY - i;
			
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX - i;
			newY = posY + i;
			
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX;
			newY = posY + i;
			
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		for (int i = 1; i < 2; i++) {
			int newX = 0;
			int newY = 0;
			newX = posX;
			newY = posY - i;
			
			boolean b = conditional(newX, newY, possibleMoves, currentPossition);
			if (b == true) {
				break;
			}
		}
		////potentials
		
		ArrayList<String> possibleMovesString = new ArrayList<>();
		for (int i = 0; i < possibleMoves.size(); i++) {
			possibleMovesString.add(arrayToString(possibleMoves.get(i)));
		}
		
		return possibleMovesString;
	}
	public ArrayList<String>queenMoves(String currentPosString){
		ArrayList<String> queenMoves = new ArrayList<>();
		ArrayList<String> bishop = bishopMoves(currentPosString);
		ArrayList<String> castle = castleMoves(currentPosString);
		queenMoves.addAll(bishop);
		queenMoves.addAll(castle);
		return queenMoves;
	}
	//Marshal combines the power of castle and knight
	public ArrayList<String>marshalMoves(String currentPosString){
		ArrayList<String> marshalMoves = new ArrayList<>();
		ArrayList<String> castle = castleMoves(currentPosString);
		ArrayList<String> knight = knightMoves(currentPosString);
		marshalMoves.addAll(castle);
		marshalMoves.addAll(knight);
		return marshalMoves;
		
	}
	//Cardinal combines the power of bishop and knight
	public ArrayList<String>cardinalMoves(String currentPosString){
		ArrayList<String> cardinalMoves = new ArrayList<>();
		ArrayList<String> bishop = bishopMoves(currentPosString);
		ArrayList<String> knight = knightMoves(currentPosString);
		cardinalMoves.addAll(bishop);
		cardinalMoves.addAll(knight);
		return cardinalMoves;
	}
	
	// Dont put pawns in these prisons
	public ArrayList<String> blackPrison = new ArrayList<>();
	public ArrayList<String> whitePrison = new ArrayList<>();
	
	public boolean pawnForPromotion = false;
	public ArrayList<String>pawnMoves(String currentPosString, String side){
		class possiblePawnMoves{
			public ArrayList<String> move1(String currentPosString1){
				int[] currentPossition = stringToArray(currentPosString1);
				ArrayList<int[]> possibleMoves = new ArrayList<>();
				int posX = currentPossition[0];
				int posY = currentPossition[1];
				for (int i = 1; i < 2; i++) {
					int newX = 0;
					int newY = 0;
					
					if (side.equals(BLACK_SIDE)) {
						if (posY==8) {
							newX = posX;
							newY = PieceMoves.this.blackPrison.size()>0 ? posY + 1 : posY;
							PieceMoves.this.pawnForPromotion = PieceMoves.this.blackPrison.size()>0 ? true : false;
						}
						else if ((posY==7) || (posY == 6) ) {
							newX = posX;
							newY = posY + 1;
							PieceMoves.this.pawnForPromotion = PieceMoves.this.blackPrison.size()>0 ? true : false;
						}
						else if (posY<6) {
							newX = posX;
							newY = posY + 1;
							PieceMoves.this.pawnForPromotion  = false;
						}
					}
					else if (side.equals(WHITE_SIDE)) {
						if (posY==1) {
							newX = posX;
							newY = PieceMoves.this.whitePrison.size()>0 ? posY - 1 : posY;
							PieceMoves.this.pawnForPromotion = PieceMoves.this.whitePrison.size()>0 ? true : false;
						}
						else if ((posY==2) || (posY == 3) ) {
							newX = posX;
							newY = posY - 1;
							PieceMoves.this.pawnForPromotion = PieceMoves.this.whitePrison.size()>0 ? true : false;
						}
						else if (posY>3) {
							newX = posX;
							newY = posY - 1;
							PieceMoves.this.pawnForPromotion  = false;
						}
					}
					
					boolean b = conditional(newX, newY, possibleMoves, currentPossition);
					if (b == true) {
						break;
					}
				}
				ArrayList<String> possibleMovesString = new ArrayList<>();
				for (int i = 0; i < possibleMoves.size(); i++) {
					possibleMovesString.add(arrayToString(possibleMoves.get(i)));
				}
				return possibleMovesString;
			}
			public ArrayList<String> move2(String currentPosString1){
				int[] currentPossition = stringToArray(currentPosString1);
				ArrayList<int[]> possibleMoves = new ArrayList<>();
				int posX = currentPossition[0];
				int posY = currentPossition[1];
				for (int i = 1; i < 2; i++) {
					int newX = 0;
					int newY = 0;
					
					newX = posX;
					newY = side.equals(BLACK_SIDE) ? posY + 2 : posY -2;
					boolean b = conditional(newX, newY, possibleMoves, currentPossition);
					if (b == true) {
						break;
					}
				}
				ArrayList<String> possibleMovesString = new ArrayList<>();
				for (int i = 0; i < possibleMoves.size(); i++) {
					possibleMovesString.add(arrayToString(possibleMoves.get(i)));
				}
				return possibleMovesString;
			}
			public ArrayList<String> move3R(String currentPosString1){
				int[] currentPossition = stringToArray(currentPosString1);
				ArrayList<int[]> possibleMoves = new ArrayList<>();
				int posX = currentPossition[0];
				int posY = currentPossition[1];
				for (int i = 1; i < 2; i++) {
					int newX = 0;
					int newY = 0;
					
					if ((posY!=9)&&(posX!=9)) {
						
						if (side.equals(BLACK_SIDE)) {
							if (posY==8) {
								newX = PieceMoves.this.blackPrison.size()>0 ? posX + 1 : posX;
								newY = PieceMoves.this.blackPrison.size()>0 ? posY + 1 : posY;
							}
							else if ((posY==7) || (posY == 6) ) {
								newX = posX + 1;
								newY = posY + 1;
								PieceMoves.this.pawnForPromotion = PieceMoves.this.blackPrison.size()>0 ? true : false;
							}
							else if (posY<6) {
								newX = posX + 1;
								newY = posY + 1;
								PieceMoves.this.pawnForPromotion  = false;
							}
						}
						else if (side.equals(WHITE_SIDE)) {
							if (posY==1) {
								newX = PieceMoves.this.whitePrison.size()>0 ? posX + 1 : posX;
								newY = PieceMoves.this.whitePrison.size()>0 ? posY - 1 : posY;
							}
							else if ((posY==2) || (posY == 3) ) {
								newX = posX + 1;
								newY = posY - 1;
								PieceMoves.this.pawnForPromotion = PieceMoves.this.whitePrison.size()>0 ? true : false;
							}
							else if (posY>3) {
								newX = posX + 1;
								newY = posY - 1;
								PieceMoves.this.pawnForPromotion  = false;
							}
						}
						boolean b = conditional(newX, newY, possibleMoves, currentPossition);
						if (b == true) {
							break;
						}
					}
				}
				ArrayList<String> possibleMovesString = new ArrayList<>();
				for (int i = 0; i < possibleMoves.size(); i++) {
					possibleMovesString.add(arrayToString(possibleMoves.get(i)));
				}
				return possibleMovesString;
			}
			public ArrayList<String> move3L(String currentPosString1){
				int[] currentPossition = stringToArray(currentPosString1);
				ArrayList<int[]> possibleMoves = new ArrayList<>();
				int posX = currentPossition[0];
				int posY = currentPossition[1];
				for (int i = 1; i < 2; i++) {
					int newX = 0;
					int newY = 0;
					if ((posX!=0) && (posY!=9)) {
						
						if (side.equals(BLACK_SIDE)) {
							if (posY==8) {
								newX = PieceMoves.this.blackPrison.size()>0 ? posX - 1 : posX;
								newY = PieceMoves.this.blackPrison.size()>0 ? posY + 1 : posY;
							}
							else if ((posY==7) || (posY == 6) ) {
								newX = posX - 1;
								newY = posY + 1;
								PieceMoves.this.pawnForPromotion = PieceMoves.this.blackPrison.size()>0 ? true : false;
							}
							else if (posY<6) {
								newX = posX - 1;
								newY = posY + 1;
								PieceMoves.this.pawnForPromotion  = false;
							}
						}
						else if (side.equals(WHITE_SIDE)) {
							if (posY==1) {
								newX = PieceMoves.this.whitePrison.size()>0 ? posX - 1 : posX;
								newY = PieceMoves.this.whitePrison.size()>0 ? posY - 1 : posY;
							}
							else if ((posY==2) || (posY == 3) ) {
								newX = posX - 1;
								newY = posY - 1;
								PieceMoves.this.pawnForPromotion = PieceMoves.this.whitePrison.size()>0 ? true : false;
							}
							else if (posY>3) {
								newX = posX - 1;
								newY = posY - 1;
								PieceMoves.this.pawnForPromotion  = false;
							}
						}
						
						boolean b = conditional(newX, newY, possibleMoves, currentPossition);
						if (b == true) {
							break;
						}
					}
				}
				ArrayList<String> possibleMovesString = new ArrayList<>();
				for (int i = 0; i < possibleMoves.size(); i++) {
					possibleMovesString.add(arrayToString(possibleMoves.get(i)));
				}
				return possibleMovesString;
			}
			// Conditions
			public boolean pawnHasNotMoved(String currentPosString1){
				boolean b = false;
				int[] currentPossition = stringToArray(currentPosString1);
				int posY = currentPossition[1];
				if (posY == 2 && side.equals(BLACK_SIDE)) {
					b = true;
				}
				if (posY == 7 && side.equals(WHITE_SIDE)) {
					b = true;
				}
				return b;
			}
			@SuppressWarnings("unused")
			public boolean enpassant_LeftSide(String currentPosString1){
				int[] currentPossition = stringToArray(currentPosString1);
				int posX = currentPossition[0];
				int posY = currentPossition[1];
				int enemyPosX = posX-1;
				int enemyPosY = posY;
				String pieceName = PieceMoves.this.theBoard.get(currentPosString1);
				boolean bool = occupiedByOpponent(new int[]{enemyPosX,enemyPosY}, pieceName);
				return bool;
			}
			@SuppressWarnings("unused")
			public boolean enpassant_RightSide(String currentPosString1){
				int[] currentPossition = stringToArray(currentPosString1);
				int posX = currentPossition[0];
				int posY = currentPossition[1];
				int enemyPosX = posX+1;
				int enemyPosY = posY;
				String pieceName = PieceMoves.this.theBoard.get(currentPosString1);
				boolean bool = occupiedByOpponent(new int[]{enemyPosX,enemyPosY}, pieceName);
				return bool;
			}
			public boolean isEnemyAt_DiagonalLeftGate(String currentPosString1){
				int[] currentPossition = stringToArray(currentPosString1);
				int posX = currentPossition[0];
				int posY = currentPossition[1];
				int enemyPosX = posX-1;
				int enemyPosY = side.equals(BLACK_SIDE) ? posY+1 : posY-1 ;
				String pieceName = PieceMoves.this.theBoard.get(currentPosString1);
				boolean bool = occupiedByOpponent(new int[]{enemyPosX,enemyPosY}, pieceName);
				return bool;
			}
			public boolean isEnemyAt_DiagonalRightGate(String currentPosString1){
				int[] currentPossition = stringToArray(currentPosString1);
				int posX = currentPossition[0];
				int posY = currentPossition[1];
				int enemyPosX = posX+1;
				int enemyPosY =side.equals(BLACK_SIDE) ? posY+1 : posY-1;
				String pieceName = PieceMoves.this.theBoard.get(currentPosString1);
				boolean bool = occupiedByOpponent(new int[]{enemyPosX,enemyPosY}, pieceName);
				return bool;
			}
			public boolean isPiece1StepInFront(String currentPosString1){
				boolean b = true;
				int[] currentPossition = stringToArray(currentPosString1);
				int posX = currentPossition[0];
				int posY = currentPossition[1];
				try {
					String check = side.equals(BLACK_SIDE) ? PieceMoves.this.theBoard.get(arrayToString(new int[]{posX,posY+1})) : PieceMoves.this.theBoard.get(arrayToString(new int[]{posX,posY-1}));
					if (check.equals(UNOCCUPIED)) {
						b = false;
					}
				} catch (NullPointerException e) {
					e.printStackTrace();
					b = false;
				}
				return b;
			}
			public boolean isPiece2StepInFront(String currentPosString1){
				boolean b = true;
				int[] currentPossition = stringToArray(currentPosString1);
				int posX = currentPossition[0];
				int posY = currentPossition[1];
				
				String check = side.equals(BLACK_SIDE) ? PieceMoves.this.theBoard.get(arrayToString(new int[]{posX,posY+2})) : PieceMoves.this.theBoard.get(arrayToString(new int[]{posX,posY-2}));
				if (check.equals(UNOCCUPIED)) {
					b = false;
				}
				return b;
			}
		}
		ArrayList<String> possibleMovesString = new ArrayList<>();
		possiblePawnMoves pM = new possiblePawnMoves();
		if (pM.isPiece1StepInFront(currentPosString) == false) {
			possibleMovesString.addAll(pM.move1(currentPosString));
		}
		if (pM.pawnHasNotMoved(currentPosString) && (pM.isPiece1StepInFront(currentPosString)==false) && (pM.isPiece2StepInFront(currentPosString)==false)) {
			possibleMovesString.addAll(pM.move2(currentPosString));
		}
		if (pM.isEnemyAt_DiagonalRightGate(currentPosString)) {
			possibleMovesString.addAll(pM.move3R(currentPosString));
		}
		if (pM.isEnemyAt_DiagonalLeftGate(currentPosString)) {
			possibleMovesString.addAll(pM.move3L(currentPosString));
		}
		return possibleMovesString;
	}
	public ArrayList<int[]> capturedMoves = new ArrayList<>();
	public ArrayList<String> piecePossibleMoves_Captured(){
		ArrayList<String> retArray = new ArrayList<>();
		for (int i = 0; i < this.capturedMoves.size(); i++) {
			retArray.add(arrayToString(this.capturedMoves.get(i)));
		}
		return retArray;
	}
	public boolean conditional(int x,int y,ArrayList<int[]> container,int[] currentPos){
		boolean b = false;
		if (((-1<x) && (x<10)) && ( (-1<y) && (y<10) )) {
			int[] val = {x,y};
			if (isAreaOccupied(val) == true) {
				if (occupiedByOpponent(val, this.theBoard.get(arrayToString(currentPos)))) {
					container.add(val);
					this.capturedMoves.add(val);
					b = true;
				}
				else if (occupiedByAlly(val, this.theBoard.get(arrayToString(currentPos)))) {
					b = true;
				}
			}
			else {
				container.add(val);
			}
		}
		return b;
	}
	public boolean isAreaOccupied(int[] pointCoordinate){
		String value = this.theBoard.get(arrayToString(pointCoordinate));
		boolean b = false;
		if (value.equals(UNOCCUPIED)) {
			b = false;
		}
		else if (!(value.equals(UNOCCUPIED))) {
			b = true;
		}
		return b;
	}
	public boolean occupiedByOpponent(int[] area,String pieceName){
		String sideColor = pieceName.substring(0, 5);
		boolean b = false;
		String unknownSide = null;
		try {
			unknownSide = this.theBoard.get(arrayToString(area)).substring(0,5);
			
		} catch (NullPointerException e) {
			unknownSide = UNOCC;
		}
		catch (IndexOutOfBoundsException e) {
			unknownSide = UNOCC;
		}
		if (unknownSide.equalsIgnoreCase(UNOCC)) {
			b = false;
		}
		else if (sideColor.equals(unknownSide)) {
			b = false;
		}
		else if (!(sideColor.equals(unknownSide))) {
			b = true;
		}
		return b;
	}
	public boolean occupiedByAlly(int[] area,String side){
		String sideColor = side.substring(0, 5);
		boolean b = false;
		String unknownSide;
		try {
			unknownSide = this.theBoard.get(arrayToString(area)).substring(0, 5);

			if (unknownSide.equalsIgnoreCase(UNOCC)) {
				b = false;
			}
			else if (sideColor.equals(unknownSide)) {
				b = true;
			}
			else if (!(sideColor.equals(unknownSide))) {
				b = false;
			}
		} catch (StringIndexOutOfBoundsException e) {
			unknownSide = UNOCC;
		}
		return b;
	}
	public static String arrayToString(int[] array){
		String sX = null;
		String sY = null;
		if (array[0]<10) {
			sX = "0"+array[0]; //$NON-NLS-1$
		}
		if (array[0]>9) {
			sX = Integer.toString(array[0]);
		}
		if (array[1]>9) {
			sX = Integer.toString(array[1]);
		}
		if (array[1]<10) {
			sY = "0"+array[1]; //$NON-NLS-1$
		}
		String s = sX +"_" + sY; //$NON-NLS-1$
		return s;
	}
	public static int[] stringToArray(String s){
		int x = 0;
		int y = 0;
		try {
			x = Integer.parseInt(s.substring(0,2));
			y = Integer.parseInt(s.substring(3,5));
		} catch (Exception e) {
			System.err.println("Parse String is : " + s);
		}
		int[] array = {x,y};
		return array;
	}
	
	public boolean isKingInCheck(String side){
		
		boolean b = false;
		Set<String> set = this.theBoard.keySet();
		if (side == BLACK_SIDE){
			Iterator<String> iterator = set.iterator();
			String bKingPossition = null;
			ArrayList<String> opponentsPossibleMove = new ArrayList<>();
			while (iterator.hasNext()) {
				String s = iterator.next();
				String piece = this.theBoard.get(s);
				if (piece == BLACK_KING) {
					bKingPossition = s;
				}
			}
			Iterator<String> iterator2 = set.iterator();
			while (iterator2.hasNext()) {
				String possition = iterator2.next();
				String piece = this.theBoard.get(possition);
				String sidep = null;
				try {
					sidep = piece.substring(0, 5);
				} catch (Exception e) {
					sidep = PieceMoves.UNOCCUPIED;
				}
				
				if (sidep.equals(WHITE)) {
					ArrayList<String> opponent_piece_possible = piecePossibleMoves(piece, possition);
					opponentsPossibleMove.addAll(opponent_piece_possible);
				}
			}
			if (opponentsPossibleMove.contains(bKingPossition)) {
				b = true;
			}
		}
		else if (side == WHITE_SIDE){
			Iterator<String> iterator = set.iterator();
			String wKingPossition = null;
			ArrayList<String> opponentsPossibleMove = new ArrayList<>();
			while (iterator.hasNext()) {
				String s = iterator.next();
				String piece = this.theBoard.get(s);
				if (piece == WHITE_KING) {
					wKingPossition = s;
				}
			}
			Iterator<String> iterator2 = set.iterator();
			while (iterator2.hasNext()) {
				String possition = iterator2.next();
				String piece = this.theBoard.get(possition);
				try {
					if (piece.substring(0, 5).equals(BLACK)) {
						ArrayList<String> opponent_piece_possible = piecePossibleMoves(piece, possition);
						opponentsPossibleMove.addAll(opponent_piece_possible);
					}
				} catch (StringIndexOutOfBoundsException e) {
					piece = UNOCC;
				}
			}
			if (opponentsPossibleMove.contains(wKingPossition)) {
				b = true;
			}
		}
		return b;
	}
	public boolean isKingCheckMate(String side,HashMap<String, String> board){
		boolean b = true;
		PieceMoves pm = new PieceMoves();
		pm.theBoard = board;
		
		if (side.equals(BLACK_SIDE) && (pm.isKingInCheck(BLACK_SIDE))) {
			
			Set<String> s = pm.theBoard.keySet();
			Iterator<String> it = s.iterator();
			while (it.hasNext()) {
				
				String piecePos = it.next();
				String piece = pm.theBoard.get(piecePos);
				if (piece.substring(0, 5).equals("BLACK")) { //$NON-NLS-1$
					
					ArrayList<String> aPiecePos = pm.piecePossibleMoves(piece, piecePos);
					ArrayList<String> filteredMoves = filterCheckMoves(aPiecePos, piece, piecePos, pm);
					for (int i = 0; i < filteredMoves.size(); i++) {
						pm.movePiece(filteredMoves.get(i), piecePos, piece);
						boolean simBool = pm.isKingInCheck(BLACK_SIDE);
						if (simBool == false) {
							b = false;
						}
					}
				}
			}
		}
		else if (side.equals(WHITE_SIDE) && (pm.isKingInCheck(WHITE_SIDE))) {
			
			Set<String> s = pm.theBoard.keySet();
			Iterator<String> it = s.iterator();
			while (it.hasNext()) {
				String piecePos = it.next();
				String piece = pm.theBoard.get(piecePos);
				String sidep = null;
				try {
					sidep = piece.substring(0, 5);
				} catch (Exception e) {
					sidep = UNOCCUPIED;
				}
				
				if (sidep.equals("WHITE")) { //$NON-NLS-1$
					ArrayList<String> aPiecePos = pm.piecePossibleMoves(piece, piecePos);
					ArrayList<String> filteredMoves = filterCheckMoves(aPiecePos, piece, piecePos, pm);
					for (int i = 0; i < filteredMoves.size(); i++) {
						pm.movePiece(filteredMoves.get(i), piecePos, piece);
						boolean simBool = pm.isKingInCheck(WHITE_SIDE);
						if (simBool == false) {
							b = false;
							
						}
					}
				}
			}
		}
		return b;
	}
	
	
	public static ArrayList<String> filterCheckMoves(ArrayList<String> unFilteredPossibleMoves, String pieceName,String currentPossition,PieceMoves board){
		
		ArrayList<String> filteredMoves = new ArrayList<>();
		String side = pieceName.substring(0, 5);
		if (side.equals(BLACK)) {
			for (int i = 0; i < unFilteredPossibleMoves.size(); i++) {
				PieceMoves simulatedMoves = new PieceMoves();
				simulatedMoves.theBoard.putAll(board.theBoard);
				simulatedMoves.movePiece(unFilteredPossibleMoves.get(i), currentPossition, pieceName);
				if (simulatedMoves.isKingInCheck(BLACK_SIDE) == false) {
					filteredMoves.add(unFilteredPossibleMoves.get(i));
				}
			}
		}
		else if (side.equals(WHITE)) {
			for (int i = 0; i < unFilteredPossibleMoves.size(); i++) {
				PieceMoves simulatedMoves = new PieceMoves();
				simulatedMoves.theBoard.putAll(board.theBoard);
				simulatedMoves.movePiece(unFilteredPossibleMoves.get(i), currentPossition, pieceName);
				if (simulatedMoves.isKingInCheck(WHITE_SIDE) == false) {
					filteredMoves.add(unFilteredPossibleMoves.get(i));
				}
			}
		}
		return filteredMoves;
	}
	public ArrayList<String> allPossibleMoves(String side){
		ArrayList<String> allMoves_Side = new ArrayList<>();
		ArrayList<String> filtered = new ArrayList<>();
		
		if (side.equals(BLACK_SIDE)) {
			Set<String> set = this.theBoard.keySet();
			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()){
				String pos = iterator.next();
				String pieceName = this.theBoard.get(pos);
				String pSide = pieceName.substring(0, 5);
				if (pSide.equals("BLACK")) { //$NON-NLS-1$
					ArrayList<String> posMoves = piecePossibleMoves(pieceName, pos);
					
					filtered = filterCheckMoves(posMoves, pieceName, pos, this);
					allMoves_Side.addAll(filtered);
				}
			}
		}
		else if (side.equals(WHITE_SIDE)) {
			Set<String> set = this.theBoard.keySet();
			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()){
				String pos = iterator.next();
				String pieceName = this.theBoard.get(pos);
				String pSide = pieceName.substring(0, 5);
				if (pSide.equals("WHITE")) { //$NON-NLS-1$
					ArrayList<String> posMoves = piecePossibleMoves(pieceName, pos);
					filtered = filterCheckMoves(posMoves, pieceName, pos, this);
					allMoves_Side.addAll(posMoves);
				}
			}
		}
		return allMoves_Side;
	}
	public boolean isStaleGame(){
		boolean b = false;
		// for black side
		if (isKingInCheck(BLACK_SIDE) == false) {
			ArrayList<String> allBlackMoves = new ArrayList<>();
			allBlackMoves.addAll(allPossibleMoves(BLACK_SIDE));
			
			if (allBlackMoves.size() == 0) {
				b = true;
			}
		}
		if (isKingInCheck(WHITE_SIDE) == false) {
			ArrayList<String> allWhiteMoves = new ArrayList<>();
			allWhiteMoves.addAll(allPossibleMoves(WHITE_SIDE));
			if (allWhiteMoves.size() == 0) {
				b = true;
			}
		}
		return b;
	}
	public String getPiecePossition(String pieceName){
		String piecePos = null;
		Set<String> set = this.theBoard.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String pos = it.next();
			if (this.theBoard.get(pos).equals(pieceName)) {
				piecePos = pos;
			}
		}
		return piecePos;
	}
	public ArrayList<String> getAllPositionOfSide(String side){
		ArrayList<String> allPositions = new ArrayList<>();
		
		Set<String> set = this.theBoard.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String pos = iterator.next();
			String side1 = this.theBoard.get(pos).substring(0,5);
			if (side.equals(side1)) {
				allPositions.add(pos);
			}
		}
		return allPositions;
	}
	
}
