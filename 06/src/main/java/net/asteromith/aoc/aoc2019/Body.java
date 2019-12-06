package net.asteromith.aoc.aoc2019;

import javax.annotation.Nullable;
import java.util.Objects;

public class Body {

  static final Body COM = Body.of("COM", null);

  final String name;
  final Body parent;
  final int orbitLength;

  private Body(String name, @Nullable Body parent) {
    this.name = name;
    this.parent = parent;
    this.orbitLength = (parent == null) ? 0 : (1 + parent.orbitLength);
  }

  int totalOrbitLength() {
    return orbitLength + (parent == null ? 0 : parent.totalOrbitLength());
  }

  static Body of(String name, @Nullable Body parent) {
    return new Body(name, parent);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Body body = (Body) o;
    return Objects.equals(name, body.name) &&
            Objects.equals(parent, body.parent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, parent);
  }
}
