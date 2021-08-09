package model.imageprocessor;

import model.exception.ModelValidationException;
import model.images.Image;
import model.images.RgbImage;
import model.pixel.Colour;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.Position;
import model.pixel.PositionImpl;
import model.pixel.RgbColor;
import model.utilities.Helper;

/**
 * This class represents an operator for the Image Processing application which
 * performs a mosaic art for an image that is a kind of image chunking
 * technique. A seed value is used to determine a number of randomly selected
 * pixels in the input image to generate the mosaic art from the given input
 * image.
 */
public class MosaicOperator implements ImageOperator {

  private final String seedLengthStr;

  /**
   * This is the constructor of MosaicOperator class which takes input seed length
   * parameter to be used for mosaic operation.
   * 
   * @param seedLengthStr the input parameter
   */
  public MosaicOperator(String seedLengthStr) {
    super();
    // seedLengthStr will be validated in apply method .
    this.seedLengthStr = seedLengthStr;
  }

  @Override
  public Image apply(Image image) throws ModelValidationException {

    Helper.imageChecks(image);

    int seedLength = Helper.inputParameterDecode(this.seedLengthStr, "Mosaic ");

    if ((seedLength <= 0) || (image.getImageHeight() * image.getImageWidth()) <= seedLength) {
      throw new ModelValidationException(" Invalid seed provided");
    }
    Pixel[][] imagePixelArr = image.getPixelArray();
    Position[] seedArray = Helper.chooseRandomSeed(seedLength, imagePixelArr);
    Pixel[][] mosaicPixelArr = closestDistPixelAppliedOnImg(imagePixelArr, seedArray);
    return new RgbImage(mosaicPixelArr, image.getImageHeight(), image.getImageWidth());
  }

  private Pixel[][] closestDistPixelAppliedOnImg(Pixel[][] imgPixelArr, Position[] seedArray) {
    Helper.isObjectNull(imgPixelArr);
    Helper.isObjectNull(seedArray);
    Pixel[][] newImagePixelMatrix = new PixelImpl[imgPixelArr.length][imgPixelArr[0].length];
    for (int i = 0; i < imgPixelArr.length; i++) {
      for (int j = 0; j < imgPixelArr[0].length; j++) {
        int closestSeed = -1;
        int minClosestSeedSq = -1;
        Position currentPixelPos = new PositionImpl(i, j);
        for (int k = 0; k < seedArray.length - 1; k++) {
          int distanceSqSeedElement1 = Helper.calEuclideanDistanceSq(currentPixelPos, seedArray[k]);
          int distanceSqSeedElement2 = Helper.calEuclideanDistanceSq(currentPixelPos,
              seedArray[k + 1]);
          if (distanceSqSeedElement1 < distanceSqSeedElement2) {
            if ((minClosestSeedSq == -1)
                || (minClosestSeedSq != -1 && minClosestSeedSq > distanceSqSeedElement1)) {
              closestSeed = k;
              minClosestSeedSq = distanceSqSeedElement1;
            }
          } else {

            if ((minClosestSeedSq == -1)
                || (minClosestSeedSq != -1 && minClosestSeedSq > distanceSqSeedElement2)) {
              closestSeed = k + 1;
              minClosestSeedSq = distanceSqSeedElement2;
            }
          }
        }
        Pixel closestDistPixel = imgPixelArr[seedArray[closestSeed]
            .getPositionHeight()][seedArray[closestSeed].getPositionWidth()];
        Colour newColor = new RgbColor(closestDistPixel.getRedColor(),
            closestDistPixel.getGreenColor(), closestDistPixel.getBlueColor());
        newImagePixelMatrix[i][j] = new PixelImpl(newColor);
      }
    }
    return newImagePixelMatrix;
  }

}
