package aoc.year2023;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import aoc.Utils;
import aoc.year2023.day22.Brick;

public class Day22 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day22().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input22.txt");
		long result = process(lines);
		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		TreeSet<Brick> bricks = loadBricks(lines);
		Set<Brick> settled = settleAll(bricks);

		int sum = 0;
		for (Brick b : settled) {
			if (b.canBeDesintegrated()) {
				sum++;
			}
		}

		return sum;
	}

	Set<Brick> settleAll(TreeSet<Brick> bricks) {
		TreeSet<Brick> settled = new TreeSet<>();
		for (Brick b : bricks) {
			b.settle(settled);
			settled.add(b);
		}
		return settled;
	}

	TreeSet<Brick> loadBricks(List<String> lines) {
		TreeSet<Brick> bricks = new TreeSet<Brick>();
		for (String line : lines) {
			bricks.add(new Brick(line));
		}
		return bricks;
	}

}
