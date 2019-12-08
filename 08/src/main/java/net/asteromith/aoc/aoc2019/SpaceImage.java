package net.asteromith.aoc.aoc2019;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SpaceImage {

  private static final byte ZERO_AS_BYTE = (byte) '0';

  final List<byte[][]> layers;

  private SpaceImage(List<byte[][]> layers) {
    this.layers = layers;
  }

  static SpaceImage parse(int width, int height, InputStream input) throws IOException {
    List<byte[][]> layers = new ArrayList<>();
    while(input.available() > 1) {
      byte[][] currentLayer = new byte[height][width];
      for (int y = 0; y < height; y ++) {
        byte[] currentLine = new byte[width];
        int bytesRead = input.read(currentLine);
        if (bytesRead == width) {
          for (int x = 0; x < width; x ++) {
            currentLine[x] = (byte) (currentLine[x] - ZERO_AS_BYTE);
          }
          currentLayer[y] = currentLine;
        }
      }
      layers.add(currentLayer);
    }
    return new SpaceImage(layers);
  }

  byte[][] render() {
    int height = layers.get(0).length;
    int width = layers.get(0)[0].length;
    byte[][] rendered = new byte[height][width];
    for (int y = 0; y < height; y ++) {
      rendered[y] = new byte[width];
      for (int x = 0; x < width; x ++) {
        boolean renderedPx = false;
        for (int l = 0; l < layers.size(); l ++) {
          byte current = layers.get(l)[y][x];
          if (!renderedPx && Pixel.values()[current] != Pixel.TRANSPARENT) {
            rendered[y][x] = current;
            renderedPx = true;
          }
        }
      }
    }
    return rendered;
  }

  enum Pixel {
    BLACK,
    WHITE,
    TRANSPARENT;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    byte[][] rendered = render();
    for (int y = 0; y < rendered.length; y ++) {
      for (int x = 0; x < rendered[y].length; x ++) {
        builder.append(rendered[y][x] == 0 ? "X" : " ");
      }
      builder.append(System.lineSeparator());
    }
    return builder.toString();
  }
}
