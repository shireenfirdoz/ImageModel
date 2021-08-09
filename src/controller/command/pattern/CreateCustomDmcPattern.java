package controller.command.pattern;

import controller.command.ImageProcessorCommand;
import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.imageprocessor.PatternGenerator;
import model.pattern.ImagePattern;

import java.io.IOException;

/**
 * This class represents a custom DMC pattern where a user can 
 * select a subset of DMC colors to generate the pattern generated with 
 * just the selected colors for the loaded image.
 */
public class CreateCustomDmcPattern implements ImageProcessorCommand<ImagePattern> {

  private final PatternGenerator patternGenerator;
  private final String listCustomDmcColor;

  /**
   * This is the constructor for instantiating an object for the creating dmc
   * cross stitched from the custom list of dmc provided and this pattern
   * generation command which is used by the controller to do creation of this
   * custom pattern of dmccolor of the cross-stitched patten.
   *
   * @param patternGenerator   the imagepattern generator for cross stitched
   *                           pattern.
   * @param listCustomDmcColor list of custom dmc color
   * 
   */
  public CreateCustomDmcPattern(PatternGenerator patternGenerator,
      String listCustomDmcColor) {
    super();
    HelperController.isObjectNull(patternGenerator);
    this.patternGenerator = patternGenerator;
    // this will be validated in the pattern generation apply method by model.
    this.listCustomDmcColor = listCustomDmcColor;
  }

  @Override
  public ImagePattern execute(ImageModel imageModel) throws ModelValidationException, IOException {
    HelperController.isObjectNull(imageModel);
    ImagePattern imagePattern = imageModel.createCustomDmcPattern(patternGenerator,
        listCustomDmcColor);
    return imagePattern;
  }
}
