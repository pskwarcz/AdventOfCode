package aoc.year2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import aoc.Utils;

public class Day17 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		new Day17().start();

		long duration = System.currentTimeMillis() - start;
		System.out.println("Time[ms]: " + duration);
		long estimated = targetN / n * duration / 1000 / 60 / 60;
		System.out.println("estimated[h]" + estimated);
	}

	int width = 7;
	long blocksDown = 0L;
	static long targetN = 1000_000_000_000L;
	public static long n = targetN;

	int maxDeep = 0;

	List<String> line = new ArrayList<>();
	List<String> cross = new ArrayList<>();
	List<String> corner = new ArrayList<>();
	List<String> ice = new ArrayList<>();
	List<String> box = new ArrayList<>();

	int shape = 0;
	List<String> currentShape;

	List<List<String>> shapes = Arrays.asList(line, cross, corner, ice, box);

	List<char[]> cave = new ArrayList<char[]>();

	long caveHeight = 0;

	int level;
	int pos;

	Map<Integer, LoopDetection> linePos = new HashMap<>();

	static class LoopDetection {

		int pos;
		int instr;
		int loopNr;
		long blocks;
		long h;

		LoopDetection(int pos, int instr) {
			this.pos = pos;
			this.instr = instr;
		}

		@Override
		public int hashCode() {
			return Objects.hash(instr, pos);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			LoopDetection other = (LoopDetection) obj;
			return instr == other.instr && pos == other.pos;
		}

	}

	private long start() {
		List<String> lines = Utils.readFile("/aoc/year2022/input17");
		return start(lines);
	}

	int instruction;
	int loop = 0;

	public long start(List<String> lines) {

		prepareShapes();
		getNextShape();

		String row = lines.get(0);

		// 3068

		while (true) {
			loop++;

			for (instruction = 0; instruction < row.length(); instruction++) {

				char direction = row.charAt(instruction);

				// System.out.println(level + " " + pos + " " + direction);
				move(direction);
				moveDown();

				if (blocksDown >= n) {
					System.out.println("Height: " + caveHeight);
					return caveHeight;
				}
			}

		}

	}

	private void print(List<char[]> l, int level2) {
		for (int y = l.size() - 1; y >= 0; y--) {
			if (y + level2 < 10) {
				System.out.print(" ");
			}
			System.out.print((y + level2) + "|");
			for (char c : l.get(y)) {
				char x = c == '#' ? c : '.';
				System.out.print(x);
			}
			System.out.println("|");

		}
	}

	private void moveDown() {

		level--;
		if (level >= 0 && movePossible(0)) {
			// block still falling
			return;
		}

		level++;

		blocksDown++;
		int y = -1;
		for (String l : currentShape) {

			y++;

			if (level + y < cave.size()) {

				// merge
				char[] c = cave.get(level + y);

				for (int x = 0; x < l.length(); x++) {
					if (l.charAt(x) == '#') {
						c[pos + x] = l.charAt(x);
					}
				}

			} else {

				char[] newLine = new char[width];
				for (int x = 0; x < l.length(); x++) {
					newLine[pos + x] = l.charAt(x);
				}
				cave.add(newLine);
				caveHeight++;

				loopDetectionOptimisation();

			}

		}

		getNextShape();

	}

	boolean loopOptimisationApplied = false;

	private void loopDetectionOptimisation() {
		if (loopOptimisationApplied) {
			return;
		}

		if (currentShape.equals(line)) {

			if (linePos.containsKey(Integer.valueOf(instruction))) {
				LoopDetection ld = linePos.get(instruction);

				if (ld.pos == pos) {

					System.out.println("Detected cycle of size " + (loop - ld.loopNr));

					// loop
					long blocksInCycle = blocksDown - ld.blocks;
					long heightInCycle = caveHeight - ld.h;

					// blocks = n
					long skipCycles = (n - blocksDown) / blocksInCycle - 1;

					long skipHeight = skipCycles * heightInCycle;
					long skipBlocks = skipCycles * blocksInCycle;

					System.out.println("skipHeight=" + skipHeight);
					System.out.println("skipBlocks=" + skipBlocks);

					caveHeight += skipHeight;
					blocksDown += skipBlocks;

					System.out.println("Skipped " + skipBlocks);

					loopOptimisationApplied = true;

				}
			} else {
				LoopDetection ld = new LoopDetection(pos, instruction);
				ld.blocks = blocksDown;
				ld.h = caveHeight;
				ld.loopNr = loop;
				linePos.put(Integer.valueOf(instruction), ld);
			}

		}
	}

	private void getNextShape() {
		currentShape = shapes.get(shape);
		shape++;
		shape %= 5;

		level = cave.size() + 3;
		pos = 2;

		// System.out.println("Next shape:");
		// print(currentShape, level);
	}

	private void move(char direction) {

		int move = direction == '>' ? 1 : -1;

		if (movePossible(move)) {
			// System.out.println("move " + direction + " possible");
			pos += move;
		} else {
			// System.out.println("move " + direction + " NOT possible");
		}

	}

	boolean movePossible(int move) {
		int y = 0;

		// check walls
		if (pos + move < 0 || pos + currentShape.get(0).length() + move > width) {
			return false;
		}

		for (String line : currentShape) {

			// check other elements
			if (level + y + 1 > cave.size()) {
				return true;
			}

			int x = pos - 1;
			for (char c : line.toCharArray()) {
				x++;
				if ('#' != c) {
					continue;
				}

				if (cave.get(level + y)[x + move] == '#') {
					return false;
				}

			}

			y++;
		}
		return true;
	}

	private void prepareShapes() {
		line.add("####");

		cross.add(" # ");
		cross.add("###");
		cross.add(" # ");

		corner.add("###");
		corner.add("  #");
		corner.add("  #");

		ice.add("#");
		ice.add("#");
		ice.add("#");
		ice.add("#");

		box.add("##");
		box.add("##");
	}

}
