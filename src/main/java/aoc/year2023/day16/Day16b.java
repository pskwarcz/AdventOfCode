package aoc.year2023.day16;

import java.util.List;
import java.util.stream.Stream;

public class Day16b extends Day16 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day16b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	@Override
	public long process(List<String> lines) {
		int result = getBeamConfigurations(lines).mapToInt(g -> {
			Cave c = new Cave(lines);
			c.startBeam(g.x, g.y, g.c);
			return c.countEnergized();
		}).max().getAsInt();

		return result;
	}

	public Stream<BeamConfig> getBeamConfigurations(List<String> lines) {
		Stream.Builder<BeamConfig> b = Stream.builder();

		int xMax = lines.get(0).length();
		int yMax = lines.size();
		for (int y = 0; y < yMax; y++) {
			b.add(new BeamConfig(0, y, '>'));
			b.add(new BeamConfig(xMax - 1, y, '<'));
		}
		for (int x = 0; x < xMax; x++) {
			b.add(new BeamConfig(x, 0, 'v'));
			b.add(new BeamConfig(x, yMax - 1, '^'));
		}
		return b.build();
	}

	class BeamConfig {
		int x;
		int y;
		char c;

		public BeamConfig(int x, int y, char c) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}

}
