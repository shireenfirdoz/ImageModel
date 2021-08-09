package controller.command.pattern;

import controller.command.ImageProcessorCommand;
import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.imageprocessor.PatternGenerator;
import model.pattern.ImagePattern;

import java.io.IOException;

/**
 * This class represents the Command for the DMC Pattern generation for an loaded image.
 * The pattern is generated using the DMC colors provided.
 */
public class DmcPatternCommand implements ImageProcessorCommand<ImagePattern> {

  private final PatternGenerator patternGenerator;

  /**
   * This is the constructor for instantiating an object for the cro stitched
   * pattern generation command which is used by the controller to perform the
   * cross-stitched patten generation for a given image.
   *
   * @param patternGenerator the imagepattern generator for cross stitched pattern.
   */
  public DmcPatternCommand(PatternGenerator patternGenerator) {
    super();
    HelperController.isObjectNull(patternGenerator);
    this.patternGenerator = patternGenerator;
  }

  @Override
  public ImagePattern execute(ImageModel imageModel) throws ModelValidationException, IOException {
    HelperController.isObjectNull(imageModel);
    ImagePattern imagePattern = imageModel.generateImagePattern(patternGenerator);
    return imagePattern;
  }
}
