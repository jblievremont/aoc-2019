package net.asteromith.aoc.aoc2019;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class AoC12 {

  public static void main(String[] args) {
    /*
    Input
    <x=12, y=0, z=-15>
    <x=-8, y=-5, z=-10>
    <x=7, y=-17, z=1>
    <x=2, y=-11, z=-6>

    Sample
    <x=-1, y=0, z=2>
    <x=2, y=-10, z=-7>
    <x=4, y=-8, z=8>
    <x=3, y=5, z=-1>
     */
    Map<Axis, Long> periodByAxis = new EnumMap<>(Axis.class);

    for (Axis axis: Axis.values()){

      Set<ComponentState> history = new HashSet<>();

      Simulation sim = Simulation.initial(
              /* */
              MoonState.initial(12, 0, -15),
              MoonState.initial(-8, -5, -10),
              MoonState.initial(7, -17, 1),
              MoonState.initial(2, -11, -6)
              /* */

              /*
              MoonState.initial(-1, 0, 2),
              MoonState.initial(2, -10, -7),
              MoonState.initial(4, -8, 8),
              MoonState.initial(3, 5, -1)
              */
      );

      long steps = 0L;
      boolean repeat = false;

      do {
        history.add(new ComponentState(axis, sim.moonStates));
        sim = sim.nextStep();
        steps += 1;
        repeat = history.contains(new ComponentState(axis, sim.moonStates));
      } while (!repeat);

      System.out.println(axis.toString() + ": " + steps);
      periodByAxis.put(axis, steps);
    }

    long commonPeriod = lcm(periodByAxis.values().toArray(new Long[0]));
    System.out.println("Overall: " + commonPeriod);
  }

  static class ComponentState {
    int[] positions;
    int[] velocities;

    ComponentState(Axis axis, MoonState[] state) {
      positions = Stream.of(state).mapToInt(axis.positionExtractor).toArray();
      velocities = Stream.of(state).mapToInt(axis.velocityExtractor).toArray();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ComponentState that = (ComponentState) o;
      return Arrays.equals(positions, that.positions) &&
              Arrays.equals(velocities, that.velocities);
    }

    @Override
    public int hashCode() {
      int result = Arrays.hashCode(positions);
      result = 31 * result + Arrays.hashCode(velocities);
      return result;
    }
  }

  enum Axis {
    X(m -> m.position.x, m -> m.velocity.vx),
    Y(m -> m.position.y, m -> m.velocity.vy),
    Z(m -> m.position.z, m -> m.velocity.vz);

    private final ToIntFunction<MoonState> positionExtractor;
    private final ToIntFunction<MoonState> velocityExtractor;

    Axis(ToIntFunction<MoonState> positionExtractor,
         ToIntFunction<MoonState> velocityExtractor) {
      this.positionExtractor = positionExtractor;
      this.velocityExtractor = velocityExtractor;
    }
  }

  /*
   * Credit for gcd/lcm implementation goes to
   * https://stackoverflow.com/a/40531215/806736
   */
  private static long gcd(long x, long y) {
    return (y == 0L) ? x : gcd(y, x % y);
  }

  public static long lcm(Long... numbers) {
    return Stream.of(numbers).reduce(1L, (x, y) -> x * (y / gcd(x, y)));
  }
}
