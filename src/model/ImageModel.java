package model;

import model.exception.ModelValidationException;
import model.imageprocessor.ImageOperator;
import model.imageprocessor.ImagePixelateOperator;
import model.imageprocessor.PatternGenerator;
import model.images.Image;
import model.pattern.ImagePattern;
import model.pixel.Colour;

import java.io.IOException;
import java.util.Map;

/**
 * The ImageModel represents the different operation that can be performed on
 * the image like filter, transform or reduce the image.
 */
public interface ImageModel {
  /**
   * Method to apply different filters, color transformations's , color
   * reduction's and create mosaic operations to an image. The different filters
   * can be blur filter, sharpen filter etc.The different transformations can be
   * grey scale transform, sepia tone transform etc.The color reduction can be
   * with essence or with essence to a given image in order to reduce the number
   * of colors in the image. It can be used to reduce the image to a 2-color,
   * 4-color, 8-color etc. depending on the parameter passed.The create mosaic
   * operator chunk image for a given input image using a given seed length. The
   * seed number determines the number of seed value pixels to be used to create a
   * mosaic art.
   *
   * @param operator The operator of ImageOperator type that applies the
   *                 respective operation.
   * @return A new image with the operation applied.
   * @throws ModelValidationException is thrown to handle operation specific input
   *                                  parameters validation.
   */
  Image applyImageOperation(ImageOperator operator) throws ModelValidationException,IOException;

  /**
   * Method to perform pixelation of a given image with a given pixelation
   * parameter. Superpixels are generated using the pixelation parameter.
   *
   * @param operator The operator of ImageOperator type that applies the
   *                 respective pixelation.
   * @return an Image type with pixelation
   */
  Image pixelate(ImagePixelateOperator operator) throws ModelValidationException,IOException;

  /**
   * This method generates a cross-stitched pattern for a given image. It calls
   * the pixelation to create image chunking before performing the pattern
   * generation.
   *
   * @param pattern an object for type PatternGenerator
   * @return a generated cross-stitched ImagePattern with unicode symbol
   * @throws IOException              for file read/write errors
   * @throws ModelValidationException is thrown if seedLength is invalid.
   */
  ImagePattern generateImagePattern(PatternGenerator pattern)
      throws IOException, ModelValidationException;

  /**
   * This method writes an image pattern to a file with a given filename. The
   * pattern is written as a .txt file to the directory.
   *
   * @param filename       a string file name
   * @param isBatchProcess Y if file to be loaded from resources for batchProcess
   * @param imagePattern   the imagepattern generator for cross stiched pattern.
   * @throws IOException              for file write error
   * @throws ModelValidationException is thrown if image pattern operation not
   *                                  performed.
   */
  void writeFile(String filename, String isBatchProcess, PatternGenerator imagePattern)
      throws IOException, ModelValidationException;

  /**
   * This method reads an image file from the directory with a given file name as
   * string and reads it as an object of the Image type.
   *
   * @param filename       a string file name
   * @param isBatchProcess Y if file to be loaded from resources for batchProcess
   * @throws IOException              for file read error
   * @throws ModelValidationException is thrown if isBatchProcess is invalid.
   */
  void readImage(String filename, String isBatchProcess)
      throws IOException, ModelValidationException;

  /**
   * This method writes an Image object to the directory with a given file name.
   *
   * @param filename       a string file name
   * @param isBatchProcess Y if file to be loaded from resources for batchProcess
   * @throws IOException              for file read error
   * @throws ModelValidationException is thrown if image is not loaded.
   */
  public void writeImage(String filename, String isBatchProcess)
      throws IOException, ModelValidationException;

  /**
   * This method swaps the DMC color for an image in the generate image pattern. The DMC color
   * is swapped with other DMC color selected from the chosen list of DMC colors.
   * @param generator an object for the image pattern generator
   * @param xPosition the coordinate for the x
   * @param yPosition the coordinate for the y
   * @param dmcColorName the string for DMC color name
   * @return an ImagePattern
   * @throws IOException exception for file read/write issues
   * @throws ModelValidationException custom exception for model validation failure
   */
  ImagePattern swapDmcColorImagePattern(PatternGenerator generator, String xPosition, 
      String yPosition, String dmcColorName) throws IOException, ModelValidationException;

  /**
   * This method replaces the DMC color for an image in the generate image pattern. The DMC color
   * is replaced with other DMC color selected from the chosen list of DMC colors.
   * @param generator an object for the image pattern generator
   * @param xPosition the coordinate for the x
   * @param yPosition the coordinate for the y
   * @return an ImagePattern
   * @throws IOException exception for file read/write issues
   * @throws ModelValidationException custom exception for model validation failure
   */
  ImagePattern replaceDmcColorImagePattern(PatternGenerator generator, String xPosition, 
      String yPosition) throws IOException, ModelValidationException;

  /**
   * Method to get the DMc color paletter map for the DMC colors.
   * @return a map for the DMC colors
   * @throws IOException for file read/write issue
   */
  public Map<String, Colour> getDmcColorPalleteMap() throws IOException;

  /**
   * Method to create a custom DM pattern from the subset of DMC colors as selected by the
   * user. The custom pattern updates with the colors chosen.
   * @param generator an object for the image pattern generator
   * @param dmcColorName the string for DMC color name
   * @return an ImagePattern
   * @throws IOException exception for file read/write issues
   * @throws ModelValidationException custom exception for model validation failure
   */
  ImagePattern createCustomDmcPattern(PatternGenerator generator, String dmcColorName)
      throws IOException, ModelValidationException;
}
