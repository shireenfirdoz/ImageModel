package controller.command;

import controller.exception.CommandValidationException;
import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;
import model.imageprocessor.PatternGenerator;
import model.images.Image;

import java.io.IOException;

/**
 * This class represents the save pattern command, for saving the generated
 * pattern for an image, used by the controller class.
 */
public class SavePatternCommand implements ImageProcessorCommand<Image> {

  private final String fileName;
  private final String isBatchProcess;
  private final PatternGenerator patternGenerator;

  /**
   * This is the constructor for the Save Pattern Command for saving the generated
   * cross-stitched pattern used by the controller.
   * 
   * @param fileName         the name of cross stichted pattern file
   * @param isBatchProcess   'Y' if file to be loaded for batch processing
   * @param patternGenerator the imagepattern generator for cross stiched pattern.
   */
  public SavePatternCommand(String fileName, String isBatchProcess,
      PatternGenerator patternGenerator) {
    super();
    HelperController.isObjectNull(patternGenerator);
    // filename and isBatchProcess will be validated in execution.
    this.fileName = fileName;
    this.isBatchProcess = isBatchProcess;
    this.patternGenerator = patternGenerator;

  }

  @Override
  public Image execute(ImageModel model)
      throws IOException, CommandValidationException, ModelValidationException {
    HelperController.isObjectNull(model);
    HelperController.isObjectNullStr(fileName, "File name is not valid.");
    HelperController.isFileNotValidText(fileName, "File extension is not valid.");
    HelperController.isObjectNullStr(isBatchProcess,
        "File to be loaded from resource for batch process parameter is empty.");

    try {

      model.writeFile(fileName, isBatchProcess, patternGenerator);

    } catch (IOException e) {
      throw new IOException("Unable to write file.");
    }
    return null;

  }
}
