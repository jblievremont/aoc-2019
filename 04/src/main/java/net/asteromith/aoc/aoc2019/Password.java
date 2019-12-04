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

  boolean meetsCriteria() {
    boolean atLeastOnePair = false;
    char previous = '0';
    int countSame = 0;
    for(char current: toString().toCharArray()) {
      if(current < previous) {
        return false;
      } else if (current == previous) {
        countSame ++;
      } else {
        atLeastOnePair = atLeastOnePair || countSame == 2;
        countSame = 1;
      }
      previous = current;
    }

    return atLeastOnePair || countSame == 2;
  }

  boolean isValid() {
    return isInInputRange() && meetsCriteria();
  }

  static long countInRange() {
    return IntStream.range(MIN, MAX + 1)
      .mapToObj(Password::of)
      //.peek(p -> System.out.printf("%s: %s%n", p, p.isValid()))
      .filter(Password::isValid)
      .count();
  }

  @Override
  public String toString() {
    return String.format("%06d", value);
  }
}
