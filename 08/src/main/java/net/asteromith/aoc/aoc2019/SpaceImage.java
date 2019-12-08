package net.asteromith.aoc.aoc2019;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
}
