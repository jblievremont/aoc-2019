package net.asteromith.aoc.aoc2019;

import java.util.stream.IntStream;

public class Password {

  private final int value;

  /*
   * Input range is 272091-815432
   */
  private static final int MIN = 272091;
  private static final int MAX = 815432;

  private Password(int candidate) {
    this.value = candidate;
  }

  static Password of(int candidate) {
    return new Password(candidate);
  }

  boolean isInInputRange() {
    return value >= MIN && value <= MAX;
  }

  boolean hasSameAdjacentDigits() {
    boolean hasSameAdjacentDigits = false;
    char[] digits = toString().toCharArray();
    char previous = ' ';
    for(char d: digits) {
      hasSameAdjacentDigits = hasSameAdjacentDigits || (d == previous);
      previous = d;
    }
    return hasSameAdjacentDigits;
  }

  boolean hasIncreasingDigits() {
    boolean hasIncreasingDigits = true;
    char[] digits = toString().toCharArray();
    char previous = '0';
    for(char d: digits) {
      hasIncreasingDigits = hasIncreasingDigits && (d >= previous);
      previous = d;
    }
    return hasIncreasingDigits;
  }

  boolean isValid() {
    return isInInputRange() && hasSameAdjacentDigits() && hasIncreasingDigits();
  }

  static long countInRange() {
    return IntStream.range(MIN, MAX)
      .mapToObj(Password::of)
      .filter(Password::isValid)
      .count();
  }

  @Override
  public String toString() {
    return String.format("%06d", value);
  }
}
