package net.asteromith.aoc.aoc2019;

import java.util.Objects;

import static java.lang.Math.abs;

public class MoonState {
  final Position position;
  final Velocity velocity;

  private MoonState(Position position, Velocity velocity) {
    this.position = position;
    this.velocity = velocity;
  }

  static MoonState initial(int x, int y, int z) {
    return new MoonState(Position.of(x, y, z), Velocity.initial());
  }

  static MoonState of(Position position, Velocity velocity) {
    return new MoonState(position, velocity);
  }

  static MoonState of(int x, int y, int z, int vx, int vy, int vz) {
    return of(Position.of(x, y, z), Velocity.of(vx, vy, vz));
  }

  MoonState influencedBy(MoonState other) {
    return new MoonState(position, velocity.add(Velocity.influence(this.position, other.position)));
  }

  MoonState move() {
    return new MoonState(position.move(velocity), velocity);
  }

  int energy() {
    return position.potentialEnergy() * velocity.kineticEnergy();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MoonState moonState = (MoonState) o;
    return position.equals(moonState.position) &&
            velocity.equals(moonState.velocity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, velocity);
  }

  @Override
  public String toString() {
    return "MoonState{" +
            "position=" + position +
            ", velocity=" + velocity +
            '}';
  }

  static class Position {
    final int x;
    final int y;
    final int z;

    private Position(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    static Position of(int x, int y, int z) {
      return new Position(x, y, z);
    }

    Position move(Velocity v) {
      return of(x + v.vx, y + v.vy ,z + v.vz);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Position position = (Position) o;
      return x == position.x &&
              y == position.y &&
              z == position.z;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
      return "Position{" +
              "x=" + x +
              ", y=" + y +
              ", z=" + z +
              '}';
    }

    public int potentialEnergy() {
      return abs(x) + abs(y) + abs(z);
    }
  }

  static class Velocity {
    final int vx;
    final int vy;
    final int vz;

    private Velocity(int vx, int vy, int vz) {
      this.vx = vx;
      this.vy = vy;
      this.vz = vz;
    }

    static Velocity of(int vx, int vy, int vz) {
      return new Velocity(vx, vy, vz);
    }

    static Velocity influence(Position p1, Position p2) {
      return of(influenceComponent(p1.x, p2.x), influenceComponent(p1.y, p2.y), influenceComponent(p1.z, p2.z));
    }

    static int influenceComponent(int c1, int c2) {
      // Not using Integer.compare(c1, c2) because spec does not mention -1/1 specifically
      //noinspection UseCompareMethod
      if (c1 < c2) {
        return 1;
      } else if (c1 > c2) {
        return -1;
      } else {
        return 0;
      }
    }

    static Velocity initial() {
      return of(0, 0, 0);
    }

    Velocity add(Velocity other) {
      return of(vx + other.vx, vy + other.vy, vz + other.vz);
    }

    int kineticEnergy() {
      return abs(vx) + abs(vy) + abs(vz);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Velocity velocity = (Velocity) o;
      return vx == velocity.vx &&
              vy == velocity.vy &&
              vz == velocity.vz;
    }

    @Override
    public int hashCode() {
      return Objects.hash(vx, vy, vz);
    }

    @Override
    public String toString() {
      return "Velocity{" +
              "vx=" + vx +
              ", vy=" + vy +
              ", vz=" + vz +
              '}';
    }
  }
}
