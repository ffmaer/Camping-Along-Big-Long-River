/*
 * TimeController orders agents to interact with each other, thus stimulating the model. It
 * ask Cell to clean up data after a Boat has moved away, ask Launcher to put more Boats
 * on river, ask Camp to kick out Boats that rest up to m hours, and finally ask Boat to make
 * movement decision.
 */

import java.util.ArrayList;
import java.util.Iterator;

public class TimeController {
	public static void nextSession(int time) {

		cleanUpCells();
		if (Day.workingTime(time) && Day.interval(time)) {
			launcherTakeActions(time);
		}

		eachCampTakeActions();
		eachBoatTakeActions(time, Day.restTime(time));

		River.boatsOnTheRiverReport();

	}

	public static void cleanUpCells() {
		Iterator<Cell> iter = River.cells.iterator();
		while (iter.hasNext()) {
			Cell currentCell = iter.next();
			currentCell.cleanUp();
		}
	}

	public static void launcherTakeActions(int time) {
		Launcher.putBoat(time);
	}

	public static void eachCampTakeActions() {
		Iterator<Camp> iter = River.camps.iterator();
		while (iter.hasNext()) {
			Camp currentCamp = iter.next();
			currentCamp.kickPeopleOut();
		}
	}

	public static void eachBoatTakeActions(int time, boolean night) {

		Iterator<Boat> iter = River.boats.iterator();
		ArrayList<Boat> boatsToBeRemoved = new ArrayList<Boat>();
		while (iter.hasNext()) {

			Boat currentBoat = iter.next();
			currentBoat.minutesTraveled += God.UNIT;
			if (!currentBoat.inCamp) {
				currentBoat.minutesOnWater += God.UNIT;
				currentBoat.move();
				if (currentBoat.withinRange()) {
					if (!currentBoat.halfway()) {
						currentBoat.decideCampingProblem(night);
					}
				} else {

					// System.out.println("out");

					Double[] data = { currentBoat.startTime, time * God.UNIT,
							currentBoat.restTime, currentBoat.minutesTraveled,
							(double) currentBoat.encountersCount,
							currentBoat.speed, (double) currentBoat.campStayed,
							currentBoat.minutesOnWater / currentBoat.campStayed };
					God.report.put((Integer) currentBoat.id, data);
					boatsToBeRemoved.add(currentBoat);
				}
			} else {
				currentBoat.restTime += God.UNIT;
			}
			// System.out.println(currentBoat.minutesTraveled
			// +"\t"+currentBoat.minutesOnWater+"\t"+currentBoat.restTime);

			// if(time==100000){
			// System.out.println(currentBoat.cellId);
			// }

		}

		iter = River.boats.iterator();
		while (iter.hasNext()) {
			Boat currentBoat = iter.next();
			if (!currentBoat.inCamp) {
				if (currentBoat.withinRange()) {

					currentBoat.decideEncounterProblem();
				}
			}

		}

		// remove boats

		iter = boatsToBeRemoved.iterator();
		while (iter.hasNext()) {
			Boat currentBoat = iter.next();
			River.boats.remove(currentBoat);
		}

		// System.out.println(River.boats.size());
	}

}