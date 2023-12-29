package aoc.year2023.day19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Workflow {

	public static final int MIN = 1;
	public static final int MAX = 4000;

	private String name;
	String[] instructions;
	public Map<String, List<XMASCondition>> outcomes = new HashMap<>();

	public Workflow(String line) {
		// System.out.println(line);
		name = line.split("\\{")[0];
		String instructions = line.split("\\{")[1].replaceAll("}", "");
		this.instructions = instructions.split(",");

		loadPossibleOutcomes();

	}

	private void loadPossibleOutcomes() {
		XMASCondition prevCondition = XMASCondition.maxXMAS();
		for (String i : instructions) {
			// System.out.println("\nResolving " + i + " prev conditions: " +
			// prevConditions);
			if (i.contains(":")) {
				Condition condition = new Condition(i.split(":")[0]);
				String result = i.split(":")[1];

				try {
					XMASCondition allConditions = prevCondition.copy();
					allConditions.and(condition);
					putResult(result, allConditions);
				} catch (ConditionNotPossible e) {
					// TODO this can be ignored
					System.err.println("One of the outcomes cannot be reached: " + i + " workflow: " + name);
					throw new IllegalArgumentException("this shouold not happen", e);
				}

				// System.out.println("all conditions " + result + " = " + allConditions);

				Condition reversed = condition.reverse();
				// System.out.println(condition + " REVERSED: " + r);
				try {
					prevCondition.and(reversed);
				} catch (ConditionNotPossible e) {
					// TODO if failed do not go further - it means that rest is not reachable
					e.printStackTrace();
				}
				// System.out.println("prev conditions: " + prevConditions);
			} else {
				putResult(i, prevCondition);
			}
			// System.out.println("OUTCOMES: " + outcomes);
		}
	}

	private void putResult(String result, XMASCondition conditions) {
		if (result.equals("R")) {
			// ignore
			return;
		}
		List<XMASCondition> possibleSolutions = outcomes.getOrDefault(result, new ArrayList<XMASCondition>());
		// System.out.println("Merging current: " + result + ":" + possibleSolutions +
		// "WITH (OR)" + conditions);
		possibleSolutions.add(conditions);
		// System.out.println(" merge result: " + possibleSolutions);
		outcomes.put(result, possibleSolutions);
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
		StringBuilder b = new StringBuilder();
		b.append("Workflow [name=");
		b.append(name);
		b.append(", instructions=");
		b.append(Arrays.toString(instructions));
		b.append(", outcomes=\n  ");
		outcomes.entrySet().stream().forEach(es -> {
			b.append("").append(es.getKey()).append(":");
			es.getValue().stream().forEach(v -> b.append("\t").append(v.toString()).append("\n  "));
		});
		b.append("]");
		return b.toString();
	}

	public boolean hasResult(String key) {
		return outcomes.containsKey(key);
	}

}
