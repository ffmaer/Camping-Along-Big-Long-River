/*
 * River is a parental agent storing and processing information along the entire river. It
 * contains important information about the number of Cells, Camps, the positions of boats
 * on river, and the length of the river (225 miles). The river agent also forces the boats to
 * move only in one direction.
 */
import java.util.ArrayList;

public class River {
	final static int cellsCount = 2000;// 1000 cells 2000 half cells
	final static int lastCellIndex = 1999;

	final static int length = 225;// miles
	final static boolean direction = true;
	static ArrayList<Cell> cells;
	static ArrayList<Boat> boats;
	static ArrayList<Camp> camps;

	public static void addBoat(Boat boat) {
		Cell firstCell = cells.get(0);
		boats.add(boat);
		firstCell.boatsList.add(boat);
	}

	public static void removeBoat(Boat boat) {
		boats.remove(boat);
	}

	public static void boatsOnTheRiverReport() {
		// System.out.println(boats.size());

		// Iterator<Cell> it = cells.iterator();
		// while(it.hasNext()){
		// Cell cell = it.next();
		// if(cell.hasCamp && !cell.camp.empty){
		// System.out.print("x");
		// }
		// else{
		// System.out.print("o");
		// }
		//
		// }
		// System.out.println("-------------");

		// Iterator<Boat> it = boats.iterator();
		// int max = 0;
		// int mean = 0;
		// int sum =0;
		// int time;
		// while(it.hasNext()){
		// Boat boat = it.next();
		// time = boat.cellsTraveledSinceLastRest;
		// if(time > max){
		// sum += time;
		// max = time;
		// }
		// }
		// mean = sum/(boats.size()+1);
		// System.out.println("max:"+max);
		// System.out.println("mean:"+mean);

	}
}