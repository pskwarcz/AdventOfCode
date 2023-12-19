package aoc.year2023.day19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class Workflow {

	public static final int MIN = 1;
	public static final int MAX = 4000;

	public static final List<Character> RATIINGS = Arrays.asList('x', 'm', 'a', 's');

	private String name;
	String[] instructions;
	public Map<String, List<Set<Condition>>> outcomes = new HashMap<>();

	public Workflow(String line) {
		name = line.split("\\{")[0];
		String instructions = line.split("\\{")[1].replaceAll("}", "");
		this.instructions = instructions.split(",");
		// System.out.println();
		loadPossibleOutcomes();
		// System.out.println(this);

		for (List<Set<Condition>> a : outcomes.values()) {
			if (a.size() > 1) {
				Comparator<Set<Condition>> cmp = (s1, s2) -> compare(s1, s2);
				a.sort(cmp);
				Set<Condition> b;
				try {
					b = a.stream().sorted(cmp).reduce(Workflow::mergeOr).get();
					a.clear();
					a.add(b);
				} catch (UnsupportedOperationException e) {
					System.err.println("cannot reduce: " + this + e.getMessage());
				}

			}
		}

		// System.out.println();
	}

	private int compare(Set<Condition> s1, Set<Condition> s2) {
		for (char c : RATIINGS) {
			int q = findCondition(s1, c).min - findCondition(s2, c).min;
			if (q != 0) {
				return q;
			}
		}
		return 0;
	}

	private Condition findCondition(Set<Condition> s1, char c) {
		return s1.stream().filter(x -> x.c == c).findFirst().orElseGet(() -> new Condition(1, 400, c));
	}

	private void loadPossibleOutcomes() {
		Set<Condition> prevConditions = new TreeSet<>();
		for (String i : instructions) {
			// System.out.println("\nResolving " + i + " prev conditions: " +
			// prevConditions);
			if (i.contains(":")) {
				Condition condition = new Condition(i.split(":")[0]);
				String result = i.split(":")[1];

				Set<Condition> allConditions = new TreeSet<>(prevConditions);
				mergeConditionAnd(allConditions, condition);

				// System.out.println("all conditions " + result + " = " + allConditions);
				putResult(result, allConditions);

				Condition r = condition.reverse();
				// System.out.println(condition + " REVERSED: " + r);
				addCondition(prevConditions, r);
				// System.out.println("prev conditions: " + prevConditions);
			} else {
				putResult(i, prevConditions);
			}
			// System.out.println("OUTCOMES: " + outcomes);
		}
	}

	private void addCondition(Set<Condition> c, Condition r) {
		// System.out.print("adding " + r + " to " + c);
		Optional<Condition> o = c.stream().filter(a -> a.c == r.c).findFirst();
		if (o.isPresent()) {
			Condition existing = o.get();
			Condition newCondition = existing.or(r);
			// System.out.println("condition " + existing + " replaced with " +
			// newCondition);
			c.remove(existing);
			c.add(newCondition);
		} else {
			c.add(r);
		}
		// System.out.println(" = " + c);
	}

	private void mergeConditionAnd(Set<Condition> conditions, Condition condition) {
		// System.out.print("Merging " + conditions + " with (AND) " + condition);
		Optional<Condition> o = conditions.stream().filter(s -> s.c == condition.c).findFirst();
		if (o.isPresent()) {
			Condition current = o.get();
			try {
				conditions.add(current.and(condition));
				conditions.remove(current);
			} catch (UnsupportedOperationException e) {
				// System.out.println("ignored: " + e.getMessage());
				throw e;
			}
		} else {
			conditions.add(condition);
		}
		// System.out.println(" result = " + conditions);
	}

	private void putResult(String result, Set<Condition> conditions) {
		if (result.equals("R")) {
			// ignore
			return;
		}
		List<Set<Condition>> possibleSolutions = outcomes.getOrDefault(result, new ArrayList<>());
		// System.out.println("Merging current: " + result + ":" + possibleSolutions +
		// "WITH (OR)" + conditions);
		possibleSolutions.add(conditions);
		// System.out.println(" merge result: " + possibleSolutions);
		outcomes.put(result, possibleSolutions);
	}

	public static Set<Condition> mergeAnd(Set<Condition> a, Set<Condition> b) {
		Set<Condition> result = new TreeSet<>();
		for (char c : Workflow.RATIINGS) {
			Condition ca = a.stream().filter(p -> p.c == c).findFirst()
					.orElse(new Condition(Condition.MIN, Condition.MAX, c));
			Condition cb = b.stream().filter(p -> p.c == c).findFirst()
					.orElse(new Condition(Condition.MIN, Condition.MAX, c));
			result.add(ca.and(cb));
		}

		return result;
	}

	public static Set<Condition> mergeOr(Set<Condition> a, Set<Condition> b) {
		// System.out.println("Merging "+a + " with "+b);
		Set<Condition> result = new TreeSet<>();
		for (char c : Workflow.RATIINGS) {
			Condition ca = a.stream().filter(p -> p.c == c).findFirst()
					.orElse(new Condition(Condition.MIN, Condition.MAX, c));
			Condition cb = b.stream().filter(p -> p.c == c).findFirst()
					.orElse(new Condition(Condition.MIN, Condition.MAX, c));
			result.add(ca.or(cb));
		}

		return result;
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
		builder.append(", conditions=");
		builder.append(outcomes);
		builder.append("]");
		return builder.toString();
	}

	public boolean hasResult(String key) {
		return outcomes.containsKey(key);
	}

}
