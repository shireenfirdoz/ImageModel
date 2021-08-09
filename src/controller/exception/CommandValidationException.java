package controller.exception;

/**
 * This class represents an exception raise for a command validation error.
 */
public class CommandValidationException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * The default constructor for error exception instantiation.
   *
   * @param errorMessage message string for exception.
   */
  public CommandValidationException(String errorMessage) {
    super(errorMessage);
  }
}