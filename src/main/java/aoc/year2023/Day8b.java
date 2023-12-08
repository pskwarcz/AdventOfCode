package aoc.year2023;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import aoc.Utils;

public class Day8b {

	static long start;

	public static void main(String[] args) throws InterruptedException {
		start = System.currentTimeMillis();
		new Day8b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() throws InterruptedException {
		List<String> lines = Utils.readFile("/aoc/year2023/input8.txt");

		long result = process(lines);

		System.out.println("\nresult: " + result);
	}

	static List<String> current;
	static char[] instructions;
	static Map<String, Node> nodes;
	static Pawn[] pawns;
	Thread[] threads;

	Result result = new Result();

	static int instrSize;

	long process(List<String> lines) throws InterruptedException {
		Iterator<String> it = lines.iterator();

		instructions = it.next().toCharArray();
		instrSize = instructions.length;
		System.out.println("instr size: " + instrSize);
		it.next(); // empty line
		nodes = readNodes(it);
		Node.linkNodes(nodes);

		System.out.println(nodes);

		current = findEndsWith(nodes, "A");

		System.out.println(current);

		int size = current.size();
		result.size = size;

		pawns = new Pawn[size];
		threads = new Thread[size];

		int tidx = 0;
		for (String c : current) {
			pawns[tidx] = new Pawn(nodes.get(c), tidx);
			tidx++;
		}

		runThreads();
		long r = 1;
		for (Pawn p : pawns) {
			System.out.println(p);
			r = lcm(r, p.step);
			System.out.println("lcm: " + r);
		}
		System.out.println(r);
		return r;
	}

	private static long lcm(long a, long b) {
		return a * (b / gcd(a, b));
	}

	private static long gcd(long a, long b) {
		while (b > 0) {
			long temp = b;
			b = a % b; // % is remainder
			a = temp;
		}
		return a;
	}

	public void runThreads() throws InterruptedException {

		for (Pawn p : pawns) {
			Thread t = new Thread(p);
			threads[p.id] = t;
			t.start();
		}

		for (Thread t : threads) {
			t.join();
		}
		System.out.println("All threads finished");
	}

	public void duration(long steps, int size) {
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println(steps + " steps/ms: " + (steps / duration));
	}

	class Pawn implements Runnable {
		private int id;
		private long step = 0;
		private Node current;
		Set<Integer> interations = new HashSet<>();
		private int i = 0;

		public Pawn(Node current, int id) {
			this.current = current;
			this.id = id;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Pawn [id=");
			builder.append(id);
			builder.append(", step=");
			builder.append(step);
			builder.append(", ");
			builder.append(current);
			builder.append("]");
			return builder.toString();
		}

		@Override
		public void run() {
			do {
				goToNext();
			} while (!current.isZ());

			System.out.println(id + " Cycle length:" + step);
		}

		void goToNext() {
			char instr = instructions[i];
			current = current.getNext(instr);
			step++;
			i++;
			if (i == instrSize) {
				i = 0;
			}
		}

	}

	private List<String> findEndsWith(Map<String, Node> nodes, String suffix) {
		return nodes.keySet().stream().filter(s -> s.endsWith(suffix)).collect(Collectors.toList());
	}

	private Map<String, Node> readNodes(Iterator<String> it) {
		Map<String, Node> nodes = new HashMap<>();
		while (it.hasNext()) {
			String[] l = it.next().split("=");
			String key = l[0].trim();
			String left = l[1].split(",")[0].split("\\(")[1];
			String right = l[1].split(",")[1].trim().split("\\)")[0];
			nodes.put(key, new Node(key, left, right));
		}
		return nodes;
	}

	public static String pawnsToString() {
		StringBuilder b = new StringBuilder();
		b.append(" [");
		for (Pawn p : pawns) {
			b.append("\n\t" + p);
		}
		b.append("\t]");
		return b.toString();
	}

}

class Node {

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

class Result {
	int size;

	private volatile long max = 0;
	int matching = 0;

	public synchronized long getMax() {
		return max;
	}

	public synchronized void setMax(long max) {
		this.max = max;
		matching = 1;
		notifyAll();
	}

	public synchronized boolean isMatching(long value) {
		if (max != value) {
			return false;
		}

		matching++;
		if (matching > 3) {
			long end = System.currentTimeMillis();
			long duration = end - Day8b.start;
			System.out.println(
					" Matching " + max + ":" + matching + " steps/ms " + max / duration + Day8b.pawnsToString());
		}

		if (matching == size) {
			System.out.println("All matching! " + max);
			notifyAll();
		}

		return true;
	}

	boolean allMatching() {
		return matching == size;
	}
}