package aoc.year2023;

import java.util.List;

import aoc.Utils;

public class Day23 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day23().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input23.txt");
		long result = process(lines);
		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		Path.initMap(lines);

		Path p = new Path(new Point(1, 0));
		p.start();
		try {
			p.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long sum = p.getMaxPath();

		return sum;
	}

}
