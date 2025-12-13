package aoc.year2023;

import java.util.List;

import aoc.Utils;
import aoc.year2023.day23.TrailMap;

public class Day23 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		TrailMap.noSlopes = false;
		new Day23().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input23.txt");
		long result = process(lines);
		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		TrailMap.noSlopes = false;
		Path.initMap(lines);

		System.out.println(Path.map);
		System.out.println();

		Path p = new Path(new Point(1, 0));
		p.start();
		try {
			p.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long sum = p.getMaxPath();

		return sum;
	}

}
