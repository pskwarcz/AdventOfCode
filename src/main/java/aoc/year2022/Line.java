package aoc.year2022;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Line {
	Point start;
	Point end;

	Line(int xa, int ya, int xb, int yb) {
		start = new Point(xa, ya);
		end = new Point(xb, yb);
	}

	public static Line findLine(Direction dir, Point p) {
		for (Line l : TileMapping.m.get(dir).keySet()) {
			if (l.containsPoint(p)) {
				return l;
			}
		}
		return null;
	}

	List<Point> getPoints() {
		List<Point> l = new ArrayList<>();

		int dx = (int) Math.signum(end.x - start.x);
		int dy = (int) Math.signum(end.y - start.y);

		Point p;
		long x = start.x;
		long y = start.y;

		do {
			p = new Point(x, y);
			l.add(p);

			x += dx;
			y += dy;
		} while (!end.equals(p));
//		System.out.println("Line from " + start + " to " + end + " " + l);
		return l;
	}

	private boolean containsPoint(Point p) {
		return between(start.x, p.x, end.x) && between(start.y, p.y, end.y);
	}

	private boolean between(long a, long x, long b) {
		return (a <= x && x <= b) || (b <= x && x <= a);
	}

	@Override
	public String toString() {
		return "[" + start + "-----" + end + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(end, start);
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
		return Objects.equals(end, other.end) && Objects.equals(start, other.start);
	}
}
