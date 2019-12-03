package net.asteromith.aoc.aoc2019;

import java.util.*;

public class Path {

  private final List<Instruction> instructions;
  private final Set<Segment> segments;

  private Path(String steps) {
    Point current = Point.of(0, 0);
    List<Instruction> insts = new ArrayList<>();
    Set<Segment> segs = new HashSet<>();
    for (String step: steps.split(",")) {
      String direction = step.substring(0, 1);
      int length = Integer.parseInt(step.substring(1), 10);
      insts.add(Instruction.of(direction, length));
      Point next = current.translate(direction, length);
      segs.add(Segment.of(current, current.translate(direction, length)));
      current = next;
    }
    this.instructions = Collections.unmodifiableList(insts);
    this.segments = Collections.unmodifiableSet(segs);
  }

  static Path of(String steps) {
    return new Path(steps);
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

  int manhattanMinCrossing(Path other) {
    Optional<Point> min = crossings(other).stream().filter(p -> !p.equals(Point.of(0, 0))).findFirst();
    if (min.isEmpty()) {
      throw new IllegalStateException();
    } else {
      return min.get().manhattanDistanceToOrigin();
    }
  }

  int stepsTo(Point p) {
    Point current = Point.of(0, 0);
    int steps = 0;
    for (Instruction inst: this.instructions) {
      for (int distanceMoved = 0; distanceMoved < inst.length; distanceMoved ++) {
        current = current.translate(inst.direction, 1);
        steps ++;
        if (current.equals(p)) {
          return steps;
        }
      }
    }
    return steps;
  }

  int totalStepsMinCrossing(Path other) {
    Set<Point> crossings = crossings(other);
    SortedSet<Point> stepsCrossings = new TreeSet<>((p1, p2) -> Integer.compare(p1.totalSteps(this, other), p2.totalSteps(this, other)));
    stepsCrossings.addAll(crossings);
    Optional<Point> min = stepsCrossings.stream().filter(p -> !p.equals(Point.of(0, 0))).findFirst();
    if (min.isEmpty()) {
      throw new IllegalStateException();
    } else {
      return min.get().totalSteps(this, other);
    }
  }
}
