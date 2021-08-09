package model.imagematrix;

import model.utilities.Helper;

/**
 * This class represents the implementation of the kernel applied on images
 * during filtering operation. It provides dynamic kernel which is used for
 * applying various filtering operations. on the images. Filtering is applied on
 * a channel.
 */
public class KernelImpl implements Kernel {

  private final float[][] kernel;
  private final int kernelHeight;
  private final int kernelWidth;

  /**
   * Constructor for KernelImpl, it initializes a kernel with a fixed odd
   * dimension and given kernel values. This will be used to perform a filter
   * operation like blur or sharpen on an image.
   *
   * @param dimension    The dimension of the kernel - similar height and width.
   * @param kernelValues Values for the kernel depending on the operation to be
   *                     performed.
   */
  public KernelImpl(int dimension, float[] kernelValues) {

    super();

    Helper.isObjectNull(kernelValues);

    if ((dimension < 0) || (dimension > 5) || (Helper.isEvenNumber(dimension))) {
      throw new IllegalArgumentException();
    }

    this.kernelHeight = dimension;
    this.kernelWidth = dimension;

    if ((kernelHeight * kernelWidth) != kernelValues.length) {
      throw new IllegalArgumentException();
    }
    kernel = new float[kernelHeight][kernelWidth];

    for (int i = 0; i < kernelHeight; i++) {
      for (int j = 0; j < kernelWidth; j++) {
        Helper.isObjectNull(kernelValues[i + j * kernelHeight]);
        kernel[i][j] = kernelValues[i + j * kernelHeight];

      }
    }

  }

  @Override
  public float[][] getKernel() {
    float[][] kernelNew = new float[kernelHeight][kernelWidth];

    for (int i = 0; i < kernelHeight; i++) {
      for (int j = 0; j < kernelWidth; j++) {

        kernelNew[i][j] = kernel[i][j];

      }
    }

    return kernelNew;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("Kernel");
    sb.append(", kernelHeight=");
    sb.append(kernelHeight);
    sb.append(", kernelWidth=");
    sb.append(kernelWidth);
    sb.append("]");
    return sb.toString();

  }

  @Override
  public int getDimension() {
    return kernelHeight;
  }

}
