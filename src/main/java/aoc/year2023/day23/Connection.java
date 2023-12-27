package aoc.year2023.day23;

import java.util.Objects;

public class Connection {

	public Crossroad to;
	public int distance = 0;

	@Override
	public int hashCode() {
		return Objects.hash(distance, to);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connection other = (Connection) obj;
		return distance == other.distance && Objects.equals(to, other.to);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[(");
		if (to != null) {
			builder.append(to.x);
			builder.append(",");
			builder.append(to.y);
		} else {
			builder.append("?,?");
		}
		builder.append(")");
		builder.append(":");
		builder.append(distance);
		builder.append("]");
		return builder.toString();
	}

}
