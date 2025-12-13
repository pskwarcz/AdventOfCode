package aoc.year2023;

import java.util.ArrayList;
import java.util.List;

public class Day14b extends Day14 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day14b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	@Override
	void moveRocks(List<String> lines) {

		List<List<String>> prevH = new ArrayList<>();

		int max = 1000000000;
		boolean loopDetected = false;
		for (int i = 0; i < max; i++) {
			moveNWSE(lines);
			System.out.println(i + " hash: " + lines.hashCode());
			if (prevH.contains(lines) && !loopDetected) {
				int p = prevH.indexOf(lines);
				System.out.println("\t " + i + " Already have " + lines.hashCode() + " after cucle: " + p);
				int c = i - p;
				System.out.println("\t cycle length " + c);
				int toGo = max - i - 1;
				int loops = (int) Math.floor(toGo / c);
				int skip = loops * c;
				System.out.println("\t togo: " + toGo + " loops count: " + loops + " skip: " + skip);
				i += skip;
				System.out.println("new i = " + i);
				loopDetected = true;
			}
			prevH.add(new ArrayList<>(lines));
		}
	}

	private void moveNWSE(List<String> lines) {
		while (moveRocksN(lines)) {
			// System.out.println();
			// print(lines);
		}
		while (moveRocksW(lines)) {
			// System.out.println();
			// print(lines);
		}
		while (moveRocksS(lines)) {
			// System.out.println();
			// print(lines);
		}
		while (moveRocksE(lines)) {
			// System.out.println();
			// print(lines);
		}
	}

	private boolean moveRocksE(List<String> lines) {
		boolean moved = false;
		for (int y = 0; y < lines.size(); y++) {
			for (int x = 1; x < lines.get(0).length(); x++) {
				char to = lines.get(y).charAt(x);
				char from = lines.get(y).charAt(x - 1);
				if (to == '.' && from == 'O') {
					moveRock(lines, y, x - 1, y, x);
					moved = true;
				}
			}
		}
		return moved;
	}

	private boolean moveRocksS(List<String> lines) {
		boolean moved = false;
		for (int y = 1; y < lines.size(); y++) {
			for (int x = 0; x < lines.get(0).length(); x++) {
				char to = lines.get(y).charAt(x);
				char from = lines.get(y - 1).charAt(x);
				if (to == '.' && from == 'O') {
					moveRock(lines, y - 1, x, y, x);
					moved = true;
				}
			}
		}
		return moved;
	}

	private boolean moveRocksW(List<String> lines) {
		boolean moved = false;
		for (int y = 0; y < lines.size(); y++) {
			for (int x = 0; x < lines.get(0).length() - 1; x++) {
				char c = lines.get(y).charAt(x);
				char up = lines.get(y).charAt(x + 1);
				if (c == '.' && up == 'O') {
					moveRock(lines, y, x + 1, y, x);
					moved = true;
				}
			}
		}
		return moved;
	}

}
