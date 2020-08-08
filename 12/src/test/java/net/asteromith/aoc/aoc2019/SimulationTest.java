package net.asteromith.aoc.aoc2019;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimulationTest {

  @Test
  void shouldSimulateSample() {

/*
Initial state
<x=-1, y=0, z=2>
<x=2, y=-10, z=-7>
<x=4, y=-8, z=8>
<x=3, y=5, z=-1>
*/
    Simulation initial = Simulation.initial(
            MoonState.initial(-1, 0, 2),
            MoonState.initial(2, -10, -7),
            MoonState.initial(4, -8, 8),
            MoonState.initial(3, 5, -1)
    );

/*
After 1 step:
pos=<x= 2, y=-1, z= 1>, vel=<x= 3, y=-1, z=-1>
pos=<x= 3, y=-7, z=-4>, vel=<x= 1, y= 3, z= 3>
pos=<x= 1, y=-7, z= 5>, vel=<x=-3, y= 1, z=-3>
pos=<x= 2, y= 2, z= 0>, vel=<x=-1, y=-3, z= 1>
*/
    Simulation step1 = initial.nextStep();

    assertThat(step1.moonStates).containsExactly(
            MoonState.of(2, -1, 1, 3, -1, -1),
            MoonState.of(3, -7, -4, 1, 3, 3),
            MoonState.of(1, -7, 5, -3, 1, -3),
            MoonState.of(2, 2, 0, -1, -3, 1)
    );

/*
After 2 steps:
pos=<x= 5, y=-3, z=-1>, vel=<x= 3, y=-2, z=-2>
pos=<x= 1, y=-2, z= 2>, vel=<x=-2, y= 5, z= 6>
pos=<x= 1, y=-4, z=-1>, vel=<x= 0, y= 3, z=-6>
pos=<x= 1, y=-4, z= 2>, vel=<x=-1, y=-6, z= 2>
*/
    Simulation step2 = step1.nextStep();

    assertThat(step2.moonStates).containsExactly(
            MoonState.of(5, -3, -1, 3, -2, -2),
            MoonState.of(1, -2, 2, -2, 5, 6),
            MoonState.of(1, -4, -1, 0, 3, -6),
            MoonState.of(1, -4, 2, -1, -6, 2)
    );

/*
After 3 steps:
pos=<x= 5, y=-6, z=-1>, vel=<x= 0, y=-3, z= 0>
pos=<x= 0, y= 0, z= 6>, vel=<x=-1, y= 2, z= 4>
pos=<x= 2, y= 1, z=-5>, vel=<x= 1, y= 5, z=-4>
pos=<x= 1, y=-8, z= 2>, vel=<x= 0, y=-4, z= 0>
*/
    Simulation step3 = step2.nextStep();

    assertThat(step3.moonStates).containsExactly(
            MoonState.of(5, -6, -1, 0, -3, 0),
            MoonState.of(0, 0, 6, -1, 2, 4),
            MoonState.of(2, 1, -5, 1, 5, -4),
            MoonState.of(1, -8, 2, 0, -4, 0)
    );

/*
After 4 steps:
pos=<x= 2, y=-8, z= 0>, vel=<x=-3, y=-2, z= 1>
pos=<x= 2, y= 1, z= 7>, vel=<x= 2, y= 1, z= 1>
pos=<x= 2, y= 3, z=-6>, vel=<x= 0, y= 2, z=-1>
pos=<x= 2, y=-9, z= 1>, vel=<x= 1, y=-1, z=-1>
*/
    Simulation step4 = step3.nextStep();

    assertThat(step4.moonStates).containsExactly(
            MoonState.of(2, -8, 0, -3, -2, 1),
            MoonState.of(2, 1, 7, 2, 1, 1),
            MoonState.of(2, 3, -6, 0, 2, -1),
            MoonState.of(2, -9, 1, 1, -1, -1)
    );

/*
After 5 steps:
pos=<x=-1, y=-9, z= 2>, vel=<x=-3, y=-1, z= 2>
pos=<x= 4, y= 1, z= 5>, vel=<x= 2, y= 0, z=-2>
pos=<x= 2, y= 2, z=-4>, vel=<x= 0, y=-1, z= 2>
pos=<x= 3, y=-7, z=-1>, vel=<x= 1, y= 2, z=-2>
*/
    Simulation step5 = step4.nextStep();

    assertThat(step5.moonStates).containsExactly(
            MoonState.of(-1, -9, 2, -3, -1, 2),
            MoonState.of(4, 1, 5, 2, 0, -2),
            MoonState.of(2, 2, -4, 0, -1, 2),
            MoonState.of(3, -7, -1, 1, 2, -2)
    );

/*
After 6 steps:
pos=<x=-1, y=-7, z= 3>, vel=<x= 0, y= 2, z= 1>
pos=<x= 3, y= 0, z= 0>, vel=<x=-1, y=-1, z=-5>
pos=<x= 3, y=-2, z= 1>, vel=<x= 1, y=-4, z= 5>
pos=<x= 3, y=-4, z=-2>, vel=<x= 0, y= 3, z=-1>
*/
    Simulation step6 = step5.nextStep();

    assertThat(step6.moonStates).containsExactly(
            MoonState.of(-1, -7, 3, 0, 2, 1),
            MoonState.of(3, 0, 0, -1, -1, -5),
            MoonState.of(3, -2, 1, 1, -4, 5),
            MoonState.of(3, -4, -2, 0, 3, -1)
    );

/*
After 7 steps:
pos=<x= 2, y=-2, z= 1>, vel=<x= 3, y= 5, z=-2>
pos=<x= 1, y=-4, z=-4>, vel=<x=-2, y=-4, z=-4>
pos=<x= 3, y=-7, z= 5>, vel=<x= 0, y=-5, z= 4>
pos=<x= 2, y= 0, z= 0>, vel=<x=-1, y= 4, z= 2>
*/
    Simulation step7 = step6.nextStep();

    assertThat(step7.moonStates).containsExactly(
            MoonState.of(2, -2, 1, 3, 5, -2),
            MoonState.of(1, -4, -4, -2, -4, -4),
            MoonState.of(3, -7, 5, 0, -5, 4),
            MoonState.of(2, 0, 0, -1, 4, 2)
    );

/*

After 8 steps:
pos=<x= 5, y= 2, z=-2>, vel=<x= 3, y= 4, z=-3>
pos=<x= 2, y=-7, z=-5>, vel=<x= 1, y=-3, z=-1>
pos=<x= 0, y=-9, z= 6>, vel=<x=-3, y=-2, z= 1>
pos=<x= 1, y= 1, z= 3>, vel=<x=-1, y= 1, z= 3>
*/
    Simulation step8 = step7.nextStep();

    assertThat(step8.moonStates).containsExactly(
            MoonState.of(5, 2, -2, 3, 4, -3),
            MoonState.of(2, -7, -5, 1, -3, -1),
            MoonState.of(0, -9, 6, -3, -2, 1),
            MoonState.of(1, 1, 3, -1, 1, 3)
    );

/*

After 9 steps:
pos=<x= 5, y= 3, z=-4>, vel=<x= 0, y= 1, z=-2>
pos=<x= 2, y=-9, z=-3>, vel=<x= 0, y=-2, z= 2>
pos=<x= 0, y=-8, z= 4>, vel=<x= 0, y= 1, z=-2>
pos=<x= 1, y= 1, z= 5>, vel=<x= 0, y= 0, z= 2>
*/
    Simulation step9 = step8.nextStep();

    assertThat(step9.moonStates).containsExactly(
            MoonState.of(5, 3, -4, 0, 1, -2),
            MoonState.of(2, -9, -3, 0, -2, 2),
            MoonState.of(0, -8, 4, 0, 1, -2),
            MoonState.of(1, 1, 5, 0, 0, 2)
    );

/*

After 10 steps:
pos=<x= 2, y= 1, z=-3>, vel=<x=-3, y=-2, z= 1>
pos=<x= 1, y=-8, z= 0>, vel=<x=-1, y= 1, z= 3>
pos=<x= 3, y=-6, z= 1>, vel=<x= 3, y= 2, z=-3>
pos=<x= 2, y= 0, z= 4>, vel=<x= 1, y=-1, z=-1>
*/
    Simulation step10 = step9.nextStep();

    assertThat(step10.moonStates).containsExactly(
            MoonState.of(2, 1, -3, -3, -2, 1),
            MoonState.of(1, -8, 0, -1, 1, 3),
            MoonState.of(3, -6, 1, 3, 2, -3),
            MoonState.of(2, 0, 4, 1, -1, -1)
    );

    assertThat(step10.totalEnergy()).isEqualTo(179);
  }
}