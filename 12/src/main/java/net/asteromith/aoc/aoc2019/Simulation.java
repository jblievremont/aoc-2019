package net.asteromith.aoc.aoc2019;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Simulation {

  final int step;
  final MoonState[] moonStates;

  private Simulation(int step, MoonState[] moonStates) {
    this.step = step;
    this.moonStates = moonStates;
  }

  static Simulation initial(MoonState... moonStates) {
    return new Simulation(0, moonStates);
  }

  Simulation nextStep() {
    MoonState[] influenced = new MoonState[moonStates.length];
    int i = 0;
    for (MoonState state: moonStates) {
      influenced[i] = state;
      int j = 0;
      for(MoonState other: moonStates) {
        if(j != i) {
          influenced[i] = influenced[i].influencedBy(other);
        }
        j ++;
      }
      i ++;
    }
    MoonState[] moved = new MoonState[influenced.length];
    i = 0;
    for(MoonState inf: influenced) {
      moved[i] = inf.move();
      i ++;
    }
    return new Simulation(step + 1, moved);
  }

  int totalEnergy() {
    return Stream.of(moonStates).mapToInt(MoonState::energy).sum();
  }
}
