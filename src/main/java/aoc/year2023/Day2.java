package aoc.year2023;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aoc.Utils;

public class Day2 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day2().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input2");

		int result = process(lines);

		System.out.println("result: " + result);
	}

	int process(List<String> lines) {
		int sum = 0;
		int game = 0;

		Map<String, Integer> qubes = new HashMap<>();
		qubes.put("red", 12);
		qubes.put("green", 13);
		qubes.put("blue", 14);

		for (String line : lines) {
			game++;
			if (isGamePossible(line, qubes)) {
				sum += game;
			}
		}
		return sum;
	}

	private boolean isGamePossible(String line, Map<String, Integer> qubes) {
		System.out.println("\nline: " + line);

		String part2 = line.split(":")[1];
		String[] sets = part2.split(";");

		for (String set : sets) {
			System.out.println("set: " + set);
			String[] draw = set.split(",");
			for (String q : draw) {
				String[] a = q.split(" ");
				System.out.println("num:" + a[1] + " color:" + a[2]);
				if (qubes.get(a[2]) < Integer.parseInt(a[1])) {
					return false;
				}
			}
		}
		return true;
	}

}
