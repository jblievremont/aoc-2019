package net.asteromith.aoc.aoc2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class AoC07 {

  public static void main(String[] args) {
    String input = "";
    try (
      BufferedReader inputReader = new BufferedReader(new InputStreamReader(AoC07.class.getResourceAsStream("/input")))
    ) {
      input = inputReader.readLine();
    } catch (IOException ioe) {
      ioe.printStackTrace(System.err);
    }
    long maxOutput = 0;
    for(int phase1 = 0; phase1 < 5; phase1 ++) {
      for(int phase2 = 0; phase2 < 5; phase2 ++) {
        for(int phase3 = 0; phase3 < 5; phase3 ++) {
          for(int phase4 = 0; phase4 < 5; phase4 ++) {
            for(int phase5 = 0; phase5 < 5; phase5 ++) {
              if(new HashSet<>(Arrays.asList(phase1, phase2, phase3, phase4, phase5)).size() == 5) {
                // Valid permutation, compute the output!
                long result = AmpChain.of(input, phase1, phase2, phase3, phase4, phase5).execute();
                maxOutput = Math.max(maxOutput, result);
                System.out.printf("%d,%d,%d,%d,%d,%d%n", phase1, phase2, phase3, phase4, phase5, result);
              }
            }
          }
        }
      }
    }
    System.out.printf("Max: %d%n", maxOutput);
  }
}
