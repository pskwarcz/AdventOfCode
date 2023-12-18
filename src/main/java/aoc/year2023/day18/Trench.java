package aoc.year2023.day18;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import aoc.year2023.Point;

public class Trench {

	Set<Point> t = new HashSet<>();
	int xMin = 0;
	int xMax = 0;
	int yMin = 0;
	int yMax = 0;

	public Trench(List<String> lines) {
		int x = 0;
		int y = 0;
		t.add(new Point(x, y));
		for (String line : lines) {
			String[] r = line.split("\\s");
			String direction = r[0];
			int d = Integer.parseInt(r[1]);

			for (int i = 0; i < d; i++) {
				switch (direction) {
				case "R":
					x++;
					break;
				case "D":
					y++;
					break;
				case "L":
					x--;
					break;
				case "U":
					y--;
					break;
				}
				t.add(new Point(x, y));
				updateMaxMin(x, y);
			}

		}
	}

	public void digOutInterior() {
		Point p = findFirstIn();
		Queue<Point> q = new LinkedList<>();
		q.add(p);
		while (!q.isEmpty()) {
			p = q.poll();
			fill(p, q);
		}

	}

	private void fill(Point p, Queue<Point> q) {
		if (t.contains(p)) {
			return;
		}
		t.add(p);
		q.offer(new Point(p.x + 1, p.y));
		q.offer(new Point(p.x - 1, p.y));
		q.offer(new Point(p.x, p.y + 1));
		q.offer(new Point(p.x, p.y - 1));
	}

	private Point findFirstIn() {
		boolean in = false;
		int y = yMin + 1;

		for (int x = xMin - 1; x < xMax + 1; x++) {
			if (!in && t.contains(new Point(x, y))) {
				in = true;
			}
			if (in && !t.contains(new Point(x, y))) {
				return new Point(x, y);
			}
		}
		throw new IllegalStateException("Could not get in");
	}

	private void updateMaxMin(int x, int y) {
		xMin = Math.min(xMin, x);
		xMax = Math.max(xMax, x);
		yMin = Math.min(yMin, y);
		yMax = Math.max(yMax, y);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		appendMap(builder);
		builder.append("xMin=");
		builder.append(xMin);
		builder.append(", xMax=");
		builder.append(xMax);
		builder.append(", yMin=");
		builder.append(yMin);
		builder.append(", yMax=");
		builder.append(yMax);
		return builder.toString();
	}

	private void appendMap(StringBuilder builder) {
		if (xMax - xMin > 100) {
			// do not print large map
			return;
		}
		for (int y = yMin; y < yMax + 1; y++) {
			for (int x = xMin; x < xMax + 1; x++) {
				if (t.contains(new Point(x, y))) {
					builder.append('#');
				} else {
					builder.append('.');
				}
			}
			builder.append("\n");
		}
	}

	public long getVolume() {
		return t.size();
	}

}
