package aoc.year2025;

import java.util.Iterator;
import java.util.Set;

public class Day08b extends Day08 {

    static void main() {
        new Day08b().start();
    }

    Day08b() {
        super(0);
    }

    int calculateResult() {
        Box first = boxes.getFirst();

        Iterator<Set<Box>> iterator = distances.values().iterator();
        Set<Box> boxes = null;
        while (first.cluster.size() != this.boxes.size()) {
            boxes = iterator.next();
            connectBoxes(boxes);
        }
        assert boxes != null;
        return boxes.stream().map(b -> b.x).reduce((x, y) -> x * y).orElseThrow();
    }


}
