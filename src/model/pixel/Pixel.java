package model.pixel;

import model.imagematrix.TransformationMatrix;
import model.imageprocessor.Clamping;

/**
 * Pixel interface represent a pixel in an image which has a RGB value and a
 * position attached to it . It capture the various intensities for the
 * different channels, the Red, Green and the Blue.
 */
public interface Pixel extends Clamping<Colour> {

  /**
   * This method performs the color transformation operation on each of the pixel
   * RGB values.
   *
   * @param matrix The transformation matrix for performing the color
   *               transformation.
   * @return It gives back the copy of the pixel object
   */
  Pixel colorTransformation(TransformationMatrix matrix);

  /**
   * Getter to get the red intensity.
   *
   * @return int Value representing the red intensity.
   */
  int getRedColor();

  /**
   * Getter to get the green intensity.
   *
   * @return int Value representing the green intensity.
   */
  int getGreenColor();

  /**
   * Getter to get the blue intensity.
   *
   * @return int Value representing the blue intensity.
   */
  int getBlueColor();

  /**
   * Getter to get the color of the pixel.
   * 
   * @return color object for the pixel.
   */
  Colour getColor();

}
