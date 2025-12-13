package aoc.year2023;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import aoc.year2023.day23.Connection;
import aoc.year2023.day23.Crossroad;
import aoc.year2023.day23.TrailMap;

public class Path extends Thread {

	static int counter = 0;
	final int idx;

	public static TrailMap map;

	List<Crossroad> visited;

	Connection p;
	private int maxPath = 0;
	static int longestPath = 0;

	Path(Point nextPoint) {
		p = new Connection();
		p.to = TrailMap.getCrossroad(nextPoint);
		idx = counter++;
		visited = new LinkedList<>();
	}

	Path(List<Crossroad> visited, int maxPath, Connection nextStep) {
		idx = counter++;
		this.visited = new LinkedList<>(visited);
		this.p = nextStep;
		this.maxPath = maxPath;
		if (idx % 100000 == 0) {
			System.out.println(idx);
		}
	}

	@Override
	public void run() {
		while (true) {
			visited.add(p.to);
			maxPath += p.distance;

			if (p.to.finish) {
				if (longestPath < maxPath) {
					longestPath = maxPath;
					StringBuilder b = new StringBuilder();
					b.append(idx + " path: ");
					visited.stream().forEach(v -> b.append("(" + v.x + "," + v.y + ")->"));
					b.append("\n");
					b.append(" \t Reached finish with the longest path so far: " + longestPath);
					System.out.println(b.toString());
				}
				break;

			}

			Set<Connection> possibleMoves = p.to.getPossibleConnections(visited);

			Iterator<Connection> it = possibleMoves.iterator();
			if (it.hasNext()) {
				p = it.next();
			} else {
				maxPath = 0;
				break;
			}

			while (it.hasNext()) {
				Connection c = it.next();
				Path t = new Path(visited, maxPath, c);
				t.start();
			}
		}
		finish();
	}

	void finish() {

		// System.out.println(idx + ": Waiting for other to complete size: " +
		// branches.size());

		// branches.add(this);
//		maxPath = branches.stream().mapToInt(b -> b.maxPath).max().getAsInt();
		// branches = null;
		// System.out.println(idx + ": Longest path found in current tree: " + maxPath);
	}

	public int getMaxPath() {
		return maxPath;
	}

	public static void initMap(List<String> lines) {
		map = new TrailMap(lines);

	}

}
