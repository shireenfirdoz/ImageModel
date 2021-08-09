package controller.command;

import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.imageprocessor.ImageOperator;
import model.images.Image;

import java.io.IOException;

/**
 * This class represents the dither command, for applying the reduction to an
 * image, used by the controller class.
 */
public class ReductionCommand implements ImageProcessorCommand<Image> {

  private final ImageOperator reductionOperator;

  /**
   * This is the constructor to instantiate a command to reduce an image with
   * essence used by the controller.
   *
   * @param reductionOperator the color reduce image operator
   */
  public ReductionCommand(ImageOperator reductionOperator) {
    super();
    HelperController.isObjectNull(reductionOperator);
    this.reductionOperator = reductionOperator;

  }

  @Override
  public Image execute(ImageModel imageModel) throws ModelValidationException, IOException {
    HelperController.isObjectNull(imageModel);
    return imageModel.applyImageOperation(reductionOperator);

  }

}
