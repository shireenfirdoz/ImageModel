package model.imageprocessor;

import model.exception.ModelValidationException;
import model.images.Image;
import model.images.RgbImage;
import model.pixel.Colour;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbColor;
import model.utilities.Helper;

/**
 * This class represents the implementation of the reducing color density
 * operation applied on images.It reduces the number of the color in which a
 * picture can be represented. To reduce it in a 8 or 16 or n colors input is
 * provided by the user.
 */
public class ReduceImage extends AbstractColorReduceOperator {

  /**
   * This is the constructor of ReduceImage class which takes maxNoOfColors
   * parameter to be used for color reduction operation.
   * 
   * @param maxNumOfColors the input parameter
   */
  public ReduceImage(String maxNumOfColors) {
    super(maxNumOfColors);
  }

  @Override
  public Image apply(Image image) throws ModelValidationException {

    Helper.isObjectNull(image);
    int maxNoColor = Helper.inputParameterDecode(this.maxNumOfColors, "Reduction ");

    if (maxNoColor <= 0) {
      throw new ModelValidationException("Reduction parameter cannot be negative");
    }

    Pixel[][] newImgArr = null;
    Pixel[][] pixelArr = image.getPixelArray();
    if (null == pixelArr || null == pixelArr[0]) {
      throw new IllegalArgumentException();
    }

    // returns the length of the rows in the array
    int imageHeight = pixelArr.length;
    // returns the length of the columns in the array
    int imageWidth = pixelArr[0].length;

    int redColor = 0;
    int greenColor = 0;
    int blueColor = 0;
    newImgArr = new PixelImpl[imageHeight][imageWidth];

    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {

        Helper.isObjectNull(pixelArr[i][j]);

        redColor = Helper.getNearestColor(pixelArr[i][j].getRedColor(), maxNoColor);
        greenColor = Helper.getNearestColor(pixelArr[i][j].getGreenColor(), maxNoColor);
        blueColor = Helper.getNearestColor(pixelArr[i][j].getBlueColor(), maxNoColor);

        Colour newReduceColor = new RgbColor(redColor, greenColor, blueColor).toClamp();
        newImgArr[i][j] = new PixelImpl(newReduceColor);
      }
    }

    return new RgbImage(newImgArr, imageHeight, imageWidth);

  }

}
