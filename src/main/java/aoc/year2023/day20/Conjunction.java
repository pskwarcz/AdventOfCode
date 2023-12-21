package aoc.year2023.day20;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Conjunction extends Module {

	Map<String, Boolean> memory = new HashMap<>();

	public Conjunction(String line) {
		super(line);
	}

	public void init(String name) {
		memory.put(name, Boolean.FALSE);
	}

	Queue<String> from = new LinkedList<>();

	@Override
	public void acceptPulse(boolean pulse, String from) {
		super.acceptPulse(pulse, from);
		this.from.offer(from);

	}

	@Override
	public void process() {
		received = false;
		if (memory.isEmpty()) {
			throw new IllegalStateException("cannnot be empty");
		}
		while (!in.isEmpty()) {
			boolean pulse = in.poll();
			memory.put(from.poll(), pulse);
			System.out.println("\t" + name + ": t memory:" + memory);
			out.offer(memory.values().contains(Boolean.FALSE));
			toSend = true;
		}
		// System.out.println("\t\t" + name + " ---" + toPulse(out) + "--->");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Conjunction [");
		builder.append("name=");
		builder.append(name);
		builder.append(", memory=");
		builder.append(memory);
		builder.append(", out=");
		builder.append(out);
		builder.append(", received=");
		builder.append(received);
		builder.append(", toSend=");
		builder.append(toSend);
		builder.append(", nextModules=");
		builder.append(nextModules);
		builder.append("]");
		return builder.toString();
	}

}
