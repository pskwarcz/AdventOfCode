package aoc.year2023.day20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class Machine {

	Map<String, Module> modules = new HashMap<>();

	Broadcaster b;

	public Machine(List<String> lines) {
		loadModules(lines);
		joinModules();
		// System.out.println(this);

		modules.values().stream().filter(m -> m instanceof Conjunction).map(m -> (Conjunction) m)
				.forEach(m -> System.out.println(m.name + " memory:" + m.memory));

		b = (Broadcaster) modules.get("roadcaster");
	}

	public void pressButton() {
		// System.out.println("button -low-> broadcaster");
		b.acceptPulse(false, "button");
		boolean busy;
		do {
			List<Module> busyModules = modules.values().stream().filter(x -> x.received).collect(Collectors.toList());
			process(busyModules);
			List<Module> toSendModules = modules.values().stream().filter(x -> x.toSend).collect(Collectors.toList());
			busy = send(toSendModules);
		} while (busy);
		// System.out.println("END");
	}

	private boolean send(List<Module> busyModules) {
		System.out.println("----SENDING----");
		boolean busy = false;
		for (Module m : busyModules) {
			m.sendPulse();
			busy = true;
		}
		return busy;
	}

	private boolean process(List<Module> busyModules) {
		System.out.println("----PROCESSING----");
		boolean busy = false;
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
			line = line.trim();
			if (StringUtils.isBlank(line)) {
				continue;
			}
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
		builder.append("size=" + modules.size());
		return builder.toString();
	}

}
