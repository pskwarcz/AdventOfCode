package aoc.year2022;

import static aoc.year2022.Monkey.monkeys;
import static aoc.year2022.Monkey.root;

import java.util.List;

import aoc.Utils;

public class Day21 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		List<String> lines = Utils.readFile("/aoc/year2022/input21");

		long result = new Day21().start(lines);

		System.out.println("RESULT:" + result);
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("Time[ms]: " + duration);
	}

	public long start(List<String> lines) {
		for (String s : lines) {
			new Monkey(s);
		}

		for (Monkey m : Monkey.monkeys.values()) {
			if (m.operation != null) {
				monkeys.get(m.m1).setParent(m);
				monkeys.get(m.m2).setParent(m);
			}
		}

		root.operation = "=";

		eliminateKnownNodes();
		// left gqjg=221864598374220

		// root: gqjg + rpjv
		// System.err.println("----left=" + monkeys.get(root.m1).getNumber());
		// System.err.println("----right=" + monkeys.get(root.m2).getNumber());

		// BigDecimal bwns = monkeys.get("bwns").getNumber();
		// System.out.println("bwns=" + bwns);

		// return root.getNumber();
		return monkeys.get("humn").getResult();
	}

	private void eliminateKnownNodes() {
		// monkeys.get("gqjg").setNumber(221864598374220L);
		// monkeys.get("lnns").setNumber(832);
		// monkeys.get("sgzf").setNumber(-832);
		// monkeys.get("psbr").setNumber(-455);
//		monkeys.get("fjlh").setNumber(60);
		// monkeys.get("humn").setNumber(2021L);
	}

}
