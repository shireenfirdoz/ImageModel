package controller.command;

import controller.exception.CommandValidationException;
import model.ImageModel;
import model.exception.ModelValidationException;

import java.io.IOException;

/**
 * This interface represents the commands for the image processor application.
 * the different commands are to load the file, perform operations, and save the
 * new file to the directory.
 * 
 *  @param <R> the type of the return parameter for the command
 */
public interface ImageProcessorCommand<R> {
  /**
   * The execute method performs a command execution with the respective arguments
   * on what operation is to be performed.
   *
   * @param model the Image Model
   * @return a result after performing operation
   * @throws IOException                on file read/write issues
   * @throws CommandValidationException on command parameter not valid
   * @throws ModelValidationException   on exception thrown from model while
   *                                    validating input
   */
  R execute(ImageModel model)
      throws IOException, CommandValidationException, ModelValidationException;

}
