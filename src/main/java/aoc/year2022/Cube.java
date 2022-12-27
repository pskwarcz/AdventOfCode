package aoc.year2022;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Cube {

	public static int MAX_SIZE = 3;

	static Map<Point, Cube> cubes = new HashMap<>();

	static Set<Point> regularCubes = new HashSet<>();
	private static Set<Point> airCubes = new HashSet<>();

	// if this is air should belong to air pocket
	Integer pocketNr;

	public static Map<Integer, Set<Point>> allAirPockets = new HashMap<>();

	Point p;
	boolean air;

	static int pocketSeq = 1;

	private Cube(Point p) {
		super();
		this.p = p;
		cubes.put(p, this);
	}

	public static Cube createCube(Point p) {
		Cube q = new Cube(p);
		regularCubes.add(q.p);
		return q;
	}

	public static Cube createAirCube(Point p) {
		Cube a = new Cube(p);
		a.air = true;
		airCubes.add(a.p);
		return a;
	}

	@Override
	public int hashCode() {
		return Objects.hash(p);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cube other = (Cube) obj;
		return Objects.equals(p, other.p);
	}

	public void findAirPocketsAround() {
		for (Cube a : getAirCubesAround()) {
			a.createAirPocket();
		}
	}

	public void createAirPocket() {
		int depth = 0;

		if (pocketNr != null) {
			// pocket already exits
			return;
		}

		Set<Point> airPocket = new HashSet<>();
		airPocket.add(this.p);

		pocketNr = pocketSeq++;
		allAirPockets.put(pocketNr, airPocket);
		expand(depth);
	}

	private void expand(int depth) {
		Set<Cube> airQubes = getAirCubesAround();
		if (airQubes.size() < 6) {
			depth = 0;
		}

		if (depth > MAX_SIZE && MAX_SIZE != -1) {
			return;
		}

		for (Cube a : airQubes) {
			if (pocketNr.equals(a.pocketNr)) {
				continue;
			}
			a.addToAirPocket(pocketNr, depth);
		}
	}

	private void addToAirPocket(Integer pocketN, int depth) {

		Set<Point> airPocket = allAirPockets.get(pocketN);

		if (pocketNr == null) {
			// qube air not connected with any pocket
			airPocket.add(this.p);
			pocketNr = pocketN;
			expand(++depth);
		} else {
			// case eliminated

			System.out.println("pocket " + pocketNr + "  merged to " + pocketN + " at " + this.p);

			Set<Point> current = allAirPockets.remove(pocketNr);

			airPocket.addAll(current);

			for (Point q : current) {
				cubes.get(q).pocketNr = pocketN;
			}

		}

	}

	private Set<Cube> getAirCubesAround() {
		Set<Cube> a = new HashSet<>();
		for (Point adj : p.getAdjacent3D()) {
			Cube q = getQube(adj);
			if (q.air) {
				a.add(q);
			}
		}
		return a;
	}

	Cube getQube(Point p) {
		if (cubes.containsKey(p)) {
			return cubes.get(p);
		}
		Cube q = createAirCube(p);
		return q;
	}

}
