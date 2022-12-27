package aoc.year2022;

import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import aoc.Utils;

public class Day18 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		List<String> lines = Utils.readFile("/aoc/year2022/input18");

		int result = new Day18().start(lines);

		System.out.println("RESULT:" + result);

		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("Time[ms]: " + duration);
	}

	Set<Cube> air = new HashSet<>();

	int surface = 0;

	public int start(List<String> lines) {

		for (String row : lines) {

			String[] c = row.split(",");
			Point p = new Point(Long.valueOf(c[0]), Long.valueOf(c[1]), Long.valueOf(c[2]));
			Cube.createCube(p);
		}

		surface = calculateSurface(Cube.regularCubes);

		Integer outer = findOuterSpace();

		findAirPockets();

		for (Entry<Integer, Set<Point>> pocketE : Cube.allAirPockets.entrySet()) {
			int s = calculateSurface(pocketE.getValue());
			if (!pocketE.getKey().equals(outer)) {
				surface -= s;
			}
		}
		return surface;
	}

	private Integer findOuterSpace() {
		Point p = Cube.regularCubes.iterator().next();
		Point a = new Point(-1, p.y, p.z);

		while (!Cube.regularCubes.contains(a)) {
			p = a;
			a = new Point(a.x + 1, a.y, a.z);
		}

		Cube q = Cube.createAirCube(p);
		q.createAirPocket();
		Cube.MAX_SIZE = -1;
		return q.pocketNr;
	}

	private int calculateSurface(Set<Point> regularQubes) {
		int s = 6 * regularQubes.size();
		for (Point p : regularQubes) {
			s -= p.getAdjacentCubesAmount(regularQubes);
		}
		return s;
	}

	private void findAirPockets() {
		for (Point p : Cube.regularCubes) {
			Cube.cubes.get(p).findAirPocketsAround();
		}
	}

}
