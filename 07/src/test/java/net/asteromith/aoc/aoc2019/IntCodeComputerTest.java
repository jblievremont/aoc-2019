package net.asteromith.aoc.aoc2019;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class IntCodeComputerTest {

  @ParameterizedTest(name = "Execution of [{0}] with input [{2}] should yield [{1}] and output [{3}]")
  @ArgumentsSource(WellFormedIntCodeProvider.class)
  public void shouldExecuteWellFormedIntCode(String program, String expectedEndState, String input, String expectedOutput) {
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    assertThat(IntCodeComputer.execute(program, in, out)).isEqualTo(expectedEndState);
    assertThat(new String(out.toByteArray())).isEqualTo(expectedOutput);
  }

  static class WellFormedIntCodeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
      return Stream.of(
        Arguments.of("1,0,0,0,99", "2,0,0,0,99", "", ""),
        Arguments.of("2,3,0,3,99", "2,3,0,6,99", "", ""),
        Arguments.of("2,4,4,5,99,0", "2,4,4,5,99,9801", "", ""),
        Arguments.of("1,1,1,4,99,5,6,0,99", "30,1,1,4,2,5,6,0,99", "", ""),
        Arguments.of("3,9,8,9,10,9,4,9,99,-1,8", "3,9,8,9,10,9,4,9,99,0,8", "0", "0" + System.lineSeparator()),
        Arguments.of("3,9,8,9,10,9,4,9,99,-1,8", "3,9,8,9,10,9,4,9,99,1,8", "8", "1" + System.lineSeparator()),
        Arguments.of("3,9,7,9,10,9,4,9,99,-1,8", "3,9,7,9,10,9,4,9,99,1,8", "0", "1" + System.lineSeparator()),
        Arguments.of("3,9,7,9,10,9,4,9,99,-1,8", "3,9,7,9,10,9,4,9,99,0,8", "8", "0" + System.lineSeparator()),
        Arguments.of("3,3,1108,-1,8,3,4,3,99", "3,3,1108,0,8,3,4,3,99", "0", "0" + System.lineSeparator()),
        Arguments.of("3,3,1108,-1,8,3,4,3,99", "3,3,1108,1,8,3,4,3,99", "8", "1" + System.lineSeparator()),
        Arguments.of("3,3,1107,-1,8,3,4,3,99", "3,3,1107,1,8,3,4,3,99", "0", "1" + System.lineSeparator()),
        Arguments.of("3,3,1107,-1,8,3,4,3,99", "3,3,1107,0,8,3,4,3,99", "8", "0" + System.lineSeparator()),
        Arguments.of("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9", "3,12,6,12,15,1,13,14,13,4,13,99,0,0,1,9", "0", "0" + System.lineSeparator()),
        Arguments.of("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9", "3,12,6,12,15,1,13,14,13,4,13,99,8,1,1,9", "8", "1" + System.lineSeparator()),
        Arguments.of("3,3,1105,-1,9,1101,0,0,12,4,12,99,1", "3,3,1105,0,9,1101,0,0,12,4,12,99,0", "0", "0" + System.lineSeparator()),
        Arguments.of("3,3,1105,-1,9,1101,0,0,12,4,12,99,1", "3,3,1105,8,9,1101,0,0,12,4,12,99,1", "8", "1" + System.lineSeparator())
      );
    }
  }
}
