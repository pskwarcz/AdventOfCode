package aoc.year2023;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import aoc.year2023.day22.Brick;

public class Day22b extends Day22 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day22b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	@Override
	long process(List<String> lines) {
		TreeSet<Brick> bricks = loadBricks(lines);
		Set<Brick> settled = settleAll(bricks);

		int sum = 0;
		for (Brick b : settled) {
			settled.stream().forEach(s -> s.desintegrated = false);
			sum += b.chainReaction();
		}
		return sum;
	}

}
