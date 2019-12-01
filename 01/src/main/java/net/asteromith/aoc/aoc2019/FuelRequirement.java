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
    return forMassRecursive(moduleMass, 0);
  }

  private long forMassRecursive(long mass, long accu) {
    long thisMass = forMass(mass);
    if (thisMass <= 0) {
      return accu;
    } else {
      return forMassRecursive(thisMass, accu + thisMass);
    }
  }

  private static long forMass(long mass) {
    return (mass / 3) - 2;
  }
}
