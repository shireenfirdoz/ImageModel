package model.imageprocessor;

import model.exception.ModelValidationException;
import model.images.Image;

import java.io.IOException;

/**
 * Interface ImageOperator represents the various image operation that can be
 * performed on an image like filter, transform, reduce, dither etc. The
 * operations take in respective values or kernels to perform a given operation.
 */
public interface ImageOperator {

  /**
   * This method is used for apply different image operations like like filter,
   * transform, reduce, dither etc. on the input image.
   * 
   * @param image the input image
   * @return the result image after operations are performed
   * @throws ModelValidationException if the validations of input parameters of
   *                                  specific operators fails.
   */
  Image apply(Image image) throws ModelValidationException,IOException;

}
