package net.asteromith.aoc.aoc2019;

import java.util.stream.Stream;

public class Instruction {

  public static final int OP_MODE_3 = 10000;
  public static final int OP_MODE_2 = 1000;
  public static final int OP_MODE_1 = 100;

  final OpCode opCode;
  final OpMode op1Mode;
  final OpMode op2Mode;
  final OpMode op3Mode;

  private Instruction(int code) {
    op3Mode = OpMode.values()[code / OP_MODE_3];
    op2Mode = OpMode.values()[(code - OP_MODE_3 * op3Mode.ordinal()) / OP_MODE_2];
    op1Mode = OpMode.values()[(code - OP_MODE_3 * op3Mode.ordinal() - OP_MODE_2 * op2Mode.ordinal()) / OP_MODE_1];
    opCode = OpCode.of(code - OP_MODE_3 * op3Mode.ordinal() - OP_MODE_2 * op2Mode.ordinal() - OP_MODE_1 * op1Mode.ordinal());
  }

  static Instruction of(int code) {
    return new Instruction(code);
  }

  enum OpCode {
    ADD(1, 4),
    MULTIPLY(2, 4),
    INPUT(3, 2),
    DISPLAY(4, 2),
    JUMP_IF(5, 0),
    JUMP_IF_NOT(6, 0),
    LESS_THAN(7, 4),
    EQUALS(8, 4),
    HLT(99, 0);

    final int code;
    final int length;

    OpCode(int code, int length) {
      this.code = code;
      this.length = length;
    }

    static OpCode of(int code) {
      return Stream.of(values())
        .filter(v -> v.code == code)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    }
  }

  enum OpMode {
    POSITION,
    IMMEDIATE
  }
}
