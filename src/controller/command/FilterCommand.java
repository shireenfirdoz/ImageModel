package controller.command;

import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.imageprocessor.ImageOperator;
import model.images.Image;

import java.io.IOException;

/**
 * This class represents the blur command, for applying the blur and sharpen
 * filter to an image, used by the controller class.
 */
public class FilterCommand implements ImageProcessorCommand<Image> {

  private final ImageOperator filterOperator;

  /**
   * The constructor for the blur and sharpen command filter that applies
   * the filter onto an image.
   *
   * @param filterOperator the blur or sharpen operator
   */
  public FilterCommand(ImageOperator filterOperator) {
    super();
    HelperController.isObjectNull(filterOperator);
    this.filterOperator = filterOperator;
  }

  @Override
  public Image execute(ImageModel imageModel) throws ModelValidationException, IOException {
    HelperController.isObjectNull(imageModel);
    return imageModel.applyImageOperation(filterOperator);
  }

}
