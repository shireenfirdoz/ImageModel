package controller.command;

import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.imageprocessor.ImageOperator;
import model.images.Image;

import java.io.IOException;

/**
 * This class represents the mosaic command, for applying the mosaic chunking to
 * an image, used by the controller class.
 */
public class MosaicCommand implements ImageProcessorCommand<Image> {

  private final ImageOperator mosaicOperator;

  /**
   * This is the constructor for the command for the controller which the
   * controller uses to execute and create a mosaic art from a given image.
   * 
   * @param mosaicOperator the mosaic image operator
   */
  public MosaicCommand(ImageOperator mosaicOperator) {
    super();
    HelperController.isObjectNull(mosaicOperator);
    this.mosaicOperator = mosaicOperator;
  }

  @Override
  public Image execute(ImageModel imageModel) throws ModelValidationException, IOException {
    HelperController.isObjectNull(imageModel);

    return imageModel.applyImageOperation(mosaicOperator);

  }

}
