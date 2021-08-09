package controller.control;

import controller.exception.CommandValidationException;
import controller.utilities.HelperController;
import model.ImageModel;
import model.exception.ModelValidationException;

import java.io.IOException;
import java.util.Scanner;

/**
 * This is the implementation class for the controller for the Image Processing
 * application. It interacts with the user to accept the commands and validate
 * the and then ask the model to execute those commands before returning the
 * acquired result from the model to the user of the application.
 */
public class ImageControllerImpl extends AbstractImageController {

  private final Readable inStream;
  private final Appendable outStream;

  /**
   * The default constructor for the controller implementation class. This
   * constructor is used to instantiate a constructor passing readable and
   * appendable interfaces.
   *
   * @param inStream  the input stream
   * @param outStream the output stream
   */
  public ImageControllerImpl(Readable inStream, Appendable outStream, ImageModel imageModel) {
    super(imageModel);
    HelperController.isObjectNull(inStream);
    HelperController.isObjectNull(outStream);
    this.inStream = inStream;
    this.outStream = outStream;
  }

  @Override
  public void start() throws IOException {
    Scanner scan = new Scanner(this.inStream);
    try {

      while (scan.hasNext()) {
        this.execute(scan);
      }

    } catch (IOException e) {
      this.outStream.append("unable to read or write file ");
    } catch (CommandValidationException | ModelValidationException e) {
      this.outStream.append(e.getMessage());
    } finally {
      scan.close();
    }
  }
}
