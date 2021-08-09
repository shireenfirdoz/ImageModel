package model.pixel;

import model.imagematrix.TransformationMatrix;
import model.utilities.Helper;

/**
 * The RgbColor class represents a collection of three intensities that
 * collectively make an RGB image composed of the RGB pixels intensities.
 */
public class RgbColor implements Colour {
  private final int redColor;
  private final int blueColor;
  private final int greenColor;

  /**
   * Constructor to instantiate an object of the RGBColor that collectively
   * captures the three red, green and the blue intensity.
   *
   * @param redColor   The intensity of the red color.
   * @param greenColor The intensity of the green color.
   * @param blueColor  The intensity of the blue color.
   */
  public RgbColor(int redColor, int greenColor, int blueColor) {
    super();

    if (Helper.isNotValid(redColor) || Helper.isNotValid(blueColor)
        || Helper.isNotValid(greenColor)) {
      throw new IllegalArgumentException();
    }

    this.redColor = redColor;
    this.blueColor = blueColor;
    this.greenColor = greenColor;
  }

  @Override
  public String toString() {

    StringBuffer sb = new StringBuffer();
    sb.append("RgbColor [redColor=");
    sb.append(redColor);
    sb.append(", blueColor=");
    sb.append(blueColor);
    sb.append(", greenColor=");
    sb.append(greenColor);
    sb.append("]");
    return sb.toString();
  }

  @Override
  public Colour colorTransformation(TransformationMatrix matrix) {

    Helper.isObjectNull(matrix);

    float[][] matrix1 = matrix.getTransformationMatrix();

    int transformedRed = (Math.round(matrix1[0][0] * redColor))
        + (Math.round(matrix1[0][1] * greenColor)) + (Math.round(matrix1[0][2] * blueColor));

    int transformedGreen = (Math.round(matrix1[1][0] * redColor))
        + (Math.round(matrix1[1][1] * greenColor)) + (Math.round(matrix1[1][2] * blueColor));

    int transformedBlue = (Math.round(matrix1[2][0] * redColor))
        + (Math.round(matrix1[2][1] * greenColor)) + (Math.round(matrix1[2][2] * blueColor));

    return new RgbColor(transformedRed, transformedGreen, transformedBlue).toClamp();
  }

  private int toClampColorValues(int value) {
    if (value < 0) {
      return 0;
    } else if (value > 255) {
      return 255;
    }
    return value;

  }

  @Override
  public int getRedColor() {
    return this.redColor;
  }

  @Override
  public int getGreenColor() {
    return this.greenColor;
  }

  @Override
  public int getBlueColor() {
    return this.blueColor;
  }

  @Override
  public Colour toClamp() {
    int red = toClampColorValues(this.getRedColor());
    int green = toClampColorValues(this.getGreenColor());
    int blue = toClampColorValues(this.getBlueColor());
    return new RgbColor(red, green, blue);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + blueColor;
    result = prime * result + greenColor;
    result = prime * result + redColor;
    return result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (!(object instanceof Colour)) {
      return false;
    }

    Colour other = (Colour) object;

    return (this.redColor == other.getRedColor()) && (this.greenColor == other.getGreenColor())
        && (this.blueColor == other.getBlueColor());
  }
}
