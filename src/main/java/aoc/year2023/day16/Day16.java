package aoc.year2023.day16;

import java.util.List;

import aoc.Utils;

public class Day16 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day16().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input16.txt");
		long result = process(lines);
		System.out.println("\nresult: " + result);
	}

	public long process(List<String> lines) {
		Cave c = new Cave(lines);
		System.out.println(c);

		c.startBeam(0, 0, '>');
		System.out.println();

		System.out.println(c);
		return c.countEnergized();
	}

}
