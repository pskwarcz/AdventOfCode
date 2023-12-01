package aoc.year2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Bingo {

	static int cnt = 0;

	int n = ++cnt;

	Set<Integer> selected = new HashSet<>();
	Set<Integer> unmarked = new HashSet<>();
	Map<Integer, Integer> numberToRow = new HashMap<>();;
	Map<Integer, Integer> numberToCol = new HashMap<>();;
	Map<Integer, List<Integer>> rows = new HashMap<>();
	Map<Integer, List<Integer>> cols = new HashMap<>();;

	public Bingo(Iterator<String> it) {

		for (int i = 0; i < 5; i++) {
			rows.put(i, new ArrayList<>());
			cols.put(i, new ArrayList<>());
		}

		for (int y = 0; y < 5; y++) {

			String[] numbers = it.next().trim().split("\\s+");

			int x = 0;
			for (String s : numbers) {
				Integer number = Integer.valueOf(s);

				unmarked.add(number);

				numberToRow.put(number, y);
				numberToCol.put(number, x);

				rows.get(y).add(number);
				cols.get(x).add(number);

				x++;
			}
		}
	}

	public boolean bingo(Integer i) {
		if (!unmarked.contains(i)) {
			return false;
		}

		unmarked.remove(i);
		selected.add(i);

		List<Integer> r = rows.get(numberToRow.get(i));

		List<Integer> c = cols.get(numberToCol.get(i));

		return selected.containsAll(r) || selected.containsAll(c);
	}

	public Integer sumOfAllUnmarked() {
		int s = 0;
		for (Integer i : unmarked) {
			s += i;
		}
		return s;
	}

	@Override
	public String toString() {
		return "Bingo [n=" + n + ", selected=" + selected + ", unmarked=" + unmarked + "]";
	}

}
