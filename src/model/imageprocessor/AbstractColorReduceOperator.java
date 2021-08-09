package model.imageprocessor;

/**
 * This abstract class is the representation of the abstracted functionality of
 * image operations color reduction with essence and without essence. It
 * contains common operation that can be performed while applying color
 * reduction operation on image.
 */
public abstract class AbstractColorReduceOperator implements ImageOperator {

  protected final String maxNumOfColors;

  /**
   * This is constructor is called from concrete class which takes maxNoOfColors
   * parameter to be used for color reduction operation.
   * 
   * @param maxNumOfColors the input parameter
   */
  protected AbstractColorReduceOperator(String maxNumOfColors) {
    super();
    // maxNumOfColors will be validated in apply method .
    this.maxNumOfColors = maxNumOfColors;
  }

}
