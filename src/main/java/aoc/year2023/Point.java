package aoc.year2023;

import java.util.Objects;

public class Point implements Comparable<Point> {
	final public int x;
	final public int y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point(Point p) {
		x = p.x;
		y = p.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
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
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		builder.append(x);
		builder.append(",");
		builder.append(y);
		builder.append(")");
		return builder.toString();
	}

	public int distanceTo(Point to) {
		return Math.abs(x - to.x) + Math.abs(y - to.y);
	}

	@Override
	public int compareTo(Point o) {
		if (y != o.y) {
			return y - o.y;
		}
		return x - o.x;
	}

}
