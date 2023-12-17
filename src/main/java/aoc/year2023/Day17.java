package aoc.year2023;

import java.util.List;

import aoc.Utils;
import aoc.year2023.day17.HeatMap;
import aoc.year2023.day17.Node;

public class Day17 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day17().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input17.txt");
		long result = process(lines);
		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		HeatMap m = new HeatMap(lines);

		m.analyze();
		Node finish = m.getFinish();

		System.out.println(finish);
		return finish.getMinHeat();
	}

}
