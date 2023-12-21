package aoc.year2023.day20;

public class FlipFlop extends Module {

	boolean on = false;

	public FlipFlop(String line) {
		super(line);
	}

	@Override
	public void process() {
		received = false;
		while (!in.isEmpty()) {
			boolean pulse = in.poll();
			if (pulse) {
				System.out.println("\t" + name + " inactive");
			} else {
				toSend = true;
				on = !on;
				if (!on) {
					System.out.println("\t" + name + " switched off");
				}
				out.offer(on);
			}
		}
		System.out.println("Processed " + this);
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
		builder.append(", out=");
		builder.append(out);
		builder.append(", nextModules=");
		builder.append(nextModules);
		builder.append("]");
		return builder.toString();
	}

}
