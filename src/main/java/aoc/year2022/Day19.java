package aoc.year2022;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import aoc.Utils;

public class Day19 {

	private static final boolean FIRST_BLUEBRINT_ONLY = false;

	private static final int PRINT_SEQUENCE_SIZE_LENGT_LIMIT = 4;

	public static final boolean PRINT_PRODUCTION_STEPS = false;

	private static final long STOP_AFER_ITERATION = -1;

	public static void main(String[] args) {
		List<String> lines = Utils.readFile("/aoc/year2022/input19");

		long start = System.currentTimeMillis();

		int result = new Day19().start(lines);

		System.out.println("RESULT:" + result);
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("Time[ms]: " + duration);
	}

	List<Blueprint> blueprints = new ArrayList<>();

	Map<Integer, Integer> best = new HashMap<>();

	public static long iterations = 0;
	public static long simulations = 0;

	public int start(List<String> lines) {

		readBlueprints(lines);

		for (Blueprint b : blueprints) {
			best.put(b.number, 0);
		}

		for (Blueprint b : blueprints) {
			System.out.println(b);

			ProductionSequence seq = new ProductionSequence(b);
			iterations = 0;
			simulations = 0;
			while (seq.hasNext()) {

				// System.out.println("----------------");
				// print(iterations + "\t: \t" + seq.get() + "\n");

				boolean finished = runSimulation(b, seq);

				if (finished) {
					// no GEODES produced
					// print("\t >>>>>>>> \n");
					seq.nextDown();
				} else {
					// NOT ALL ROBOTS BUILD - sequence too LONG
					// print("\t\t\t\t\t\t\t\t NOT ALL ROBOTS BUILT\n");
					seq.next();
				}

				iterations++;

				if (STOP_AFER_ITERATION > 0 && iterations >= STOP_AFER_ITERATION) {
					break;
				}

			}

			System.out.println("\n iterations: " + iterations);
			System.out.println(" skipped: " + (iterations - simulations));
			System.out.println(" simulations: " + simulations);
			iterations = 0;
			simulations = 0;

			System.out.println("Best: " + best);

			if (best.get(b.number) == 0) {
				System.out.println("\n\nOne of the blueprints max is 0: " + b.number);
				return 0;
			}

			System.out.println();
			if (FIRST_BLUEBRINT_ONLY)
				break;

		}

		int result = 1;
		for (Entry<Integer, Integer> e : best.entrySet()) {
			int idx = e.getKey();
			int max = e.getValue();
			result = result * max;

		}
		return result;
	}

	private void print(String string) {
		int from = 526326;
		int to = from + 100;
		if (iterations >= from && iterations < to) {
			System.out.print(string);
		}

	}

	private boolean runSimulation(Blueprint b, ProductionSequence seq) {
		Deque<Material> process = seq.get();

		if (process.size() <= PRINT_SEQUENCE_SIZE_LENGT_LIMIT) {
			System.out.println(iterations + "\tsequence:\t" + process);
		}

		int geodes = new RobotFactory(b, process).run();
		// System.out.println("GEO produced:" + geodes);

		if (best.get(b.number) < geodes) {
			System.out.println("Best sequence so far (" + geodes + "): " + seq.get());
			best.put(b.number, geodes);
		}

		return process.isEmpty();

	}

	private void readBlueprints(List<String> lines) {
		if (true) {
			Blueprint bp = new Blueprint(lines.get(2));
			blueprints.add(bp);
			return;
		}

		int n = 0;

		for (String line : lines) {
			Blueprint bp = new Blueprint(line);
			blueprints.add(bp);
			if (++n >= 3) {
				return;
			}

			if (FIRST_BLUEBRINT_ONLY)
				return;
		}
	}

}
