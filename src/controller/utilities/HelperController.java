package controller.utilities;

import controller.command.ImageProcessorCommand;
import controller.exception.CommandValidationException;
import model.images.Image;
import model.pattern.ImagePattern;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * This Helper class represents the basic utility methods that are used by the
 * controller to perform the operations that are common.
 */
public class HelperController {

  /**
   * This method validate whether argument is null or not.
   *
   * @param object Object with datatype as object.
   */
  public static void isObjectNull(Object object) {

    if (null == object) {
      throw new IllegalArgumentException("Object is null");
    }
  }

  /**
   * This method validate whether command is null or not.
   *
   * @param object String with datatype as object.
   * @throws CommandValidationException excption if command is invalid.
   */
  public static void isObjectNullStr(String object, String message)
      throws CommandValidationException {

    if (null == object && "".equals(object)) {
      throw new CommandValidationException(message);
    }
  }

  /**
   * This method validate whether file type is valid or not.
   *
   * @param object  String with datatype as object.
   * @param message the error message.
   * @throws CommandValidationException exception when filename type is invalid
   */
  public static void isFileNotValidImg(String object, String message)
      throws CommandValidationException {
    if (null != object && !"".equals(object)) {
      String ext = object.substring(object.lastIndexOf(".") + 1);
      if (null != ext && !"".equals(ext)) {
        if (!("jpg".equals(ext) || "jpeg".equals(ext) || "png".equals(ext))) {
          throw new CommandValidationException(message);
        }
      }

    }

  }

  /**
   * This method validate whether file type is valid or not.
   *
   * @param object  String with datatype as object.
   * @param message the error message.
   * @throws CommandValidationException exception when filename type is invalid
   */
  public static void isFileNotValidText(String object, String message)
      throws CommandValidationException {
    if (null != object && !"".equals(object)) {
      String ext = object.substring(object.lastIndexOf(".") + 1);
      if (null != ext && !"".equals(ext)) {
        if (!("txt".equals(ext))) {
          throw new CommandValidationException(message);
        }
      }

    }
  }

  /**
   * This helper method validate the command to check whether it is a valid 
   * or not.
   * @param input string input for the command
   * @param knownCommands the map of known commands
   */
  public static void validateCommand(String input,
      Map<String, Function<Scanner, ImageProcessorCommand<Image>>> knownCommands) {
    isObjectNull(knownCommands);
    isObjectNull(input);
    if (input == null || (null != input && input.equals(""))) {
      new CommandValidationException("Command is empty. ");
    } else if (!knownCommands.containsKey(input)) {
      new CommandValidationException("Invalid command. ");

    }
  }
  
  /**
   * This helper method will check whether a correct command has been specified for
   * the pattern generation for an image.
   * @param input input string input for the command
   * @param knownCommands the map of known commands
   */
  public static void validatePatternCommand(String input,
      Map<String, Function<Scanner, ImageProcessorCommand<ImagePattern>>> knownCommands) {
    isObjectNull(knownCommands);
    isObjectNull(input);
    if (input == null || (null != input && input.equals(""))) {
      new CommandValidationException("Command is empty. ");
    } else if (!knownCommands.containsKey(input)) {
      new CommandValidationException("Invalid command. ");

    }
  }
}
