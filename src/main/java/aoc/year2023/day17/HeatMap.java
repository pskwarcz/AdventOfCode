package aoc.year2023.day17;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class HeatMap {

	public Node[][] m;
	static int xMax;
	static int yMax;

	public static int minMoves = 1;
	public static int maxMoves = 3;

	Queue<Move> moves = new LinkedList<>();

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
		moves.offer(new Move(1, 0, ">", 0));
		moves.offer(new Move(0, 1, "v", 0));
		processMoves();
	}

	private void processMoves() {
		while (true) {
			Move mo = moves.poll();
			if (mo == null) {
				return;
			}
			visit(mo.y, mo.x, mo.d, mo.totalHeatLos);
		}
	}

	private void visit(int y, int x, String c, int totalHeatLos) {
		Node n = m[y][x];
		// System.out.println(c + " h=" + totalHeatLos + " analyzing node " + n);
		if (n.visit(c, totalHeatLos)) {
			Set<Move> nextMoves = n.getNextMoves(c, totalHeatLos);
			moves.addAll(nextMoves);
		} else {
			// System.out.println("TERMINATED");
			// there is already better path to
		}

	}

	public Node getFinalNode() {
		return m[yMax - 1][xMax - 1];
	}

}
