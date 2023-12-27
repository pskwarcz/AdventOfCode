package aoc.year2023.day23;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import aoc.year2023.Point;

public class Crossroad extends Point {

	public Set<Connection> connections = new HashSet<>();

	public boolean finish = false;

	public Crossroad(int x, int y) {
		super(x, y);
	}

	public Crossroad(Point p) {
		super(p);
	}

	static int cnt = 0;

	public void addConnection(Connection c) {
		connections.add(c);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (finish) {
			builder.append("FINISH [");
		} else {
			builder.append("Crossroad [");
		}
		builder.append(super.toString());
		builder.append(" -> ");
		builder.append(connections);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Crossroad other = (Crossroad) obj;
		return Objects.equals(connections, other.connections) && finish == other.finish;
	}

	public Set<Connection> getPossibleConnections(Collection<Crossroad> visited) {
		return connections.stream().filter(c -> !visited.contains(c.to)).collect(Collectors.toSet());
	}

}
