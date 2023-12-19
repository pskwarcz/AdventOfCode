package aoc.year2023.day19;

import java.util.Arrays;

public class Workflow {

	private String name;
	String[] instructions;

	public Workflow(String line) {
		name = line.split("\\{")[0];
		String instructions = line.split("\\{")[1].replaceAll("}", "");
		this.instructions = instructions.split(",");
		System.out.println(this);
	}

	public String getName() {
		return name;
	}

	public String getResult(Part p) {
		for (String i : instructions) {
			if (i.contains(":")) {
				String condition = i.split(":")[0];
				String result = i.split(":")[1];
				if (p.isTrue(condition)) {
					return result;
				}
			} else {
				return i;
			}
		}
		throw new IllegalStateException("No result " + this + " part: " + p);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Workflow [name=");
		builder.append(name);
		builder.append(", instructions=");
		builder.append(Arrays.toString(instructions));
		builder.append("]");
		return builder.toString();
	}

}
