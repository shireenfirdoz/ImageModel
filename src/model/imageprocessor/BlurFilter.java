package model.imageprocessor;

import model.imagematrix.Kernel;
import model.imagematrix.KernelImpl;
import model.images.Image;
import model.utilities.Constant;
import model.utilities.Helper;

/**
 * This class represent the blur filter for an image processing i.e. used to
 * apply the blur effect to an image. The kernel determines the values that
 * define how much blur is to be applied to the given input image.
 */
public class BlurFilter extends AbstractFilterImageOperator {

  private final Kernel kernel;

  /**
   * Constructor for the blur filter that is used to initialize an object for the
   * blur filter to be applied to an image.
   *
   * @param kernel The set of values that determine the blur effect filter.
   */
  public BlurFilter(Kernel kernel) {
    Helper.isObjectNull(kernel);
    this.kernel = kernel;

  }

  /**
   * Default constructor for the blur filter that is used to initialize an object
   * for the blur filter to be applied to an image. It contains default kernel to
   * be applied to produce a blurring effect on an image.
   */
  public BlurFilter() {
    this(new KernelImpl(3, Constant.BLUR_KERNEL_MATRIX));

  }

  @Override
  public Image apply(Image image) {
    Helper.isObjectNull(image);

    return this.performFilter(image, kernel);
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("BlurFilter [kernel=");
    sb.append(kernel);
    sb.append("]");
    return sb.toString();

  }
}
