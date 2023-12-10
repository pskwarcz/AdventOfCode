package aoc.year2023.day10;

import java.util.List;

import aoc.Utils;
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

}
