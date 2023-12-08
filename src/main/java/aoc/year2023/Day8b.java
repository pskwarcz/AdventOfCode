package aoc.year2023;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
	static List<String> ends;
	static char[] instructions;
	static Map<String, Node> nodes;
	Pawn[] pawns;
	Thread[] threads;

	Result result = new Result();

	long process(List<String> lines) throws InterruptedException {
		Iterator<String> it = lines.iterator();

		instructions = it.next().toCharArray();
		it.next(); // empty line
		nodes = readNodes(it);
		Node.linkNodes(nodes);

		System.out.println(nodes);

		current = findEndsWith(nodes, "A");
		ends = findEndsWith(nodes, "Z");

		System.out.println(current);
		System.out.println(ends);

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
		for (Pawn p : pawns) {
			System.out.println(p);
		}

		return result.getMax();
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
		System.out.println("Round finished");
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

		private int i = 0;
		private long lastZ = 0;

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
			builder.append(", current=");
			builder.append(current);
			builder.append("]");
			return builder.toString();
		}

		@Override
		public void run() {
			boolean shouldContinue = true;
			while (shouldContinue) {
				// System.out.println(id + " STARTED step:" + step + " at z:" + current);
				goToNext();
				while (!isAtZ()) {
					goToNext();
				}
				saveLast();
				// System.out.println(id + " FINISHED step:" + step + " at z:" + current);
				try {
					shouldContinue = shouldContinue();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(id + " TERMINATED step:" + step + " at z:" + current);
		}

		private void saveLast() {
			lastZ = step;
		}

		// wait for others or continue if there is larger result
		boolean shouldContinue() throws InterruptedException {
			synchronized (result) {
				if (lastZ > result.getMax()) {
					result.setMax(lastZ);

					// System.out.println(id + " new end = " + result.getMax());
					result.wait();
					// System.out.println(id + " continue " + result.getMax());
					return !result.allMatching();
				}

				if (lastZ < result.getMax()) {
					// System.out.println(id + " max not reached, continue max = " +
					return true;
				}

				if (result.isMatching(lastZ)) {
					if (result.allMatching()) {
						return false;
					}

					result.wait();
					// System.out.println(id + " continue " + result.getMax());
					return !result.allMatching();
				}

			}
			return false;
		}

		boolean isAtZ() {
			return current.isZ();
		}

		void goToNext() {
			char instr = instructions[i];
			current = current.getNext(instr);
			step++;
			i = (i + 1) % instructions.length;

			if (step % 500000000 == 0) {
				long end = System.currentTimeMillis();
				long duration = end - start;
				System.out.println(id + " c:" + step + " steps/ms " + step / duration);
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

}

class Node {

	private String left;
	private String right;
	private String name;
	private Node l;
	private Node r;
	boolean isZ;

	public Node(String name, String left, String right) {
		super();
		this.name = name;
		this.left = left;
		this.right = right;
		isZ = name.endsWith("Z");
	}

	public static void linkNodes(Map<String, Node> nodes) {
		for (Entry<String, Node> en : nodes.entrySet()) {
			Node n = en.getValue();
			n.name = en.getKey();
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
		builder.append("(");
		builder.append(left);
		builder.append(", ");
		builder.append(right);
		builder.append(")");
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
		if (matching > 2) {
			System.out.println(" Matching " + max + ":" + matching);
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