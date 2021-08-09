package model.exception;

/**
 * This class represents an exception raise for a command validation error.
 */
public class ModelValidationException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * The default consturctor for error exception instantiation.
   *
   * @param errorMessage message string.
   */
  public ModelValidationException(String errorMessage) {
    super(errorMessage);
  }
}
