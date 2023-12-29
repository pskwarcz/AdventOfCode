package aoc.year2023.day19;

import java.util.ArrayList;
import java.util.List;

public class Path {

	public List<String> names = new ArrayList<>();
	List<XMASCondition> p = new ArrayList<>();

	public void add(XMASCondition outcome, String name) {
		p.add(outcome);
		names.add(name);
	}

	@Override
	public Path clone() {
		Path clone = new Path();
		clone.names = new ArrayList<>(names);
		clone.p = new ArrayList<>(p);
		return clone;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Path [names=");
		builder.append(names);
		builder.append(", p=");
		builder.append(p);
		builder.append("]");
		return builder.toString();
	}

	public XMASCondition aggregate() {
		// System.out.println("Aggregating path: " + names);
		// p.stream().forEach(a -> System.out.println("\t" + a));
		return p.stream().reduce((t, u) -> {
			try {
				return XMASCondition.mergeAnd(t, u);
			} catch (ConditionNotPossible e) {
				e.printStackTrace();
				return null;
			}
		}).get();
		// System.out.println(" =>" + r);
	}

}
