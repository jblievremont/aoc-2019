package net.asteromith.aoc.aoc2019;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InstructionTest {

  @Test
  public void shouldCorrectlyParseOpCode() {
    Instruction inst = Instruction.of(1002);
    assertThat(inst.opCode).isEqualTo(Instruction.OpCode.MULTIPLY);
    assertThat(inst.op1Mode).isEqualTo(Instruction.OpMode.POSITION);
    assertThat(inst.op2Mode).isEqualTo(Instruction.OpMode.IMMEDIATE);
    assertThat(inst.op3Mode).isEqualTo(Instruction.OpMode.POSITION);
  }
}
