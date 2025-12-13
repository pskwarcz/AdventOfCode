package aoc.year2023;

import java.util.ArrayList;
import java.util.List;

import aoc.Utils;
import aoc.year2023.day24.Hailstone;

public class Day24 {

	public static void main(String[] args) {
		Hailstone.min = 200000000000000L;
		Hailstone.max = 400000000000000L;
		long start = System.currentTimeMillis();
		new Day24().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input24.txt");
		long result = process(lines);
		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		List<Hailstone> l = load(lines);
		long sum = 0;
		for (int i = 0; i < l.size() - 1; i++) {
			for (int j = i + 1; j < l.size(); j++) {
				if (l.get(i).intersect(l.get(j))) {
					sum++;
				}
			}
		}
		return sum;
	}

	private List<Hailstone> load(List<String> lines) {
		List<Hailstone> l = new ArrayList<>();
		for (String line : lines) {
			l.add(new Hailstone(line));
		}
		return l;
	}

}
