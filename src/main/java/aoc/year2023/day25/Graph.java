package aoc.year2023.day25;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

public class Graph implements Cloneable {

	public Map<String, Component> components = new HashMap<>();

	List<Edge> edges = new ArrayList<>();

	public Graph(List<String> lines) {
		for (String line : lines) {

			for (String name : line.split(":?\\s")) {
				if (!components.containsKey(name)) {
					components.put(name, new Component(name));
				}
			}
			Component c = components.get(line.split(":")[0]);
			for (String name : line.split(":\\s")[1].split("\\s")) {
				Component w = components.get(name);
				Edge e = c.createNewEdge(w);
				edges.add(e);
			}
		}
		// components.values().stream().forEach(n -> System.out.println(n));
		// edges.stream().forEach(n -> System.out.println(n));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Graph {\n");
		components.values().forEach(c -> builder.append(c).append("\n"));
		builder.append("]");

		builder.append("edges: [");
		edges.stream().forEach(e -> builder.append(e).append("\n"));
		builder.append("]");
		return builder.toString();
	}

	private void dfs(Component v, Set<Component> visited, Collection<Edge> edgesToCut) {
		visited.add(v);
		for (Edge e : v.connections) {
			Component u = e.getSecond(v);
			if (edgesToCut.contains(e)) {
				continue;
			}
			if (!visited.contains(u)) {
				dfs(u, visited, edgesToCut);
			}
		}
	}

	public long cut3andCount() {
		int cs = components.size();
		int size = stream(0, edges.size() - 2).map(this::streamOf3Connections).flatMap(Function.identity())
				.map(this::mapIntsToEdges).parallel().map(this::cutAndGetSize).filter(a -> a > 1 && a < cs).findFirst()
				.get();
		return (cs - size) * size;
	}

	private Stream<int[]> streamOf3Connections(Integer i) {
		Builder<int[]> sb = Stream.<int[]>builder();
		for (int j = i + 1; j < edges.size() - 1; j++) {
			for (int k = j + 1; k < edges.size(); k++) {
				sb.add(new int[] { i, j, k });
			}
		}
		return sb.build();
	}

	private Stream<Integer> stream(int start, int end) {
		Builder<Integer> sb = Stream.builder();
		for (int i = start; i < end; i++) {
			sb.add(i);
		}
		return sb.build();
	}

	private Collection<Edge> mapIntsToEdges(int[] ie) {
		Set<Edge> e = new HashSet<>();
		for (int i : ie) {
			e.add(edges.get(i));
		}
		return e;
	}

	static int n = 0;

	private Integer cutAndGetSize(Collection<Edge> edgesToCut) {
		Component c = components.values().iterator().next();
		Set<Component> visited = new HashSet<>();
		dfs(c, visited, edgesToCut);
		n++;
		if (n % 100_000 == 0) {
			System.out.println(n + ": " + edgesToCut + " size:");
		}
		int i = visited.size();
		if (i > 1 && i < components.size()) {
			System.out.println("solution:" + edgesToCut + "griuo size: " + i);
		}
		return i;
	}

	public Optional<Integer> karger(int cutSize, int targetSize) {
		Random random = new Random();
		Component c;
		do {
			int x = random.nextInt(edges.size());
			c = contract(edges.get(x));
			removeSelfLoops(c);
		} while (components.size() > targetSize);
		if (edges.size() == cutSize) {
			return Optional.of(c.size);
		}
		return Optional.empty();
	}

	private void removeSelfLoops(Component c) {
		for (Edge e : c.removeSelfLoops()) {
			edges.remove(e);
		}
	}

	Component contract(Edge e) {
		Component a = e.getA();
		Component b = e.getB();
		Component c = new Component(a.name + b.name);
		c.size = a.size + b.size;

		edges.remove(e);
		e.unlink();

		reconnect(a, c);
		reconnect(b, c);

		components.remove(a.name);
		components.remove(b.name);
		components.put(c.name, c);
		return c;
	}

	private void reconnect(Component o, Component n) {
		for (Edge e : new ArrayList<>(o.connections)) {
			e.replace(o, n);
		}
	}

}
