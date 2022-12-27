package aoc.year2022;

enum Direction {
	RIGHT(0, '>'), DOWN(1, 'v'), LEFT(2, '<'), UP(3, '^');

	int number;
	char c;

	Direction(int number, char c) {
		this.number = number;
		this.c = c;
	}

	public static Direction fromChar(char c) {
		for (Direction d : Direction.values()) {
			if (d.c == c) {
				return d;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return String.valueOf(c);
	}

	public Direction nextToConsider() {
		switch (this) {
		case UP:
			return DOWN;
		case DOWN:
			return LEFT;
		case LEFT:
			return RIGHT;
		case RIGHT:
			return UP;
		default:
			return null;
		}

	}

}