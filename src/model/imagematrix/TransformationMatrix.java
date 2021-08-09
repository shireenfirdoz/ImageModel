package model.imagematrix;

/**
 * This interface is for abstracting the Transformation Matrix behavior which is
 * applied on images during color transformation to get the images in tone like
 * grey scale and sepia tone. These operations are performed with varied matrix.
 */
public interface TransformationMatrix {
  /**
   * Getter method to get the transformation matrix i.e. the set of values to
   * perform an operation on an image.
   *
   * @return A 2D array/matrix of kernel values.
   */
  float[][] getTransformationMatrix();

  /**
   * Getter method to get the dimension of the transformation matrix.
   *
   * @return An int value representing the dimension of the transformation matrix.
   */
  int getDimension();

}
