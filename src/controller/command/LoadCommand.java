package controller.command;

import controller.exception.CommandValidationException;
import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.images.Image;

import java.io.IOException;

/**
 * This class represents the load command, for loading an image file from the
 * given directory, used by the controller class.
 */
public class LoadCommand implements ImageProcessorCommand<Image> {

  private final String fileName;
  private final String isBatchProcess;

  /**
   * This is the constructor to instantiate an object for the load command for the
   * controller which the controller uses to load an image for performing the
   * different implemented operations like filter, transform, etc.
   *
   * @param fileName      a string type file name
   * @param isBatchProcess 'Y' if file to be loaded for batch processing
   */
  public LoadCommand(String fileName, String isBatchProcess) {
    super();
    // filename and isBatchProcess will be validated in execution.
    this.fileName = fileName;
    this.isBatchProcess = isBatchProcess;

  }

  @Override
  public Image execute(ImageModel imageModel) throws CommandValidationException, IOException, 
      ModelValidationException {
    HelperController.isObjectNull(imageModel);
    HelperController.isObjectNullStr(fileName, "File name is not valid.");
    HelperController.isFileNotValidImg(fileName, "File extension is not valid.");
    HelperController.isObjectNullStr(isBatchProcess,
        "File to be loaded from resource for batch process parameter is empty.");
    try {
      imageModel.readImage(fileName, isBatchProcess);
    } catch (IOException e) {
      throw new IOException("Unable to read image file.");
    }
    return null;
  }

}
