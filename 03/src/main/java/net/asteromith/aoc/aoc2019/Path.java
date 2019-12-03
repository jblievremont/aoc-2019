package net.asteromith.aoc.aoc2019;

import java.util.*;

public class Path {

  private final Set<Segment> segments;

  private Path(String steps) {
    this.segments = parse(steps);
  }

  static Path of(String steps) {
    return new Path(steps);
  }

  private static Set<Segment> parse(String steps) {
    Point current = Point.of(0, 0);
    Set<Segment> segments = new HashSet<>();
    for (String step: steps.split(",")) {
      String direction = step.substring(0, 1);
      int length = Integer.parseInt(step.substring(1), 10);
      Point next = current.translate(direction, length);
      segments.add(Segment.of(current, next));
      current = next;
    }
    return Collections.unmodifiableSet(segments);
  }

  Collection<Segment> getSegments() {
    return segments;
  }

  SortedSet<Point> crossings(Path other) {
    TreeSet<Point> intersections = new TreeSet<>();
    for (Segment thisSegment: this.segments) {
      for (Segment otherSegment: other.segments) {
        Optional<Point> intersect = thisSegment.intersect(otherSegment);
        intersect.ifPresent(intersections::add);
      }
    }
    return intersections;
  }

  int minCrossing(Path other) {
    Optional<Point> min = crossings(other).stream().filter(p -> !p.equals(Point.of(0, 0))).findFirst();
    if (min.isEmpty()) {
      throw new IllegalStateException();
    } else {
      return min.get().manhattanDistanceToOrigin();
    }
  }
}
