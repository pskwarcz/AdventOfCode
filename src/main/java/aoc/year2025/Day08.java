package aoc.year2025;

import aoc.Utils;

import java.util.*;

public class Day08 {

    static long start = System.currentTimeMillis();

    static private void logTime() {
        long end = System.currentTimeMillis();
        long duration = end - start;
        IO.println("\nTime[ms]: " + duration);
    }

    static void main() {
        new Day08(1000).start();
        logTime();
    }

    final int MAX_CONNECTIONS;

    Day08(int connections) {
        this.MAX_CONNECTIONS = connections;
    }

    void start() {
        List<String> lines = Utils.readFile("/aoc/year2025/input08");
        long result = process(lines);
        IO.println("\nresult: " + result);
    }

    static List<Box> boxes;
    static Map<Long, Set<Box>> distances = new TreeMap<>();

    long process(List<String> lines) {
        boxes = lines.stream().map(Box::new).toList();
        logTime();

        for (Box a : boxes) {
            for (Box b : boxes) {
                if (a != b) {
                    a.calculateDistanceTo(b);
                }
            }
        }

        logTime();

        // distances is ordered by key=distance, so we start with the shortest ones
        distances.entrySet().stream().limit(MAX_CONNECTIONS).forEach(es -> connectBoxes(es.getValue()));

        logTime();

        //IO.println();
        // boxes.stream().forEach(b -> IO.println(b + "\t c.size:" + b.cluster.size() + "\t hash: " + b.cluster.hashCode() + " " + b.cluster));
        return boxes.stream().map(b -> b.cluster).filter(c -> !c.isEmpty()).distinct().mapToInt(Set::size)
                .map(i -> -i) // flip sign to achieve reverse order (IntStream has only method for asc sort)
                .sorted()
                .map(i -> -i) //flip back
                .limit(3) // get 3 biggest circuits
                .reduce((x, y) -> x * y)
                .getAsInt();
    }

    private void connectBoxes(Set<Box> value) {
        if (value.size() != 2) {
            throw new IllegalArgumentException("Unexpected size " + value);
        }
        Iterator<Box> it = value.iterator();
        Box a = it.next();
        Box b = it.next();

        a.cluster.addAll(value);
        a.cluster.addAll(b.cluster);

        for (Box box : b.cluster) {
            box.cluster = a.cluster;
        }
        b.cluster = a.cluster;
    }

    static class Box {
        int x;
        int y;
        int z;

        Set<Box> cluster = new HashSet<>();

        Map<Box, Long> distanceMap = new HashMap<>();

        Box(String line) {
            x = Integer.parseInt(line.split(",")[0]);
            y = Integer.parseInt(line.split(",")[1]);
            z = Integer.parseInt(line.split(",")[2]);
        }

        void setDistance(Box to, long value) {
            distanceMap.put(to, value);
        }

        public void calculateDistanceTo(Box b) {
            // distance will be used just for comparing, so we do not need to calculate root
            long distance = distanceMap.computeIfAbsent(b, _ -> (long) Math.pow(b.x - x, 2) + (long) Math.pow(b.y - y, 2) + (long) Math.pow(b.z - z, 2));
            b.setDistance(this, distance);
            Set<Box> boxes = distances.computeIfAbsent(distance, _ -> new HashSet<>());
            boxes.add(this);
            boxes.add(b);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Box box = (Box) o;
            return x == box.x && y == box.y && z == box.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }

        @Override
        public String toString() {
            return "(" + +x + "," + y + "," + z + ')';
        }
    }

}
