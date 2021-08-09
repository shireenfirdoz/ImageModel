package model.pixel;

/**
 * This interface represents a position type which contains the grid coordinate
 * i.e. the values for the height and the width in the image.
 */
public interface Position {

  /**
   * Getter method to get the height coordinate for the given position.
   *
   * @return int for the value of height
   */
  int getPositionHeight();

  /**
   * Getter method to get the width coordinate for the given position.
   *
   * @return int for the value of width
   */
  int getPositionWidth();
}
