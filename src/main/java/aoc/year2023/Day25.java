package aoc.year2023;

import java.util.List;
import java.util.Optional;

import aoc.Utils;
import aoc.year2023.day25.Graph;

public class Day25 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day25().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input25.txt");
		long result = process(lines);
		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		Graph g = new Graph(lines);

		int all = g.components.size();
		long result = 0;

		int loop = 0;
		do {
			g = new Graph(lines);
			System.out.println(loop++);
			Optional<Integer> r = g.karger(3, 2);
			if (r.isPresent()) {
				int s = r.get();

				result = s * (all - s);
				System.out.println("Result size: " + s);
				System.out.println(g);
			}
		} while (result == 0);

		return result;
	}

}
