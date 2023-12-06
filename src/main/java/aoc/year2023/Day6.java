package aoc.year2023;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import aoc.Utils;

public class Day6 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day6().start();
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

		long[] times = Utils.toNumbers(l.next().split(":")[1].trim().split("\\s+"));
		long[] distances = Utils.toNumbers(l.next().split(":")[1].trim().split("\\s+"));
		System.out.println(Arrays.toString(times));
		System.out.println(Arrays.toString(distances));

		long res = 1;
		for (int i = 0; i < times.length; i++) {
			res *= count(times[i], distances[i]);
		}

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
