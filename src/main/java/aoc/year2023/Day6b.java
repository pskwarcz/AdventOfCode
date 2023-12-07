package aoc.year2023;

import java.util.Iterator;
import java.util.List;

import aoc.Utils;

public class Day6b {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day6b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input6.txt");

		long result = process(lines);

		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		Iterator<String> l = lines.iterator();

		long time = Long.parseLong(l.next().split(":")[1].trim().replaceAll("\\s", ""));
		long distance = Long.parseLong(l.next().split(":")[1].trim().replaceAll("\\s", ""));
		System.out.println(time);
		System.out.println(distance);

		long res = count(time, distance);

		return res;
	}

	private long count(long t, long record) {
		long c = 0;
		for (long x = 1; x < t; x++) {
			long d = x * (t - x);
			if (d > record) {
				c++;
			}
		}
		System.out.println("t:" + t + " r:" + record + " c:" + c);
		return c;
	}

}
