package aoc.year2022;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Point {

	long x;
	long y;
	long z;

	public Point(long x, long y) {
		super();
		this.x = x;
		this.y = y;
		this.z = 0;
	}

	public Point(long x, long y, long z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	public int getAdjacentCubesAmount(Set<Point> points) {
		int i = 0;
		for (Point adjacent : getAdjacent3D()) {
			if (points.contains(adjacent)) {
				i++;
			}
		}
		return i;
	}

	public Set<Point> getAdjacent3D() {
		Set<Point> points = new HashSet<>();
		points.add(new Point(x + 1, y, z));
		points.add(new Point(x - 1, y, z));
		points.add(new Point(x, y + 1, z));
		points.add(new Point(x, y - 1, z));
		points.add(new Point(x, y, z + 1));
		points.add(new Point(x, y, z - 1));
		return points;
	}

	public Set<Point> getPointsAround2D() {
		Set<Point> points = new HashSet<>();
		points.add(new Point(x + 1, y));
		points.add(new Point(x + 1, y + 1));
		points.add(new Point(x, y + 1));
		points.add(new Point(x - 1, y + 1));
		points.add(new Point(x - 1, y));
		points.add(new Point(x - 1, y - 1));
		points.add(new Point(x, y - 1));
		points.add(new Point(x + 1, y - 1));
		return points;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		return x == other.x && y == other.y && z == other.z;
	}

	public Point next(Direction d) {
		switch (d) {
		case DOWN:
			return new Point(x, y + 1);
		case LEFT:
			return new Point(x - 1, y);
		case RIGHT:
			return new Point(x + 1, y);
		case UP:
			return new Point(x, y - 1);
		default:
			return null;
		}
	}

}
