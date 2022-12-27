package aoc.year2022;

import java.util.List;
import java.util.Objects;

public class Pawn {

	Direction facing = Direction.RIGHT;
	Tile currentPosition;
	Tile nextStep;
	Board b;

	public Pawn(Tile currentPosition, Board b) {
		this.currentPosition = currentPosition;
		this.b = b;
	}

	public Pawn(Tile currentPosition) {
		this.currentPosition = currentPosition;
	}

	@Override
	public String toString() {
		return currentPosition.getPosition() + " -> " + (nextStep != null ? nextStep.getPosition() : null);
	}

	public Tile considerNextStep(Direction facing) {
		nextStep = null;
		Direction considerDirection = facing;

		if (allAdjecetnFree()) {
			// System.out.println(currentPosition.getPosition() + " " + " alone");
			return nextStep;
		}

		for (int i = 0; i < 4; i++) {
			// System.out.println(currentPosition.getPosition() + " " + considerDirection +
			// " ?");

			if (areFreeSpaces(considerDirection)) {
				// System.out.println("OK");
				nextStep = nextStep(considerDirection);
				nextStep.candidates.add(this);
				break;
			}
			considerDirection = considerDirection.nextToConsider();
		}

		// System.out.println(this + " " + considerDirection);
		return nextStep;
	}

	private boolean allAdjecetnFree() {
		for (Point p : currentPosition.getPointsAround2D()) {
			if (!b.isFree(p)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(currentPosition);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pawn other = (Pawn) obj;
		return Objects.equals(currentPosition, other.currentPosition);
	}

	private Tile nextStep(Direction d) {
		// System.out.println("Want move " + d);
		Point p = currentPosition.getPosition().next(d);
		return b.getTile(p);
	}

	private boolean areFreeSpaces(Direction d) {
		Point p = currentPosition.getPosition().next(d);
		switch (d) {
		case DOWN:
		case UP:
			return isFreeSpaceLCR(p);
		case LEFT:
		case RIGHT:
			return isFreeSpaceUCD(p);
		default:
			return false;
		}
	}

	private boolean isFreeSpaceUCD(Point s) {
		long center = s.y;

		for (s.y = center - 1; s.y <= center + 1; s.y++) {
			if (!b.isFree(s)) {
				return false;
			}
		}
		return true;
	}

	private boolean isFreeSpaceLCR(Point s) {
		long center = s.x;

		for (s.x = center - 1; s.x <= center + 1; s.x++) {
			if (!b.isFree(s)) {
				return false;
			}
		}
		return true;
	}

	public void move(String i) {
		if ("L".equals(i)) {
			rotate(-1);
			return;
		}

		if ("R".equals(i)) {
			rotate(1);
			return;
		}

		int n = Integer.parseInt(i);
		moveForward(n);

	}

	private void moveForward(int n) {

		while (n-- > 0) {
			Tile next = currentPosition.getNext(facing);

			Direction newDirection = facing;

			if (next == null) {
				try {
					TileMapping tm = TileMapping.getNext(currentPosition, facing);
					next = tm.next;
					newDirection = tm.direction;

					System.out.println("FOUND POINT ON DIFFERENT EDGE: " + next + " " + newDirection);

				} catch (Exception e) {
					System.err.println("Next for " + currentPosition + " facing " + facing);
					throw e;
				}
			}

			if (next.isWall()) {
				// System.out.println("Hit the wall, Stopped");
				return;
			}

			facing = newDirection;

			// System.out.println("moving to: " + next);
			currentPosition = next;
			currentPosition.c = facing.c;
		}

	}

	private void rotate(int i) {
		int o = (4 + facing.ordinal() + i) % 4;
		facing = Direction.values()[o];
		currentPosition.c = facing.c;
	}

	public void moveIfNoCollision() {
		if (nextStep == null) {
			return;
		}

		List<Pawn> nextStepCandidates = nextStep.candidates;

		if (nextStepCandidates.contains(this) && nextStepCandidates.size() == 1) {
			currentPosition.c = '.';
			currentPosition = nextStep;
			currentPosition.c = '#';

		} else {
			for (Pawn p : nextStepCandidates) {
				p.nextStep = null;
			}
		}

		nextStepCandidates.clear();
	}

}