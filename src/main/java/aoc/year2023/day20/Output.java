package aoc.year2023.day20;

public class Output extends Module {

	public Output(String line) {
		this.name = line;
		System.err.println("Output module created: '" + name + "'");
	}

	@Override
	public void acceptPulse(boolean pulse, String from) {
		super.acceptPulse(pulse, from);
		in.clear();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Output [name=");
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

	@Override
	public void process() {
		received = false;
		toSend = false;
		throw new UnsupportedOperationException("should never be processed pulse");
	}

}
