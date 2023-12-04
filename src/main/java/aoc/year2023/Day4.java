package aoc.year2023;

import java.util.Arrays;
import java.util.List;

import aoc.Utils;

public class Day4 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day4().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input4");

		long result = process(lines);

		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		long sum = 0;

		for (String line : lines) {
			sum += cardPoint(line);
		}

		return sum;
	}

	private long cardPoint(String line) {
		String cards = line.split(":")[1];
		String winning = cards.split("\\|")[0];
		String numers = cards.split("\\|")[1];

		List<String> w = Arrays.asList(winning.trim().split("\\s+"));
		List<String> n = Arrays.asList(numers.trim().split("\\s+"));

		// System.out.println(cards);
		// System.out.println(w);
		// System.out.println(n);

		long c = n.stream().filter(w::contains).count();
		long points = (long) Math.pow(2, c - 1);
		System.out.println("count:" + c + " points:" + points);
		return points;
	}

}
