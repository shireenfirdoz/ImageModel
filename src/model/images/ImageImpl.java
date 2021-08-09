package model.images;

import model.utilities.Helper;

/**
 * The ImageImpl is the implementation class for the Image that represents image
 * which is composed of pixels across the row and the columns. A 2D matrix of
 * Pixels.
 */
public abstract class ImageImpl implements Image {

  protected final int imageHeight;

  protected final int imageWidth;

  /**
   * Constructor for the ImageImpl class that can initialize an object of the
   * Image class, a 2D collection of Pixels.
   *
   * @param imageHeight The height of the image.
   * @param imageWidth  The width of the image.
   */
  protected ImageImpl(int imageHeight, int imageWidth) {
    super();
    if (Helper.isNegative(imageHeight) || Helper.isNegative(imageWidth)) {
      throw new IllegalArgumentException();
    }

    this.imageHeight = imageHeight;
    this.imageWidth = imageWidth;
  }

  @Override
  public int getImageHeight() {
    return this.imageHeight;
  }

  @Override
  public int getImageWidth() {
    return this.imageWidth;
  }

  @Override
  public String toString() {

    StringBuffer sb = new StringBuffer();
    sb.append("ImageImpl [");
    sb.append("imageHeight=");
    sb.append(imageHeight);
    sb.append(", imageWidth=");
    sb.append(imageWidth);
    sb.append("]");
    return sb.toString();

  }

}
