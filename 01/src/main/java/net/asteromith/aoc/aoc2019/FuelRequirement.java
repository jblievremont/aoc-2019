package net.asteromith.aoc.aoc2019;

/**
 * Fuel required to launch a given module is based on its mass. Specifically, to
 * find the fuel required for a module, take its mass, divide by three, round
 * down, and subtract 2.
 */
public class FuelRequirement {

  private long moduleMass;

  private FuelRequirement(long moduleMass) {
    this.moduleMass = moduleMass;
  }

  public static FuelRequirement forModuleMass(long moduleMass) {
    return new FuelRequirement(moduleMass);
  }

  public long getValue() {
    return (moduleMass / 3) - 2;
  }
}
