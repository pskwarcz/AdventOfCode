package aoc.year2023;

import java.util.List;

import aoc.Utils;

public class Day {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input9.txt");
		long result = process(lines);
		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {

		long sum = 0;

		return sum;
	}

}
