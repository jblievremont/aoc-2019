package net.asteromith.aoc.aoc2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntCodeComputer {

  public static final String SEPARATOR = ",";

  private static final int OFFSET_OPERAND1 = 1;
  private static final int OFFSET_OPERAND2 = 2;
  private static final int OFFSET_TARGET   = 3;

  private final int id;
  private final List<Long> memory;
  private final BufferedReader in;
  private final PrintWriter out;
  private int programCounter;
  private int relativeOffset;

  private IntCodeComputer(int id, String program, InputStream in, OutputStream out) {
    this.id = id;
    this.memory = parse(program);
    this.in = new BufferedReader(new InputStreamReader(in));
    this.out = new PrintWriter(out);
    this.programCounter = 0;
    this.relativeOffset = 0;
  }

  static IntCodeComputer of(int id, String program, InputStream in, OutputStream out) {
    return new IntCodeComputer(id, program, in, out);
  }

  static String execute(String program, InputStream in, OutputStream out) {
    return of(0, program, in, out).execute();
  }

  private static List<Long> parse(String program) {
    return Stream.of(program.split(SEPARATOR))
      .map(Long::valueOf)
      .collect(Collectors.toList());
  }

  String execute() {
    Instruction lastOp;
    System.err.println(id + " Start");
    do {
      lastOp = Instruction.of((int) lazyLoad(programCounter));
      System.err.println("@" + programCounter + ":" + relativeOffset + ":" + lastOp);
      switch(lastOp.opCode) {
        case ADD:
          store(OFFSET_TARGET, lastOp.op3Mode, loadFirstOperand(lastOp.op1Mode) + loadSecondOperand(lastOp.op2Mode));
          break;
        case MULTIPLY:
          store(OFFSET_TARGET, lastOp.op3Mode, loadFirstOperand(lastOp.op1Mode) * loadSecondOperand(lastOp.op2Mode));
          break;
        case INPUT:
          try {
            long input = Long.parseLong(in.readLine(), 10);
            System.err.println(id + " Read: " + input);
            store(OFFSET_OPERAND1, lastOp.op1Mode, input);
          } catch(IOException e) {
            e.printStackTrace(System.err);
          }
          break;
        case DISPLAY:
          long output = loadFirstOperand(lastOp.op1Mode);
          out.println(output);
          out.flush();
          System.err.println(id + " Wrote: " + output);
          break;
        case JUMP_IF:
          if (loadFirstOperand(lastOp.op1Mode) != 0L) {
            programCounter = (int) loadSecondOperand(lastOp.op2Mode);
          } else {
            programCounter += 3;
          }
          break;
        case JUMP_IF_NOT:
          if (loadFirstOperand(lastOp.op1Mode) == 0L) {
            programCounter = (int) loadSecondOperand(lastOp.op2Mode);
          } else {
            programCounter += 3;
          }
          break;
        case LESS_THAN:
          store(OFFSET_TARGET, lastOp.op3Mode, loadFirstOperand(lastOp.op1Mode) < loadSecondOperand(lastOp.op2Mode) ? 1 : 0);
          break;
        case EQUALS:
          store(OFFSET_TARGET, lastOp.op3Mode, loadFirstOperand(lastOp.op1Mode) == loadSecondOperand(lastOp.op2Mode) ? 1 : 0);
          break;
        case ADJUST_OFFSET:
          relativeOffset += (int) loadFirstOperand(lastOp.op1Mode);
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

  private void store(int targetOffset, Instruction.OpMode storeMode, long operationResult) {
    long targetIndex = getTargetIndex(targetOffset);
    if (storeMode == Instruction.OpMode.POSITION) {
      lazyStore((int) targetIndex, operationResult);
    } else if (storeMode == Instruction.OpMode.RELATIVE) {
      lazyStore(relativeOffset + (int) targetIndex, operationResult);
    } else {
      throw new IllegalArgumentException("IMMEDIATE mode not supported for store");
    }
  }

  private long getTargetIndex(int offset) {
    return lazyLoad(programCounter + offset);
  }

  private long loadFirstOperand(Instruction.OpMode mode) {
    long operandIndex = getFirstOperandIndex();
    if (mode == Instruction.OpMode.POSITION) {
      return lazyLoad((int) operandIndex);
    } else if(mode == Instruction.OpMode.RELATIVE) {
      return lazyLoad(relativeOffset + (int) operandIndex);
    } else {
      return operandIndex;
    }
  }

  private long loadSecondOperand(Instruction.OpMode mode) {
    long operandIndex = getSecondOperandIndex();
    if (mode == Instruction.OpMode.POSITION) {
      return lazyLoad((int) operandIndex);
    } else if(mode == Instruction.OpMode.RELATIVE) {
      return lazyLoad(relativeOffset + (int) operandIndex);
    } else {
      return operandIndex;
    }
  }

  private long lazyLoad(int index) {
    growMemory(index);
    System.err.println("Load: @" + index + " => " + memory.get(index));
    return memory.get(index);
  }

  private void lazyStore(int index, long value) {
    growMemory(index);
    System.err.println("Store: " + value + " @" + index);
    memory.set(index, value);
  }

  private void growMemory(int index) {
    while (memory.size() <= index) {
      memory.add(0L);
    }
  }

  private long getFirstOperandIndex() {
    return lazyLoad(programCounter + OFFSET_OPERAND1);
  }

  private long getSecondOperandIndex() {
    return lazyLoad(programCounter + OFFSET_OPERAND2);
  }
}
