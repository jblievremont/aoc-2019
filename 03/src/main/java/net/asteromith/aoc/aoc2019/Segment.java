package net.asteromith.aoc.aoc2019;

import java.util.Optional;

class Segment {
  private Point start;
  private Point end;

  private Segment(Point start, Point end) {
    this.start = start;
    this.end = end;
  }

  static Segment of(Point start, Point end) {
    return new Segment(start, end);
  }

  Optional<Point> intersect(Segment other) {
    if (this.isHorizontal() && other.isHorizontal()) {
      // Both horizontal => no single crossing
      return Optional.empty();
    } else if (this.isVertical() && other.isVertical()) {
      // Both vertical => no single crossing
      return Optional.empty();
    } else if (this.isVertical()) {
      // Other segment is horizontal
      if (other.start.x > this.start.x && other.end.x > this.start.x) {
        // Other segment is too far right
        return Optional.empty();
      } else if (other.start.x < this.start.x && other.end.x < this.start.x) {
        // Other segment is too far left
        return Optional.empty();
      } else {
        if (other.start.y < this.start.y && other.start.y < this.end.y) {
          // Other is too low
          return Optional.empty();
        } else if (other.start.y > this.end.y && other.start.y > this.start.y) {
          // Other is too high
          return Optional.empty();
        } else {
          // Bingo!
          return Optional.of(Point.of(this.start.x, other.start.y));
        }
      }
    } else { // this.isHorizontal()
      // Other segment is vertical
      if (other.start.y > this.start.y && other.end.y > this.start.y) {
        // Other segment is too high
        return Optional.empty();
      } else if (other.start.y < this.start.y && other.end.y < this.start.y) {
        // Other segment is too low
        return Optional.empty();
      } else {
        if (other.start.x < this.start.x && other.start.x < this.end.x) {
          // Other is too far left
          return Optional.empty();
        } else if (other.start.x > this.start.x && other.start.x > this.end.x) {
          // Other is too far right
          return Optional.empty();
        } else {
          // Bingo!
          return Optional.of(Point.of(other.start.x, this.start.y));
        }
      }
    }
  }

  boolean isVertical() {
    return this.start.x == this.end.x;
  }

  boolean isHorizontal() {
    return this.start.y == this.end.y;
  }
}
