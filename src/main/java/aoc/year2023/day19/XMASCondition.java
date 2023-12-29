package aoc.year2023.day19;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class XMASCondition implements Comparable<XMASCondition> {

	public static final List<Character> RATIINGS = Arrays.asList('x', 'm', 'a', 's');

	private Set<Condition> conditions = new TreeSet<>();

	public Set<Condition> getConditions() {
		return conditions;
	}

	public XMASCondition copy() {
		XMASCondition xmas = new XMASCondition();
		xmas.conditions.addAll(conditions);
		return xmas;
	}

	public void and(Condition condition) throws ConditionNotPossible {
		// System.out.print("Merging " + conditions + " with (AND) " + condition);
		Condition current = get(condition.c);
		if (current == null) {
			return;
		}
		conditions.remove(current);
		if (condition != null) {
			conditions.add(current.and(condition));
		}
		// System.out.println(" result = " + conditions);
	}

	public static XMASCondition mergeOr(XMASCondition a, XMASCondition b) throws ConditionNotPossible {
		XMASCondition result = new XMASCondition();
		for (char c : RATIINGS) {
			Condition ca = a.get(c);
			Condition cb = b.get(c);
			if (ca != null) {
				result.conditions.add(ca.or(cb));
			} else if (cb != null) {
				result.conditions.add(cb.or(ca));
			} else {
				System.out.println("Both conditions are null result in null");
			}
		}
		return result;
	}

	public static XMASCondition mergeAnd(XMASCondition a, XMASCondition b) throws ConditionNotPossible {
		XMASCondition result = new XMASCondition();
		for (char c : XMASCondition.RATIINGS) {
			Condition ca = a.get(c);
			Condition cb = b.get(c);
			result.conditions.add(ca.and(cb));
		}
		return result;
	}

	@Override
	public int compareTo(XMASCondition o) {
		for (char c : RATIINGS) {
			Condition a = get(c);
			Condition b = o.get(c);

			int q = (a != null ? a.min : 0) - (b != null ? b.min : 0);
			if (q != 0) {
				return q;
			}
		}
		return 0;
	}

	private Condition get(char c) {
		return conditions.stream().filter(x -> x.c == c).findAny().orElseGet(() -> null);
	}

	public static XMASCondition maxXMAS() {
		XMASCondition x = new XMASCondition();
		for (char c : RATIINGS) {
			x.conditions.add(Condition.maxCondition(c));
		}
		return x;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("XMASCondition ");
		builder.append(conditions);
		return builder.toString();
	}

	public long countPosibilites() {
		return conditions.stream().map(Condition::getRange).reduce(1L, (a, b) -> a * b);
	}

}
