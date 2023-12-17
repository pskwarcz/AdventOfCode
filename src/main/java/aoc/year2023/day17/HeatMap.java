package aoc.year2023.day17;

import java.util.List;
import java.util.Set;

public class HeatMap {

	public Node[][] m;
	int xMax;
	int yMax;

	public HeatMap(List<String> lines) {
		yMax = lines.size();
		xMax = lines.get(0).length();
		m = new Node[yMax][xMax];
		int y = 0;
		for (String line : lines) {
			int x = 0;
			for (char c : line.toCharArray()) {
				m[y][x] = new Node(x, y, Character.getNumericValue(c));
				x++;
			}
			y++;
		}

	}

	public void analyze() {
		visit(0, 1, ">", 0);
		visit(1, 0, "v", 0);
	}

	private void visit(int y, int x, String c, int totalHeatLos) {
		if (y < 0 || y >= yMax || x < 0 || x >= xMax) {
			return;
		}

		Node n = m[y][x];
		// System.out.println(c + " h=" + totalHeatLos + " analyzing node " + n);
		if (n.visit(c, totalHeatLos)) {
			proceed(n, c, totalHeatLos);
		} else {
			// System.out.println("TERMINATED");
			// there is already better path to
		}

	}

	private void proceed(Node n, String c, int totalHeatLos) {
		Set<String> possibleDirections = n.getPossibleDirections(c);
		for (String d : possibleDirections) {
			int x = n.x;
			int y = n.y;
			switch (d) {
			case ">":
			case ">>":
			case ">>>":
				x++;
				break;
			case "<":
			case "<<":
			case "<<<":
				x--;
				break;
			case "v":
			case "vv":
			case "vvv":
				y++;
				break;
			case "^":
			case "^^":
			case "^^^":
				y--;
				break;
			}
			visit(y, x, d, totalHeatLos + n.heat);
		}
	}

	public Node getFinish() {
		Node[] lastRow = m[m.length - 1];
		return lastRow[lastRow.length - 1];
	}

}
