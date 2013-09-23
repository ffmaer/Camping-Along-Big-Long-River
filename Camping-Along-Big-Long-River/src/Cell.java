
import java.util.ArrayList;
public class Cell{
	boolean hasCamp = false;
	Camp camp;
	int id;
	ArrayList<Boat> boatsList = new ArrayList<Boat>();
	public Cell(int id){
		this.id = id;
		if(id%4==2){//so you don't have a camp at the starting point
			hasCamp = true;	
			camp = new Camp();
			River.camps.add(camp);
		}
	}
	public void cleanUp(){
		
		// if(id == 10){
		// 	System.out.println(boatsList.size());
		// }
		boatsList = new ArrayList<Boat>();
	}	
}