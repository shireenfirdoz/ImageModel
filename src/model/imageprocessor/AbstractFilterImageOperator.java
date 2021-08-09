package model.imageprocessor;

import model.imagematrix.Kernel;
import model.images.Image;
import model.images.RgbImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbColor;
import model.utilities.Helper;

/**
 * This abstract class is the representation of the abstracted functionality of
 * image operations blur and sharpen filter operations. It contains common
 * operation that can be performed while applying filter operation on image.
 */
public abstract class AbstractFilterImageOperator implements ImageOperator {

  /**
   * Method to apply different filters to an image. The different filters can be
   * blur filter, sharpen filter etc. which will be determined by the kernel being
   * passed.
   *
   * @param image  The input image to apply filter to.
   * @param kernel The kernel to be applied to this image, it will determine the
   *               filter.
   * @return A new image array with the filter applied.
   */
  protected Image performFilter(Image image, Kernel kernel) {
    Helper.isObjectNull(image);
    Helper.isObjectNull(kernel);

    Pixel[][] pixelArr = image.getPixelArray();
    if (null == pixelArr || null == pixelArr[0]) {
      throw new IllegalArgumentException();
    }

    // returns the length of the rows in the array
    int imageHeight = pixelArr.length;
    // returns the length of the columns in the array
    int imageWidth = pixelArr[0].length;

    if (imageHeight < kernel.getDimension() || imageWidth < kernel.getDimension()) {
      throw new IllegalArgumentException();
    }

    if (null == kernel.getKernel() || null == kernel.getKernel()[0]) {
      throw new IllegalArgumentException();
    }
    float[][] kernelValues = kernel.getKernel();
    int kernelDimension = kernelValues[0].length;

    // padding to the image array
    int paddingToArr = kernelDimension / 2;

    Pixel[][] paddedImgArr = Helper.padChannel(pixelArr, paddingToArr);
    int newWidth = imageWidth + 2 * paddingToArr;
    int newHeight = imageHeight + 2 * paddingToArr;
    int sumRed = 0;
    int sumGreen = 0;
    int sumBlue = 0;

    if (null == paddedImgArr) {
      throw new IllegalArgumentException();
    }

    Pixel[][] imageMatrixResult = new PixelImpl[imageHeight][imageWidth];

    for (int j = 0; j < newHeight - 2 * paddingToArr; j++) {
      for (int k = 0; k < newWidth - 2 * paddingToArr; k++) {
        for (int m = 0; m < kernelDimension; m++) {
          for (int n = 0; n < kernelDimension; n++) {
            int newJ = j + m;
            int newN = k + n;

            Helper.isObjectNull(paddedImgArr[newJ][newN]);

            sumRed += paddedImgArr[newJ][newN].getRedColor() * kernelValues[m][n];

            sumGreen += paddedImgArr[newJ][newN].getGreenColor() * kernelValues[m][n];

            sumBlue += paddedImgArr[newJ][newN].getBlueColor() * kernelValues[m][n];

          }
        }

        Pixel pixelNew = new PixelImpl(new RgbColor(sumRed, sumGreen, sumBlue).toClamp());

        imageMatrixResult[j][k] = pixelNew;

        sumRed = 0;
        sumGreen = 0;
        sumBlue = 0;
      }

    }

    return new RgbImage(imageMatrixResult, imageHeight, imageWidth);

  }
}
