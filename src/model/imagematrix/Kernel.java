package model.imagematrix;

/**
 * This interface is for abstracting the kernel behavior which is used during
 * filtering applied on images to perform various operations like blur, sharped.
 * These operations are performed with varied kernel size like 3X3 or 5X5 and so
 * on.
 */
public interface Kernel {
  /**
   * Getter method to get the kernel i.e. the set of values to perform an
   * operation on an image.
   *
   * @return A 2D array/matrix of kernel values.
   */
  float[][] getKernel();

  /**
   * Getter method to get the dimension of the Kernel.
   *
   * @return An int value representing the dimension of the Kernel.
   */
  int getDimension();
}
