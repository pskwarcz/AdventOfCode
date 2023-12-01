package aoc.year2023;

import java.util.List;

import aoc.Utils;

public class Day1 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day1().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input1");

		int result = process(lines);

		System.out.println("result: " + result);
	}

	int process(List<String> lines) {
		int sum = 0;
		for (String line : lines) {
			char[] a = line.toCharArray();
			Integer first = null;
			Integer last = null;
			for (char c : a) {
				if (Character.isDigit(c)) {
					if (first == null) {
						first = Integer.valueOf(String.valueOf(c));
					}
					last = Integer.valueOf(String.valueOf(c));
				}
			}
			sum += 10 * first + last;
		}
		return sum;
	}

}
