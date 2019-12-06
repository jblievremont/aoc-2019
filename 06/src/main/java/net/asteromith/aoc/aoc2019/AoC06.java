package net.asteromith.aoc.aoc2019;

public class AoC06 {

  public static void main(String[] args) {
    StarSystem inputSystem = StarSystem.of(AoC06.class.getResourceAsStream("/input"));
    System.out.println(inputSystem.totalNumberOfOrbits());
    System.out.println(inputSystem.orbitTransfers("YOU", "SAN"));
  }
}
