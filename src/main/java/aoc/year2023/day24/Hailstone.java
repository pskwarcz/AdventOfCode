package aoc.year2023.day24;

public class Hailstone {

	public static long min = 7L;
	public static long max = 27L;

	long x;
	long y;
	long z;

	int vx;
	int vy;
	int vz;

	double a;
	double b;

	public Hailstone(String line) {
		String[] c = line.split("@")[0].split(",");
		x = Long.parseLong(c[0].trim());
		y = Long.parseLong(c[1].trim());
		z = Long.parseLong(c[2].trim());

		String p = line.split("@")[1];
		String[] v = p.split(",");
		vx = Integer.parseInt(v[0].trim());
		vy = Integer.parseInt(v[1].trim());
		vz = Integer.parseInt(v[2].trim());
		System.out.println(this);

		a = (double) vy / vx;
		b = y - a * x;
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
		builder.append("@");
		builder.append("(");
		builder.append(vx);
		builder.append(",");
		builder.append(vy);
		builder.append(",");
		builder.append(vz);
		builder.append(")");
		return builder.toString();
	}

	public boolean intersect(Hailstone h) {
		if (a == h.a && h.b != b) {
			// check if one is faster and fly from behind
			return false;
		}

		double xi = (h.b - b) / (a - h.a);
		double yi = a * xi + b;

		if (xi < min || max < xi) {
			return false;
		}
		if (yi < min || max < yi) {
			return false;
		}

		if (!this.isReachable(xi, yi) || !h.isReachable(xi, yi)) {
			return false;
		}

		System.out.println(this + " " + h + " x=" + xi + ", y=" + yi);

		return true;
	}

	private boolean isReachable(double xi, double yi) {
		if (vx < 0 && xi > x || vx > 0 && xi < x) {
			return false;
		}
		if (vy < 0 && yi > y || vy > 0 && yi < y) {
			return false;
		}
		return true;
	}

}
