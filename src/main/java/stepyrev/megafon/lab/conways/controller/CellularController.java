package stepyrev.megafon.lab.conways.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import stepyrev.megafon.lab.conways.GameApplication;
import stepyrev.megafon.lab.conways.domain.Cellular;

/** A class that represents a cellular controller. */
public class CellularController {
  /** A hashtable that contains only alive cellulars. */
  private Hashtable<Cellular, Integer> aliveCellularTable;
  /** A hashtable that contains only alive cellulars from the next iteration. */
  private Hashtable<Cellular, Integer> aliveCellularNextIterationTable;

  public CellularController() {
    aliveCellularTable = new Hashtable<>();
    aliveCellularNextIterationTable = new Hashtable<>();
  }

  /** A method that performs one game iteration. */
  public boolean makeGameIteration() {
    // a table that contains all alive cellulars after iteration
    aliveCellularNextIterationTable = new Hashtable<>(aliveCellularTable);

    for (Map.Entry<Cellular, Integer> element : aliveCellularTable.entrySet()) {
      Cellular cellular = element.getKey();
      int neighboursNumber = getAliveNeighborsNumber(cellular);
      checkCellular(cellular, neighboursNumber);

      // check neighbors
      List<Cellular> neighbors = getCellularNeighbors(cellular);
      for (Cellular neighbor : neighbors) {
        neighboursNumber = getAliveNeighborsNumber(neighbor);
        checkCellular(neighbor, neighboursNumber);
      }
    }

    if (aliveCellularTable.equals(aliveCellularNextIterationTable)) {
      return false;
    }

    aliveCellularTable = aliveCellularNextIterationTable;
    return true;
  }

  /**
   * A method that adds a new cellular to the list.
   *
   * If cellular is not alive, it will not be added to the list.
   *
   * @param cellular - a new cellular
   */
  public void addCellular(Cellular cellular, int neighboursNumber) {
    if (cellular.isAlive()) {
      aliveCellularTable.put(cellular, neighboursNumber);
    }
  }

  /**
   * A method that returns a number of alive cellulars.
   *
   * @return - a number of alive cellulars
   */
  public int getAliveCellularsNumber() {
    return aliveCellularTable.size();
  }

  /**
   * A method that draws all alive cellulars.
   *
   * @param gameApplication - a game field
   */
  public void draw(GameApplication gameApplication) {
    for (Map.Entry<Cellular, Integer> element : aliveCellularTable.entrySet()) {
      Cellular cellular = element.getKey();
      cellular.draw(gameApplication);
    }
  }

  /**
   * A method that checks the cellular.
   *
   * If cellular's neighbour number is 3 and its dead, cellular comes alive.
   * If cellular's neighbour number is less then 2 or more then 3 and its alive, cellular dies.
   * Otherwise cellular does not change its position.
   * @param cellular - a cellular that is checked
   * @param neighboursNumber - a number of cellular's neighbours
   */
  private void checkCellular(Cellular cellular, int neighboursNumber) {
    if (!cellular.isAlive()) {
      if (neighboursNumber == 3) { // a life is born in the cellular
        cellular.setAlive(true);
        aliveCellularNextIterationTable.put(cellular, neighboursNumber);
      }

      return;
    }

    // cellular is alive as we returned earlie
    if (neighboursNumber > 3 || neighboursNumber <= 1) { // the cellular dies
      aliveCellularNextIterationTable.remove(cellular);
    }
  }

  /**
   * A method that checks all neighbors for the cellular for the living or dead ones and returns a
   * number of alive cellulars.
   *
   * @param cellular - the cellular which neighbors will be checked
   * @return - a number of alive cellulars
   */
  private Integer getAliveNeighborsNumber(Cellular cellular) {
    int aliveNeighborsNumber = 0;
    List<Cellular> neighbors = getCellularNeighbors(cellular);

    for (Cellular neighbor : neighbors) {
      aliveNeighborsNumber += neighbor.isAlive() ? 1 : 0;
    }

    return aliveNeighborsNumber;
  }

  /**
   * A method that returns all neighbors of the cellular.
   *
   * @param cellular - the cellular, which neighbors should be returned
   * @return - a list of cellular neighbors
   */
  private List<Cellular> getCellularNeighbors(Cellular cellular) {
    List<Cellular> neighbors = new ArrayList<>();
    Integer cellularX = cellular.getX();
    Integer cellularY = cellular.getY();

    // get neighbors to the left of the cellular
    neighbors.add(getCellular(cellularX - 1, cellularY - 1));
    neighbors.add(getCellular(cellularX - 1, cellularY));
    neighbors.add(getCellular(cellularX - 1, cellularY + 1));

    // get upper neighbor
    neighbors.add(getCellular(cellularX, cellularY - 1));

    // get lower neighbor
    neighbors.add(getCellular(cellularX, cellularY + 1));

    // get neighbors to the right of the cellular
    neighbors.add(getCellular(cellularX + 1, cellularY - 1));
    neighbors.add(getCellular(cellularX + 1, cellularY));
    neighbors.add(getCellular(cellularX + 1, cellularY + 1));

    return neighbors;
  }

  /**
   * A method that returns cellular.
   *
   * As we rewrote hashcode method in cellular, operation containsKey is correct
   * @param x - a 'x' coordinate of the cellular
   * @param y - a 'x' coordinate of the cellular
   * @return - a cellular
   */
  private Cellular getCellular(int x, int y) {
    Cellular cellular = new Cellular(x, y);
    cellular.setAlive(aliveCellularTable.containsKey(cellular));
    return cellular;
  }
}
