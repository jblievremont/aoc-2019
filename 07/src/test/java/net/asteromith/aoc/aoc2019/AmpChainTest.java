package net.asteromith.aoc.aoc2019;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AmpChainTest {

  @ParameterizedTest(name = "Program {0} with phases {1},{2},{3},{4},{5} should yield output {6}")
  @CsvSource({
    "'3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0', 4, 3, 2, 1, 0, 43210",
    "'3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0', 0, 1, 2, 3, 4, 54321",
    "'3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0', 1, 0, 4, 3, 2, 65210"
  })
  public void shouldComputeOutput(String program, int phase1, int phase2, int phase3, int phase4, int phase5, int expectedOutput) {
    assertThat(AmpChain.of(program, phase1, phase2, phase3, phase4, phase5).execute()).isEqualTo(expectedOutput);
  }
}
