package aoc.year2023.day16;

import java.util.List;

public class Cave {
	Tile[][] tiles;

	public Cave(List<String> lines) {
		tiles = new Tile[lines.size()][lines.get(0).length()];
		int y = 0;
		for (String line : lines) {
			int x = 0;
			for (char c : line.toCharArray()) {
				Tile t = new Tile(x, y, c);
				tiles[y][x] = t;
				x++;
			}
			y++;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Tile[] row : tiles) {
			for (Tile t : row) {
				if (t.isEnergized()) {
					builder.append('#');
				} else {
					builder.append(t.c);
				}
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	public void startBeam(int x, int y, char c) {
		Tile t = tiles[y][x];
		visit(t, c);
	}

	private void visit(Tile t, char c) {
		// System.err.println("Visiting " + t);
		if (t.visited(c)) {
			return;
		}
		t.add(c);

		next(t, c);
	}

	private void next(Tile t, char c) {
		for (char d : t.next(c)) {
			switch (d) {
			case '>':
				if (t.x + 1 != tiles[0].length) {
					visit(tiles[t.y][t.x + 1], d);
				}
				break;
			case '<':
				if (t.x != 0) {
					visit(tiles[t.y][t.x - 1], d);
				}
				break;
			case '^':
				if (t.y != 0) {
					visit(tiles[t.y - 1][t.x], d);
				}
				break;
			case 'v':
				if (t.y + 1 != tiles.length) {
					visit(tiles[t.y + 1][t.x], d);
				}
				break;
			}
		}

	}

	public int countEnergized() {
		int n = 0;
		for (Tile[] row : tiles) {
			for (Tile t : row) {
				if (t.isEnergized()) {
					n++;
				}
			}
		}
		return n;
	}

}
