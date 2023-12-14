package aoc.year2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import aoc.Utils;

public class Day7b {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day7b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input7.txt");

		long result = process(lines);

		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {

		List<Hand> hands = loadHands(lines);
		System.out.println(hands);

		Collections.sort(hands);
		System.out.println();
		System.out.println(hands);

		long sum = 0;
		int rank = 0;
		for (Hand h : hands) {
			rank++;
			System.out.println("rank:" + rank + " bid:" + h.bid);
			sum += rank * h.bid;
		}

		return sum;
	}

	private List<Hand> loadHands(List<String> lines) {
		List<Hand> h = new ArrayList<>();
		for (String line : lines) {
			h.add(new Hand(line));
		}
		return h;
	}

	enum Type {
		ONE, PAIR, TWO_PAIR, THREE, FULL, FOUR, FIVE
	}

	class Hand implements Comparable<Hand> {

		char[] cards;
		long bid;
		Map<Character, Integer> amount = new HashMap<>();
		Type t;

		public Hand(String line) {
			cards = line.split("\\s")[0].toCharArray();
			bid = Long.valueOf(line.split("\\s")[1]);
			types();
		}

		public void types() {
			for (char c : cards) {
				if (!amount.containsKey(c)) {
					amount.put(c, 1);
				} else {
					amount.get(c);
					amount.replace(c, amount.get(c) + 1);
				}
			}

			// replace jocker cards
			if (amount.containsKey('J') && amount.get('J') < 5) {
				int j = amount.remove('J');
				char m = findMax();
				amount.replace(m, amount.get(m) + j);
			}

			t = checkType();
		}

		private char findMax() {
			Character c = null;
			int max = 0;
			for (Entry<Character, Integer> e : amount.entrySet()) {
				if (e.getValue() > max) {
					c = e.getKey();
					max = e.getValue();
				}
			}
			if (c == null) {
				System.out.println(this);
			}
			return c;
		}

		public Type checkType() {
			if (amount.values().contains(5)) {
				return Type.FIVE;
			}
			if (amount.values().contains(4)) {
				return Type.FOUR;
			}
			if (amount.values().contains(3)) {
				if (amount.values().contains(2)) {
					return Type.FULL;
				} else {
					return Type.THREE;
				}
			}
			if (amount.values().contains(2)) {
				if (amount.size() == 3) {
					return Type.TWO_PAIR;
				} else {
					return Type.PAIR;
				}
			}
			return Type.ONE;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("\nHand [cards=");
			builder.append(Arrays.toString(cards));
			builder.append(", bid=");
			builder.append(bid);
			builder.append(", t=");
			builder.append(t);
			builder.append("]");
			return builder.toString();
		}

		@Override
		public int compareTo(Hand o) {
			if (t != o.t) {
				return t.ordinal() - o.t.ordinal();
			}
			for (int i = 0; i < cards.length; i++) {
				int a = value(cards[i]);
				int b = value(o.cards[i]);
				if (a != b) {
					return a - b;
				}
			}
			return 0;
		}

		private int value(char c) {
			if (Character.isDigit(c)) {
				return Character.getNumericValue(c);
			}
			switch (c) {
			case 'T':
				return 10;
			case 'J':
				return 0;
			case 'Q':
				return 12;
			case 'K':
				return 13;
			case 'A':
				return 14;
			}
			throw new IllegalStateException("Unknown card:" + c);
		}

	}

}
