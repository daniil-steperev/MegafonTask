package stepyrev.megafon.lab.conways;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import java.util.List;
import stepyrev.megafon.lab.conways.controller.CellularController;
import stepyrev.megafon.lab.conways.domain.Cellular;
import stepyrev.megafon.lab.conways.util.CellularUtils;

/** A class that represents a Conway's Game application. */
public class GameApplication extends Game {
  /** A width of the game field. */
  private static final int WIDTH = 40;
  /** A height of the game field. */
  private static final int HEIGHT = 40;

  /** A 'x' coordinate of the first cellular object. */
  private static final int FIRST_X = WIDTH / 2;
  /** A 'y' coordinate of the first cellular object. */
  private static final int FIRST_Y = HEIGHT / 2;


  /** A cellular controller. */
  private CellularController controller;

  /** A method that initializes a game field. */
  @Override
  public void initialize() {
    setScreenSize(WIDTH, HEIGHT);
    createGame();
    showGrid(true);
  }

  /** A method that realizes events that occurs during one turn. */
  public void onTurn(int step) {
    check();
    controller.makeGameIteration();
    drawScene();
  }

  /** A method that creates a game. */
  private void createGame() {
    controller = new CellularController();
    createCellularObject();
    drawScene();
    controller.draw(this);
    setTurnTimer(500);
  }


  /**
   * A method that creates a cellular object.
   */
  private void createCellularObject() {
    List<Cellular> firstCellularsObject = CellularUtils.createCellularObject(FIRST_X, FIRST_Y);
    for (Cellular cellular : firstCellularsObject) {
      controller.addCellular(cellular, 0);
    }
  }

  /** A method that draws a scene. */
  private void drawScene() {
    for (int y = 0; y < HEIGHT; y++) {
      for (int x = 0; x < WIDTH; x++) {
        setCellColor(x, y, Color.WHITE);
      }
    }

    controller.draw(this);
    setScore(controller.getAliveCellularsNumber());
  }


  /**
   * A method that checks if the game is ended.
   *
   * <p>A game should be finished when alive cellulars number will be zero.
   */
  private void check() {
    int aliveCellularsNumber = controller.getAliveCellularsNumber();
    if (aliveCellularsNumber == 0) {
      finishGame();
    }
  }

  private void finishGame() {
    String message = "All cellulars are dead! " + "\n" + "Game is over.";
    showMessageDialog(Color.DARKSEAGREEN, message, Color.RED, 50);
    stopTurnTimer();

  }
}
