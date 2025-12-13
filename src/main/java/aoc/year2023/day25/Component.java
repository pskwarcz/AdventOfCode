package aoc.year2023.day25;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Component {

	int size = 1;
	final String name;

	List<Edge> connections = new ArrayList<>();

	public Component(String name) {
		this.name = name;
	}

	public Edge createNewEdge(Component c) {
		Edge e = new Edge(this, c);
		connections.add(e);
		c.connections.add(e);
		return e;
	}

	public void attach(Edge e) {
		connections.add(e);
	}

	public void dettach(Edge e) {
		if (!connections.remove(e)) {
			throw new IllegalStateException("Component does nothave this edge" + this + " e:" + e);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Component other = (Component) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Component [");
		builder.append(name);
		builder.append("(");
		builder.append(size);
		builder.append(")");
		builder.append(connections.stream().map(c -> c.name).collect(Collectors.toSet()));
		builder.append("]");
		return builder.toString();
	}

	public List<Edge> removeSelfLoops() {
		List<Edge> toRemove = new ArrayList<>();
		Iterator<Edge> it = connections.iterator();
		while (it.hasNext()) {
			Edge e = it.next();
			if (e.isSelfLoop()) {
				it.remove();
				toRemove.add(e);
			}
		}
		return toRemove;
	}

}
