package aoc.year2022;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import aoc.Utils;

public class Day20 {

	static Element firstElement;

	class Element {

		long v;
		Long nMove;

		boolean isFirst;

		Element left;
		Element right;

		@Override
		public String toString() {
			String l = left == null ? null : "" + left.v;
			String r = right == null ? null : "" + right.v;
			return "[" + l + "<-(" + v + ")->" + r + "]";
		}

		Element(long v) {
			this.v = v * DECRYPTION_KEY;
		}

		public void move() {

			if (nMove == null) {
				nMove = v % (elements.size() - 1);
			}
			int n = nMove.intValue();

			if (n == 0) {
				return;
			}

			if (n < 0) {
				Element l = this.right;
				remove();
				Element e = l.getLeft(-n);
				addAfter(e);
				return;
			}

			if (n > 0) {
				Element l = this.left;
				remove();
				Element e = l.getRight(n);
				addAfter(e);
				return;
			}

		}

		private void addAfter(Element e) {
			left = e;
			right = e.right;
			left.right = this;
			right.left = this;

		}

		private void remove() {
			if (isFirst) {
				right.isFirst = true;
				firstElement = right;
				isFirst = false;
			}
			left.right = right;
			right.left = left;
			left = null;
			right = null;
		}

		public Element getRight(long n) {
			Element e = this;
			for (long i = 0; i < n; i++) {
				e = e.right;
			}
			return e;
		}

		private Element getLeft(long n) {
			Element e = this;
			for (long i = 0; i <= n; i++) {
				e = e.left;
			}
			return e;
		}

	}

	static final long DECRYPTION_KEY = 811589153;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		List<String> lines = Utils.readFile("/aoc/year2022/input20");

		long result = new Day20().start(lines);

		System.out.println("RESULT:" + result);
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("Time[ms]: " + duration);
	}

	List<Element> elements = new ArrayList<>();

	public long start(List<String> lines) {

		Iterator<String> it = lines.iterator();

		firstElement = new Element(Integer.parseInt(it.next()));
		firstElement.isFirst = true;

		elements.add(firstElement);

		Element last = firstElement;

		Element zero = null;

		while (it.hasNext()) {
			Element e = new Element(Integer.parseInt(it.next()));
			elements.add(e);
			last.right = e;
			e.left = last;
			last = e;
			if (e.v == 0) {
				zero = e;
			}
		}
		last.right = firstElement;
		firstElement.left = last;

		// printElements();
		for (int i = 0; i < 10; i++) {
			for (Element v : elements) {
				v.move();
			}
		}
		// printElements();

		System.out.println(elements.size());

		System.out.println("zero: " + zero);

		long sum = 0;

		Element e = zero.getRight(1000);
		System.out.println(e);
		sum += e.v;

		e = e.getRight(1000);
		System.out.println(e);
		sum += e.v;

		e = e.getRight(1000);
		System.out.println(e);
		sum += e.v;

		return sum;
	}

	private void printElements() {
		Element e = firstElement;
		do {
			System.out.print(e);
			e = e.right;
		} while (!e.isFirst);
		System.out.println();

	}

}
