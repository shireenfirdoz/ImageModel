package model.imageprocessor;

import model.imagematrix.TransformationMatrix;
import model.imagematrix.TransformationMatrixImpl;
import model.images.Image;
import model.utilities.Constant;
import model.utilities.Helper;

/**
 * This class represent the sepia tone transformation for an image processing
 * i.e. used to apply the sepia tone transformation to an image. The matrix
 * determines the values that define the sepia tone operation that is to be
 * applied to the given input image.
 */
public class SepiaToneTransformation extends AbstractColorTransformOperator {

  private final TransformationMatrix transformationMatrix;

  /**
   * Constructor for the sepia tone transformation that is used to initialize an
   * object for the image transformation operation to a sepia tone image to be
   * applied to an image.
   *
   * @param transformationMatrix The set of values that determine the
   *                             transformation to be performed.
   */
  public SepiaToneTransformation(TransformationMatrix transformationMatrix) {

    Helper.isObjectNull(transformationMatrix);
    this.transformationMatrix = transformationMatrix;

  }

  /**
   * Default constructor for the sepia tone color transformation that is used to
   * initialize an object for the image transformation operation to a sepia tone
   * image to be applied to an image. It contains default transformation matrix to
   * be applied to produce a sepia tone effect on an image.
   */
  public SepiaToneTransformation() {
    this(new TransformationMatrixImpl(3, 3, Constant.SEPIA_TRANSFORMATION_MATRIX));
  }

  @Override
  public Image apply(Image image) {

    Helper.isObjectNull(image);
    return this.performTransformation(image, transformationMatrix);

  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("SepiaToneTransformation [transformationMatrix=");
    sb.append(transformationMatrix);
    sb.append("]");
    return sb.toString();

  }

}
