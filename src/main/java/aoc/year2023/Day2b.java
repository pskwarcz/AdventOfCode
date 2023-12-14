package aoc.year2023;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aoc.Utils;

public class Day2b {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day2b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input2");

		long result = process(lines);

		System.out.println("result: " + result);
	}

	long process(List<String> lines) {
		long sum = 0;

		for (String line : lines) {
			sum += power(line);
		}
		return sum;
	}

	long power(String line) {
		System.out.println("\nline: " + line);

		String part2 = line.split(":")[1];
		String[] sets = part2.split(";");

		Map<String, Integer> qubes = new HashMap<>();

		for (String set : sets) {

			System.out.println("set: " + set);
			String[] draw = set.split(",");
			for (String q : draw) {
				String[] a = q.split(" ");
				int val = Integer.parseInt(a[1]);
				String color = a[2];

				if (!qubes.containsKey(color)) {
					qubes.put(color, val);
					System.out.println("Min " + color + "=" + val);
				} else {
					int min = qubes.get(color);
					if (min <= val) {
						System.out.println("Min " + color + "=" + val);
						qubes.put(color, val);
					}
				}
			}
		}

		long power = qubes.get("red") * qubes.get("green") * qubes.get("blue");
		System.out.println("power=" + power);
		return power;
	}

}
