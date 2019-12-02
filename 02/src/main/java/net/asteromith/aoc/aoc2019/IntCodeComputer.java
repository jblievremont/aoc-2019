package net.asteromith.aoc.aoc2019;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntCodeComputer {

  public static final String SEPARATOR = ",";

  private static final int OFFSET_OPERAND1 = 1;
  private static final int OFFSET_OPERAND2 = 2;
  private static final int OFFSET_TARGET   = 3;
  private static final int SIZE_OPERATION  = 4;

  private final List<Integer> memory;
  private int programCounter;
  private int lastOp;

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
    while(lastOp != 99) {
      lastOp = memory.get(programCounter);
      switch(lastOp) {
        case 1:
          store(loadFirstOperand() + loadSecondOperand());
          programCounter += SIZE_OPERATION;
          break;
        case 2:
          store(loadFirstOperand() * loadSecondOperand());
          programCounter += SIZE_OPERATION;
          break;
        case 99:
          break;
        default:
          throw new IllegalStateException(String.format("%d is not a valid operation code%nat PC=%d)", lastOp, programCounter));
      }
    }
    return memory.stream().map(Object::toString).collect(Collectors.joining(SEPARATOR));
  }

  private void store(int operationResult) {
    memory.set(getTargetIndex(), operationResult);
  }

  private Integer getTargetIndex() {
    return memory.get(programCounter + OFFSET_TARGET);
  }

  private Integer loadFirstOperand() {
    return memory.get(getFirstOperandIndex());
  }

  private Integer loadSecondOperand() {
    return memory.get(getSecondOperandIndex());
  }

  private Integer getFirstOperandIndex() {
    return memory.get(programCounter + OFFSET_OPERAND1);
  }

  private Integer getSecondOperandIndex() {
    return memory.get(programCounter + OFFSET_OPERAND2);
  }
}
