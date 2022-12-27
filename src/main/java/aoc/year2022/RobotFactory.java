package aoc.year2022;

import static aoc.year2022.Material.GEODE;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RobotFactory {

	Blueprint b;

	List<Material> robots = new ArrayList<>();

	Map<Material, Integer> materials = new HashMap<>();

	int time = 1;
	static final int AVAILABLE_TIME = Blueprint.TOTAL_TIME;

	Deque<Material> production = new LinkedList<>();

	public RobotFactory(Blueprint b, Deque<Material> production) {
		this.b = b;
		robots.add(Material.ORE);

		for (Material m : Material.values()) {
			materials.put(m, 0);
		}

		this.production = production;

	}

	public int run() {
		Day19.simulations++;

		while (time <= AVAILABLE_TIME) {
			step();
		}
		if (Day19.PRINT_PRODUCTION_STEPS)
			System.out.println(materials + " robots: " + robots);
		return materials.get(GEODE);
	}

	public void step() {
		// System.out.println("Minute start: " + (time));

		boolean canStartBuilding = canStartBuilding();

		produceMaterials();

		if (canStartBuilding) {
			build(production.poll());
		}

		if (Day19.PRINT_PRODUCTION_STEPS)
			System.out.println(time + " mat:" + materials + " robots=" + robots);

		time++;
	}

	private boolean canStartBuilding() {
		if (production.isEmpty()) {
			return false;
		}

		Material robotType = production.peek();

		if (production.contains(robotType)) {
			if (canAfford(robotType)) {
				// System.out.println("Can build: " + robotType);

				production.remove(robotType);
				production.addFirst(robotType);

				return true;
			}
		}

		return false;
	}

	private boolean canAfford(Material robotType) {
		Map<Material, Integer> cost = b.robotCost.get(robotType);
		for (Entry<Material, Integer> i : cost.entrySet()) {
			if (materials.get(i.getKey()).intValue() < i.getValue().intValue()) {
				return false;
			}
		}
		return true;
	}

	private void build(Material robotType) {
		Map<Material, Integer> cost = b.robotCost.get(robotType);
		removeMaterials(cost);
		robots.add(robotType);
		// System.out.println(time + " Building robot: " + robotType);
	}

	private void removeMaterials(Map<Material, Integer> cost) {
		for (Entry<Material, Integer> i : cost.entrySet()) {
			int n = materials.get(i.getKey()) - i.getValue();
			materials.put(i.getKey(), n);
		}
	}

	private void produceMaterials() {
		for (Material robot : robots) {
			int n = materials.get(robot).intValue() + 1;
			materials.put(robot, n);
		}
	}
}
