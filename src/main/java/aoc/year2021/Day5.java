package aoc.year2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aoc.Utils;

public class Day5 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		List<String> lines = Utils.readFile("/aoc/year2021/input5");

		long result = new Day5().start(lines);
		System.out.println("RESULT: " + result);

		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private int start(List<String> lines) {
		String[] intpur = lines.toArray(new String[0]);
		return getOverlap(intpur);
	}

	private static int getOverlap(String[] input) {
		Map<String, Integer> map = new HashMap<>();
		for (String line : input) {
			String[] points = line.split(" -> ");
			int x1 = Integer.parseInt(points[0].split(",")[0]);
			int y1 = Integer.parseInt(points[0].split(",")[1]);
			int x2 = Integer.parseInt(points[1].split(",")[0]);
			int y2 = Integer.parseInt(points[1].split(",")[1]);
			if (x1 == x2) {
				int minY = Math.min(y1, y2);
				int maxY = Math.max(y1, y2);
				for (int i = minY; i <= maxY; i++) {
					String key = x1 + "," + i;
					map.put(key, map.getOrDefault(key, 0) + 1);
				}
			} else {
				int minX = Math.min(x1, x2);
				int maxX = Math.max(x1, x2);
				for (int i = minX; i <= maxX; i++) {
					String key = i + "," + y1;
					map.put(key, map.getOrDefault(key, 0) + 1);
				}
			}
		}
		int overlap = 0;
		for (int count : map.values()) {
			if (count >= 2) {
				overlap++;
			}
		}
		return overlap;
	}

}
