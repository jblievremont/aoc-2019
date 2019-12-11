package net.asteromith.aoc.aoc2019;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class AoC11 {

  public static void main(String[] args) throws IOException {
    PaintRobot robot = new PaintRobot();
    robot.run();
    char[][] buffer = new char[6][43];
    for (int i = 0; i < buffer.length; i ++) {
      buffer[i] = new char[43];
      for (int j = 0; j < buffer[i].length; j ++) {
        buffer[i][j] = ' ';
      }
    }
    robot.state.path.entrySet().forEach(e -> {
      PaintRobot.Cell position = e.getKey();
      PaintRobot.Paint color = e.getValue().getColor();
      buffer[-position.y][position.x] = color == PaintRobot.Paint.WHITE ? '#' : ' ';
    });
    for (char[] chars : buffer) {
      System.out.println(new String(chars));
    }
  }
}
