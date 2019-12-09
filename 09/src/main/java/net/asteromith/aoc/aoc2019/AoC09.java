package net.asteromith.aoc.aoc2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AoC09 {

  public static void main(String[] args) {
    try (
      BufferedReader inputReader = new BufferedReader(new InputStreamReader(AoC09.class.getResourceAsStream("/input")))
    ) {
      String input = inputReader.readLine();
      IntCodeComputer.execute(input, System.in, System.out);
    } catch (IOException ioe) {
      ioe.printStackTrace(System.err);
    }
  }
}
