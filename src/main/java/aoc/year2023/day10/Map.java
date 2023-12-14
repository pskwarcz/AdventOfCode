package aoc.year2023.day10;

import java.util.HashSet;
import java.util.List;

import aoc.Utils;
import aoc.year2023.Day10b;
import aoc.year2023.Point;

public class Map {
	private final char[][] m;
	public final Tile s;

	public Map(List<String> lines) {
		m = Utils.toMatrix(lines);
		s = findS();
	}

	Tile getTile(Point p) {
		char c = m[p.y][p.x];
		return new Tile(p, c);
	}

	private Tile findS() {
		for (int y = 0; y < m.length; y++) {
			char[] row = m[y];
			for (int x = 0; x < row.length; x++) {
				char tile = row[x];
				if (tile == 'S') {
					return new Tile(x, y, 'S');
				}
			}
		}
		throw new IllegalStateException("S not found");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (char[] pipe : m) {
			for (char c : pipe) {
				builder.append(c);
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	public Tile getN(Point p) {
		if (p.y == 0) {
			return new Tile(p.x, p.y - 1, '.');
		}
		return new Tile(p.x, p.y - 1, m[p.y - 1][p.x]);
	}

	public Tile getS(Tile p) {
		if (p.y == m.length - 1) {
			return new Tile(p.x, p.y + 1, '.');
		}
		return new Tile(p.x, p.y + 1, m[p.y + 1][p.x]);
	}

	public Tile getE(Tile p) {
		if (p.x == m[p.y].length - 1) {
			return new Tile(p.x + 1, p.y, '.');
		}
		return new Tile(p.x + 1, p.y, m[p.y][p.x + 1]);
	}

	public Tile getW(Tile p) {
		if (p.x == 0) {
			return new Tile(p.x - 1, p.y, '.');
		}
		return new Tile(p.x - 1, p.y, m[p.y][p.x - 1]);
	}

	public int countEnclosed(HashSet<Point> path) {
		int n = 0;

		m[s.y][s.x] = Day10b.replaceS;

		System.out.println(path);

		for (int y = 0; y < m.length; y++) {
			System.out.println(y);
			boolean in = false;
			char[] row = m[y];
			char corner = '?';
			for (int x = 0; x < row.length; x++) {
				Point p = new Point(x, y);
				char c = row[x];
				if (path.contains(p)) {
					switch (c) {
					case '-':
						break;
					case 'F':
					case 'L':
						corner = c;
						break;
					case 'J':
						if (corner == 'F') {
							in = !in;
						}
						break;
					case '7':
						if (corner == 'L') {
							in = !in;
						}
						break;
					case '|':
						in = !in;
						break;
					default:
						in = !in;
					}

					System.out.println(p + " in:" + in);
				} else {
					if (in) {
						System.out.println("IN :" + x + ", " + y);
						n++;
					}
				}
			}

		}
		return n;
	}

}
