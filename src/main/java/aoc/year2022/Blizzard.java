package aoc.year2022;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Blizzard extends Pawn {

	Expedition e;
	Queue<Tile> destinations = new LinkedList<>();

	public Blizzard(Tile t, Expedition e) {
		super(t);
		this.e = e;
	}

	public Blizzard(Tile tile, Expedition expedition, Queue<Tile> q) {
		this(tile, expedition);
		destinations.addAll(q);
	}

	public void moveForward() {
		Tile n = currentPosition.getNext(facing);
		while (n.isWall()) {
			n = n.getNext(facing);
		}
		currentPosition.c = '.';
		currentPosition = n;
	}

	public List<Direction> getPossibleMoves() {
		List<Direction> moves = new ArrayList<>();

		try {
			if (currentPosition.getNext(Direction.RIGHT).isDot()) {
				moves.add(Direction.RIGHT);
			}
			if (currentPosition.getNeighbours().containsKey(Direction.DOWN)
					&& currentPosition.getNext(Direction.DOWN).isDot()) {
				moves.add(Direction.DOWN);
			}
			if (currentPosition.getNeighbours().containsKey(Direction.UP)
					&& currentPosition.getNext(Direction.UP).isDot()) {
				moves.add(Direction.UP);
			}
			if (currentPosition.getNext(Direction.LEFT).isDot()) {
				moves.add(Direction.LEFT);
			}
			if (currentPosition.c == 'E' || currentPosition.isDot()) {
				moves.add(null);
			}
		} catch (Exception e) {
			System.err.println("Current position:" + currentPosition + " \n neigh" + currentPosition.getNeighbours());
			throw e;
		}

		return moves;
	}

	@Override
	public String toString() {
		return "[" + currentPosition.x + ", " + currentPosition.y + "]";
	}

	public boolean reachedGoal() {
		Tile currentDestination = destinations.peek();
		if (currentDestination == null) {
			return true;
		}

		if (currentDestination.equals(currentPosition)) {
			destinations.remove();
		}
		// TODO Auto-generated method stub
		return destinations.isEmpty();
	}

}
