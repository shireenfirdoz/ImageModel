package controller.command;

import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.imageprocessor.PatternGenerator;
import model.images.Image;

import java.io.IOException;

/**
 * This class represents the pattern generation command, for for creating a
 * cross stitched pattern for an image, used by the controller class.
 */
public class PatternCommand implements ImageProcessorCommand<Image> {

  private final PatternGenerator patternGenerator;

  /**
   * This is the constructor for instantiating an object for the cross stitched
   * pattern generation command which is used by the controller to perform the
   * cross-stitched pattern generation for a given image.
   *
   * @param patternGenerator the imagepattern generator for cross stitched pattern.
   */
  public PatternCommand(PatternGenerator patternGenerator) {
    super();
    HelperController.isObjectNull(patternGenerator);
    this.patternGenerator = patternGenerator;
  }

  @Override
  public Image execute(ImageModel imageModel) throws ModelValidationException, IOException {
    HelperController.isObjectNull(imageModel);
    imageModel.generateImagePattern(patternGenerator);
    // returning null to adhere the command pattern design for batch processing
    return null;
  }
}
