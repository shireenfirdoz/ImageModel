package model.images;

import model.pixel.Colour;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbColor;
import model.utilities.Helper;

/**
 * The RGBImage class represents a collection of pixels that collectively make
 * an RGB image composed of the RGB pixels.
 */
public class RgbImage extends ImageImpl {

  private final Pixel[][] pixelArr;

  /**
   * Constructor for the RGBImage lass to initialize an object that represents a
   * collection of pixels that collectively make up an image.It takes 3D int array
   * to intialize the object of type RbgImage.
   *
   * @param imageArr    A 3D array of image with the RGB intensities across.
   * @param imageHeight The height of the image.
   * @param imageWidth  The width of the image.
   */
  public RgbImage(int[][][] imageArr, int imageHeight, int imageWidth) {

    super(imageHeight, imageWidth);

    Helper.isObjectNull(imageArr);
    Helper.isObjectNull(imageArr[0]);
    Helper.isNotEqual(imageArr.length, imageHeight);
    Helper.isNotEqual(imageArr[0].length, imageWidth);

    this.pixelArr = new PixelImpl[imageHeight][imageWidth];
    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {
        Colour color = new RgbColor(imageArr[i][j][Channel.RED.ordinal()],
            imageArr[i][j][Channel.GREEN.ordinal()], imageArr[i][j][Channel.BLUE.ordinal()]);
        Pixel pixelNew = new PixelImpl(color);
        pixelArr[i][j] = pixelNew;

      }
    }

  }

  /**
   * Constructor for the RGBImage class to initialize an object that represents a
   * collection of pixels that collectively make up an image.It takes 2D pixel
   * array to intialize the object of type RbgImage.
   *
   * @param pixelImgArr A 2D array of pixel with the RGB intensities across.
   * @param imageHeight The height of the image.
   * @param imageWidth  The width of the image.
   */
  public RgbImage(Pixel[][] pixelImgArr, int imageHeight, int imageWidth) {

    super(imageHeight, imageWidth);
    Helper.isObjectNull(pixelImgArr);
    Helper.isObjectNull(pixelImgArr[0]);
    Helper.isNotEqual(pixelImgArr.length, imageHeight);
    Helper.isNotEqual(pixelImgArr[0].length, imageWidth);

    this.pixelArr = new PixelImpl[imageHeight][imageWidth];
    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {
        Colour color = new RgbColor(pixelImgArr[i][j].getRedColor(),
            pixelImgArr[i][j].getGreenColor(), pixelImgArr[i][j].getBlueColor());
        Pixel pixelNew = new PixelImpl(color);
        this.pixelArr[i][j] = pixelNew;

      }
    }

  }

  @Override
  public String toString() {

    StringBuffer sb = new StringBuffer();
    sb.append("RgbImage [pixel=");
    if (null != pixelArr) {
      sb.append(pixelArr.length);
    } else {
      sb.append(0);
    }
    sb.append("]");
    return sb.toString();
  }

  @Override
  public Pixel[][] getPixelArray() {
    Pixel[][] newpixelArr = new PixelImpl[imageHeight][imageWidth];
    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {
        Colour color = new RgbColor(pixelArr[i][j].getRedColor(), pixelArr[i][j].getGreenColor(),
            pixelArr[i][j].getBlueColor());
        Pixel pixelNew = new PixelImpl(color);
        newpixelArr[i][j] = pixelNew;
      }
    }
    return newpixelArr;
  }

}
