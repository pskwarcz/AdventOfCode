package aoc.year2022;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Monkey {

	static Map<String, Monkey> monkeys = new HashMap<>();
	static Monkey root;
	static Monkey humn;

	final String name;

	final String m1;
	final String m2;

	private Monkey parent;

	String operation;

	private Long number;

	Monkey(String line) {
		name = line.split(":")[0];

		String[] task = line.split(":")[1].split(" ");
		if (task.length > 2) {
			m1 = task[1];
			operation = task[2];
			m2 = task[3];
			number = null;
		} else {
			number = Long.parseLong(task[1]);
			operation = null;
			m1 = null;
			m2 = null;
		}
		monkeys.put(name, this);
		if ("root".equals(name)) {
			root = this;
		}
	}

	public void setNumber(long number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monkey other = (Monkey) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Monkey [name=" + name + ", m1=" + m1 + ", m2=" + m2 + ", operation=" + operation + ", number=" + number
				+ "]";
	}

	public long getNumber() {
		if (number != null) {
			System.out.println(name + " = " + number + " (original)");
			return number;
		}

		long a = getNumber(m1);
		long b = getNumber(m2);
		long rsp = performOperation(a, b);
		System.out.println(name + " = " + m1 + " " + operation + " " + m2 + " = ?");
		System.out.println(name + " = " + rsp);
		return rsp;
	}

	private long performOperation(long a, long b) {
		switch (operation) {
		case "+":
			return a + b;
		case "-":
			return a - b;
		case "*":
			return a * b;
		case "/":
			return a / b;
		case "=":
			System.out.println(a + " ?= " + b);
			return b - a;
		default:
			throw new UnsupportedOperationException("operation: [" + operation + "]");
		}
	}

	private long getNumber(String other) {
		return monkeys.get(other).getNumber();
	}

	public Monkey getParent() {
		return parent;
	}

	public long getResult() {
		long result = parent.getResult(name);
		System.out.println("Result for " + name + " = " + result);
		return result;
	}

	private long getResult(String x) {
		if (number != null) {
			System.err.println("ShortCut: " + name + " = " + number);
			return number;
		}

		long other;
		if (x.equals(m1)) {
			other = getNumber(m2);
		} else {
			other = getNumber(m1);
		}

		if (operation.equals("=")) {
			System.out.println("root yelds!: " + x + " = " + other);
			return other;
		}

		System.out.println(x + "=?  " + m1 + " " + operation + " " + m2 + " = " + parent.name);
		long r = getResult();

		long rsp = performReverseOperation(r, other, x.equals(m1));

		return rsp;
	}

	// a # b = c
	private long performReverseOperation(long c, long other, boolean xFirst) {
		switch (operation) {
		case "-":
			if (xFirst) {
				return c + other;
			} else {
				return other - c;
			}
		case "+":
			return c - other;
		case "/":
			if (xFirst) {
				return c * other;
			} else {
				return other / c;
			}
		case "*":
			return c / other;
		default:
			throw new UnsupportedOperationException("operation: [" + operation + "]");
		}
	}

	public void setParent(Monkey m) {
		if (parent != null) {
			System.out.println(this + " already has parent: " + parent);
		}
		this.parent = m;

	}

}
