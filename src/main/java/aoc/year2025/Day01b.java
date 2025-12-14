package aoc.year2025;

import aoc.Utils;

import java.util.List;

public class Day01b {

    private static final int INITIAL = 50;

    void main() {
        long start = System.currentTimeMillis();
        new Day01b().start();
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("\nTime[ms]: " + duration);
    }

    private void start() {
        List<String> lines = Utils.readFile("/aoc/year2025/input01");

        int result = process(lines);

        System.out.println("\nresult: " + result);
    }


    int process(List<String> lines) {
        Wheel w = new Wheel(INITIAL);
        for (String l : lines) {
            int s = getSpin(l);
            w.spin(s);
        }
        return w.countZero;
    }

    int getSpin(String line) {
        char direction = line.charAt(0);
        int rotation = Integer.parseInt(line.substring(1));

        return switch (direction) {
            case 'L' -> -rotation;
            case 'R' -> rotation;
            default -> 0;
        };
    }

}

class Wheel {
    static final int MAX = 100;
    int current;
    int countZero = 0;

    Wheel(int current) {
        this.current = current;
    }

    Wheel spin(int spin) {
        if (spin == 0) {
            return this;
        }
        int target = current + spin;

        if (spin > 0) {
            countZero += target / MAX;
        } else if (target <= 0) {
            countZero += (-target / MAX) + (current > 0 ? 1 : 0);
        }

        current = (target % MAX + MAX) % MAX;
        return this;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "current=" + current +
                ", countZero=" + countZero +
                '}';
    }

    int get() {
        return countZero;
    }

}