package aoc.year2023;

import java.util.List;

import aoc.Utils;
import aoc.year2023.day10.Map;
import aoc.year2023.day10.Pawn;

public class Day10 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day10().start();
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
		System.out.println(map);

		Pawn p = new Pawn(map);
		System.out.println(p);
		p.move();
		System.out.println(p);

		int moves = 1;

		while (!p.isAtS()) {
			p.move();
			moves++;
		}

		System.out.println("Finished in " + moves);
		long res = moves / 2;
		System.out.println("Finished in " + res);

		return res;
	}

}
