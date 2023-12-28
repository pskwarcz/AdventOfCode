package aoc.year2023.day25;

import java.util.Objects;

public class Edge {

	String name;
	Component a;
	Component b;

	Edge(Component a, Component b) {
		if (a.name.compareTo(b.name) < 0) {
			this.name = a.name + b.name;
			this.a = a;
			this.b = b;
		} else {
			this.name = b.name + a.name;
			this.a = b;
			this.b = a;
		}

	}

	public Component getA() {
		return a;
	}

	public Component getB() {
		return b;
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
		Edge other = (Edge) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Edge ");
		builder.append(name);
		builder.append("=[");
		builder.append(a.name);
		builder.append("-");
		builder.append(b.name);
		builder.append("]");
		return builder.toString();
	}

	public Component getSecond(Component v) {
		return v.equals(a) ? b : a;
	}

	public void unlink() {
		a.connections.remove(this);
		b.connections.remove(this);
		a = null;
		b = null;
	}

	public void replace(Component o, Component n) {
		o.dettach(this);
		if (a == o) {
			a = n;
		} else {
			b = n;
		}
		n.attach(this);
	}

	boolean isSelfLoop() {
		return a.equals(b);
	}

}
