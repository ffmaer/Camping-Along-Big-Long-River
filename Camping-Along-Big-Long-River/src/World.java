public class World {
	final static int MONTH = 6;
	final static int LENGTH = (int) (MONTH * 30 * 24 * (60 / God.UNIT));

	final static int daysUp = 18;
	final static int daysLow = 6;

	public static void init(int interval, int mark1, int mark2,
			double fastMean, double slowMean) {
		Launcher.interval = interval;// 1-->71
		Launcher.mark1 = mark1;// 0-98-
		Launcher.mark2 = mark2;// 0-98

		// this decided the capacity of the river
		Launcher.fastMean = fastMean;
		Launcher.slowMean = slowMean;
	}

	public static void printData() {
		for (Integer id : God.report.keySet()) {
			Double[] data = God.report.get(id);
			String values = "";
			for (Double value : data) {
				values += value + "\t";
			}
			System.out.println(id + "\t" + values);
		}
	}

	public static double[] outputArray() {

		// 5 parameters that will be used in the output array
		double moreDays = 0;
		double lessDays = 0;
		double averageDays = 0;
		double averageEncountersPerDayPerBoat = 0;
		double averageBoatsPerDay = 0;

		// intermediate parameters that won't be used in output array
		double totalMinutes = 0;
		double totalDays = 0;
		double totalBoats = 0;
		double totalEncounters = 0;
		double minutes = 0;
		double averageEncountersPerDayTotal = 0;

		for (Integer id : God.report.keySet()) {
			Double[] data = God.report.get(id);

			minutes = data[1] - data[0];
			if (minutes / 60 / 24 > daysUp) {
				moreDays++;
			}
			if (minutes / 60 / 24 < daysLow) {
				lessDays++;
			}
			totalMinutes += minutes;
			totalBoats++;
			totalEncounters += data[4];

			if (id == 1000) {
			}
			averageEncountersPerDayTotal += data[4] / (minutes / 60 / 24);

		}
		totalDays = totalMinutes / 60 / 24;
		averageEncountersPerDayPerBoat = averageEncountersPerDayTotal
				/ totalBoats;
		averageBoatsPerDay = totalBoats / MONTH / 30;
		averageDays = totalDays / totalBoats;

		// pack the 5 parameters into an array and return it
		double[] outputs = { moreDays, lessDays, averageDays,
				averageEncountersPerDayPerBoat, averageBoatsPerDay };
		return outputs;
	}

	public static double target_function_score() {
//		unpack the 5 parameters from the output array
		double[] outputArray = outputArray();
		double moreDays = outputArray[0];
		double lessDays = outputArray[1];
		double averageDays = outputArray[2];
		double averageEncountersPerDayPerBoat = outputArray[3];
		double averageBoatsPerDay = outputArray[4];

//		print values of every parameters
		System.out.println("more days: " + moreDays);
		System.out.println("less days: " + lessDays);
		System.out.println("ave. days: " + averageDays);
		System.out.println("ave. encount.: " + averageEncountersPerDayPerBoat);
		System.out.println("ave. boats: " + averageBoatsPerDay);
		
//		calculate the score and return it
		double score = averageBoatsPerDay - averageEncountersPerDayPerBoat
		- moreDays - lessDays;
		System.out.println("score: " + score);
		return score;

	}

	public static void run() {
		God.createTheWorld();
		int time = 0;
		while (time < LENGTH) {
			TimeController.nextSession(time);
			time++;
		}
	}

}