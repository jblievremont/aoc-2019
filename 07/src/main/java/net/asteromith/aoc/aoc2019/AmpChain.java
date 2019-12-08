package net.asteromith.aoc.aoc2019;

import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.output.TeeOutputStream;

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

  long execute() throws IOException, InterruptedException {
    Thread[] ampThreads = new Thread[phases.length];
    ByteArrayOutputStream finalOut = new ByteArrayOutputStream();
    ByteArrayOutputStream initialIn = new ByteArrayOutputStream();
    PipedInputStream[] ins = new PipedInputStream[phases.length];
    PipedOutputStream[] outs = new PipedOutputStream[phases.length];
    for (int i = 0; i < phases.length; i ++) {
      ins[i] = new PipedInputStream();
      outs[i] = new PipedOutputStream();
      IntCodeComputer amp;
      if (i == 0) {
        amp = IntCodeComputer.of(i, program, new TeeInputStream(ins[i], initialIn), outs[i]);
      } else if (i < phases.length - 1) {
        amp = IntCodeComputer.of(i, program, ins[i], outs[i]);
      } else {
        amp = IntCodeComputer.of(i, program, ins[i], new TeeOutputStream(outs[i], finalOut));
      }
      ampThreads[i] = new Thread(amp::execute);
    }
    outs[phases.length - 1].connect(ins[0]);
    for (int i = 1; i < phases.length; i++) {
      outs[i - 1].connect(ins[i]);
    }

    ampThreads[0].start();
    outs[phases.length - 1].write(String.format("%d%n", phases[0]).getBytes(StandardCharsets.UTF_8));
    for (int i = 1; i < phases.length; i++) {
      ampThreads[i].start();
      outs[i - 1].write(String.format("%d%n", phases[i]).getBytes(StandardCharsets.UTF_8));
    }

    outs[phases.length - 1].write(String.format("0%n").getBytes(StandardCharsets.UTF_8));
    ampThreads[phases.length - 1].join();
    try {
      new BufferedReader(new InputStreamReader(ins[0])).readLine();
    } catch(Throwable t) {
      // NOP
    }

    BufferedReader finalReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(initialIn.toByteArray())));
    int result = 0;
    String lastLine = finalReader.readLine();
    do {
      System.err.println(lastLine);
      result = Integer.parseInt(lastLine);
      lastLine = finalReader.readLine();
    } while(lastLine != null && !lastLine.isEmpty());
    return result;
  }
}
