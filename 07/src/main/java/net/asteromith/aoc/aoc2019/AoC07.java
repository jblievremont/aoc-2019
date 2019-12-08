package net.asteromith.aoc.aoc2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class AoC07 {

  public static final int MIN_PHASE = 5;
  public static final int MAX_PHASE = 10;

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
    for(int phase1 = MIN_PHASE; phase1 < MAX_PHASE; phase1 ++) {
      for(int phase2 = MIN_PHASE; phase2 < MAX_PHASE; phase2 ++) {
        for(int phase3 = MIN_PHASE; phase3 < MAX_PHASE; phase3 ++) {
          for(int phase4 = MIN_PHASE; phase4 < MAX_PHASE; phase4 ++) {
            for(int phase5 = MIN_PHASE; phase5 < MAX_PHASE; phase5 ++) {
              if(new HashSet<>(Arrays.asList(phase1, phase2, phase3, phase4, phase5)).size() == 5) {
                // Valid permutation, compute the output!
                long result = 0;
                try {
                  result = AmpChain.of(input, phase1, phase2, phase3, phase4, phase5).execute();
                } catch(Throwable ioe) {
                  ioe.printStackTrace(System.err);
                }
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
