package model.pixel;

import model.imagematrix.TransformationMatrix;
import model.utilities.Helper;

/**
 * PixelImpl class represent a pixel that captures the three intensities of the
 * different channels the red, green and the blue.
 */
public class PixelImpl implements Pixel {

  private final Colour color;

  /**
   * Constructor for PixelImpl class that instantiates an object taking an
   * argument of Color that represents the three intensities red, green and blue.
   *
   * @param color An object of color is passed which has RGB intensity of the
   *              pixel.
   */
  public PixelImpl(Colour color) {
    Helper.isObjectNull(color);

    this.color = color;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("PixelImpl [position=");
    sb.append(", color=");
    sb.append(color);
    sb.append(", dmcColorName=");
    sb.append("]");
    return sb.toString();
  }

  @Override
  public Pixel colorTransformation(TransformationMatrix matrix) {
    Helper.isObjectNull(matrix);
    Colour newColor = color.colorTransformation(matrix);
    PixelImpl newPixel = new PixelImpl(newColor);
    return newPixel;
  }

  @Override
  public int getRedColor() {
    return this.color.getRedColor();
  }

  @Override
  public int getGreenColor() {
    return this.color.getGreenColor();
  }

  @Override
  public int getBlueColor() {
    return this.color.getBlueColor();
  }

  @Override
  public Colour toClamp() {

    return color.toClamp();
  }

  @Override
  public Colour getColor() {
    int red = this.color.getRedColor();
    int green = this.color.getGreenColor();
    int blue = this.color.getBlueColor();
    Colour color = new RgbColor(red, green, blue);
    return color;
  }

}
