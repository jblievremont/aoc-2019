package net.asteromith.aoc.aoc2019;

public class AoC06 {

  public static void main(String[] args) {
    System.out.println(
            StarSystem.of(
                    AoC06.class.getResourceAsStream("/input")
            ).totalNumberOfOrbits()
    );
  }
}
