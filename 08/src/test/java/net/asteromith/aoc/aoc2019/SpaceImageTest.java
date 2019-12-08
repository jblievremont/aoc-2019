package net.asteromith.aoc.aoc2019;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class SpaceImageTest {

  @Test
  public void shouldParse() throws Throwable {
    SpaceImage image = SpaceImage.parse(3, 2, new ByteArrayInputStream("123456789012".getBytes(StandardCharsets.US_ASCII)));
    assertThat(image.layers).hasSize(2);
    assertThat(image.layers.get(0)).hasSize(2);
    assertThat(image.layers.get(0)[0]).containsExactly(1, 2, 3);
    assertThat(image.layers.get(0)[1]).containsExactly(4, 5, 6);
    assertThat(image.layers.get(1)).hasSize(2);
    assertThat(image.layers.get(1)[0]).containsExactly(7, 8, 9);
    assertThat(image.layers.get(1)[1]).containsExactly(0, 1, 2);
  }
}
