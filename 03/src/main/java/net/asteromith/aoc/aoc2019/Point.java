package net.asteromith.aoc.aoc2019;

import java.util.Objects;

class Point implements Comparable<Point> {
  int x;
  int y;

  private Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  static Point of(int x, int y) {
    return new Point(x, y);
  }

  Point translate(String direction, int length) {
    switch(direction) {
      case "U":
        return of(x, y + length);
      case "D":
        return of(x, y - length);
      case "L":
        return of(x - length, y);
      case "R":
        return of(x + length, y);
      default:
        return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Point point = (Point) o;
    return x == point.x &&
      y == point.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  int manhattanDistanceToOrigin() {
    return Math.abs(x) + Math.abs(y);
  }

  @Override
  public int compareTo(Point o) {
    return Integer.compare(this.manhattanDistanceToOrigin(), o.manhattanDistanceToOrigin());
  }

  @Override
  public String toString() {
    return "Point{" +
      "x=" + x +
      ", y=" + y +
      '}';
  }

  int totalSteps(Path p1, Path p2) {
    return p1.stepsTo(this) + p2.stepsTo(this);
  }
}
