package aoc.year2023.day18;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
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

			switch (direction) {
			case "R":
				x += d;
				break;
			case "D":
				y += d;
				break;
			case "L":
				x -= d;
				break;
			case "U":
				y -= d;
				break;
			}
			t.add(new Point(x, y));
			updateMaxMin(x, y);
		}

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
		Queue<Point> q = new PriorityQueue<>(t);

		long volume = 0;
		Queue<Line> lines = initLines(q);
		volume += currentLineVolume(Collections.emptyList(), lines);
		System.out.println("first line volume: " + volume);

		while (!lines.isEmpty()) {
			int y = q.peek().y;
			int h = y - lines.iterator().next().a.y - 1;

			long block = volume(lines, h);
			System.out.println(y + ": " + lines + "h: " + h + " block: " + block);

			volume += block;
			Queue<Line> newLines = updateLines(lines, q);

			long c = currentLineVolume(lines, newLines);
			System.out.println(y + " current line: " + c);
			volume += c;

			lines = newLines;

		}
		System.out.println(lines + " volume: " + volume);
		return volume;
	}

	private long currentLineVolume(Collection<Line> lines, Collection<Line> newLines) {
		long v = volume(lines, 1) + volume(newLines, 1);

		for (Line l : lines) {
			for (Line n : newLines) {
				v -= l.commonPoints(n);
			}
		}

		return v;
	}

	private Queue<Line> updateLines(Queue<Line> lines, Queue<Point> q) {
		Queue<Point> result = new LinkedList<>();

		Queue<Point> existingPoints = new LinkedList<>();
		int y = q.peek().y;
		for (Line l : lines) {
			l.setLevel(y);
			existingPoints.add(l.a);
			existingPoints.add(l.b);
		}
		while (q.peek() != null && q.peek().y == y) {
			Point p = q.peek();
			if (p.equals(existingPoints.peek())) {
				existingPoints.remove();
				q.remove();
			} else if (existingPoints.peek() == null || p.x < existingPoints.peek().x) {
				result.add(q.remove());
			} else {
				result.add(existingPoints.remove());
			}
		}
		result.addAll(existingPoints);

		return initLines(result);
	}

	private long volume(Collection<Line> lines, int h) {
		return totalLength(lines) * h;
	}

	private long totalLength(Collection<Line> lines) {
		long s = 0;
		for (Line l : lines) {
			s += l.length();
		}
		return s;
	}

	private Queue<Line> initLines(Queue<Point> q) {
		Queue<Line> lines = new LinkedList<>();
		if (q.isEmpty()) {
			return lines;
		}
		Point a = q.remove();
		Point b = q.remove();
		lines.add(new Line(a, b));
		while (q.peek() != null && q.peek().y == a.y) {
			lines.add(new Line(q.remove(), q.remove()));
		}
		return lines;
	}

}
