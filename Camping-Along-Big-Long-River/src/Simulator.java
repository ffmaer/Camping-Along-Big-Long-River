import java.util.Random;
import java.lang.Math;

public class Simulator {

	/*
	 * Simulated annealing (SA) is a generic probabilistic metaheuristic for the
	 * global optimization problem of locating a good approximation to the
	 * global optimum of a given function in a large search space. It is often
	 * used when the search space is discrete (e.g., all tours that visit a
	 * given set of cities). For certain problems, simulated annealing may be
	 * more efficient than exhaustive enumeration Ñ provided that the goal is
	 * merely to find an acceptably good solution in a fixed amount of time,
	 * rather than the best possible solution.
	 */
	
	
	final static int initial_a = 25;
	final static int initial_b = 80;
	final static int initial_c = 95;
	final static int initial_d = 18;
	final static int initial_e = 18;

	

	public static void simulated_annealing() {

		
		
		
		int maxTries = 10;
		int a, b, c, d, e;//five critical parameters
		Random rnd = new Random();
		double best = -9999;
		int best_a = 0, best_b = 0, best_c = 0, best_d = 0, best_e = 0;
		int best_a_local = 0, best_b_local = 0, best_c_local = 0, best_d_local = 0, best_e_local = 0;
		int j;
		double tmin = 0.01;
		double tmax = 0.03;
		double t;
		int numberOfVars = 5;
		double r = 5 / (double) (maxTries * numberOfVars);
		double vc = -9999, vn = -9999, delta = 0;

		for (int tries = 0; tries < maxTries; tries++) {

			j = 0;

			a = rnd.nextInt(initial_a) + 5;
			b = rnd.nextInt(initial_b);
			c = rnd.nextInt(initial_c - b) + b;
			d = rnd.nextInt(initial_d) + 6;
			e = rnd.nextInt(initial_e) + 6;

			double var[] = { a, b, c, d, e };
			System.out.println("try no." + tries);
			System.out.println("new try: " + var[0] + "\t" + var[1] + "\t"
					+ var[2] + "\t" + var[3] + "\t" + var[4]);
			World.init((int) var[0], (int) var[1], (int) var[2], var[3], var[4]);
			World.run();
			vc = World.target_function_score();

			while (vc < 0) {
				var[0] = rnd.nextInt(25) + 5;
				var[1] = rnd.nextInt(80);
				var[2] = rnd.nextInt(95 - b) + b;
				var[3] = rnd.nextInt(18) + 6;
				var[4] = rnd.nextInt(18) + 6;

				System.out.println("try no." + tries);
				System.out.println("new try: " + var[0] + "\t" + var[1] + "\t"
						+ var[2] + "\t" + var[3] + "\t" + var[4]);
				World.init((int) var[0], (int) var[1], (int) var[2], var[3],
						var[4]);
				World.run();
				vc = World.target_function_score();
			}

			if (vc > best) {
				best = vc;
				best_a = a;
				best_b = b;
				best_c = c;
				best_d = d;
				best_e = e;
			}

			System.out.println("global best: " + best + "\t" + best_a + "\t"
					+ best_b + "\t" + best_c + "\t" + best_d + "\t" + best_e);
			System.out.println("------------------------------");

			t = tmax;
			while (t >= tmin) {
				t = tmax * Math.exp(-j * r);
				// for(int k=1;k<numberOfVars;k++ ){
				//
				// }

				for (int k = 0; k < numberOfVars; k++) {

					// k = rnd.nextInt(5);

					if (k == 0) {

						if (var[k] > 69) {
							var[k]--;
						} else if (var[k] < 2) {
							var[k]++;
						} else {
							if (rnd.nextInt(2) == 1) {
								var[k]++;
							} else {
								var[k]--;
							}
						}
					} else if (k == 1) {
						if (var[k] > 79) {
							var[k]--;
						} else if (var[k] < 2) {
							var[k]++;
						} else {
							if (rnd.nextInt(2) == 1) {
								var[k]++;
							} else {
								var[k]--;
							}
						}
					} else if (k == 2) {
						if (var[k] - 1 == var[k - 1]) {
							var[k]++;
						} else if (var[k] > 95) {
							var[k]--;
						} else {
							if (rnd.nextInt(2) == 1) {
								var[k]++;
							} else {
								var[k]--;
							}
						}
					} else if (k == 3) {
						if (var[k] > 29) {
							var[k]--;
						} else if (var[k] < 13) {
							var[k]++;
						} else {
							if (rnd.nextInt(2) == 1) {
								var[k]++;
							} else {
								var[k]--;
							}
						}
					} else if (k == 4) {
						if (var[k] > 29) {
							var[k]--;
						} else if (var[k] < 13) {
							var[k]++;
						} else {
							if (rnd.nextInt(2) == 1) {
								var[k]++;
							} else {
								var[k]--;
							}
						}
					}
					System.out.println("temp: " + t);

					System.out.println("try no." + tries);

					System.out.println("new try: " + var[0] + "\t" + var[1]
							+ "\t" + var[2] + "\t" + var[3] + "\t" + var[4]);

					World.init((int) var[0], (int) var[1], (int) var[2],
							var[3], var[4]);
					World.run();
					vn = World.target_function_score();

					if (vn > best) {
						best = vn;
						best_a = a;
						best_b = b;
						best_c = c;
						best_d = d;
						best_e = e;
					}

					System.out.println("global best: " + best + "\t" + best_a
							+ "\t" + best_b + "\t" + best_c + "\t" + best_d
							+ "\t" + best_e);
					System.out.println("------------------------------");

					delta = vc - vn;
					if (delta < 0) {
						vc = vn;
						best_a_local = a;
						best_b_local = b;
						best_c_local = c;
						best_d_local = d;
						best_e_local = e;
					} else {
						if (rnd.nextFloat() < Math.exp(-delta / t)) {
							vc = vn;
							best_a_local = a;
							best_b_local = b;
							best_c_local = c;
							best_d_local = d;
							best_e_local = e;
						}
					}
				}

				System.out.println("*******");
				System.out.println("*******");
				System.out.println("*******");
				System.out.println("*******");

				j++;

			}

			if (vc > best) {
				best = vc;
				best_a = a;
				best_b = b;
				best_c = c;
				best_d = d;
				best_e = e;
			}

		}

		System.out.println(best);
		System.out.println(best_a);
		System.out.println(best_b);
		System.out.println(best_c);
		System.out.println(best_d);
		System.out.println(best_e);

		// a=12; time unit 1-70
		// b=33; mark1
		// c=66; mark2
		// d=20; mean fast 12 - 30
		// e=18; mean slow 12 - 30

		// 10
		// 9
		// 45
		// 23
		// 19
		// a=50;
		// b=33;
		// c=66;
		// d=20;
		// e=18;

		// a=rnd.nextInt(69)+1;
		// b=rnd.nextInt(80);
		// c=rnd.nextInt(95-b)+b;
		// d=rnd.nextInt(18)+12;
		// e=rnd.nextInt(18)+12;

		// System.out.println(a+"\t"+b+"\t"+c+"\t"+d+"\t"+e);

		// for(int a=1;a<25;a++){
		// for(int b=0;b<80;b++){
		// for(int c=b;c<95;c++){
		// for(int d=12;d<30;d++){
		// for(int e=12;e<30;e++){

		// a=10;
		// b=9;
		// c=45;
		// d=23;
		// e=19;

		// double var[] = {10,9,45,23,19};

		// ave. days: 11,12,13 ave. encounters: small ave. boats per day: large

	}

	public static void main(String[] args) {
		// sa();
		// 16 50 87 13 7
		// 7 64 75 6 6

		double max = 0, min = 0, day = 0, encount = 0, boat = 0;

		// for(int i=3;i<30;i++){
		for (int i = 0; i < 5; i++) {
			World.init(23, 33, 66, 19, 18);
			// 9
			// 22
			// 86
			// 21
			// 20

			// Mcm.init(i,22,86,21,20);
			World.run();
			// Mcm.output();
			double[] o = World.outputArray();
			max += o[0];
			min += o[1];
			day += o[2];
			encount += o[3];
			boat += o[4];
			System.out.println("-----------------");

		}

		System.out.println("max: " + max / 5);
		System.out.println("min: " + min / 5);
		System.out.println("day: " + day / 5);
		System.out.println("encount: " + encount / 5);
		System.out.println("boat: " + boat / 5);

	}
}