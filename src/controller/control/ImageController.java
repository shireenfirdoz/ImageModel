package controller.control;

import java.io.IOException;

/**
 * This interface represents the controller in the Image Processing application
 * which has been created following the MVC architecture. The controller class
 * interacts with the user of the application and passes on the commands to the
 * model for execution and returns back the results to the user.
 */
public interface ImageController {

  /**
   * The main entry point for the controller which starts the execution for the
   * Image Processing application.
   *
   * @throws IOException when IO operation fails
   */
  void start() throws IOException;
}
