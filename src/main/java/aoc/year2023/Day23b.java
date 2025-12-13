package aoc.year2023;

import java.util.List;

import aoc.year2023.day23.TrailMap;

public class Day23b extends Day23 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day23b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	@Override
	long process(List<String> lines) {
		TrailMap.noSlopes = true;
		Path.initMap(lines);

		System.out.println(Path.map);
		System.out.println();

		Path p = new Path(new Point(1, 0));
		p.start();

// TODO wait for all threads to finish  
		return Path.longestPath;
	}

}
