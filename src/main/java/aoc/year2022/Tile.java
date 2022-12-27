package aoc.year2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class Tile extends Point {

	char c;

	List<Pawn> candidates = new ArrayList<>();

	private Map<Direction, Tile> neighbours = new HashMap<>();

	public Tile(long x, long y, char c) {
		super(x, y);
		this.c = c;
	}

	public Tile(Point p, char c) {
		this(p.x, p.y, c);
	}

	@Override
	public String toString() {
		return "Tile [c=" + c + ", n=" + neighbours.size() + ", x=" + x + ", y=" + y + "]";
	}

	Point getPosition() {
		return new Point(x, y);
	}

	public Map<Direction, Tile> getNeighbours() {
		return neighbours;
	}

	public void addNeighbour(Direction direction, Tile t) {
		neighbours.put(direction, t);
	}

	public void printNeighbours() {
		for (Entry<Direction, Tile> d : neighbours.entrySet()) {
			Tile p = d.getValue();
			System.out.println("\t" + d.getKey() + " " + p);
		}
	}

	public Tile getNext(Direction d) {
		return neighbours.get(d);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(c);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		return c == other.c;
	}

	public boolean isWall() {
		return '#' == c;

	}

	public boolean isDot() {
		return c == '.' || c == 'E';
	}

}
