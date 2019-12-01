package net.asteromith.aoc.aoc2019;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FuelRequirementTest {

  /*
  - For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
  - For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.
  - For a mass of 1969, the fuel required is 654.
  - For a mass of 100756, the fuel required is 33583.
   */
  @ParameterizedTest(name = "Module with mass {0} should have fuel requirement of {1}")
  @CsvSource({
    // Taken from the requirements
    "12, 2",
    "14, 2",
    "1969, 654",
    "100756, 33583"
  })
  public void shouldComputeFuelRequirementForModule(long moduleMass, long expectedFuelRequirement) {
    assertThat(FuelRequirement.forModuleMass(moduleMass).getValue()).isEqualTo(expectedFuelRequirement);
  }
}
