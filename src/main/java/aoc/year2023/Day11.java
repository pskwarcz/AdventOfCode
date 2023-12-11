package aoc.year2023;

import java.util.ArrayList;
import java.util.List;

import aoc.Utils;

public class Day11 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day11().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input11.txt");

		long result = process(lines);

		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		List<List<Character>> universe = loadUniverse(lines);
		List<List<Character>> expanded = expand(universe);
		print(expanded);

		List<Point> stars = findStars(expanded);
		System.out.println(stars);
		int sum = 0;
		for (int i = 0; i < stars.size(); i++) {
			Point s = stars.get(i);
			for (int x = i + 1; x < stars.size(); x++) {
				Point to = stars.get(x);
				int res = s.distanceTo(to);
				// System.out.println("from " + s + " to " + to + " = " + res);
				sum += res;
			}
			// System.out.println();
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

	private List<List<Character>> expand(List<List<Character>> universe) {
		List<List<Character>> expanded = new ArrayList<>();

		for (List<Character> line : universe) {
			expanded.add(new ArrayList<>(line));
			if (!line.stream().filter(s -> !s.equals('.')).findFirst().isPresent()) {
				expanded.add(new ArrayList<>(line));
			}
		}

		int x = 0;
		int cnt = universe.get(0).size();
		for (int i = 0; i < cnt; i++) {
			x++;
			if (allEmpty(universe, i)) {
				for (List<Character> e : expanded) {
					e.add(x, '.');
				}
				x++;
			}
		}

		return expanded;
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
