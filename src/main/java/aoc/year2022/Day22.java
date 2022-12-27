package aoc.year2022;

import static aoc.year2022.Direction.DOWN;
import static aoc.year2022.Direction.UP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aoc.Utils;

public class Day22 {

	static Map<Point, Tile> fields = new HashMap<>();
	Pawn pawn;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		List<String> lines = Utils.readFile("/aoc/year2022/input22");

		long result = new Day22().start(lines);

		System.out.println("RESULT:" + result);
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("Time[ms]: " + duration);

	}

	public long start(List<String> lines) {
		loadBoard(lines);
		System.out.println("starting position:" + pawn);

		List<String> instructions = parseInstructions(lines.get(lines.size() - 1));

		for (String i : instructions) {
			try {
				pawn.move(i);
				// System.out.println("after " + i + ": " + pawn);
				// pawn.currentPosition.printNeighbours();
			} catch (Exception e) {
				System.err.println(pawn);
				printBoard(lines.size() - 1);
				throw e;

			}

		}

		printBoard(lines.size() - 1);

		System.out.println("FINAL POSITION: " + pawn);

		return 1000 * pawn.currentPosition.y + 4 * pawn.currentPosition.x + pawn.facing.number;
	}

	private void printFieldDetails(int x, int y) {
		Tile t = fields.get(new Point(x, y));
		System.out.println(t);
		t.printNeighbours();
	}

	private void printBoard(int my) {

		Point p = new Point(1, 1);
		for (p.y = 1; p.y < my; p.y++) {
			boolean wasOnBoard = false;

			p.x = 1;
			while (!wasOnBoard || fields.containsKey(p)) {

				if (fields.containsKey(p)) {
					char c = fields.get(p).c;
					System.out.print(c);
					wasOnBoard = true;
				} else {
					System.out.print(' ');
				}

				p.x++;
			}
			System.out.println();
		}

	}

	private List<String> parseInstructions(String line) {
		return Arrays.asList(line.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)"));
	}

	private void loadBoard(List<String> lines) {

		int maxX = 1;

		// rows
		int y = 0;
		for (String line : lines) {
			y++;

			Tile firstInArow = null;
			Tile lastInARow = null;

			int x = 0;
			for (char c : line.toCharArray()) {
				x++;
				maxX = Math.max(maxX, x);

				if (c == ' ') {
					continue;
				}

				Tile t = new Tile(x, y, c);
				fields.put(new Point(x, y), t);

				if (firstInArow == null) {
					// System.out.print("Row " + y + " from " + y + " to ");
					if (y == 1) {
						pawn = new Pawn(t);
					}
					firstInArow = t;
				}

				if (lastInARow != null) {
					lastInARow.addNeighbour(Direction.RIGHT, t);
					t.addNeighbour(Direction.LEFT, lastInARow);
				}

				lastInARow = t;

//				lastInARow.addNeighbour(RIGHT, firstInArow);
//				firstInArow.addNeighbour(LEFT, lastInARow);

			}
			// System.out.println(x);

			if (y == lines.size() - 2) {
				break;
			}
		}

		joinVertically(lines, maxX);

	}

	private void joinVertically(List<String> lines, int maxX) {
		int y;
		for (int x = 1; x <= maxX; x++) {

			Tile firstInAColl = null;
			Tile lastInAColl = null;

			for (y = 1; y < lines.size(); y++) {

				Tile t = fields.get(new Point(x, y));

				if (t == null) {
					if (firstInAColl == null) {
						continue;
					} else {
						// lastInAColl.addNeighbour(DOWN, firstInAColl);
						// firstInAColl.addNeighbour(UP, lastInAColl);
						break;
					}

				}

				if (firstInAColl == null) {
					// System.out.print("Col " + x + " from " + y);
					firstInAColl = t;
				}

				if (lastInAColl != null) {
					lastInAColl.addNeighbour(DOWN, t);
					t.addNeighbour(UP, lastInAColl);
				}

				lastInAColl = t;

				// lastInAColl.addNeighbour(DOWN, firstInAColl);
				// firstInAColl.addNeighbour(UP, lastInAColl);
			}

		}
	}

}
