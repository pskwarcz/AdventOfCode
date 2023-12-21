package aoc.year2023.day20;

import java.util.HashMap;
import java.util.Map;

public class Conjunction extends Module {

	Map<String, Boolean> memory = new HashMap<>();

	public Conjunction(String line) {
		super(line);
	}

	public void init(String name) {
		memory.put(name, Boolean.FALSE);
	}

	@Override
	public void acceptPulse(boolean pulse, String from) {
		super.acceptPulse(pulse, from);
		memory.put(from, pulse);
	}

	@Override
	public void process() {
		received = false;
		if (memory.isEmpty()) {
			throw new IllegalStateException("cannnot be empty");
		}
		out = memory.values().contains(Boolean.FALSE);
		toSend = true;
		System.out.println("Processed in:" + in + " " + this);
		// memory.clear();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Conjunction [");
		builder.append("name=");
		builder.append(name);
		builder.append(", memory=");
		builder.append(memory);
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
