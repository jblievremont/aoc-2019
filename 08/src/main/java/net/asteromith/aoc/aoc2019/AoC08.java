package net.asteromith.aoc.aoc2019;

public class AoC08 {

  public static final int IMG_WIDTH = 25;
  public static final int IMG_HEIGHT = 6;

  public static void main(String[] args) throws Throwable {
    SpaceImage image = SpaceImage.parse(IMG_WIDTH, IMG_HEIGHT, AoC08.class.getResourceAsStream("/input"));
    int[][] countsByLayer = new int[image.layers.size()][3];
    int minZeroes = IMG_WIDTH * IMG_HEIGHT;
    int minZLayer = -1;
    for (int l = 0; l < image.layers.size(); l ++) {
      countsByLayer[l] = new int[3];
      byte[][] layer = image.layers.get(l);
      for (int y = 0; y < layer.length; y ++) {
        for (int x = 0; x < layer[y].length; x ++) {
          countsByLayer[l][layer[y][x]] ++;
        }
      }
      if (countsByLayer[l][0] < minZeroes) {
        minZeroes = countsByLayer[l][0];
        minZLayer = l;
      }
    }
    System.out.println(countsByLayer[minZLayer][1] * countsByLayer[minZLayer][2]);
  }
}
