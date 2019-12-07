package net.asteromith.aoc.aoc2019;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AmpChain {

  final String program;
  final int[] phases;

  private AmpChain(String program, int... phases) {
    this.program = program;
    this.phases = phases;
  }

  static AmpChain of(String program, int... phases) {
    return new AmpChain(program, phases);
  }

  long execute() {
    long currentValue = 0;
    ByteArrayOutputStream currentOut = new ByteArrayOutputStream();
    for(int currentPhase: phases) {
      try {
        // System.out.printf("Phase: %d - Input: %d%n", currentPhase, currentValue);
        currentOut.write(String.format("%d%n%d%n", currentPhase, currentValue).getBytes(StandardCharsets.UTF_8));
        ByteArrayInputStream currentIn = new ByteArrayInputStream(currentOut.toByteArray());
        currentOut = new ByteArrayOutputStream();
        IntCodeComputer.execute(program, currentIn, currentOut);
        currentValue = Integer.parseInt(new String(currentOut.toByteArray(), StandardCharsets.UTF_8).trim(), 10);
        currentOut.reset();
      } catch(IOException ioe) {
        ioe.printStackTrace(System.err);
      }
    }
    return currentValue;
  }
}
