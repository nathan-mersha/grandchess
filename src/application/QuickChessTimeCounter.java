package application;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

/*
 * This class performs the basic of ChessCounter it counts back wards from a given minute Value.
 * Displays a Message Pane when the counter is at 0
 */

public class QuickChessTimeCounter implements Runnable   {


	public static int secStandard = 59;
	
	public  int second;
	public  int minute;
	
	public SimpleStringProperty timeInfo = new SimpleStringProperty();
	public SimpleDoubleProperty remainingGameTimeSec = new SimpleDoubleProperty();
	
	public MessageTab message = new MessageTab();

	public String playersName;
	 
	
	
	public QuickChessTimeCounter(){}
	public QuickChessTimeCounter(int assignMinute,String playerName){
		this.playersName = playerName;
		this.minute = assignMinute;
		this.remainingGameTimeSec.set((this.minute*60) + 60);
	
		
	}
	private void conditionCounter() {
		if ((secStandard==0)&&(this.minute==0)) {
			System.out.println();
		} else {
			if (this.minute>=0) {
				counter();
			}
		}
	}
	public void counter() {

		try {
			for (int i = secStandard; i > -1; i--) {
					Thread.sleep(1000);
					this.remainingGameTimeSec.set(this.remainingGameTimeSec.get()-1);
					this.second = i;
					if (this.second<10) {
						this.timeInfo.set(this.minute + ":" + "0" + this.second + " min");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						
					} else {
						this.timeInfo.set(this.minute + ":" + this.second + " min"); //$NON-NLS-1$ //$NON-NLS-2$
									
					}
					if (i==0) {
						
						this.minute = this.minute-1;
						secStandard = 59;
						if(this.minute>=0){
						counter();
						}
						if(this.minute<0){
							SharedVariables.lostPlayerName = this.playersName;
							
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									try {
										Counter.secondaryStage.hide();
										QuickChessTimeCounter.this.message.start(new Stage());
										
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
							
						}
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		conditionCounter();
	}
}

