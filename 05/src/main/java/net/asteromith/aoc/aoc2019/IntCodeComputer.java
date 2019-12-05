package net.asteromith.aoc.aoc2019;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntCodeComputer {

  public static final String SEPARATOR = ",";

  private static final int OFFSET_OPERAND1 = 1;
  private static final int OFFSET_OPERAND2 = 2;
  private static final int OFFSET_TARGET   = 3;

  private final List<Integer> memory;
  private int programCounter;

  private IntCodeComputer(String input) {
    this.memory = parse(input);
    this.programCounter = 0;
  }

  static String execute(String input) {
    return new IntCodeComputer(input).execute();
  }

  private static List<Integer> parse(String input) {
    return Stream.of(input.split(SEPARATOR))
      .map(Integer::valueOf)
      .collect(Collectors.toList());
  }

  private String execute() {
    Instruction lastOp;
    do {
      lastOp = Instruction.of(memory.get(programCounter));
      switch(lastOp.opCode) {
        case ADD:
          store(OFFSET_TARGET, loadFirstOperand(lastOp.op1Mode) + loadSecondOperand(lastOp.op2Mode));
          break;
        case MUL:
          store(OFFSET_TARGET,loadFirstOperand(lastOp.op1Mode) * loadSecondOperand(lastOp.op2Mode));
          break;
        case INP:
          System.out.print("input: ");
          try(
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
          ) {
            store(OFFSET_OPERAND1, Integer.parseInt(r.readLine(), 10));
          } catch(Throwable e) {
            throw new IllegalArgumentException(e);
          }
          break;
        case DSP:
          System.out.println(loadFirstOperand(lastOp.op1Mode));
          break;
        case HLT:
          break;
        default:
          throw new IllegalStateException(String.format("%s is not a valid operation code%nat PC=%d)", lastOp.opCode, programCounter));
      }
      programCounter += lastOp.opCode.length;
    } while(lastOp.opCode != Instruction.OpCode.HLT);
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
