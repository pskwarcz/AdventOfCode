package aoc.year2022;

import static aoc.year2022.Direction.DOWN;
import static aoc.year2022.Direction.LEFT;
import static aoc.year2022.Direction.RIGHT;
import static aoc.year2022.Direction.UP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Expedition {

	Map<Point, Tile> board = new HashMap<>();
	List<Blizzard> blizzards = new ArrayList<>();
	Map<Point, Blizzard> expeditions = new HashMap<>();
	Map<Point, Blizzard> nextMove = new HashMap<>();
	Tile finish;
	Tile start;

	int xMax;
	int yMax;

	public Expedition(List<String> lines) {
		yMax = lines.size();
		xMax = lines.get(0).length();

		System.out.println(new Point(xMax, yMax));

		int y = 0;
		for (String line : lines) {
			int x = 0;
			for (char c : line.toCharArray()) {
				Tile t = new Tile(x, y, c);
				board.put(new Point(x, y), t);
				if (c != '#' && c != '.') {
					Blizzard b = new Blizzard(t, this);
					b.facing = Direction.fromChar(c);
					blizzards.add(b);
				}
				x++;
			}
			y++;
		}

		connectTiles();

		finish = getTile(new Point(xMax - 2, yMax - 1));
		start = getTile(new Point(1, 0));

		Queue<Tile> q = new LinkedList<>();
		q.add(finish);
		q.add(start);
		q.add(finish);

		Point p = new Point(1, 0);
		Blizzard exp = new Blizzard(getTile(p), this, q);
		expeditions.put(p, exp);

	}

	private void connectTiles() {
		Point p = new Point(0, 0);

		for (p.y = 0; p.y < yMax; p.y++) {
			Tile prev = null;
			Tile first = null;
			for (p.x = 0; p.x < xMax; p.x++) {
				Tile t = getTile(p);
				if (first == null) {
					first = t;
				}
				if (prev != null) {
					prev.addNeighbour(RIGHT, t);
					t.addNeighbour(LEFT, prev);
				}
				prev = t;
			}
			first.addNeighbour(LEFT, prev);
			prev.addNeighbour(RIGHT, first);
		}

		for (p.x = 0; p.x < xMax; p.x++) {
			Tile prev = null;
			Tile first = null;
			for (p.y = 0; p.y < yMax; p.y++) {
				Tile t = getTile(p);
				if (first == null) {
					first = t;
				}
				if (prev != null) {
					prev.addNeighbour(DOWN, t);
					t.addNeighbour(UP, prev);
				}
				prev = t;
			}
			first.addNeighbour(UP, prev);
			prev.addNeighbour(DOWN, first);
		}

		getTile(new Point(1, 0)).getNeighbours().remove(UP);
		getTile(new Point(xMax - 2, yMax - 1)).getNeighbours().remove(DOWN);

	}

	public Tile getTile(Point p) {
		return board.get(p);
	}

	public void moveBlizzards() {
		for (Blizzard b : blizzards) {
			b.moveForward();
		}
		for (Blizzard b : blizzards) {
			if (b.currentPosition.c != '.') {
				b.currentPosition.c = '*';
			} else {
				b.currentPosition.c = b.facing.c;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		Point p = new Point(0, 0);
		for (p.y = 0; p.y < yMax; p.y++) {
			for (p.x = 0; p.x < xMax; p.x++) {
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

	public boolean moveExpeditions() {
		for (Blizzard e : expeditions.values()) {
			if (e.getPossibleMoves().isEmpty()) {
				// System.out.println("Expedition " + e.currentPosition + " dies!");
				continue;
			}

			for (Direction d : e.getPossibleMoves()) {
				Tile next = d != null ? e.currentPosition.getNext(d) : e.currentPosition;

				Blizzard exp = new Blizzard(next, this, e.destinations);
				Point p = new Point(next.x, next.y);

				if (exp.reachedGoal()) {
					System.out.println("We hava a winner!");
					return true;
				}

				if (nextMove.containsKey(p)) {
					if (nextMove.get(p).destinations.size() > e.destinations.size()) {
						nextMove.put(p, exp);
					}
					// System.out.println("Expeditions meet on same position. Discarding one of
					// them");
				} else {
					nextMove.put(p, exp);
				}

			}
		}
		expeditions = nextMove;
		nextMove = new HashMap<>();
		System.out.println("Number of expeditions: " + expeditions.size());
		return false;
	}

	public boolean reachedDestination() {
		moveBlizzards();
		return moveExpeditions();
	}

}
