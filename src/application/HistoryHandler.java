package application;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoryHandler {
	HashMap<String, String> theBoard;
	ArrayList<String[]> historyHolder = new ArrayList<>();
	
	public HistoryHandler(HashMap<String, String> theboard){
		this.theBoard = theboard;
	}
	public void addHistory(String positionA, String pieceA , String positionB, String pieceB){
		String[] theHistory = {positionA,pieceA,positionB,pieceB};
		this.historyHolder.add(theHistory);
	}
	public String[] getLattestHistory(){
		int historySize = this.historyHolder.size()-1;
		String[] lattestHistory = this.historyHolder.get(historySize);
		this.historyHolder.remove(historySize);
		
		return lattestHistory;
	}
	public boolean anyHistoryLeft(){
		int historySize = this.historyHolder.size();
		return historySize>0 ? true : false;
	}
	
}
