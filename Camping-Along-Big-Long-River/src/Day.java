public class Day{
	final static int HOUR = (int)(60/God.UNIT);
	final static int DAY = 24*HOUR;
	final static int WORKING_TIME_START = 8*HOUR;
	final static int WORKING_TIME_END = 16*HOUR;
	final static int REST_TIME_START = 18*HOUR; 
	
	public static boolean workingTime(int time){
		if(time % DAY > WORKING_TIME_START && time % DAY < WORKING_TIME_END){
			return true;
		}
		return false;
	}
	
	public static boolean restTime(int time){
		if(time % DAY < WORKING_TIME_START && time % DAY > REST_TIME_START){
			return true;
		}
		return false;
	}
	
	public static boolean interval(int time){
		if(time % Launcher.interval == 0){
			return true;
		}
		return false;	
	}
}