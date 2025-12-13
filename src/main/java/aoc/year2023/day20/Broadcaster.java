package aoc.year2023.day20;

public class Broadcaster extends Module {

	public Broadcaster(String line) {
		super(line);
	}

	@Override
	protected void process() {
		received = false;
		out = in;
		toSend = true;
		// System.out.println("Processed " + this);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Broadcaster [name=");
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
