package controller.control;

import java.util.List;

import model.pixel.Position;

/**
 * This interface represents a set of features that the program offers. Each
 * feature is exposed as a function in this interface. This function is used
 * suitably as a callback by the view, to pass control to the controller. How
 * the view uses them as callbacks is completely up to how the view is designed
 * (e.g. it could use them as a callback for a button, or a callback for a
 * dialog box, or a set of text inputs, etc.). Each function is designed to take
 * in the necessary data to complete that functionality.
 */

public interface ImageFeatures {

  /**
   * Exit the program.
   */
  void exitProgram();

  /**
   * Load a Image File into the application.
   */
  void loadFile();

  /**
   * Blur a given image.
   */
  void blur();

  /**
   * Sharpen a given image.
   */
  void sharpen();

  /**
   * Covert a given image to Sepia Tone.
   */
  void sepiaTone();

  /**
   * Convert a given image to Grey Scale.
   */
  void greyScale();

  /**
   * Dither a given image.
   */
  void dither();

  /**
   * Reduce the colors for a given image.
   */
  void reduceColor();

  /**
   * Create Mosaic Art for a given image.
   */
  void mosaic();

  /**
   * Pixelate a given image.
   */
  void pixelate();

  /**
   * Save the image that has been processed.
   * 
   * @param fileName the filename
   */
  void saveFile(String fileName);

  /**
   * Generate Cross-Stitched Pattern for a given image.
   */
  void generatePattern();

  /**
   * Menu Click for swapping DMC Colors for an Image in the pattern generated.
   */
  void swapDmcColorMenuClick();

  /**
   * Swap DMC Colors for an Image in the pattern generated.
   * 
   * @param position     position of the dmc color to be replaced
   * @param dmcColorName dmc color to be replaced with
   */
  public void swapDmcColor(Position position, String dmcColorName);

  /**
   * Menu Click to create a custom DMC Pattern for an image using selective DMC
   * colors.
   * 
   */
  void customDmcPatternMenuClick();

  /**
   * Remove a DMC color from the given image.
   * 
   * @param position position of the dmc color to be replaced
   */
  public void removeDmcColor(Position position);

  /**
   * Menu Click to remove a DMC color from the given image.
   */
  void removeDmcColorMenuClick();

  /**
   * Create a custom DMC Pattern for an image using selective DMC colors.
   * 
   * @param dmcColorNameList list of dmc color to be used to create pattern with
   */
  public void customDmcColorPattern(List<String> dmcColorNameList);

  /**
   * Save the generated image pattern to a file.
   * 
   * @param fileName the fileName to save the file to
   */
  void savePattern(String fileName);

  /**
   * Execute a batch file to process the instructions from the batch file.
   * 
   */
  void executeBatch();

  /**
   * Method to add load file command to batch file for batch file execution.
   */
  void loadCommand();

  /**
   * Method to add save file command to batch file for batch file execution.
   */
  void saveCommand();

  /**
   * Method to save pattern load command to batch file for batch file execution.
   */
  void savePatternCommand();

  /**
   * Method to add blur command to batch file for batch file execution.
   */
  void blurCommand();

  /**
   * Method to add sharpen command to batch file for batch file execution.
   */
  void sharpenCommand();

  /**
   * Method to add grey scale command to batch file for batch file execution.
   */
  void greyScaleCommand();

  /**
   * Method to add sepia tone command to batch file for batch file execution.
   */
  void sepiaCommand();

  /**
   * Method to add dither command to batch file for batch file execution.
   */
  void ditherCommand();

  /**
   * Method to add mosaic art command to batch file for batch file execution.
   */
  void mosaicCommand();

  /**
   * Method to add reduce image command to batch file for batch file execution.
   */
  void reduceCommand();

  /**
   * Method to add pixelate image command to batch file for batch file execution.
   */
  void pixelateCommand();

  /**
   * Method to cross stitch pattern generation load command to batch file for
   * batch file execution.
   */
  void patternCommand();

}
