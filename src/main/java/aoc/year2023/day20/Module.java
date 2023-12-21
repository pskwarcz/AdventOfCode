package aoc.year2023.day20;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Module {

	public static long lowCounter = 0;
	public static long highCounter = 0;

	char c;
	public String name;

	boolean received = false;
	boolean toSend = false;

	boolean in;
	String from;
	boolean out;

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
		received = true;
		this.in = pulse;
		this.from = from;
		// System.out.println("\t" + " received: " + (in ? "high " : "low ") + this);
	}

	abstract void process();

	final void sendPulse() {
		if (!toSend) {
			return;
		}
		for (Module n : next) {
			System.out.println(c + name + " -" + (out ? "high" : "low") + "-> " + n.name);
			n.acceptPulse(out, name);
		}
		toSend = false;
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
