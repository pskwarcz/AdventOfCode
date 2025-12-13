package aoc.year2023;

import java.util.List;

import aoc.Utils;
import aoc.year2023.day18.Trench;

public class Day18 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day18().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input18.txt");
		long result = process(lines);
		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		Trench t = new Trench(lines);
		System.out.println(t);
		// System.out.println(t);
		return t.getVolume();
	}

}
