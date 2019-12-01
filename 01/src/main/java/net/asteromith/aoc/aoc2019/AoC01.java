package net.asteromith.aoc.aoc2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AoC01 {

  public static void main(String[] args) {
    long total = 0;
    long count = 0;
    try(
      BufferedReader inputReader = new BufferedReader(new InputStreamReader(AoC01.class.getResourceAsStream("/input")))
    ) {
      String currentLine = inputReader.readLine();
      while (currentLine != null && !currentLine.isBlank()) {
        long moduleMass = Long.parseLong(currentLine, 10);
        long value = FuelRequirement.forModuleMass(moduleMass).getValue();
        System.out.printf("Mass: %d => Fuel: %d%n", moduleMass, value);
        total += value;
        count += 1;
        currentLine = inputReader.readLine();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace(System.err);
    }
    System.out.printf("Total fuel requirement for %d modules is %d%n", count, total);
  }
}
