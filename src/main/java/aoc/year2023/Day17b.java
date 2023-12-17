package aoc.year2023;

import java.util.List;

import aoc.year2023.day17.HeatMap;
import aoc.year2023.day17.Node;

public class Day17b extends Day17 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day17b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	@Override
	long process(List<String> lines) {
		HeatMap.minMoves = 4;
		HeatMap.maxMoves = 10;

		HeatMap m = new HeatMap(lines);

		m.analyze();
		Node finish = m.getFinalNode();

		System.out.println(finish);
		return finish.getMinHeat();
	}

}
