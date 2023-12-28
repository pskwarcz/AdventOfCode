package aoc.year2023.day25;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Component {

	final String name;
	Set<Component> connections = new HashSet<>();

	public Component(String name) {
		this.name = name;
	}

	public void wireWith(Component c) {
		connections.add(c);
		c.connections.add(this);
	}

	public void disconnect(Component c) {
		connections.remove(c);
		c.connections.remove(this);
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
		builder.append(": ");
		builder.append(connections.stream().map(c -> c.name).collect(Collectors.toSet()));
		builder.append("]");
		return builder.toString();
	}

}
