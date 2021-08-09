package model.pixel;

import model.utilities.Helper;

/**
 * This class represent an image for the coordinate system. The x and the y
 * coordinate are represented as a single object for Position.
 */
public class PositionImpl implements Position {

  private final int positionHeight;
  private final int positionWidth;

  /**
   * This represent the constructor for an image for the coordinate system. The x
   * and the y coordinate are represented as a single object for Position. This is
   * the default constructor with the x and the y position.
   *
   * @param positionHeight the y coordinate for the position.
   * @param positionWidth  the x coordinate for the position.
   */
  public PositionImpl(int positionHeight, int positionWidth) {

    super();
    Helper.isNegative(positionHeight);
    Helper.isNegative(positionWidth);
    this.positionHeight = positionHeight;
    this.positionWidth = positionWidth;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Position [positionHeight=");
    sb.append(positionHeight);
    sb.append(", positionWidth=");
    sb.append(positionWidth);
    sb.append("]");
    return sb.toString();
  }

  @Override
  public int getPositionHeight() {
    int positionX = this.positionHeight;
    return positionX;
  }

  
  @Override
  public int getPositionWidth() {
    int positionY = this.positionWidth;
    return positionY;
  }

}
