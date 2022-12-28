package aoc.year2021;

import java.util.List;

import aoc.Utils;

public class Day7 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		List<String> lines = Utils.readFile("/aoc/year2021/input3");

		long result = new Day7().start(lines);
		System.out.println("RESULT: " + result);

		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private int start(List<String> lines) {

		for (String line : lines) {

		}

		return 0;
	}

}
