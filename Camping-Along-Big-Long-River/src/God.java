import java.util.HashMap;
import java.util.ArrayList;
public class God{
	static int campCount;
	static int orginalBoatCount;
	final static double UNIT = 1.6875;
	
	static HashMap<Integer, Double[]> report;
	
	static Boat marker;
	
	public static void createTheWorld(){
		
		River.cells = new ArrayList<Cell>();
		River.boats = new ArrayList<Boat>();	
		River.camps = new ArrayList<Camp>();
		report = new HashMap<Integer, Double[]>();
		Launcher.boatId = 0;
		
		for(int i=0;i<River.cellsCount;i++){
			River.cells.add(new Cell(i));
		}
		
	}
}