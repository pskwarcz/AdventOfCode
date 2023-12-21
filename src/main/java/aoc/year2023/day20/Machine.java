package aoc.year2023.day20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Machine {

	Map<String, Module> modules = new HashMap<>();

	Broadcaster b;

	public Machine(List<String> lines) {
		loadModules(lines);
		joinModules();
		System.out.println(this);
		b = (Broadcaster) modules.get("roadcaster");
	}

	public void pressButton() {
		System.out.println("button -low-> broadcaster");
		b.acceptPulse(false, "button");
		boolean busy;
		do {
			busy = false;
			List<Module> busyModules = modules.values().stream().filter(x -> x.received).collect(Collectors.toList());
			busy = process(busy, busyModules);
			send(busyModules);
		} while (busy);
		System.out.println("END");
	}

	private void send(List<Module> busyModules) {
		// System.out.println("----SENDING----");
		for (Module m : busyModules) {
			m.sendPulse();
		}
	}

	private boolean process(boolean busy, List<Module> busyModules) {
		// System.out.println("----PROCESSING----");
		for (Module m : busyModules) {
			m.process();
			if (m.toSend) {
				busy = true;
			}
		}
		return busy;
	}

	private void joinModules() {
		for (Module m : modules.values()) {
			m.join(modules);
		}
	}

	private void loadModules(List<String> lines) {
		for (String line : lines) {
			Module m;
			switch (line.charAt(0)) {
			case '%':
				m = new FlipFlop(line);
				break;
			case '&':
				m = new Conjunction(line);
				break;
			case 'b':
				m = new Broadcaster(line);
				break;
			default:
				throw new IllegalArgumentException("Unknown type: " + line);
			}
			modules.put(m.name, m);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Machine [modules=\n");
		for (Module m : modules.values()) {
			builder.append("\t");
			builder.append(m);
			builder.append("\n");
		}
		builder.append("]");
		return builder.toString();
	}

}
