package aoc.year2023;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Day15b extends Day15 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day15b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	@Override
	long process(List<String> lines) {
		List<Box> boxes = initBoxes();
		insertLenses(lines, boxes);
		print(boxes);
		long sum = fPower(boxes);

		return sum;
	}

	private long fPower(List<Box> boxes) {
		long sum = 0;

		int n = 1;
		for (Box b : boxes) {
			int slot = 1;
			for (Lens l : b.lenses) {
				long p = n * slot * l.f;
				System.out.println(l.label + " " + p);
				sum += p;
				slot++;

			}
			n++;
		}
		return sum;
	}

	public void insertLenses(List<String> lines, List<Box> boxes) {
		for (String line : lines) {
			for (String s : line.split(",")) {
				String label = s.split("[=-]")[0];

				if (s.contains("=")) {
					Box b = boxes.get(hash(label));
					int f = Integer.parseInt(s.split("=")[1]);
					b.addOrReplace(new Lens(label, f));
				} else {
					boxes.get(hash(label)).remove(label);
				}

				// System.out.println("\nafter '" + s + "':");
				// print(boxes);
			}
		}
	}

	private void print(List<Box> boxes) {
		int i = 0;
		for (Box b : boxes) {
			if (!b.lenses.isEmpty()) {
				System.out.println("Box " + i + ": " + b.lenses);
			}
			i++;
		}

	}

	public List<Box> initBoxes() {
		List<Box> boxes = new ArrayList<>();
		for (int i = 0; i < 256; i++) {
			boxes.add(new Box());
		}
		return boxes;
	}

	static class Box {
		List<Lens> lenses = new LinkedList<>();

		void remove(String label) {
			lenses.remove(new Lens(label));
		}

		void addOrReplace(Lens l) {
			for (Lens old : lenses) {
				if (old.equals(l)) {
					old.f = l.f;
					return;
				}
			}
			lenses.add(l);
		}
	}

	static class Lens {
		private String label;
		private int f;

		public Lens(String label) {
			this.label = label;
		}

		public Lens(String label, int f) {
			this(label);
			this.f = f;
		}

		@Override
		public int hashCode() {
			return Objects.hash(label);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Lens other = (Lens) obj;
			return Objects.equals(label, other.label);
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("[");
			builder.append(label);
			builder.append(" ");
			builder.append(f);
			builder.append("]");
			return builder.toString();
		}

	}
}
