package aoc.year2022;

import static aoc.year2022.Material.CLAY;
import static aoc.year2022.Material.GEODE;
import static aoc.year2022.Material.OBSIDIAN;
import static aoc.year2022.Material.ORE;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Blueprint {

	Map<Material, Map<Material, Integer>> robotCost = new HashMap<>();

	int number;

	Map<Material, int[]> maxd = new HashMap<>();

	static final int TOTAL_TIME = 32;

	int maxOreRobots = 0;
	int maxClayRobots = 0;
	int maxObsidianRobots = 0;
	int maxGeoRobots = 0;

	public Blueprint(String line) {
		number = Integer.valueOf(line.split(":")[0].split(" ")[1]);

		String[] robots = line.split(":")[1].split(" ");

		Map<Material, Integer> cost = new HashMap<>();
		cost.put(Material.ORE, Integer.valueOf(robots[5]));
		robotCost.put(ORE, cost);

		cost = new HashMap<>();
		cost.put(Material.ORE, Integer.valueOf(robots[11]));
		robotCost.put(CLAY, cost);

		cost = new HashMap<>();
		cost.put(ORE, Integer.valueOf(robots[17]));
		cost.put(CLAY, Integer.valueOf(robots[20]));
		robotCost.put(OBSIDIAN, cost);

		cost = new HashMap<>();
		cost.put(ORE, Integer.valueOf(robots[26]));
		cost.put(OBSIDIAN, Integer.valueOf(robots[29]));
		robotCost.put(Material.GEODE, cost);

		maxOreRobots = maxOreRobots();
		maxClayRobots = clayRobots(TOTAL_TIME);
		maxObsidianRobots = obsidianRobots(TOTAL_TIME);
		maxGeoRobots = geoRobots(TOTAL_TIME);
		System.out.println("Blueprint created " + number);
	}

	@Override
	public String toString() {
		return "Blueprint [number=" + number + ", robotCost=" + robotCost + ",\n maxOreRobots=" + maxOreRobots
				+ ",\n maxClayRobots=" + maxClayRobots + ",\n maxObsidianRobots=" + maxObsidianRobots
				+ ",\n maxGeoRobots=" + maxGeoRobots + "]";
	}

	int maxOreRobots() {
		int a = Math.max(robotCost.get(GEODE).get(ORE), robotCost.get(OBSIDIAN).get(ORE));
		return Math.max(robotCost.get(CLAY).get(ORE), a);
	}

	private int oreRobots(int t) {
		int max = maxOreRobots();
		int orr = 1 + robots(robotCost.get(ORE).get(ORE), (t - 1));
		return Math.min(orr, max);
	}

	private int maxOres(int t) {
		int o = 0;
		for (int n = 1; n <= t; n++) {
			o += oreRobots(n - 1);
		}
		return o;
	}

	private int clayRobots(int t) {
		if (t <= 0) {
			return 0;
		}
		int prev = clayRobots(t - 1);
		int est = robots(robotCost.get(CLAY).get(ORE), maxOres(t - 1));
		return Math.min(prev + 1, est);
	}

	private int maxClays(int t) {
		int c = 0;
		for (int n = 1; n <= t; n++) {
			c += clayRobots(n - 1);
		}
		return c;
	}

	private int obsidianRobots(int t) {
		if (t <= 0) {
			return 0;
		}
		int prev = obsidianRobots(t - 1);
		int est = robots(robotCost.get(OBSIDIAN).get(CLAY), maxClays(t - 1));
		return Math.min(prev + 1, est);
	}

	private int maxObsidians(int t) {
		int c = 0;
		for (int n = 1; n < t; n++) {
			c += obsidianRobots(n - 1);
		}
		return c;
	}

	private int geoRobots(int t) {
		if (t <= 0) {
			return 0;
		}
		int prev = geoRobots(t - 1);
		int est = robots(robotCost.get(GEODE).get(OBSIDIAN), maxObsidians(t - 1));
		return Math.min(prev + 1, est);
	}

	private int maxGeos(int t) {
		int c = 0;
		for (int n = 1; n < t; n++) {
			c += geoRobots(n - 1);
		}
		return c;
	}

	private int maxOresWithOptimalRobotsProduction() {

		int max = 0;

		Deque<Material> production = new LinkedList<>();
		for (int i = 0; production.isEmpty(); i++) {
			production.clear();
			for (int n = 0; n < i; n++) {
				production.add(ORE);
			}

			RobotFactory rf = new RobotFactory(this, production);
			rf.run();
			// System.out.println(i + " ore-robots: " + rf.materials);
			max = Math.max(max, rf.materials.get(ORE));
		}

		return max;
	}

	private int robots(int consumption, int materials) {
		if (materials <= 0) {
			return 0;
		}
		return (int) Math.floor(1.0 / consumption * materials);
	}

}
