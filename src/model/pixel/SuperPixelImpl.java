package model.pixel;

import model.utilities.Helper;

/**
 * This class implements the interface SuperPixel represents the super pixel which is a collection 
 * of pixels in the image as generated by the cross stitched image pattern generator. The nearby 
 * pixels are combined using the nearest DMC color.
 */
public class SuperPixelImpl extends PixelImpl implements SuperPixel {

  private final int superPixelPosY;

  private final int superPixelPosX;

  private final String symbol;

  private final String dmcColorName;

  private final int superPixelHeight;

  private final int superPixelWidth;

  /**
   * Constructor for the SuperPixelImpl class that can initialize an object of the
   * SuperPixelImpl class.
   *
   * @param color            An object of color is passed which has RGB intensity
   *                         of the SuperPixel.
   * @param superPixelPosY   The Y positon of the SuperPixel.
   * @param superPixelPosX   The X position of the SuperPixel.
   * @param symbol           The unicode symbol SuperPixel.
   * @param dmcColorName     The dmc color name for the SuperPixel.
   * @param superPixelHeight The height of the SuperPixel.
   * @param superPixelWidth  The width of the SuperPixel.
   */
  public SuperPixelImpl(Colour color, int superPixelPosY, int superPixelPosX, String symbol,
      String dmcColorName, int superPixelHeight, int superPixelWidth) {
    super(color);
    if (Helper.isNegative(superPixelPosY) || Helper.isNegative(superPixelPosX)) {
      throw new IllegalArgumentException();
    }
    Helper.isObjectNull(symbol);
    Helper.isObjectNull(dmcColorName);
    this.superPixelPosY = superPixelPosY;
    this.superPixelPosX = superPixelPosX;
    this.symbol = symbol;
    this.dmcColorName = dmcColorName;
    this.superPixelHeight = superPixelHeight;
    this.superPixelWidth = superPixelWidth;
  }

  /**
   * Constructor for the SuperPixelImpl class that can initialize an object of the
   * SuperPixelImpl class.
   *
   * @param color            An object of color is passed which has RGB intensity
   *                         of the SuperPixel.
   * @param superPixelPosY   The Y position of the SuperPixel.
   * @param superPixelPosX   The X position of the SuperPixel.
   * @param superPixelHeight The height of the SuperPixel.
   * @param superPixelWidth  The width of the SuperPixel.
   */
  public SuperPixelImpl(Colour color, int superPixelPosY, int superPixelPosX, int superPixelHeight,
      int superPixelWidth) {
    super(color);
    if (Helper.isNegative(superPixelPosY) || Helper.isNegative(superPixelPosX)) {
      throw new IllegalArgumentException();
    }

    this.superPixelPosY = superPixelPosY;
    this.superPixelPosX = superPixelPosX;
    this.symbol = null;
    this.dmcColorName = null;
    this.superPixelHeight = superPixelHeight;
    this.superPixelWidth = superPixelWidth;
  }

  @Override
  public int getSuperPixelPosY() {
    return this.superPixelPosY;
  }

  @Override
  public int getSuperPixelPosX() {
    return this.superPixelPosX;
  }

  @Override
  public String toString() {

    StringBuffer sb = new StringBuffer();
    sb.append("SuperPixelImpl [");
    sb.append("superPixelHeight=");
    sb.append(superPixelPosY);
    sb.append(", superPixelWidth=");
    sb.append(superPixelPosX);
    sb.append(", symbol=");
    sb.append(symbol);
    sb.append("]");
    return sb.toString();

  }

  @Override
  public String getSymbol() {
    String symbol = this.symbol;
    return symbol;
  }

  @Override
  public String getDmcColorName() {
    String dmcColorName = this.dmcColorName;
    return dmcColorName;
  }

  @Override
  public int getSuperPixelHeight() {
    int superPixelHeight = this.superPixelHeight;
    return superPixelHeight;

  }

  @Override
  public int getSuperPixelWidth() {
    int superPixelWidth = this.superPixelWidth;
    return superPixelWidth;
  }

}