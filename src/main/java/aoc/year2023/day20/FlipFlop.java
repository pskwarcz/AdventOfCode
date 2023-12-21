package aoc.year2023.day20;

public class FlipFlop extends Module {

	boolean on = false;

	public FlipFlop(String line) {
		super(line);
	}

	@Override
	public void process() {
		received = false;
		if (in) {
			toSend = false;
			// System.out.println("\t" + name + " switched off");
		} else {
			toSend = true;
			on = !on;
			out = on;
		}
		// System.out.println("Processed " + this);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlipFlop [on=");
		builder.append(on);
		builder.append(", name=");
		builder.append(name);
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
