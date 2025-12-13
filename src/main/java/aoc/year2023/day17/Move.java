package aoc.year2023.day17;

import java.util.Objects;

import aoc.year2023.Point;

public class Move extends Point {
	String d;
	int totalHeatLos;

	public Move(int x, int y, String d, int totalHeatLos) {
		super(x, y);
		this.d = d;
		this.totalHeatLos = totalHeatLos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(d, totalHeatLos);
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
		Move other = (Move) obj;
		return Objects.equals(d, other.d) && totalHeatLos == other.totalHeatLos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Move [");
		builder.append(d);
		builder.append(super.toString());
		builder.append(totalHeatLos);
		builder.append("]");
		return builder.toString();
	}

}
