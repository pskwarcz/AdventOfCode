package aoc.year2023.day16;

import java.util.HashSet;
import java.util.Set;

import aoc.year2023.Point;

public class Tile extends Point {
	char c;
	Set<Character> incomeDirection = new HashSet<>();

	public Tile(int x, int y, char c) {
		super(x, y);
		this.c = c;
	}

	public void add(char d) {
		incomeDirection.add(d);
	}

	public boolean visited(char c) {
		return incomeDirection.contains(c);
	}

	public Set<Character> next(char d) {
		Set<Character> n = new HashSet<>();
		switch (c) {
		case '.':
			n.add(d);
			break;
		case '|':
			switch (d) {
			case '>':
			case '<':
				n.add('^');
				n.add('v');
				break;
			default:
				n.add(d);
				break;
			}
			break;
		case '-':
			switch (d) {
			case '>':
			case '<':
				n.add(d);
				break;
			default:
				n.add('>');
				n.add('<');
				break;
			}
			break;
		case '/':
			switch (d) {
			case '>':
				n.add('^');
				break;
			case '<':
				n.add('v');
				break;
			case '^':
				n.add('>');
				break;
			case 'v':
				n.add('<');
				break;
			}
			break;
		case '\\':
			switch (d) {
			case '>':
				n.add('v');
				break;
			case '<':
				n.add('^');
				break;
			case '^':
				n.add('<');
				break;
			case 'v':
				n.add('>');
				break;
			}
			break;
		}
		return n;
	}

	public boolean wasVisited() {
		return !incomeDirection.isEmpty();
	}
}
