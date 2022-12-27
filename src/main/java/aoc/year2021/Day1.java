package aoc.year2021;

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
		List<String> lines = Utils.readFile("/aoc/year2021/input1");

		int larger = 0;

		int last = sumLast3(lines, 2);

		for (int i = 3; i < lines.size(); i++) {

			int s = sumLast3(lines, i);

			if (s > last) {
				larger++;
			}
			last = s;

		}
		System.out.println("larger: " + larger);
	}

	private int sumLast3(List<String> lines, int i) {
		return Integer.parseInt(lines.get(i)) + Integer.parseInt(lines.get(i - 1)) + Integer.parseInt(lines.get(i - 2));
	}

}
