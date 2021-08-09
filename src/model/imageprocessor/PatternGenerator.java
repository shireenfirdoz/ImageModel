package model.imageprocessor;

import model.exception.ModelValidationException;
import model.images.Image;
import model.pattern.ImagePattern;

import java.io.IOException;

/**
 * This interface represents an operator for the image processing application to
 * generate a cross-stitched pattern for a given image. The pattern generation
 * is performed on a chunked image that is done using pixelation with a given
 * pixelation parameter.
 */
public interface PatternGenerator {

  /**
   * This method generates a cross-stitched pattern for a given image.
   *
   * @param image      and Image type object
   * @param pixelation pixelation parameter
   * @return a generated cross-stitched pattern
   * @throws IOException              for file read/write issues
   * @throws ModelValidationException for validating pixelate operator
   */
  ImagePattern generateImagePattern(Image image, int pixelation)
      throws IOException, ModelValidationException;

  /**
   * This method swaps the DMC color for an image in the generate image pattern. The DMC color
   * is swapped with other DMC color selected from the chosen list of DMC colors.
   * @param xPosition the coordinate for the x
   * @param yPosition the coordinate for the y
   * @param dmcColorName the string for DMC color name
   * @return an ImagePattern
   * @throws IOException exception for file read/write issues
   * @throws ModelValidationException custom exception for model validation failure
   */
  ImagePattern swapDmcColorImagePattern(ImagePattern imagePattern, String xPosition,
      String yPosition, String dmcColorName) throws IOException, ModelValidationException;

  /**
   * This method replaces the DMC color for an image in the generate image pattern. The DMC color
   * is replaced with other DMC color selected from the chosen list of DMC colors.
   * @param xPosition the coordinate for the x
   * @param yPosition the coordinate for the y
   * @return an ImagePattern
   * @throws IOException exception for file read/write issues
   * @throws ModelValidationException custom exception for model validation failure
   */
  ImagePattern replaceDmcColorImagePattern(ImagePattern imagePattern, String xPosition,
      String yPosition) throws IOException, ModelValidationException;


  /**
   * Method to create a custom DM pattern from the subset of DMC colors as selected by the
   * user. The custom pattern updates with the colors chosen.
   * @param dmcColorName the string for DMC color name
   * @return an ImagePattern
   * @throws IOException exception for file read/write issues
   * @throws ModelValidationException custom exception for model validation failure
   */
  ImagePattern createCustomDmcPattern(ImagePattern imagePattern, String dmcColorName,
      int pixelation) throws IOException, ModelValidationException;

}
