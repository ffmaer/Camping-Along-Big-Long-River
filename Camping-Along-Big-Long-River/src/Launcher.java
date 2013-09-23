import java.util.Random;
public class Launcher{
	
	
	static double fastMean;
	static double slowMean;
	
	static double[] fastConfig ={2,1.5}; //speed, camp hours mean, camp hours std
	static double[] slowConfig ={1,2};
	
	static int interval;
	static int mark1;
	static int mark2;
	static int boatId;
	
	
	public static void putBoat(int time){
		if(poisson()){
			Random rand = new Random();
			int num = rand.nextInt(99);//0,1,2...,98
			Boat slow,fast;
			if(num<mark1){
				slow = new Boat(slowConfig,slowMean,boatId,time*God.UNIT);
				River.addBoat(slow);
				boatId ++;
			}
			else if(num>=mark1 && num <mark2){
				fast = new Boat(fastConfig,fastMean,boatId,time*God.UNIT);
				River.addBoat(fast);
				boatId ++;
			}
			else if(num>=mark2){
				slow = new Boat(slowConfig,slowMean,boatId,time*God.UNIT);
				River.addBoat(slow);
				boatId ++;
				fast = new Boat(fastConfig,fastMean,boatId,time*God.UNIT);
				River.addBoat(fast);
				boatId ++;
			}
		}
	}
	
	public static boolean poisson(){
		Random rand = new Random();
		int num = rand.nextInt(2);//0,1
		if(num == 1){
			return true;
		}
		return false;
	}
}
