
public class Camp{
	boolean empty=true;
	Boat boat;
	int duration=0;
	double stayLength;
	
	public void kickPeopleOut(){
		if(!empty){
			duration += God.UNIT;
			if(duration >= stayLength){
				boat.inCamp = false;
				resetCamp();
			}
		}
	}
	public void resetCamp(){
		empty = true;
		duration = 0;
	}
	
}