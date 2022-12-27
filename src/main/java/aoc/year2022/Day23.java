package aoc.year2022;

import java.util.List;

import aoc.Utils;

public class Day23 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		List<String> lines = Utils.readFile("/aoc/year2022/input23");

		long result = new Day23().start(lines);

		System.out.println("\nRESULT:" + result);
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("Time[ms]: " + duration);
	}

	Board b;

	public long start(List<String> lines) {
		init(lines);

		// System.out.println("\nINITIAL BOARD");
		// System.out.println(b);
		int i = 0;
		do {
			i++;
			System.out.println("\nROUND " + i);
			b.nextTurn();
		} while (!b.noElfMoved());

		System.out.println("\nFINAL BOARD");
		System.out.println(b);

		return i;
	}

	private void init(List<String> lines) {
		b = new Board(lines);
		b.printMax();

	}

}
