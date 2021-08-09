package model.pixel;

import model.imagematrix.TransformationMatrix;
import model.imageprocessor.Clamping;

/**
 * Color interface represent the different colors across the channels that occur
 * in an image. It capture the various intensities for the different channels,
 * the Red, Green and the Blue. The intensities lie in the range 0-255.
 */
public interface Colour extends Clamping<Colour> {
  /**
   * This method performs the color transformation operation on each of the color
   * RGB values.
   *
   * @param matrix The transformation matrix for performing the color
   *               transformation.
   * @return It gives back the copy of the color object.
   */
  Colour colorTransformation(TransformationMatrix matrix);

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
}
