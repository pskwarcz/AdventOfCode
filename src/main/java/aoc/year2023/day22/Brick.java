package aoc.year2023.day22;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Brick implements Comparable<Brick> {
	static int brickCounter = 0;
	int id = 0;
	Point3D a;
	Point3D b;

	Set<Brick> supports = new HashSet<>();
	Set<Brick> settledOn = new HashSet<>();

	public Brick(String line) {
		id = brickCounter++;
		Point3D p0 = new Point3D(line.split("~")[0]);
		Point3D p1 = new Point3D(line.split("~")[1]);
		if (p0.compareTo(p1) < 0) {
			a = p0;
			b = p1;
		} else {
			a = p1;
			b = p0;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(a);
		builder.append("~");
		builder.append(b);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(a, b);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brick other = (Brick) obj;
		return Objects.equals(a, other.a) && Objects.equals(b, other.b);
	}

	@Override
	public int compareTo(Brick o) {
		if (a.compareTo(o.a) != 0) {
			return a.compareTo(o.a);
		}
		return b.compareTo(o.b);
	}

	Set<Point3D> getBasePoints() {
		if (a.z == b.z) {
			return getAllPoints();
		} else {
			Set<Point3D> r = new HashSet<>();
			r.add(a);
			return r;
		}
	}

	Set<Point3D> getUpperPoints() {
		if (a.z == b.z) {
			return getAllPoints();
		} else {
			Set<Point3D> r = new HashSet<>();
			r.add(a);
			return r;
		}
	}

	private Set<Point3D> getAllPoints() {
		Set<Point3D> r = new HashSet<>();
		for (int z = Math.min(a.z, b.z); z <= Math.max(a.z, b.z); z++) {
			for (int y = Math.min(a.y, b.y); y <= Math.max(a.y, b.y); y++) {
				for (int x = Math.min(a.x, b.x); x <= Math.max(a.x, b.x); x++) {
					r.add(new Point3D(x, y, z));
				}
			}
		}
		return r;
	}

	public boolean canSupport(Brick b2) {
		Set<Point3D> top = getUpperPoints();
		Set<Point3D> bottom = b2.getBasePoints();
		return haveCommonXY(top, bottom);
	}

	private boolean haveCommonXY(Set<Point3D> top, Set<Point3D> bottom) {
		for (Point3D t : top) {
			for (Point3D b : bottom) {
				if (t.x == b.x && t.y == b.y) {
					return true;
				}
			}
		}
		return false;
	}

	// return highest z
	public int getTopLevel() {
		return b.z;
	}

	// returns lowest z
	public int getLevel() {
		return a.z;
	}

	public void setLevel(int level) {
		if (getLevel() < level) {
			throw new IllegalArgumentException("Brick cannot be moved up to level " + level + " " + this);
		}
		int dif = getLevel() - level;
		a.z -= dif;
		b.z -= dif;
	}

	public void settleOn(Set<Brick> support) {
		settledOn.addAll(support);
		for (Brick s : support) {
			s.supports.add(this);
		}
	}

	public boolean canBeDesintegrated() {
		for (Brick s : supports) {
			if (s.settledOn.size() < 2) {
				return false;
			}
		}
		return true;
	}

	public void settle(TreeSet<Brick> settled) {
		int maxY = 0;
		Set<Brick> support = new HashSet<>();
		for (Brick s : settled) {
			if (s.getTopLevel() >= maxY && s.canSupport(this)) {
				if (s.getTopLevel() > maxY) {
					support.clear();
					maxY = s.getTopLevel();
				}

				support.add(s);

			}
		}
		System.out.println("Brick " + this + " settled on : " + support);
		setLevel(maxY + 1);
		settleOn(support);
	}

}
