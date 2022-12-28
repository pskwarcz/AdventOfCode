package aoc.year2021;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import aoc.Utils;

public class Day4 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		List<String> lines = Utils.readFile("/aoc/year2021/input4");

		long result = new Day4().start(lines);
		System.out.println("RESULT: " + result);

		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	List<Integer> numbers = new ArrayList<>();

	List<Bingo> games = new ArrayList<>();

	private int start(List<String> lines) {

		init(lines);
		System.out.println("\n------------------------\n");
		for (Integer i : numbers) {
			System.out.println("number: " + i);

			Iterator<Bingo> it = games.iterator();
			System.out.println("number of games: " + games.size());

			while (it.hasNext()) {
				Bingo b = it.next();
				if (b.bingo(i)) {
					System.out.println("BINGO! " + i);
					System.out.println(b);
					if (games.size() > 1) {
						it.remove();
						System.out.println("removed");
					} else {
						System.out.println("returning value");
						return b.sumOfAllUnmarked() * i;
					}
				}
			}
		}

		return 0;
	}

	public void init(List<String> lines) {
		Iterator<String> it = lines.iterator();

		for (String s : it.next().split(",")) {
			numbers.add(Integer.valueOf(s));
		}

		while (it.hasNext()) {
			it.next();
			Bingo b = new Bingo(it);
			games.add(b);
			System.out.println(b);
		}

	}

}
