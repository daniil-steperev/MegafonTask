package stepyrev.megafon.lab.conways;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Key;
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

  private boolean isGameStopped;


  /** A cellular controller. */
  private CellularController controller;

  /** A method that initializes a game field. */
  @Override
  public void initialize() {
    setScreenSize(WIDTH, HEIGHT);
    createGame();
    showGrid(true);
  }

  /** A method that creates a game. */
  private void createGame() {
    controller = new CellularController();
    createCellularObject();
    drawScene();

    isGameStopped = true;
    startGame();
  }

  private void startGame() {
    controller.draw(this);
    setTurnTimer(500);
    isGameStopped = false;
  }

  /** A method that realizes events that occurs during one turn. */
  public void onTurn(int step) {
    check();
    boolean isFieldChanged = controller.makeGameIteration();
    if (!isFieldChanged) {
      finishGame("All cellulars are stabled!");
    }
    drawScene();
  }

  /** A method that reflects all events that occurs when key button was pressed. */
  @Override
  public void onKeyPress(Key key) {
    if (key == Key.SPACE) {
      if (isGameStopped) {
        createGame();
        startGame();
      }
    }
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
      finishGame("All cellulars are dead!");
    }
  }

  /**
   * A method that finishes the game.
   * @param message - a message that will be printed to user
   */
  private void finishGame(String message) {
    StringBuilder msg = new StringBuilder(message);
    msg.append("\n");
    msg.append("To restart the game press Space.");
    showMessageDialog(Color.LIMEGREEN, msg.toString(), Color.WHITE, 35);
    stopTurnTimer();
    isGameStopped = true;
  }
}
