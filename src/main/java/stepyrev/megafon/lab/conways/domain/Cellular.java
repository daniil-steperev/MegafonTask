package stepyrev.megafon.lab.conways.domain;

import com.javarush.engine.cell.Color;
import javafx.scene.control.Cell;
import stepyrev.megafon.lab.conways.GameApplication;
import stepyrev.megafon.lab.conways.util.CellularUtils;

/** A class that represents a cellular. */
public class Cellular {
  /** An 'x' coordinate of the cellular. */
  private Integer x;
  /** An 'y' coordinate of the cellular. */
  private Integer y;


  /** A field that reflects if the cellular is alive. */
  private boolean isAlive;

  public Cellular(Integer x, Integer y) {
    this.x = x;
    this.y = y;
    this.isAlive = false;
  }

  public Cellular(Integer x, Integer y, boolean isAlive) {
    this.x = x;
    this.y = y;
    this.isAlive = isAlive;
  }

  @Override
  public boolean equals(Object object) {
    Cellular cell = (Cellular) object;
    return x.equals(cell.x) && y.equals(cell.y);
  }

  public Integer getX() {
    return x;
  }

  public void setX(Integer x) {
    this.x = x;
  }

  public Integer getY() {
    return y;
  }

  public void setY(Integer y) {
    this.y = y;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void setAlive(boolean alive) {
    isAlive = alive;
  }

  public void draw(GameApplication game) {
    game.setCellColor(x, y, Color.BLACK);
  }

  @Override
  public int hashCode() {
    // collisions will not occur because for each column we assume 666 values while we
    // set height as 40
    return x * CellularUtils.HASH_SHIFT + y;
  }
}
