package net.asteromith.aoc.aoc2019;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class IntCodeComputerTest {

  @ParameterizedTest(name = "Execution of [{0}] should yield [{1}]")
  @ArgumentsSource(WellFormedIntCodeProvider.class)
  public void shouldExecuteWellFormedIntCode(String input, String expected) {
    assertThat(IntCodeComputer.execute(input)).isEqualTo(expected);
  }

  static class WellFormedIntCodeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
      return Stream.of(
        Arguments.of("1,0,0,0,99", "2,0,0,0,99"),
        Arguments.of("2,3,0,3,99", "2,3,0,6,99"),
        Arguments.of("2,4,4,5,99,0", "2,4,4,5,99,9801"),
        Arguments.of("1,1,1,4,99,5,6,0,99", "30,1,1,4,2,5,6,0,99")
      );
    }
  }
}
