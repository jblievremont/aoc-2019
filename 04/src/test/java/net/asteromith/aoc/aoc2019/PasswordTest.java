package net.asteromith.aoc.aoc2019;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordTest {

  @ParameterizedTest(name = "{0}.meetsCriteria should be {1}")
  @CsvSource({
    "112345, true",
    "122345, true",
    "123345, true",
    "123445, true",
    "123455, true",
    "122225, false",
    "111122, true", // From puzzle
    "111111, false",
    "112233, true", // From puzzle
    "334566, true",
    "122223, false",
    "111123, false",
    "333455, true",
    "334555, true",
    "123456, false",
    "122256, false",
    "123336, false",
    "122222, false",
    "111222, false",
    "123444, false", // From puzzle
    "123789, false", // From puzzle
    "111112, false",
    "123450, false",
    "112340, false"
  })
  public void shouldMeetCriteria(int input, boolean expected) {
    assertThat(Password.of(input).meetsCriteria()).isEqualTo(expected);
  }

  @Test
  public void shouldCountPasswordsInRange() {
    assertThat(Password.countInRange()).isEqualTo(609L);
  }
}
