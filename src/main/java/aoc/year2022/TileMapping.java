package aoc.year2022;

import static aoc.year2022.Direction.DOWN;
import static aoc.year2022.Direction.LEFT;
import static aoc.year2022.Direction.RIGHT;
import static aoc.year2022.Direction.UP;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TileMapping {

	Tile next;
	Direction direction;

	public static Map<Direction, Map<Line, Mapping>> m = new HashMap<>();

	static {
		Line from = new Line(51, 1, 100, 1);
		Line to = new Line(1, 151, 1, 200);
		addMapping(new Mapping(from, UP, to, RIGHT));
		addMapping(new Mapping(to, LEFT, from, DOWN));

		from = new Line(51, 150, 100, 150);
		to = new Line(50, 151, 50, 200);
		addMapping(new Mapping(from, DOWN, to, LEFT));
		addMapping(new Mapping(to, RIGHT, from, UP));

		from = new Line(1, 101, 50, 101);
		to = new Line(51, 51, 51, 100);
		addMapping(new Mapping(from, UP, to, RIGHT));
		addMapping(new Mapping(to, LEFT, from, DOWN));

		from = new Line(1, 200, 50, 200);
		to = new Line(101, 1, 150, 1);
		addMapping(new Mapping(from, DOWN, to, DOWN));
		addMapping(new Mapping(to, UP, from, UP));

		from = new Line(150, 1, 150, 50);
		to = new Line(100, 150, 100, 101);
		addMapping(new Mapping(from, RIGHT, to, LEFT));
		addMapping(new Mapping(to, RIGHT, from, LEFT));

		from = new Line(1, 101, 1, 150);
		to = new Line(51, 50, 51, 1);
		addMapping(new Mapping(from, LEFT, to, RIGHT));
		addMapping(new Mapping(to, LEFT, from, RIGHT));

		from = new Line(100, 51, 100, 100);
		to = new Line(101, 50, 150, 50);
		addMapping(new Mapping(from, RIGHT, to, UP));
		addMapping(new Mapping(to, DOWN, from, LEFT));
	}

	static TileMapping getNext(Point source, Direction facing) {
		source = new Point(source.x, source.y);
		System.out.println("looking next point for " + source + " dir=" + facing);

		Map<Line, Mapping> dirMap = m.get(facing);
		// System.out.println("Found dirmap: " + dirMap);

		Line l = Line.findLine(facing, source);
//		System.out.println("found line: " + l);

		Mapping map = dirMap.get(l);
		// System.out.println("Found map: " + map);

		Point nextPoint = map.getNextPoint(source);
		Direction d = map.d;
		return new TileMapping(nextPoint, d);
	}

	TileMapping(Point p, Direction d) {
		this.next = Day22.fields.get(p);
		this.direction = d;
	}

	static void addMapping(Mapping mapping) {
		if (!m.containsKey(mapping.dirFrom)) {
			m.put(mapping.dirFrom, new HashMap<>());
		}
		m.get(mapping.dirFrom).put(mapping.from, mapping);
	}

	static class Mapping {
		Line from;
		Line to;
		Direction dirFrom;
		Direction d;

		public Mapping(Line from, Direction dirFrom, Line to, Direction d) {
			super();
			this.from = from;
			this.to = to;
			this.d = d;
			this.dirFrom = dirFrom;
		}

		Point getNextPoint(Point p) {
			int sourceIdx = from.getPoints().indexOf(p);
			return to.getPoints().get(sourceIdx);
		}

		@Override
		public int hashCode() {
			return Objects.hash(d, dirFrom, from, to);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Mapping other = (Mapping) obj;
			return d == other.d && dirFrom == other.dirFrom && Objects.equals(from, other.from)
					&& Objects.equals(to, other.to);
		}

		@Override
		public String toString() {
			return "Mapping [from=" + from + ", to=" + to + ", d=" + d + "]";
		}

	}

}
