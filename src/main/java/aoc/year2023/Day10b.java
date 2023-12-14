package aoc.year2023;

import java.util.List;

import aoc.Utils;
import aoc.year2023.day10.Map;
import aoc.year2023.day10.Pawn;

public class Day10b {

	public static char replaceS;

	public Day10b(char c) {
		// TODO this can be calculated, but was easier to just find S on map and figure
		// out to which letter should it be changed
		replaceS = c;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day10b('L').start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input10.txt");

		long result = process(lines);

		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		Map map = new Map(lines);

		Pawn p = new Pawn(map);
		p.move();

		while (!p.isAtS()) {
			p.move();
		}

		int res = p.countEnclosed();

		System.out.println("Finished in " + res);

		return res;
	}

}
