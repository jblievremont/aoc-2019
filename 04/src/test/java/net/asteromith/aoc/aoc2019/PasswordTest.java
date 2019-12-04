package net.asteromith.aoc.aoc2019;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordTest {

  @Test
  public void shouldBeLowerThanInputRange() {
    assertThat(Password.of(123456).isInInputRange()).isFalse();
  }

  @Test
  public void shouldBeHigherThanInputRange() {
    assertThat(Password.of(999999).isInInputRange()).isFalse();
  }

  @Test
  public void shouldBeInInputRange() {
    assertThat(Password.of(555555).isInInputRange()).isTrue();
  }

  @Test
  public void shouldNotHaveSameAdjacentDigits() {
    assertThat(Password.of(123456).hasSameAdjacentDigits()).isFalse();
  }

  @Test
  public void shouldNotHave2SameAdjacentDigits() {
    assertThat(Password.of(112456).hasSameAdjacentDigits()).isTrue();
  }

  @Test
  public void shouldNotHave3SameAdjacentDigits() {
    assertThat(Password.of(122256).hasSameAdjacentDigits()).isTrue();
  }

  @Test
  public void shouldFormatToStringZeroes() {
    assertThat(Password.of(0).toString()).isEqualTo("000000");
  }

  @Test
  public void shouldFormatToString() {
    assertThat(Password.of(999999).toString()).isEqualTo("999999");
  }

  @Test
  public void shouldNotHaveIncreasingDigits() {
    assertThat(Password.of(123450).hasIncreasingDigits()).isFalse();
  }

  @Test
  public void shouldHaveIncreasingDigits() {
    assertThat(Password.of(112456).hasIncreasingDigits()).isTrue();
  }

  @Test
  public void shouldCountPasswordsInRange() {
    assertThat(Password.countInRange()).isEqualTo(931L);
  }
}
