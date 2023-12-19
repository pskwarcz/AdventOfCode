package aoc.year2023;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import aoc.year2023.day19.Condition;
import aoc.year2023.day19.Workflow;

public class Day19b extends Day19 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day19b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	@Override
	long process(List<String> lines) {
		load(lines);

		List<List<List<Set<Condition>>>> paths = getPathsTo("A");

		System.out.println("Size before: " + paths.size());

		List<Set<Condition>> aggregated = paths.stream().map(this::split).flatMap(Collection::stream)
				.map(this::aggregate).collect(Collectors.toList());

		System.out.println("Size after: " + aggregated.size());

		return aggregated.stream().map(this::getPosibilitesCount).mapToLong(a -> a).sum();
	}

	private long getPosibilitesCount(Set<Condition> path) {
		long r = path.stream().map(Condition::getRange).reduce(1L, (a, b) -> a * b);
		System.out.println(path + " posibilities: " + r);
		return r;
	}

	private Set<Condition> aggregate(List<Set<Condition>> path) {
		// System.out.print(path);
		Set<Condition> r = path.stream().reduce(Workflow::mergeAnd).get();
		System.out.println("=>" + r);
		return r;
	}

	private List<List<Set<Condition>>> split(List<List<Set<Condition>>> path) {
		// System.out.println("SPLITTING: " + path);
		List<List<Set<Condition>>> result = new ArrayList<>();
		result.add(new ArrayList<>());

		for (List<Set<Condition>> steps : path) {
			List<List<Set<Condition>>> prev = new ArrayList<>(result);

			result = new ArrayList<>();
			for (Set<Condition> s : steps) {
				for (List<Set<Condition>> p : prev) {
					List<Set<Condition>> a = new ArrayList<>(p);
					a.add(s);
					result.add(a);
				}
			}

		}

		if (result.size() > 1) {
			result.stream().forEach(r -> System.out.println("\t -> : " + r));
		}
		return result;
	}

	private List<List<List<Set<Condition>>>> getPathsTo(String name) {

		List<List<List<Set<Condition>>>> paths = new ArrayList<>();
		List<Workflow> r = findWithResult(name);
		for (Workflow w : r) {
			if ("in".equals(w.getName())) {
				List<List<Set<Condition>>> newPath = new ArrayList<>();
				newPath.add(w.outcomes.get(name));
				paths.add(newPath);
				return paths;
			}
			for (List<List<Set<Condition>>> p : getPathsTo(w.getName())) {
				List<List<Set<Condition>>> newPath = new ArrayList<>(p);
				newPath.add(w.outcomes.get(name));
				paths.add(newPath);
			}
		}
		return paths;
	}

	private List<Workflow> findWithResult(String result) {
		List<Workflow> r = new ArrayList<>();
		for (Workflow w : workflows.values()) {
			if (w.hasResult(result)) {
				r.add(w);
			}
		}
		return r;
	}

}
