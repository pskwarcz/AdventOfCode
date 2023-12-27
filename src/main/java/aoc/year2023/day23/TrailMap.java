package aoc.year2023.day23;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import aoc.Utils;
import aoc.year2023.Point;

public class TrailMap {

	public static boolean noSlopes = false;

	private static char[][] map;

	Crossroad start;
	Crossroad finish;

	public static Set<Crossroad> nodes = new HashSet<>();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		nodes.stream().forEach(n -> sb.append(n).append("\n"));
		return sb.toString();

	}

	public TrailMap(List<String> lines) {
		map = Utils.toMatrix(lines);
		int lastRow = map.length - 1;
		finish = new Crossroad(map[lastRow].length - 2, lastRow);
		finish.finish = true;
		nodes.add(finish);
		loadNodes();
	}

	void loadNodes() {
		start = getCrossroad(new Point(1, 0));
		Point prev = new Point(1, 0);
		Point p = new Point(1, 1);

		getNextConnection(start, prev, p);

	}

	private void getNextConnection(Crossroad from, Point prev, Point p) {
		if (map[from.y][from.x] != '.') {
			throw new IllegalStateException("This is not ., might be some problem: " + from);
		}
		Connection c = new Connection();

		while (true) {
			c.distance++;

			if (p.x == finish.x && p.y == finish.y) {
				c.to = getCrossroad(p);
				if (!c.to.finish) {
					throw new IllegalStateException("This should be final tile " + c.to);
				}
				if (from.connections.contains(c)) {
					return;
				}
				from.addConnection(c);
				return;
			}

			Set<Point> possibleMoves = getPossibleMoves(prev, p);
			if (possibleMoves.size() > 1) {
				c.to = getCrossroad(p);
				if (from.connections.contains(c)) {
					return;
				}
				from.addConnection(c);

				getNextConnection(c.to, p, prev);
				for (Point n : possibleMoves) {
					getNextConnection(c.to, p, n);
				}
				return;
			} else if (possibleMoves.size() == 1) {
				prev = p;
				p = possibleMoves.iterator().next();
			} else if (possibleMoves.size() == 0) {
				return;
			}
		}

	}

	public static Crossroad getCrossroad(Point p) {
		Optional<Crossroad> o = nodes.stream().filter(n -> p.x == n.x && p.y == n.y).findFirst();
		if (o.isPresent()) {
			return o.get();
		}
		Crossroad c = new Crossroad(p);
		nodes.add(c);
		return c;
	}

	private Set<Point> getPossibleMoves(Point prev, Point p) {
		Set<Point> a = getDirections(p);

		Set<Point> possibleMoves = new HashSet<>();
		for (Point d : a) {
			if (prev.equals(d) || d.y < 0 || d.y >= map.length || map[d.y][d.x] == '#') {
				continue;
			}
			possibleMoves.add(d);
		}
		return possibleMoves;
	}

	private Set<Point> getDirections(Point p) {
		Set<Point> a = new HashSet<>();

		if (noSlopes) {
			a.add(new Point(p.x + 1, p.y));
			a.add(new Point(p.x - 1, p.y));
			a.add(new Point(p.x, p.y + 1));
			a.add(new Point(p.x, p.y - 1));
			return a;
		}

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
		return a;
	}

}
