package model.imageprocessor;

import model.exception.ModelValidationException;

import java.io.IOException;

/**
 * This interface is an extension to the ImageOperator for pixelation image
 * operation to provide the additional functionality of getting the pixelation
 * parameter.
 */
public interface ImagePixelateOperator extends ImageOperator {

  /**
   * This methods get the pixelation parameter for the pixelation operation
   * performed.
   * 
   * @return the pixelate parameter
   * @throws ModelValidationException if the pixelate parameter is invalid
   */
  int getPixelation() throws ModelValidationException, IOException;

}
