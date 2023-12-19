package aoc.year2023.day19;

public class Condition implements Comparable<Condition> {

	public static final int MIN = 1;
	public static final int MAX = 4000;

	final int min;
	final int max;
	public final char c;

	public Condition(int min, int max, char c) {
		super();
		this.min = min;
		this.max = max;
		this.c = c;
	}

	public Condition(String instruction) {
		c = instruction.charAt(0);

		int v = Integer.parseInt(instruction.substring(2));
		switch (instruction.charAt(1)) {
		case '<':
			min = MIN;
			max = v - 1;
			break;
		case '>':
			min = v + 1;
			max = MAX;
			break;
		default:
			throw new IllegalArgumentException("Unknown condition: " + instruction);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(c);
		builder.append("=");
		builder.append("<");
		builder.append(min);
		builder.append(",");
		builder.append(max);
		builder.append(">");
		return builder.toString();
	}

	public Condition reverse() {
		if (min == MIN) {
			return new Condition(max + 1, MAX, c);
		}
		return new Condition(MIN, min - 1, c);
	}

	public Condition or(Condition c2) {
		if (max + 1 == c2.min || c2.max + 1 == min) {
			return new Condition(Math.min(min, c2.min), Math.max(max, c2.max), c);
		} else if (max >= c2.min) {
			return new Condition(min, c2.max, c);
		}
		// System.err.println("unsupported OR: " + this + " OR " + c2);
		throw new UnsupportedOperationException("unsupported OR: " + this + " OR " + c2);
	}

	public Condition and(Condition c2) {
		return new Condition(Math.max(min, c2.min), Math.min(max, c2.max), c);
	}

	public Long getRange() {
		return (long) max - min + 1;
	}

	public boolean overlap(Condition cb) {
		return cb.min <= max && cb.max >= min;
	}

	@Override
	public int compareTo(Condition o) {
		if (c != o.c) {
			return c - o.c;
		}

		if (min != o.min) {
			return o.min - min;
		}

		return o.max - max;
	}

}
