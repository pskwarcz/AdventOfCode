package aoc.year2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import aoc.Utils;
import aoc.year2023.day19.Part;
import aoc.year2023.day19.Workflow;

public class Day19 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day19().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input19.txt");
		long result = process(lines);
		System.out.println("\nresult: " + result);
	}

	Map<String, Workflow> workflows = new HashMap<>();
	List<Part> parts = new ArrayList<>();

	long process(List<String> lines) {
		load(lines);

		long sum = parts.parallelStream().filter(this::isAccepted).mapToInt(Part::getRating).sum();

		return sum;
	}

	private boolean isAccepted(Part p) {
		String result = "in";
		while (!"A".equals(result) && !"R".equals(result)) {
			Workflow w = workflows.get(result);
			result = w.getResult(p);
		}
		return "A".equals(result);
	}

	void load(List<String> lines) {
		Iterator<String> it = lines.iterator();
		String line = it.next();
		while (!StringUtils.isBlank(line)) {
			Workflow w = new Workflow(line);
			workflows.put(w.getName(), w);
			line = it.next();
		}
		while (it.hasNext()) {
			parts.add(new Part(it.next()));
		}
	}

}
