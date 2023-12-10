package aoc.year2023.day10;

import java.util.Objects;

import aoc.year2023.Point;

public class Tile extends Point {
	final public char c;

	public Tile(int x, int y, char c) {
		super(x, y);
		this.c = c;
	}

	public Tile(Point p, char c) {
		super(p);
		this.c = c;
	}

	public boolean isS() {
		return c == 'S';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(c);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		return c == other.c;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		builder.append(x);
		builder.append(", ");
		builder.append(y);
		builder.append(")=");
		builder.append(c);
		return builder.toString();
	}

	public boolean accesibleFromS() {
		switch (c) {
		case 'S':
		case '|':
		case '7':
		case 'F':
			return true;
		default:
			return false;
		}
	}

	public boolean accesibleFromN() {
		switch (c) {
		case 'S':
		case '|':
		case 'L':
		case 'J':
			return true;
		default:
			return false;
		}
	}

	public boolean accesibleFromE() {
		switch (c) {
		case 'S':
		case '-':
		case 'L':
		case 'F':
			return true;
		default:
			return false;
		}
	}

	public boolean accesibleFromW() {
		switch (c) {
		case 'S':
		case '-':
		case 'J':
		case '7':
			return true;
		default:
			return false;
		}
	}

	public char getDirection(char d) {
		switch (c) {
		case 'F':
			return d == 'N' ? 'E' : 'S';
		case '|':
			return d;
		case 'L':
			return d == 'S' ? 'E' : 'N';
		case '-':
			return d;
		case 'J':
			return d == 'S' ? 'W' : 'N';
		case '7':
			return d == 'N' ? 'W' : 'S';
		}
		throw new IllegalStateException("Dont know where to go.");
	}

}
