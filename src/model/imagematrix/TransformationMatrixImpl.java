package model.imagematrix;

import model.utilities.Constant;
import model.utilities.Helper;

/**
 * This class represents the implementation of the transformations matrix
 * applied on images during color transformation. It provides dynamic matrix
 * which is used for applying various transformation operations like sepia tone
 * or grey scale on the images.Unlike filtering this is applied on every pixel.
 */
public class TransformationMatrixImpl implements TransformationMatrix {

  private final float[][] transformationMatrix;
  private final int matrixHeight;
  private final int matrixWidth;

  /**
   * Constructor for TransformationMatrixImpl, it initializes a transformation
   * matrix with a fixed dimension and given matrix values. This will be used to
   * perform the transform operation like grey scale or sepia tone transform on an
   * image.
   *
   * @param height                    The height of the transformation matrix.
   * @param width                     The width of the transformation matrix.
   * @param transformationMatrixValue The transformation matrix values array.
   */
  public TransformationMatrixImpl(int height, int width, float[] transformationMatrixValue) {
    super();
    Helper.isObjectNull(transformationMatrixValue);

    // An exception will be thrown if the height and width is not equal to 3.
    if ((height != Constant.TRANSFORMATION_MATRIX_SIZE)
        || (width != Constant.TRANSFORMATION_MATRIX_SIZE)) {
      throw new IllegalArgumentException();
    }

    this.matrixHeight = height;
    this.matrixWidth = width;

    if ((height * width) != transformationMatrixValue.length) {
      throw new IllegalArgumentException();
    }
    this.transformationMatrix = new float[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Helper.isObjectNull(transformationMatrixValue[i * height + j]);
        this.transformationMatrix[i][j] = transformationMatrixValue[i * height + j];

      }
    }

  }

  @Override
  public float[][] getTransformationMatrix() {
    float[][] matrix = new float[matrixHeight][matrixWidth];

    for (int i = 0; i < matrixHeight; i++) {
      for (int j = 0; j < matrixWidth; j++) {

        matrix[i][j] = transformationMatrix[i][j];

      }
    }
    return matrix;
  }

  @Override
  public String toString() {

    StringBuffer sb = new StringBuffer();
    sb.append("TransformationImpl [");
    sb.append(" matrixHeight=");
    sb.append(matrixHeight);
    sb.append(", matrixWidth=");
    sb.append(matrixWidth);
    sb.append("]");
    return sb.toString();

  }

  @Override
  public int getDimension() {
    return this.matrixHeight;
  }

}
