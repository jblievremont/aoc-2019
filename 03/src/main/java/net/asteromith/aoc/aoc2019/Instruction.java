package net.asteromith.aoc.aoc2019;

public class Instruction {
  String direction;
  int length;

  public Instruction(String direction, int length) {
    this.direction = direction;
    this.length = length;
  }

  static Instruction of(String direction, int length) {
    return new Instruction(direction, length);
  }
}
