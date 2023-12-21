package aoc.year2023.day20;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public abstract class Module {

	public static long lowCounter = 0;
	public static long highCounter = 0;

	char c;
	public String name;

	boolean received = false;
	boolean toSend = false;

	Queue<Boolean> in = new LinkedList<>();
	Queue<Boolean> out = new LinkedList<>();

	List<String> nextModules = new ArrayList<>();

	List<Module> next = new ArrayList<>();

	public Module(String line) {
		c = line.charAt(0);
		name = line.split("\\s")[0].substring(1);
		for (String n : line.split("->")[1].split(",")) {
			nextModules.add(n.trim());
		}
	}

	Module() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(c);
		builder.append(" " + name);
		builder.append(" -> ");
		for (Module n : next) {
			builder.append(n.name);
			builder.append(" ");
		}
		return builder.toString();
	}

	public void acceptPulse(boolean pulse, String from) {
		if (pulse) {
			highCounter++;
		} else {
			lowCounter++;
		}
		this.in.offer(pulse);
		received = true;
		// System.out.println("\t" + " received: " + (in ? "high " : "low ") + this);
	}

	public String toPulse(boolean value) {
		return value ? "high" : "low-";
	}

	abstract void process();

	void sendPulse() {
		if (!toSend) {
			throw new IllegalStateException("should not happen send when toSend false" + this);
		}
		while (!out.isEmpty()) {
			boolean pulse = out.poll();
			for (Module n : next) {
				printSend(pulse, n.name);
				n.acceptPulse(pulse, name);
			}
		}
		toSend = false;
	}

	void printSend(boolean pulse, String target) {
		System.out.println(c + name + " -" + (pulse ? "high" : "low") + "-> " + target);
	}

	public void join(Map<String, Module> modules) {
		for (String m : nextModules) {
			Module n = modules.get(m);
			if (n == null) {
				n = new Output(m);
			}
			if (n instanceof Conjunction) {
				Conjunction c = (Conjunction) n;
				c.init(name);
			}
			next.add(n);
		}

	}

	@Override
	public int hashCode() {
		return Objects.hash(c, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Module other = (Module) obj;
		return c == other.c && Objects.equals(name, other.name);
	}

}
