package net.asteromith.aoc.aoc2019;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntCodeComputer {

  public static final String SEPARATOR = ",";

  private static final int OFFSET_OPERAND1 = 1;
  private static final int OFFSET_OPERAND2 = 2;
  private static final int OFFSET_TARGET   = 3;

  private final int id;
  private final List<Integer> memory;
  private final BufferedReader in;
  private final PrintWriter out;
  private int programCounter;

  private IntCodeComputer(int id, String program, InputStream in, OutputStream out) {
    this.id = id;
    this.memory = parse(program);
    this.in = new BufferedReader(new InputStreamReader(in));
    this.out = new PrintWriter(out);
    this.programCounter = 0;
  }

  static IntCodeComputer of(int id, String program, InputStream in, OutputStream out) {
    return new IntCodeComputer(id, program, in, out);
  }

  static String execute(String program, InputStream in, OutputStream out) {
    return of(0, program, in, out).execute();
  }

  private static List<Integer> parse(String program) {
    return Stream.of(program.split(SEPARATOR))
      .map(Integer::valueOf)
      .collect(Collectors.toList());
  }

  String execute() {
    Instruction lastOp;
    System.err.println(id + " Start");
    do {
      lastOp = Instruction.of(memory.get(programCounter));
      switch(lastOp.opCode) {
        case ADD:
          store(OFFSET_TARGET, loadFirstOperand(lastOp.op1Mode) + loadSecondOperand(lastOp.op2Mode));
          break;
        case MULTIPLY:
          store(OFFSET_TARGET,loadFirstOperand(lastOp.op1Mode) * loadSecondOperand(lastOp.op2Mode));
          break;
        case INPUT:
          try {
            int input = Integer.parseInt(in.readLine(), 10);
            System.err.println(id + " Read: " + input);
            store(OFFSET_OPERAND1, input);
          } catch(IOException e) {
            e.printStackTrace(System.err);
          }
          break;
        case DISPLAY:
          Integer output = loadFirstOperand(lastOp.op1Mode);
          out.println(output);
          out.flush();
          System.err.println(id + " Wrote: " + output);
          break;
        case JUMP_IF:
          if (loadFirstOperand(lastOp.op1Mode) != 0) {
            programCounter = loadSecondOperand(lastOp.op2Mode);
          } else {
            programCounter += 3;
          }
          break;
        case JUMP_IF_NOT:
          if (loadFirstOperand(lastOp.op1Mode) == 0) {
            programCounter = loadSecondOperand(lastOp.op2Mode);
          } else {
            programCounter += 3;
          }
          break;
        case LESS_THAN:
          store(OFFSET_TARGET, loadFirstOperand(lastOp.op1Mode) < loadSecondOperand(lastOp.op2Mode) ? 1 : 0);
          break;
        case EQUALS:
          store(OFFSET_TARGET, loadFirstOperand(lastOp.op1Mode).equals(loadSecondOperand(lastOp.op2Mode)) ? 1 : 0);
          break;
        case HLT:
          break;
        default:
          throw new IllegalStateException(String.format("%s is not a valid operation code%nat PC=%d)", lastOp.opCode, programCounter));
      }
      programCounter += lastOp.opCode.length;
    } while(lastOp.opCode != Instruction.OpCode.HLT);
    System.err.println(id + " End");
    try {
      // Force flush of input
      in.readLine();
    } catch (Throwable t) {
      // NOP
    }
    out.flush();
    out.close();
    return memory.stream().map(Object::toString).collect(Collectors.joining(SEPARATOR));
  }

  private void store(int targetOffset, int operationResult) {
    memory.set(getTargetIndex(targetOffset), operationResult);
  }

  private Integer getTargetIndex(int offset) {
    return memory.get(programCounter + offset);
  }

  private Integer loadFirstOperand(Instruction.OpMode mode) {
    if (mode == Instruction.OpMode.POSITION) {
      return memory.get(getFirstOperandIndex());
    } else {
      return getFirstOperandIndex();
    }
  }

  private Integer loadSecondOperand(Instruction.OpMode mode) {
    if (mode == Instruction.OpMode.POSITION) {
      return memory.get(getSecondOperandIndex());
    } else {
      return getSecondOperandIndex();
    }
  }

  private Integer getFirstOperandIndex() {
    return memory.get(programCounter + OFFSET_OPERAND1);
  }

  private Integer getSecondOperandIndex() {
    return memory.get(programCounter + OFFSET_OPERAND2);
  }
}
