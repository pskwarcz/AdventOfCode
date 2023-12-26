package aoc.year2023;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import aoc.Utils;

public class Path extends Thread {
	static int counter = 0;
	final int idx;
	static char[][] map;
	static Point finish;

	final Set<Point> path;

	List<Path> branches = new ArrayList<Path>();

	Point p;
	private int maxPath = 0;

	Path(Point nextStep) {
		idx = counter++;
		path = new HashSet<>();
		p = nextStep;
	}

	Path(Set<Point> path, Point nextStep) {
		idx = counter++;
		this.path = new HashSet<>(path);
		this.p = nextStep;
	}

	@Override
	public void run() {

		while (true) {
			path.add(p);

			if (finish.equals(p)) {
				maxPath = path.size() - 1;
				System.out.println(idx + ": Reached finish with path length: " + path.size());
				break;
			}

			Iterator<Point> it = getPossibleMoves().iterator();
			if (it.hasNext()) {
				p = it.next();
			} else {
				System.out.println(idx + ": Dead end!");
				break;
			}

			while (it.hasNext()) {
				Path t = new Path(path, it.next());
				t.start();
				branches.add(t);
			}
		}
		finish();
	}

	void finish() {
		// System.out.println(idx + ": Waiting for other to complete size: " +
		// branches.size());
		for (Path o : branches) {
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		branches.add(this);
		maxPath = branches.stream().mapToInt(b -> b.maxPath).max().getAsInt();
		// System.out.println(idx + ": Longest path found in current tree: " + maxPath);
	}

	private Set<Point> getPossibleMoves() {
		Set<Point> a = new HashSet<>();

		char c = map[p.y][p.x];
		switch (c) {
		case '>':
			a.add(new Point(p.x + 1, p.y));
			break;
		case '<':
			a.add(new Point(p.x - 1, p.y));
			break;
		case 'v':
			a.add(new Point(p.x, p.y + 1));
			break;
		case '^':
			a.add(new Point(p.x, p.y - 1));
			break;
		case '.':
			a.add(new Point(p.x + 1, p.y));
			a.add(new Point(p.x - 1, p.y));
			a.add(new Point(p.x, p.y + 1));
			a.add(new Point(p.x, p.y - 1));
			break;
		default:
			throw new IllegalArgumentException("Invalid tile: " + p + "=" + c);
		}

		Set<Point> possibleMoves = new HashSet<>();
		for (Point d : a) {
			if (path.contains(d) || d.y < 0 || map[d.y][d.x] == '#') {
				continue;
			}
			possibleMoves.add(d);
		}
		return possibleMoves;
	}

	public int getMaxPath() {
		return maxPath;
	}

	public static void initMap(List<String> lines) {
		map = Utils.toMatrix(lines);
		int lastRow = map.length - 1;
		finish = new Point(map[lastRow].length - 2, lastRow);
	}

}
