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
 * operation with essence applied on an image.It reduces the number of the color
 * in which a picture can be represented. To reduce it in a 8 or 16 or n colors
 * input is provided by the user. Floyd Steinberg Algorithm is used to implement
 * the dithering to an image.
 */
public class ReduceImageEssence extends AbstractColorReduceOperator {

  /**
   * This is the constructor of ReduceImage class which takes maxNoOfColors
   * parameter to be used for color reduction operation.
   * 
   * @param maxNumOfColors the input parameter
   */
  public ReduceImageEssence(String maxNumOfColors) {
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

    int newRedColor = 0;
    int newGreenColor = 0;
    int newBlueColor = 0;
    newImgArr = new PixelImpl[imageHeight][imageWidth];

    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {

        Helper.isObjectNull(pixelArr[i][j]);

        newRedColor = Helper.getNearestColor(pixelArr[i][j].getRedColor(), maxNoColor);
        newGreenColor = Helper.getNearestColor(pixelArr[i][j].getGreenColor(), maxNoColor);
        newBlueColor = Helper.getNearestColor(pixelArr[i][j].getBlueColor(), maxNoColor);

        int errorRed = pixelArr[i][j].getRedColor() - newRedColor;
        int errorGreen = pixelArr[i][j].getGreenColor() - newGreenColor;
        int errorBlue = pixelArr[i][j].getBlueColor() - newBlueColor;

        Colour newDitherColor = new RgbColor(newRedColor,
            newGreenColor, newBlueColor);
        Pixel ditherPixel = new PixelImpl(newDitherColor);

        pixelArr[i][j] = ditherPixel;

        if (j + 1 < imageWidth) {

          Helper.isObjectNull(pixelArr[i][j + 1]);

          int newRedEssenceColor = (int) Math
              .round((pixelArr[i][j + 1].getRedColor() + (errorRed * (7.0 / 16.0))));
          int newGreenEssenceColor = (int) Math
              .round((pixelArr[i][j + 1].getGreenColor() + (errorGreen * (7.0 / 16.0))));
          int newBlueEssenceColor = (int) Math
              .round((pixelArr[i][j + 1].getBlueColor() + (errorBlue * (7.0 / 16.0))));

          Colour newReduceColor = new RgbColor(newRedEssenceColor,
              newGreenEssenceColor, newBlueEssenceColor);
          Pixel reducePixelEssence = new PixelImpl(newReduceColor);

          pixelArr[i][j + 1] = reducePixelEssence;
        }

        if (j - 1 >= 0 && i + 1 < imageHeight) {

          Helper.isObjectNull(pixelArr[i + 1][j - 1]);

          int newRedEssenceColor = (int) Math
              .round((pixelArr[i + 1][j - 1].getRedColor() + (errorRed * (3.0 / 16.0))));
          int newGreenEssenceColor = (int) Math
              .round((pixelArr[i + 1][j - 1].getGreenColor() + (errorGreen * (3.0 / 16.0))));
          int newBlueEssenceColor = (int) Math
              .round((pixelArr[i + 1][j - 1].getBlueColor() + (errorBlue * (3.0 / 16.0))));

          Colour newReduceColor = new RgbColor(newRedEssenceColor,
              newGreenEssenceColor, newBlueEssenceColor);
          Pixel reducePixelEssence = new PixelImpl(newReduceColor);

          pixelArr[i + 1][j - 1] = reducePixelEssence;
        }

        if (i + 1 < imageHeight) {

          Helper.isObjectNull(pixelArr[i + 1][j]);

          int newRedEssenceColor = (int) Math
              .round((pixelArr[i + 1][j].getRedColor() + (errorRed * (5.0 / 16.0))));
          int newGreenEssenceColor = (int) Math
              .round((pixelArr[i + 1][j].getGreenColor() + (errorGreen * (5.0 / 16.0))));
          int newBlueEssenceColor = (int) Math
              .round((pixelArr[i + 1][j].getBlueColor() + (errorBlue * (5.0 / 16.0))));

          Colour newReduceColor = new RgbColor(newRedEssenceColor,
              newGreenEssenceColor, newBlueEssenceColor);
          Pixel reducePixelEssence = new PixelImpl(newReduceColor);
          pixelArr[i + 1][j] = reducePixelEssence;
        }

        if (j + 1 < imageWidth && i + 1 < imageHeight) {

          Helper.isObjectNull(pixelArr[i + 1][j + 1]);

          int newRedEssenceColor = (int) Math
              .round((pixelArr[i + 1][j + 1].getRedColor() + (errorRed * (1.0 / 16.0))));
          int newGreenEssenceColor = (int) Math
              .round((pixelArr[i + 1][j + 1].getGreenColor() + (errorGreen * (1.0 / 16.0))));
          int newBlueEssenceColor = (int) Math
              .round((pixelArr[i + 1][j + 1].getBlueColor() + (errorBlue * (1.0 / 16.0))));

          Colour newReduceColor = new RgbColor(newRedEssenceColor,
              newGreenEssenceColor, newBlueEssenceColor);
          Pixel reducePixelEssence = new PixelImpl(newReduceColor);

          pixelArr[i + 1][j + 1] = reducePixelEssence;
        }

        newImgArr[i][j] = new PixelImpl(pixelArr[i][j].toClamp());

      }
    }

    return new RgbImage(newImgArr, imageHeight, imageWidth);

  }

}
