package aoc.year2021;

import java.util.List;

import aoc.Utils;

public class Day2 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		List<String> lines = Utils.readFile("/aoc/year2021/input2");

		long result = new Day2().start(lines);
		System.out.println("RESULT: " + result);

		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private int start(List<String> lines) {

		int x = 0;
		int d = 0;
		int aim = 0;

		for (String line : lines) {
			String[] course = line.split(" ");

			String command = course[0];
			long s = Long.valueOf(course[1]);

			switch (command) {
			case "forward":
				x += s;
				d += aim * s;
				break;
			case "down":
				aim += s;
				break;
			case "up":
				aim -= s;
				break;
			default:
				throw new IllegalStateException("unsupported operation " + line);
			}

		}
		return x * d;
	}

}
