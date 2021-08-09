package model.imageprocessor;

import model.imagematrix.Kernel;
import model.imagematrix.KernelImpl;
import model.images.Image;
import model.utilities.Constant;
import model.utilities.Helper;

/**
 * This class represent the sharpen filter for an image processing i.e. used to
 * apply the sharpen effect to an image. The kernel determines the values that
 * define how much sharpness is to be applied to the given input image.
 */
public class SharpenFilter extends AbstractFilterImageOperator {

  private final Kernel kernel;

  /**
   * Constructor for the sharpen filter that is used to initialize an object for
   * the sharpen filter to be applied to an image.
   *
   * @param kernel The set of values that determine the blur effect filter.
   */
  public SharpenFilter(Kernel kernel) {
    Helper.isObjectNull(kernel);
    this.kernel = kernel;

  }

  /**
   * Default constructor for the sharpen filter that is used to initialize an
   * object for the sharpen filter to be applied to an image. It contains default
   * kernel to be applied to sharpen an image.
   */
  public SharpenFilter() {
    this(new KernelImpl(5, Constant.SHARPEN_KERNEL_MATRIX));

  }

  @Override
  public Image apply(Image image) {
    Helper.isObjectNull(image);

    return this.performFilter(image, kernel);
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("SharpenFilter [kernel=");
    sb.append(kernel);
    sb.append("]");
    return sb.toString();

  }

}
