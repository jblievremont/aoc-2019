package net.asteromith.aoc.aoc2019;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InstructionTest {

  @Test
  public void shouldCorrectlyParseOpCode0() {
    Instruction inst = Instruction.of(1002);
    assertThat(inst.opCode).isEqualTo(Instruction.OpCode.MULTIPLY);
    assertThat(inst.op1Mode).isEqualTo(Instruction.OpMode.POSITION);
    assertThat(inst.op2Mode).isEqualTo(Instruction.OpMode.IMMEDIATE);
    assertThat(inst.op3Mode).isEqualTo(Instruction.OpMode.POSITION);
  }

  @Test
  public void shouldCorrectlyParseOpCode1() {
    Instruction inst = Instruction.of(203);
    assertThat(inst.opCode).isEqualTo(Instruction.OpCode.INPUT);
    assertThat(inst.op1Mode).isEqualTo(Instruction.OpMode.RELATIVE);
    assertThat(inst.op2Mode).isEqualTo(Instruction.OpMode.POSITION);
    assertThat(inst.op3Mode).isEqualTo(Instruction.OpMode.POSITION);
  }
}
