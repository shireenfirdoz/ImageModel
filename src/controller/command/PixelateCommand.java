package controller.command;

import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.imageprocessor.ImagePixelateOperator;
import model.images.Image;

import java.io.IOException;

/**
 * This class represents the pixelate command for image chunking via pixelation
 * to an image, used by the controller class.
 */
public class PixelateCommand implements ImageProcessorCommand<Image> {

  private final ImagePixelateOperator pixelateOperator;

  /**
   * This is the constructor to instantiate a command to perform pixelation for an
   * image used by the controller.
   *
   * @param pixelateOperator the pixelate image operator
   */
  public PixelateCommand(ImagePixelateOperator pixelateOperator) {
    super();
    HelperController.isObjectNull(pixelateOperator);
    this.pixelateOperator = pixelateOperator;
  }

  @Override
  public Image execute(ImageModel imageModel) throws ModelValidationException, IOException {
    HelperController.isObjectNull(imageModel);
    return imageModel.pixelate(pixelateOperator);

  }

}
