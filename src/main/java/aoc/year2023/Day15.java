package aoc.year2023;

import java.util.List;

import aoc.Utils;

public class Day15 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day15().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input15.txt");
		long result = process(lines);
		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {

		long sum = 0;
		for (String line : lines) {
			for (String s : line.split(",")) {
				int h = hash(s);
				System.out.println(s + "\t" + h);
				sum += h;
			}
		}

		return sum;
	}

	int hash(String s) {
		int v = 0;
		for (char c : s.toCharArray()) {
			v += c;
			v *= 17;
			v %= 256;
		}
		return v;
	}

}
