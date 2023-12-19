package aoc.year2023.day18;

import java.util.Objects;

import aoc.year2023.Point;

public class Line {

	Point a;
	Point b;

	public Line(Point a, Point b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public int hashCode() {
		return Objects.hash(a, b);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Line other = (Line) obj;
		return Objects.equals(a, other.a) && Objects.equals(b, other.b);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(a);
		builder.append("--");
		builder.append(b);
		builder.append("=");
		builder.append(length());
		return builder.toString();
	}

	public int length() {
		return a.distanceTo(b) + 1;
	}

	public void setLevel(int y) {
		a = new Point(a.x, y);
		b = new Point(b.x, y);
	}

	public int commonPoints(Line n) {
		if (n.b.x < a.x || n.a.x > b.x) {
			return 0;
		}
		int start = Math.max(n.a.x, a.x);
		int end = Math.min(n.b.x, b.x);
		return end - start + 1;
	}

}
