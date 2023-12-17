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

		if (c.length() == 3) {
			String s = c.substring(0, 2);
			if (paths.getOrDefault(s, Integer.MAX_VALUE) <= calculated) {
				return false;
			}
		}

		if (c.length() >= 2) {
			String s = c.substring(0, 1);
			if (paths.getOrDefault(s, Integer.MAX_VALUE) <= calculated) {
				return false;
			}
		}

		if (paths.getOrDefault(c, Integer.MAX_VALUE) <= calculated) {
			return false;
		}

		paths.put(c, calculated);
		return true;
	}

	public Set<String> getPossibleDirections(String c) {
		Set<String> p = new HashSet<>();
		switch (c) {
		case ">":
			p.add(">>");
			p.add("v");
			p.add("^");
			break;
		case ">>":
			p.add(">>>");
			p.add("v");
			p.add("^");
			break;
		case ">>>":
			p.add("v");
			p.add("^");
			break;

		case "<":
			p.add("<<");
			p.add("v");
			p.add("^");
			break;
		case "<<":
			p.add("<<<");
			p.add("v");
			p.add("^");
			break;
		case "<<<":
			p.add("v");
			p.add("^");
			break;

		case "v":
			p.add("vv");
			p.add("<");
			p.add(">");
			break;
		case "vv":
			p.add("vvv");
			p.add("<");
			p.add(">");
			break;
		case "vvv":
			p.add("<");
			p.add(">");
			break;

		case "^":
			p.add("^^");
			p.add("<");
			p.add(">");
			break;
		case "^^":
			p.add("^^^");
			p.add("<");
			p.add(">");
			break;
		case "^^^":
			p.add("<");
			p.add(">");
			break;

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
			case ">>":
			case ">>>":
				nx++;
				break;
			case "<":
			case "<<":
			case "<<<":
				nx--;
				break;
			case "v":
			case "vv":
			case "vvv":
				ny++;
				break;
			case "^":
			case "^^":
			case "^^^":
				ny--;
				break;
			}
			if (nx < 0 || ny < 0 || ny >= HeatMap.yMax || nx >= HeatMap.xMax) {
				continue;
			}
			moves.add(new Move(nx, ny, d, totalHeatLos + heat));
		}
		return moves;
	}

}
