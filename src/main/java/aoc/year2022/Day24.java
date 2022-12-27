package aoc.year2022;

import java.util.List;

import aoc.Utils;

public class Day24 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		List<String> lines = Utils.readFile("/aoc/year2022/input24");

		long result = new Day24().start(lines);

		System.out.println("RESULT:" + result);
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("Time[ms]: " + duration);
	}

	public long start(List<String> lines) {
		Expedition e = new Expedition(lines);
		System.out.println(e);

		int i = 1;
		while (!e.reachedDestination()) {
			i++;
		}

		return i;
	}

}
