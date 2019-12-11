package net.asteromith.aoc.aoc2019;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class AoC11 {

  public static void main(String[] args) throws IOException {
    PaintRobot robot = new PaintRobot();
    robot.run();
    System.out.println(robot.countPainted());
  }
}
