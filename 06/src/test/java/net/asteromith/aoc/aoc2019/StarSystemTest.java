package net.asteromith.aoc.aoc2019;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StarSystemTest {

  @Test
  public void parseAndComputeOrbitLength() {
    StarSystem underTest = StarSystem.of(new ByteArrayInputStream((
            "COM)B\n" +
                    "B)C\n" +
                    "C)D\n" +
                    "D)E\n" +
                    "E)F\n" +
                    "B)G\n" +
                    "G)H\n" +
                    "D)I\n" +
                    "E)J\n" +
                    "J)K\n" +
                    "K)L\n").getBytes(StandardCharsets.UTF_8))
    );
    assertThat(underTest.bodies.size()).isEqualTo(12);
    assertThat(underTest.totalNumberOfOrbits()).isEqualTo(42);
  }
}
