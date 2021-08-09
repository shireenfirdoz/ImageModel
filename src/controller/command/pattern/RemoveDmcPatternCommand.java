package controller.command.pattern;

import controller.command.ImageProcessorCommand;
import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.imageprocessor.PatternGenerator;
import model.pattern.ImagePattern;

import java.io.IOException;

/**
 * This class represents the Remove generated DMC Pattern command for the image
 * over which a pattern has been generated.
 */
public class RemoveDmcPatternCommand implements ImageProcessorCommand<ImagePattern> {

  private final PatternGenerator patternGenerator;
  private final String xPosition;
  private final String yPosition;

  /**
   * This is the constructor for instantiating an object for the removing dmc
   * cross stitched pattern generation command which is used by the controller to
   * do removal of dmccolor of the cross-stitched patten.
   *
   * @param patternGenerator the imagepattern generator for cross stitched
   *                         pattern.
   * @param xPosition        x position of the dmc color to be removed
   * @param yPosition        y position of the dmc color to be removed
   */
  public RemoveDmcPatternCommand(PatternGenerator patternGenerator, String xPosition,
      String yPosition) {
    super();
    HelperController.isObjectNull(patternGenerator);
    this.patternGenerator = patternGenerator;
    // this will be validated in the pattern generation apply method by model.
    this.xPosition = xPosition;
    this.yPosition = yPosition;
  }

  @Override
  public ImagePattern execute(ImageModel imageModel) throws ModelValidationException, IOException {
    HelperController.isObjectNull(imageModel);
    ImagePattern imagePattern = imageModel.replaceDmcColorImagePattern(patternGenerator, xPosition,
        yPosition);
    return imagePattern;
  }
}
