package stepyrev.megafon.lab.conways.util;

import java.util.ArrayList;
import java.util.List;
import stepyrev.megafon.lab.conways.domain.Cellular;

/** A class that represents a cellular utils. */
public class CellularUtils {
  /** A shift to avoid collisions in hashcode method. */
  public static final int HASH_SHIFT = 666;

  /**
   * A method that creates a first cellular object.
   *
   * @param x - a 'x' coordinate of the object's center
   * @param y - a 'y' coordinate of the object's center
   * @return - a list of cellulars that represents the object
   */
  public static List<Cellular> createCellularObject(int x, int y) {
    List<Cellular> cellulars = new ArrayList<>();

    cellulars.addAll(createLeftPartOfCellularObject(x, y));
    cellulars.addAll(createRightPartOfCellularObject(x, y));
    cellulars.add(new Cellular(x,y - 2,true));
    cellulars.add(new Cellular(x,y + 2,true));

    return cellulars;
  }

  /**
   * A method that creates a left part of the cellular object.
   * @param x - a 'x' coordinate of the object's center
   * @param y - a 'y' coordinate of the object's center
   * @return - a list of cellulars that represents the left part of the object
   */
  private static List<Cellular> createLeftPartOfCellularObject(int x, int y) {
    List<Cellular> cellulars = new ArrayList<>();

    cellulars.add(new Cellular(x - 1, y - 1, true));
    cellulars.add(new Cellular(x - 1, y, true));
    cellulars.add(new Cellular(x - 1, y + 1, true));
    cellulars.add(new Cellular(x - 2, y, true));

    return cellulars;
  }

  /**
   * A method that creates a right part of the cellular object.
   * @param x - a 'x' coordinate of the object's center
   * @param y - a 'y' coordinate of the object's center
   * @return - a list of cellulars that represents the left part of the object
   */
  private static List<Cellular> createRightPartOfCellularObject(int x, int y) {
    List<Cellular> cellulars = new ArrayList<>();

    cellulars.add(new Cellular(x + 1, y - 1, true));
    cellulars.add(new Cellular(x + 1, y, true));
    cellulars.add(new Cellular(x + 1, y + 1, true));
    cellulars.add(new Cellular(x + 2, y, true));

    return cellulars;
  }
}
