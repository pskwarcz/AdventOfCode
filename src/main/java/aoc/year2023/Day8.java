package aoc.year2023;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import aoc.Utils;

public class Day8 {

	public class Node {
		String left;
		String right;

		public Node(String left, String right) {
			super();
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("(");
			builder.append(left);
			builder.append(", ");
			builder.append(right);
			builder.append(")");
			return builder.toString();
		}

	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day8().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input8.txt");

		long result = process(lines);

		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		Iterator<String> it = lines.iterator();

		char[] instructions = it.next().toCharArray();
		it.next(); // empty line
		Map<String, Node> nodes = readNodes(it);
		System.out.println(nodes);

		String s = "AAA";
		int steps = 0;
		int i = 0;
		while (!"ZZZ".equals(s)) {
			Node n = nodes.get(s);
			char ins = instructions[i];
			// System.out.println("Current node: " + s + " = " + n + " instruction:" + ins);
			if (ins == 'L') {
				s = n.left;
			} else {
				s = n.right;
			}
			i = (i + 1) % instructions.length;
			steps++;
			// System.out.println("Step:" + steps + " Next node:" + s + ".");
		}

		return steps;
	}

	private Map<String, Node> readNodes(Iterator<String> it) {
		Map<String, Node> nodes = new HashMap<>();
		while (it.hasNext()) {
			String[] l = it.next().split("=");
			String key = l[0].trim();
			String left = l[1].split(",")[0].split("\\(")[1];
			String right = l[1].split(",")[1].trim().split("\\)")[0];
			nodes.put(key, new Node(left, right));
		}
		return nodes;
	}

}
