package aoc.year2023.day22;

import java.util.Objects;

public class Point3D implements Comparable<Point3D> {

	int x;
	int y;
	int z;

	public Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point3D(String coordinates) {
		String[] c = coordinates.split(",");
		x = Integer.parseInt(c[0]);
		y = Integer.parseInt(c[1]);
		z = Integer.parseInt(c[2]);
	}

	@Override
	public int compareTo(Point3D o) {
		if (z != o.z) {
			return z - o.z;
		}
		if (y != o.y) {
			return y - o.y;
		}
		return x - o.x;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		return x == other.x && y == other.y && z == other.z;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		builder.append(x);
		builder.append(",");
		builder.append(y);
		builder.append(",");
		builder.append(z);
		builder.append(")");
		return builder.toString();
	}

}
