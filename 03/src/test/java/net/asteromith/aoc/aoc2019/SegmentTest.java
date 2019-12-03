package net.asteromith.aoc.aoc2019;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SegmentTest {

  @Test
  public void shouldIntersectInOneDirection() {
    assertThat(
      Segment.of(Point.of(-1, 0), Point.of(1, 0))
      .intersect(Segment.of(Point.of(0, -1), Point.of(0, 1)))).hasValue(Point.of(0, 0));
  }

  @Test
  public void shouldIntersectInOneDirectionReverse() {
    assertThat(
      Segment.of(Point.of(0, -1), Point.of(0, 1))
        .intersect(Segment.of(Point.of(-1, 0), Point.of(1, 0)))).hasValue(Point.of(0, 0));
  }

  @Test
  public void shouldIntersectInReverseDirection() {
    assertThat(
      Segment.of(Point.of(-1, 0), Point.of(1, 0))
        .intersect(Segment.of(Point.of(0, 1), Point.of(0, -1)))).hasValue(Point.of(0, 0));
  }

  @Test
  public void shouldIntersectInReverseDirectionReverse() {
    assertThat(
      Segment.of(Point.of(0, 1), Point.of(0, -1))
        .intersect(Segment.of(Point.of(-1, 0), Point.of(1, 0)))).hasValue(Point.of(0, 0));
  }

  @Test
  public void shouldNotIntersectBothVertical() {
    assertThat(
      Segment.of(Point.of(-1, 0), Point.of(1, 0))
        .intersect(Segment.of(Point.of(-1, 1), Point.of(1, 1)))).isEmpty();
  }

  @Test
  public void shouldNotIntersectBothHorizontal() {
    assertThat(
      Segment.of(Point.of(0, -1), Point.of(0, 1))
        .intersect(Segment.of(Point.of(1, -1), Point.of(1, 1)))).isEmpty();
  }
}
