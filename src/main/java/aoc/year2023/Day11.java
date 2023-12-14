package aoc.year2023;

import java.util.ArrayList;
import java.util.List;

import aoc.Utils;

public class Day11 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day11(1000000).start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private int expansion;

	public Day11(int expansion) {
		this.expansion = expansion;
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input11.txt");

		long result = process(lines);

		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		List<List<Character>> universe = loadUniverse(lines);

		print(universe);

		List<Point> stars = findStars(universe);

		System.out.println(stars);
		long sum = 0;
		for (int i = 0; i < stars.size(); i++) {
			Point s = stars.get(i);
			for (int x = i + 1; x < stars.size(); x++) {
				Point to = stars.get(x);
				int res = s.distanceTo(to);
				// System.out.println("from " + s + " to " + to + " = " + res);
				res += ((expansion - 1) * countEmptyColsBetween(universe, s.x, to.x));
				res += ((expansion - 1) * countEmptyRowsBetween(universe, s.y, to.y));
				// System.out.println("AFTER EXP: from " + s + " to " + to + " = " + res);

				sum += res;
			}
//			System.out.println();
		}

		return sum;
	}

	private List<Point> findStars(List<List<Character>> expanded) {
		int y = 0;
		List<Point> stars = new ArrayList<>();
		for (List<Character> line : expanded) {
			int x = 0;
			for (Character e : line) {
				if (e.equals('#')) {
					stars.add(new Point(x, y));
				}
				x++;
			}
			y++;
		}
		return stars;
	}

	int countEmptyColsBetween(List<List<Character>> universe, int from, int to) {
		int c = 0;

		if (from >= to) {
			int to2 = to;
			to = from;
			from = to2;
		}

		for (int x = from + 1; x < to; x++) {
			if (allEmpty(universe, x)) {
				c++;
			}
		}
		return c;
	}

	int countEmptyRowsBetween(List<List<Character>> universe, int from, int to) {
		int c = 0;

		if (from >= to) {
			int to2 = to;
			to = from;
			from = to2;
		}

		for (int y = from + 1; y < to; y++) {
			List<Character> line = universe.get(y);
			if (!line.stream().filter(s -> !s.equals('.')).findFirst().isPresent()) {
				c++;
			}
		}

		// System.out.println("empty rows between " + from + " to " + to + " = " + c);
		return c;
	}

	private boolean allEmpty(List<List<Character>> universe, int x) {
		for (List<Character> line : universe) {
			if (line.get(x).equals('#')) {
				return false;
			}
		}
		return true;
	}

	private void print(List<List<Character>> universe) {
		for (List<Character> line : universe) {
			for (Character c : line) {
				System.out.print(c);
			}
			System.out.println();
		}

	}

	private List<Character> emptyLine() {
		return new ArrayList<>();
	}

	private List<List<Character>> loadUniverse(List<String> lines) {
		List<List<Character>> universe = new ArrayList<>();
		for (String line : lines) {
			char[] stars = line.toCharArray();
			List<Character> s = new ArrayList<>();
			for (char c : stars) {
				s.add(c);
			}
			universe.add(s);
		}
		return universe;
	}

}
