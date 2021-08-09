package controller.driver;

import controller.control.ImageController;
import controller.control.ImageControllerImpl;
import controller.control.ImageViewController;
import imageview.ImageViewImpl;
import model.ImageModel;
import model.ImageModelImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The ImageProcessor class represents the driver for the Image Processing
 * application. It instantiates the controller and the model and calls onto the
 * controller to read user input or instructions, query the instructions with
 * the model and return the returns.
 */
public class Driver {

  /**
   * This method get the absolute location for a file name in the project
   * directory.
   *
   * @param filename a string filename
   * @return a string of absolute filename
   */
  public static String getAbsoluteFileName(String filename) {

    File f = new File("abc");

    // Get the absolute path of file f
    String path = f.getAbsolutePath();
    String absolute = path.substring(0, path.length() - 3);
    // the file path of absolute file
    StringBuffer fileNameBuffer = new StringBuffer();

    fileNameBuffer.append(absolute).append(filename);

    return fileNameBuffer.toString();
  }

  /**
   * The starting point of the application.
   *
   * @param args Not used
   */
  public static void main(String[] args) {

    try {

      ImageModel imageModel = new ImageModelImpl();
      ImageController imageController = null;

      if (null != args[0] && "-script".equals(args[0])) {
        String absolutefileName = getAbsoluteFileName(args[1]);
        InputStream inputStream = new FileInputStream(absolutefileName);
        imageController = new ImageControllerImpl(new InputStreamReader(inputStream), System.out,
            imageModel);
        imageController.start();

      } else if (null != args[0] && "-interactive".equals(args[0])) {
        imageController = new ImageViewController(imageModel,
            new ImageViewImpl("Image Processing Application"));
        imageController.start();
      } else {
        System.out.println("Invalid argument while running the jar");
      } 

      System.out.println("");
      System.out.println("Operation completed.");

    } catch (IOException e) {
      System.out.println("Unable to do IO Operation.");
    }
  }
  
}
