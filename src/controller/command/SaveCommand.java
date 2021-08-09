package controller.command;

import controller.exception.CommandValidationException;
import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.images.Image;

import java.io.IOException;

/**
 * This class represents the save command, for saving an updated image to a file
 * on the system, used by the controller class.
 */
public class SaveCommand implements ImageProcessorCommand<Image> {
  private final String fileName;
  private final String isBatchProcess;

  /**
   * This is the constructor for saving a file to the directory after
   * processing the image for filter, transformations, and reduction operations
   * and others, etc. This command is executed via the controller.
   *
   * @param fileName a string type file name
   * @param isBatchProcess 'Y' if file to be loaded for batch processing
   */
  public SaveCommand(String fileName,String isBatchProcess) {
    super();
    // filename and isBatchProcess will be validated in execution.
    this.fileName = fileName;
    this.isBatchProcess = isBatchProcess;

  }

  @Override
  public Image execute(ImageModel model) throws IOException, CommandValidationException, 
      ModelValidationException {
    HelperController.isObjectNull(model);
    HelperController.isObjectNullStr(fileName, "File name is not valid.");
    HelperController.isFileNotValidImg(fileName, "File extension is not valid.");
    HelperController.isObjectNullStr(isBatchProcess,
        "File to be loaded from resource for batch process parameter is empty.");
    try {
      model.writeImage(fileName, isBatchProcess);
    } catch (IOException e) {
      throw new IOException("Unable to write image file.");
    }
    return null;
  }

}
