package model.imageprocessor;

import model.imagematrix.TransformationMatrix;
import model.images.Image;
import model.images.RgbImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.utilities.Helper;

/**
 * This abstract class is the representation of the abstracted functionality of
 * image operations sepia tone and greyscale color transformation. It contains
 * common operation that can be performed while applying color transformation
 * operation on image.
 */
public abstract class AbstractColorTransformOperator implements ImageOperator {

  /**
   * Method to apply transformations on an image. The different transformations
   * can be grey scale transform, sepia tone transform etc.
   *
   * @param image                The input image to apply transform to.
   * @param transformationMatrix The transformation matrix to be applied to this
   *                             image, this will determine the type of
   *                             transformation being applied to the image.
   * @return A new image with the transformation applied.
   */
  protected Image performTransformation(Image image, TransformationMatrix transformationMatrix) {

    Helper.isObjectNull(image);
    Helper.isObjectNull(transformationMatrix);
    Pixel[][] newImgArr = null;
    Pixel[][] pixelArr = image.getPixelArray();
    if (null == pixelArr || null == pixelArr[0]) {
      throw new IllegalArgumentException();
    }
    // returns the length of the rows in the array
    int imageHeight = pixelArr.length;
    // returns the length of the columns in the array
    int imageWidth = pixelArr[0].length;

    if (imageHeight < transformationMatrix.getDimension()
        || imageWidth < transformationMatrix.getDimension()) {
      throw new IllegalArgumentException();
    }

    newImgArr = new PixelImpl[imageHeight][imageWidth];

    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {

        Helper.isObjectNull(pixelArr[i][j]);

        newImgArr[i][j] = pixelArr[i][j].colorTransformation(transformationMatrix);
      }
    }

    return new RgbImage(newImgArr, imageHeight, imageWidth);

  }
}
