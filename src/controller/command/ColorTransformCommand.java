package controller.command;

import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.imageprocessor.ImageOperator;
import model.images.Image;

import java.io.IOException;


/**
 * This class represents the color transform command, for applying the grey
 * scale and sepia tone transformation to an image, used by the controller
 * class.
 */
public class ColorTransformCommand implements ImageProcessorCommand<Image> {

  private final ImageOperator colorTransformOperator;

  /**
   * The constructor for the grey scale and sepia tone color
   * transformation command that applies the color transformation onto an image.
   *
   * @param colorTransformOperator the grey scale or sepia tone image operator
   */
  public ColorTransformCommand(ImageOperator colorTransformOperator) {
    super();
    HelperController.isObjectNull(colorTransformOperator);
    this.colorTransformOperator = colorTransformOperator;
  }

  @Override
  public Image execute(ImageModel imageModel) throws ModelValidationException, IOException {
    HelperController.isObjectNull(imageModel);
    return imageModel.applyImageOperation(colorTransformOperator);
  }

}
