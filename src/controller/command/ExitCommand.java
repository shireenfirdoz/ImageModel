package controller.command;

import model.ImageModel;
import model.images.Image;

import java.io.IOException;

/**
 * This class represents the exit/quit command for the controller. The batch
 * file determines when a command should exit from the execution flow.
 */
public class ExitCommand implements ImageProcessorCommand<Image> {

  @Override
  public Image execute(ImageModel model) throws IOException {
    System.exit(0); 
    return null;
  }


}
