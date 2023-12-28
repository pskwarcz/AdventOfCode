package aoc.year2023.day25;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Edge {
	private Set<String> v = new HashSet<>();

	Edge(String a, String b) {
		v.add(a);
		v.add(b);
	}

	@Override
	public int hashCode() {
		return Objects.hash(v);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		return Objects.equals(v, other.v);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(v);
		return builder.toString();
	}

}
