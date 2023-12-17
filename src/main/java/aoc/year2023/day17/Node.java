package aoc.year2023.day17;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import aoc.year2023.Point;

public class Node extends Point {

	Map<String, Integer> paths = new HashMap<>();

	int heat;

	public Node(int x, int y, int h) {
		super(x, y);
		this.heat = h;
	}

	public boolean visit(String c, int heatLoss) {
		int calculated = heatLoss + heat;

		for (int i = HeatMap.maxMoves; i > HeatMap.minMoves; i--) {
			if (c.length() >= i) {
				String s = c.substring(0, i - 1);
				if (paths.getOrDefault(s, Integer.MAX_VALUE) <= calculated) {
					return false;
				}
			}
		}

		if (paths.getOrDefault(c, Integer.MAX_VALUE) <= calculated) {
			return false;
		}

		paths.put(c, calculated);
		return true;
	}

	private Set<String> getPossibleDirections(String c) {
		Set<String> p = new HashSet<>();

		if (c.startsWith(">")) {
			if (c.length() < HeatMap.minMoves) {
				p.add(c.concat(">"));
			} else if (c.length() < HeatMap.maxMoves) {
				p.add(c.concat(">"));
				p.add("v");
				p.add("^");
			} else {
				p.add("v");
				p.add("^");
			}
		}

		if (c.startsWith("<")) {
			if (c.length() < HeatMap.minMoves) {
				p.add(c.concat("<"));
			} else if (c.length() < HeatMap.maxMoves) {
				p.add(c.concat("<"));
				p.add("v");
				p.add("^");
			} else {
				p.add("v");
				p.add("^");
			}
		}

		if (c.startsWith("v")) {
			if (c.length() < HeatMap.minMoves) {
				p.add(c.concat("v"));
			} else if (c.length() < HeatMap.maxMoves) {
				p.add(c.concat("v"));
				p.add("<");
				p.add(">");
			} else {
				p.add("<");
				p.add(">");
			}
		}

		if (c.startsWith("^")) {
			if (c.length() < HeatMap.minMoves) {
				p.add(c.concat("^"));
			} else if (c.length() < HeatMap.maxMoves) {
				p.add(c.concat("^"));
				p.add("<");
				p.add(">");
			} else {
				p.add("<");
				p.add(">");
			}
		}

		return p;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Node(");
		builder.append(x);
		builder.append(",");
		builder.append(y);
		builder.append(")=");
		builder.append(heat);
		builder.append(" paths:");
		builder.append(paths);
		return builder.toString();
	}

	public long getMinHeat() {
		return paths.values().stream().mapToInt(v -> v).min().getAsInt();
	}

	public Set<Move> getNextMoves(String c, int totalHeatLos) {
		Set<String> possibleDirections = getPossibleDirections(c);

		Set<Move> moves = new HashSet<>();
		for (String d : possibleDirections) {
			int nx = x;
			int ny = y;

			switch (d) {
			case ">":
				if (x + HeatMap.minMoves >= HeatMap.xMax) {
					continue;
				}
				break;
			case "<":
				if (x - HeatMap.minMoves < 0) {
					continue;
				}
				break;
			case "v":
				if (y + HeatMap.minMoves >= HeatMap.yMax) {
					continue;
				}
				break;
			case "^":
				if (y - HeatMap.minMoves < 0) {
					continue;
				}
				break;
			}

			if (d.startsWith(">")) {
				nx++;
			}
			if (d.startsWith("<")) {
				nx--;
			}
			if (d.startsWith("v")) {
				ny++;
			}
			if (d.startsWith("^")) {
				ny--;
			}

			if (nx < 0 || ny < 0 || ny >= HeatMap.yMax || nx >= HeatMap.xMax) {
				continue;
			}
			moves.add(new Move(nx, ny, d, totalHeatLos + heat));
		}
		return moves;
	}

}
