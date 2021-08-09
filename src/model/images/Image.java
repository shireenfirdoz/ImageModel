package model.images;

import model.pixel.Pixel;

/**
 * The Image interface class represents image which is composed of pixels across
 * the rows and the columns. A 2D matrix of Pixels.
 */
public interface Image {
  /**
   * This method gets the 2D array of pixel having colors in RGB format.
   *
   * @return A 2 D array of pixel type.
   */
  Pixel[][] getPixelArray();

  /**
   * This methods return the image height.
   *
   * @return height of the image.
   */
  int getImageHeight();

  /**
   * This methods return the image width.
   *
   * @return width of the image.
   */
  int getImageWidth();
}
