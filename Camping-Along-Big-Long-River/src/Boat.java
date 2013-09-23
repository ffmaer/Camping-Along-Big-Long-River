import java.util.Map.Entry;
import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math;
public class Boat{
	
	
	// -,- -,- -,- -,- -,- -,- -,- -,- -,- -,- index -,- -,- -,- -,- -,- -,- -,- -,- -,- -,- -,- -,- 

	
	
	int id;
	double startTime;
	double endTime;

	double restTime=0;
	double minutesTraveled=0;
	
	int encountersCount=0;
	double speed;
	
	int campStayed=0;
	double minutesOnWater = 0;
	
	
	// -,- -,- -,- -,- -,- -,- -,- -,- -,- -,- index -,- -,- -,- -,- -,- -,- -,- -,- -,- -,- -,- -,- 

	
	
	static double alpha = 700;
	static double beta=0.001;
	
	HashMap<Boat, Integer> boatsInView = new HashMap<Boat, Integer>();
	ArrayList<Boat> boatList = new ArrayList<Boat>();
	int cellId=0;
	boolean inCamp = false;
	int lookAheadCampCount = 2;
	double minutesTraveledSinceLastRest = 0;
	int w =1;//a ratio in the decision making process
	boolean endTrip = false;
	
	double campTimeMean;
	double campDurationStd;
	
	
	public Boat(double[] type, double campTimeMean, int id, double startTime){
		this.startTime = startTime;
		this.speed = type[0];// the speed can be either slow or fast
		this.campTimeMean = campTimeMean;
		this.campDurationStd = type[1];
		this.id = id;
		
	}
	
	public int getEmptyCampCount(){
		int counter=0;
		for(int i =0;i<lookAheadCampCount;i++){
			if(cellId < River.cellsCount-i){
				Cell theCell = River.cells.get(cellId);
				if(theCell.hasCamp){
					if(theCell.camp.empty){
						counter++;
					}
				}
			}
		}
		return counter;
	}
	
	public void decideCampingProblem(boolean night){
		if(River.cells.get(cellId).hasCamp){
			
			Camp camp = River.cells.get(cellId).camp;
			Random rnd = new Random();
			
			if(camp.empty){
				double probStay,probContinue;
				if(night){
					probContinue = 0;
					probStay = 1;
				}
				else{
					// int m = getEmptyCampCount();
					double n =minutesTraveledSinceLastRest;
					double t=minutesTraveled;
					probStay = (alpha/t)*(1-1/t)*(1-Math.exp(-beta*n));
					probContinue = 1-probStay;
				}
				boolean wantToStay = false;
			

				if(rnd.nextFloat() > probContinue){
					wantToStay = true;
				}
				else
				{
					wantToStay = false;
				}
				if(wantToStay){
					minutesTraveledSinceLastRest = 0;
					camp.boat = this;
					camp.empty =false;
					inCamp = true;
					camp.stayLength = (campDurationStd * rnd.nextGaussian() + campTimeMean)*60;
					campStayed ++;
				}
			}
		}
	}
	public void add(Boat boat){
		boatList.add(boat);
	}
	
	
	public void move(){

		cellId += speed;
		if(cellId<River.cellsCount){
			River.cells.get(cellId).boatsList.add(this);
		}
		minutesTraveledSinceLastRest += God.UNIT;

		
	}
	public boolean withinRange(){
		if(cellId > River.lastCellIndex){
			endTrip = true;
			return false;
		}
		return true;
	}
	public boolean halfway(){
		if(cellId %2 == 1){
			return true;
		}
		return false;
	}

	public void decideEncounterProblem(){
		HashMap<Boat, Integer> newBoatsInView = new HashMap<Boat, Integer>();
		Cell currentCell = River.cells.get(cellId);
		
		newBoatsInView = look(currentCell, newBoatsInView);
		if(cellId > 0){
			Cell backCell = River.cells.get(cellId-1);
			newBoatsInView = look(backCell, newBoatsInView);
		}
		if(cellId < River.length-1){
			Cell frontCell = River.cells.get(cellId+1);
			newBoatsInView = look(frontCell, newBoatsInView);
		}

		boatsInView = newBoatsInView;
		Iterator<Entry<Boat, Integer>> iter = boatsInView.entrySet().iterator();
		while(iter.hasNext()){
			Integer duration = iter.next().getValue();
			if(duration>=2){
				encountersCount++;

			}
		}
	}

//  ~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~. assistant functions ~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.



public HashMap<Boat, Integer> look(Cell cell, HashMap<Boat, Integer> newBoatsInView){
	if(cell.boatsList.size()>0){
		Iterator<Boat> iter = cell.boatsList.iterator();
		Boat currentBoat;
		while(iter.hasNext()){
			currentBoat = iter.next();
			if(currentBoat.id != id){
				if(boatsInView.containsKey(currentBoat)){
					newBoatsInView.put(currentBoat,boatsInView.get(currentBoat)+1);
				}
				else{
					newBoatsInView.put(currentBoat,1);
				}
			}
		
		}
	}
	return newBoatsInView;
}


}