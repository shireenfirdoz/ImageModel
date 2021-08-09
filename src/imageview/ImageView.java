package imageview;

import java.awt.image.BufferedImage;
import java.util.Map;

import controller.control.ImageFeatures;
import model.pixel.Colour;
import model.pixel.SuperPixel;

/**
 * The interface for our view class.
 */
public interface ImageView {

  /**
   * Get the set of feature callbacks that the view can use.
   * 
   * @param f the set of feature callbacks as a Features object
   */
  void setFeatures(ImageFeatures f);

  /**
   * Method to show an error message to the user as specified in the message.
   * 
   * @param message the error message to be shown
   */
  void errorMessage(String message);

  /**
   * Method to show an operation success method to the user.
   * 
   * @param message the success message to be shown
   */
  void successMessage(String message);

  /**
   * Method to show the image to the user in the panel.
   * 
   * @param image an image of type BufferedImage
   */
  void showBufferedImage(BufferedImage image);

  /**
   * Method to toggle the visibility for a component, to make it visible.
   */
  void setVisible();

  /**
   * Accessor to get the DMC Palette Map.
   * 
   * @param dmcPallete the DMC Palette Map
   */
  void getDmcPalleteMap(Map<String, Colour> dmcPallete);

  /**
   * Method to show the DMC Palette in the GUI on the basis of selection.
   * 
   * @param multiSelect a boolean value for multiSelect
   */
  public void showDmcPallete(boolean multiSelect);

  /**
   * Method to show the Legend in the GUI.
   * 
   * @param legend the map for the legend for the image
   */
  void showLegend(Map<String, String> legend);

  /**
   * Method to show the Image Pattern that is generated for the Image.
   * 
   * @param image    an image of type BufferedImage
   * @param imageMap a 2D array of SuperPixel map
   */
  void showImagePattern(BufferedImage image, SuperPixel[][] imageMap);

  /**
   * Method to show the Pattern Dimension for the generated pattern for the image.
   * 
   * @param patternHeight the int height for the image
   * @param patternWidth  the int width for the image
   */
  void showPatternDimension(int patternHeight, int patternWidth);

  /**
   * Method to generate an information message for the user.
   * 
   * @param message the information message string
   */
  public void infoMessage(String message);

  /**
   * Method to remove the showing for the DMC color view in the GUI.
   */
  public void showRemoveDmcColorView();

  /**
   * Method to show the cross stitched pattern view in the GUI.
   */
  public void showPatternView();

  /**
   * Method to show the file chooser in the GUI.
   */
  public void getImage();

  /**
   * Method to remove the previous loaded image in the GUI.
   */
  public void removePreviousImage();

  /**
   * Method to get the file name.
   */
  public String getFileName();

  /**
   * Method to get the input from the user.
   * 
   * @return input from user
   */
  public String getInput(String message);
  
  /**
   * Method to append  the commands name.
   */
  public void appendTextArea(String command);
  
  /**
   * Method to get the text area content for batch processing.
   */
  public String getTextArea();

}
