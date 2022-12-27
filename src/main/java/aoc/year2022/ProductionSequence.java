package aoc.year2022;

import static aoc.year2022.Material.CLAY;
import static aoc.year2022.Material.GEODE;
import static aoc.year2022.Material.OBSIDIAN;
import static aoc.year2022.Material.ORE;
import static aoc.year2022.RobotFactory.AVAILABLE_TIME;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

public class ProductionSequence {

	Blueprint b;
	private Deque<Material> sequence = new LinkedList<>();

	boolean hasNext = true;

	ProductionSequence(Blueprint b) {
		this.b = b;
	}

	public Deque<Material> get() {
		Deque<Material> copy = new LinkedList<>();
		copy.addAll(sequence);
		return copy;
	}

	public void nextDown() {

		if (sequence.contains(OBSIDIAN) && countInSequence(GEODE) < b.maxObsidianRobots) {
			sequence.add(GEODE);
			return;
		}

		if (sequence.contains(CLAY) && countInSequence(OBSIDIAN) < b.maxObsidianRobots) {
			sequence.add(OBSIDIAN);
			return;
		}

		if (countInSequence(CLAY) < b.maxClayRobots) {
			sequence.add(CLAY);
		} else {
			next();
		}
	}

	public boolean hasNext() {
		return hasNext;
	}

	public void next() {
		Material last = sequence.pollLast();

		switch (last) {
		case GEODE:
			if (countInSequence(OBSIDIAN) < b.maxObsidianRobots) {
				sequence.add(OBSIDIAN);
				break;
			}
		case OBSIDIAN:
			if (countInSequence(CLAY) < b.maxClayRobots) {
				sequence.add(CLAY);
				break;
			}
		case CLAY:
			if (countInSequence(ORE) < b.maxOreRobots) {
				sequence.add(ORE);
				break;
			}
		case ORE:
			if (sequence.isEmpty()) {
				hasNext = false;
				return;
			}
			next();
		}

		// if (!canBuildOnTime()) {
		// System.out.println("\t\t" + sequence + " Optimised, no sufficient time to
		// build all requested robots");
		// next();
		// }

		// if (!sequence.contains(GEODE) || !sequence.contains(OBSIDIAN) ||
		// !sequence.contains(CLAY)) {
		// System.out.println("\t\t" + sequence + " Optimised, no sufficient
		// materials!");
		// nextDown();
		// }

	}

	private boolean canBuildOnTime() {
		int[] cnt = new int[4];
		cnt[Material.ORE.ordinal()]++;

		for (Material m : sequence) {
			cnt[m.ordinal()]++;
		}

		int estimatedTime = 0;

		Map<Material, Integer> prevCost = b.robotCost.get(GEODE);
		for (Material m : Material.values()) {

			// System.out.println("calculating for " + m + " count : " + cnt[m.ordinal()]);

			if (!m.equals(GEODE)) {
				// czas produkcji OBS na wybudowanie GEO
				if (cnt[m.ordinal()] != 0) {

					double cost = prevCost.get(m);
					int prodTime = (int) Math.ceil(cost / cnt[m.ordinal()]);
					// System.out.println(" \t" + Material.values()[m.ordinal() - 1] + " count: " +
					// prevCount + " cost: "
					// + cost + " " + m + " prodTime: " + prodTime);
					estimatedTime += prodTime;

				}
			}
			estimatedTime += cnt[m.ordinal()] / 2;
			// System.out.println("\ttotal time = " + estimatedTime);
			prevCost = b.robotCost.get(m);
		}

		estimatedTime = Math.max(estimatedTime, sequence.size());
//		System.out.println("Estimated time:" + estimatedTime);
		return AVAILABLE_TIME >= estimatedTime;

	}

	private int countInSequence(Material ore) {
		int i = 0;
		for (Material m : sequence) {
			if (m.equals(ore)) {
				i++;
			}
		}
		return i;
	}

}
