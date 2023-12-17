package aoc.year2023.day8;

import java.util.Map;
import java.util.Map.Entry;

public class Node {

	private final String name;
	private final String left;
	private final String right;
	private Node l;
	private Node r;
	final boolean isZ;

	public Node(String name, String left, String right) {
		this.name = name;
		this.left = left;
		this.right = right;
		isZ = name.endsWith("Z");
	}

	public static void linkNodes(Map<String, Node> nodes) {
		for (Entry<String, Node> en : nodes.entrySet()) {
			Node n = en.getValue();
			n.l = nodes.get(n.left);
			n.r = nodes.get(n.right);
		}
	}

	public boolean isZ() {
		return isZ;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Node [");
		builder.append(name);
		builder.append(" = (");
		builder.append(left);
		builder.append(", ");
		builder.append(right);
		builder.append(")]");
		return builder.toString();
	}

	public Node getNext(char ins) {
		if (ins == 'L') {
			return l;
		} else {
			return r;
		}
	}

}
