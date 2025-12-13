package aoc.year2023;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import aoc.Utils;
import aoc.year2023.day8.Node;

public class Day8b {

	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
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

	static char[] instructions;
	static Map<String, Node> nodes;
	static Pawn[] pawns;
	Thread[] threads;

	long process(List<String> lines) throws InterruptedException {
		Iterator<String> it = lines.iterator();

		instructions = it.next().toCharArray();

		it.next(); // empty line
		nodes = readNodes(it);
		Node.linkNodes(nodes);

		System.out.println(nodes);

		List<String> nodesA = findEndsWith(nodes, "A");
		System.out.println(nodesA);

		int size = nodesA.size();

		pawns = new Pawn[size];
		threads = new Thread[size];

		int tidx = 0;
		for (String c : nodesA) {
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

	class Pawn implements Runnable {
		private int id;
		private long step = 0;
		private Node current;
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
			if (i == instructions.length) {
				i = 0;
			}
		}

	}

	private static List<String> findEndsWith(Map<String, Node> nodes, String suffix) {
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

}
