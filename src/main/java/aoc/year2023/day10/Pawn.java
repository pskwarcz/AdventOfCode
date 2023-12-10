package aoc.year2023.day10;

import aoc.year2023.Point;

public class Pawn {
	Map m;
	Tile p;
	Tile prev;
	char direction = '?';

	public Pawn(Map m) {
		this.m = m;
		p = m.s;
		prev = p;
	}

	private void moveTo(Point p) {
		moveTo(m.getTile(p));
	}

	private void moveTo(Tile t) {
		prev = p;
		System.out.println("going " + direction + " to " + t);
		p = t;
	}

	public void move() {
		if (p.isS()) {
			if (m.getN(p).accesibleFromS()) {
				direction = 'N';
				moveDirection();
				return;
			}

			if (m.getS(p).accesibleFromN()) {
				direction = 'S';
				moveDirection();
				return;
			}

			if (m.getE(p).accesibleFromE()) {
				direction = 'E';
				moveDirection();
				return;
			}

			if (m.getW(p).accesibleFromW()) {
				direction = 'W';
				moveDirection();
				return;
			}
			throw new IllegalStateException("No possible move");
		} else {
			direction = p.getDirection(direction);
			moveDirection();
		}
	}

	private void moveDirection() {
		switch (direction) {
		case 'N':
			moveTo(new Point(p.x, p.y - 1));
			break;
		case 'S':
			moveTo(new Point(p.x, p.y + 1));
			break;
		case 'E':
			moveTo(new Point(p.x + 1, p.y));
			break;
		case 'W':
			moveTo(new Point(p.x - 1, p.y));
			break;
		default:
			throw new IllegalStateException("Unknown direction: '" + direction + "' " + this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pawn at ");
		builder.append(p);
		return builder.toString();
	}

	public boolean isAtS() {
		return p.isS();
	}

}
