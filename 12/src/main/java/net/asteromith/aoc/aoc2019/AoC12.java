package net.asteromith.aoc.aoc2019;

public class AoC12 {

  public static void main(String[] args) {
    /*
    Input

    <x=12, y=0, z=-15>
    <x=-8, y=-5, z=-10>
    <x=7, y=-17, z=1>
    <x=2, y=-11, z=-6>

     */
    Simulation sim = Simulation.initial(
            MoonState.initial(12, 0, -15),
            MoonState.initial(-8, -5, -10),
            MoonState.initial(7, -17, 1),
            MoonState.initial(2, -11, -6)
    );

    for (int i = 0; i < 1000; i ++) {
      sim = sim.nextStep();
    }

    System.out.println(sim.totalEnergy());
  }
}
