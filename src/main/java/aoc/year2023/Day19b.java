package aoc.year2023;

import java.util.ArrayList;
import java.util.List;

import aoc.year2023.day19.Path;
import aoc.year2023.day19.Workflow;
import aoc.year2023.day19.XMASCondition;

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
		return getPathsTo("A").stream().map(Path::aggregate).map(XMASCondition::countPosibilites).mapToLong(a -> a)
				.sum();
	}

	private List<Path> getPathsTo(String name) {
		List<Workflow> r = findWithResult(name);
		List<Path> paths = new ArrayList<>();
		for (Workflow w : r) {
			if ("in".equals(w.getName())) {
				for (XMASCondition c : w.outcomes.get(name)) {
					Path p = new Path();
					p.add(c, name);
					paths.add(p);
				}
			}
			for (Path p : getPathsTo(w.getName())) {

				for (XMASCondition c : w.outcomes.get(name)) {
					Path newPath = p.clone();
					newPath.add(c, name);
					paths.add(newPath);
				}
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
