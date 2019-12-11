package net.asteromith.aoc.aoc2019;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaintRobotTest {

  @Test
  public void shouldMoveState() {
    PaintRobot.State state = new PaintRobot.State();

    state.run(PaintRobot.Instruction.of(PaintRobot.Paint.WHITE, PaintRobot.Move.LEFT));
    assertThat(state.position).isEqualTo(PaintRobot.Cell.of(-1, 0));
    assertThat(state.currentColor()).isEqualTo(PaintRobot.Paint.BLACK);

    state.run(PaintRobot.Instruction.of(PaintRobot.Paint.BLACK, PaintRobot.Move.LEFT));
    assertThat(state.position).isEqualTo(PaintRobot.Cell.of(-1, -1));
    assertThat(state.currentColor()).isEqualTo(PaintRobot.Paint.BLACK);

    state.run(PaintRobot.Instruction.of(PaintRobot.Paint.WHITE, PaintRobot.Move.LEFT));
    assertThat(state.position).isEqualTo(PaintRobot.Cell.of(0, -1));
    assertThat(state.currentColor()).isEqualTo(PaintRobot.Paint.BLACK);

    state.run(PaintRobot.Instruction.of(PaintRobot.Paint.WHITE, PaintRobot.Move.LEFT));
    assertThat(state.position).isEqualTo(PaintRobot.Cell.of(0, 0));
    assertThat(state.currentColor()).isEqualTo(PaintRobot.Paint.WHITE);
  }
}
