package net.asteromith.aoc.aoc2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AoC02 {

  public static void main(String[] args) {
    try (
      BufferedReader inputReader = new BufferedReader(new InputStreamReader(AoC02.class.getResourceAsStream("/input")))
    ) {
      String originalInput = inputReader.readLine();
      for (int i=0; i < 100; i++) {
        for (int j=0; j < 100; j++) {
          String input = originalInput.replaceFirst("^(\\d+),(\\d+),(\\d+)", String.format("$1,%d,%d", i, j));
          String result = IntCodeComputer.execute(input);
          if (result.startsWith("19690720,")) {
            System.out.println(i * 100 + j);
          }
        }
      }
    } catch (IOException ioe) {
      ioe.printStackTrace(System.err);
    }
  }
}
