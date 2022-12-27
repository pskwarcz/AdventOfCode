package aoc.year2022;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

	Map<Point, Tile> board = new HashMap<>();
	List<Pawn> elves = new ArrayList<>();

	Direction startingDirection = Direction.UP;

	long minX = 1;
	long minY = 1;
	long maxX = 1;
	long maxY = 1;

	public Board(List<String> lines) {
		long y = minY;
		for (String line : lines) {
			long x = minX;
			for (char c : line.toCharArray()) {
				Tile t = new Tile(x, y, c);
				if (c == '#') {
					Pawn elve = new Pawn(t, this);
					elve.facing = Direction.UP;
					elves.add(elve);
				}

				addTile(t);
				x++;
			}
			y++;
		}
	}

	private void addTile(Tile t) {
		board.put(new Point(t.x, t.y), t);
		minX = Math.min(minX, t.x);
		minY = Math.min(minY, t.y);
		maxX = Math.max(maxX, t.x);
		maxY = Math.max(maxY, t.y);
	}

	public void printMax() {
		System.out.println("board size " + new Point(minX, minY) + " - " + new Point(maxX, maxY));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		Point p = new Point(minX, minY);
		for (p.y = minY; p.y <= maxY; p.y++) {
			for (p.x = minX; p.x <= maxX; p.x++) {
				if (board.containsKey(p)) {
					sb.append(board.get(p).c);
				} else {
					sb.append(' ');
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public boolean isFree(Point s) {
		return !board.containsKey(s) || !board.get(s).isWall();
	}

	public void nextTurn() {
		// System.out.println("starting direction: " + startingDirection);

		for (Pawn e : elves) {
			e.considerNextStep(startingDirection);
		}
		for (Pawn e : elves) {
			e.moveIfNoCollision();
		}
		// System.out.println(this);
		startingDirection = startingDirection.nextToConsider();
	}

	public Tile getTile(Point p) {
		if (board.containsKey(p)) {
			return board.get(p);
		}
		Tile t = new Tile(p, '.');
		addTile(t);
		return t;
	}

	public int getEmptySpacesInRectangle() {

		Point min = new Point(999999999, 99999999);
		Point max = new Point(-9999990, -9999999);
		for (Pawn p : elves) {
			long x = p.currentPosition.x;
			long y = p.currentPosition.y;

			min = new Point(min(x, min.x), min(y, min.y));
			max = new Point(max(x, max.x), max(y, max.y));
		}

		System.out.println("min:" + min + " max:" + max);

		int cnt = 0;
		Point p = new Point(min.x, min.y);
		for (p.x = min.x; p.x <= max.x; p.x++) {
			for (p.y = min.y; p.y <= max.y; p.y++) {
				if (!getTile(p).isWall()) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	public boolean noElfMoved() {
		for (Pawn e : elves) {
			if (e.nextStep != null) {
				return false;
			}
		}
		return true;
	}

}
