package net.asteromith.aoc.aoc2019;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PaintRobot {

  State state;
  private IntCodeComputer computer;
  private PipedOutputStream commandOut;
  private PipedInputStream programIn;

  PaintRobot() {
    this.state = new State();
    this.commandOut = new PipedOutputStream();
    this.programIn = new PipedInputStream();
    try (
      BufferedReader inputReader = new BufferedReader(new InputStreamReader(AoC11.class.getResourceAsStream("/input")))
    ) {
      PipedInputStream in = new PipedInputStream();
      in.connect(commandOut);
      PipedOutputStream out = new PipedOutputStream();
      out.connect(programIn);
      String input = inputReader.readLine();
      computer = IntCodeComputer.of(0, input, in, out);
    } catch (IOException ioe) {
      ioe.printStackTrace(System.err);
    }
  }

  void run() throws IOException {
    Thread computeThread = new Thread(computer::execute);
    computeThread.start();
    PrintWriter commandWriter = new PrintWriter(new OutputStreamWriter(commandOut));
    BufferedReader stateReader = new BufferedReader(new InputStreamReader(programIn));
    do {
      commandWriter.println(state.currentColor().ordinal());
      commandWriter.flush();
      String paintLine = stateReader.readLine();
      if (paintLine != null) {
        Paint paint = Paint.values()[Integer.parseInt(paintLine)];
        Move move = Move.values()[Integer.parseInt(stateReader.readLine())];
        state.run(Instruction.of(paint, move));
      }
    } while(computer.isRunning());
    commandWriter.close();
    stateReader.close();
  }

  long countPainted() {
    return state.path.values().stream().filter(Panel::wasPainted).count();
  }

  static class State {
    Direction direction;
    Cell position;
    Map<Cell, Panel> path;

    State() {
      this.direction = Direction.UP;
      this.position = Cell.of(0, 0);
      this.path = new HashMap<>();
      Panel startPanel = new Panel();
      startPanel.paint(Paint.WHITE);
      this.path.put(position, startPanel);
    }

    void run(Instruction instruction) {
      paint(instruction.paint);
      move(instruction.move);
    }

    Paint currentColor() {
      return path.get(position).getColor();
    }

    void paint(Paint paint) {
      path.get(position).paint(paint);
    }

    void move(Move move) {
      Direction nextDir = direction.turn(move);
      Cell nextCell = position.move(nextDir);
      if (!path.containsKey(nextCell)) {
        path.put(nextCell, new Panel());
      }
      position = nextCell;
      direction = nextDir;
    }
  }

  static class Instruction {
    Paint paint;
    Move move;

    private Instruction(Paint paint, Move move) {
      this.paint = paint;
      this.move = move;
    }

    static Instruction of(Paint paint, Move move) {
      return new Instruction(paint, move);
    }
  }

  static class Panel {
    private Paint color;
    private boolean painted;

    private Panel() {
      this.color = Paint.BLACK;
      painted = false;
    }

    void paint(Paint color) {
      this.color = color;
      painted = true;
    }

    Paint getColor() {
      return color;
    }

    boolean wasPainted() {
      return painted;
    }
  }

  enum Direction {
    UP,
    LEFT,
    DOWN,
    RIGHT;

    Direction turn(Move move) {
      switch(this) {
        case UP:
          return move == Move.LEFT ? LEFT : RIGHT;
        case LEFT:
          return move == Move.LEFT ? DOWN : UP;
        case DOWN:
          return move == Move.LEFT ? RIGHT : LEFT;
        default:
          return move == Move.LEFT ? UP : DOWN;
      }
    }
  }

  enum Paint {
    BLACK,
    WHITE
  }

  enum Move {
    LEFT,
    RIGHT
  }

  static class Cell {
    final int x;
    final int y;

    private Cell(int x, int y) {
      this.x = x;
      this.y = y;
    }

    static Cell of(int x, int y) {
      return new Cell(x, y);
    }

    Cell move(Direction dir) {
      switch(dir) {
        case UP:
          return Cell.of(x, y + 1);
        case LEFT:
          return Cell.of(x - 1, y);
        case DOWN:
          return Cell.of(x, y - 1);
        default:
          return Cell.of(x + 1, y);
      }
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Cell cell = (Cell) o;
      return x == cell.x &&
        y == cell.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }

    @Override
    public String toString() {
      return "(" +
        "" + x +
        "," + y +
        ')';
    }
  }
}
