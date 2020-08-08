package net.asteromith.aoc.aoc2019;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoonStateTest {

  @Test
  void shouldInfluenceVelocity() {
    MoonState m1 = MoonState.initial(5, 4, 3);
    MoonState m2 = MoonState.initial(3, 4, 5);
    MoonState movedM1 = m1.influencedBy(m2).move();
    MoonState movedM2 = m2.influencedBy(m1).move();

    assertThat(movedM1).isEqualTo(MoonState.of(4, 4, 4, -1, 0, 1));
    assertThat(movedM2).isEqualTo(MoonState.of(4, 4, 4, 1, 0, -1));
  }
}
