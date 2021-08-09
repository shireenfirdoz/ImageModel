package controller.command.pattern;

import controller.command.ImageProcessorCommand;
import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.imageprocessor.PatternGenerator;
import model.pattern.ImagePattern;

import java.io.IOException;

/**
 * This class represents the command for swapping the DMC color that has been generated
 * for a given image. The user needs to select the superpixel that needs the color to
 * be swapped with another in the palette.
 */
public class SwapDmcPatternCommand implements ImageProcessorCommand<ImagePattern> {

  private final PatternGenerator patternGenerator;
  private final String xPosition;
  private final String yPosition;
  private final String dmcColorName;

  /**
   * This is the constructor for instantiating an object for the swap dmc cross
   * stitched pattern generation command which is used by the controller to do
   * swapping of dmccolors of the cross-stitched patten.
   *
   * @param patternGenerator the imagepattern generator for cross stitched
   *                         pattern.
   * @param xPosition  x position of the dmc color to be replaced
   * @param yPosition  y position of the dmc color to be replaced
   * @param dmcColorName name of the dmc color to be replaced with
   */
  public SwapDmcPatternCommand(PatternGenerator patternGenerator, String xPosition,
      String yPosition, String dmcColorName) {
    super();
    HelperController.isObjectNull(patternGenerator);
    this.patternGenerator = patternGenerator;
    // this will be validated in the pattern generation apply method by model.
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.dmcColorName = dmcColorName;
  }

  @Override
  public ImagePattern execute(ImageModel imageModel) throws ModelValidationException, IOException {
    HelperController.isObjectNull(imageModel);
    ImagePattern imagePattern = imageModel.swapDmcColorImagePattern(patternGenerator, xPosition,
        yPosition, dmcColorName);
    return imagePattern;
  }
}
