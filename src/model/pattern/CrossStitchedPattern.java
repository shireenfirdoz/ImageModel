package model.pattern;

import model.pixel.Colour;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbColor;
import model.pixel.SuperPixel;
import model.pixel.SuperPixelImpl;
import model.utilities.Helper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * This class represents a cross-stitched pattern for an image that is generated
 * after performing chunking over a given image. The chunking operation is
 * performed by pixelation with a given pixelation parameter. The generated
 * image pattern contains the image dimensions, an image map, and a legend for
 * the DMC floss colors to be used to represent that image.
 */
public class CrossStitchedPattern implements ImagePattern {
  private final int width;
  private final int height;
  private final SuperPixel[][] imageMap;
  private final Map<String, String> legend;
  private final Pixel[][] pixelArray;

  /**
   * The default constructor for the cross stitched pattern that is generated for
   * an image after performing a chunking operation using pixelation.
   *
   * @param imageMap the pixel map for the image pattern generated.
   * @param legend   the DMC floss legend for the image.
   * @param pixelArr the pixel array for the image parameter.
   * @throws IOException IOException while loading dmc color from properties file
   */
  public CrossStitchedPattern(SuperPixel[][] imageMap, Map<String, String> legend,
      Pixel[][] pixelArr) throws IOException {
    super();

    Helper.isObjectNull(imageMap);
    Helper.isObjectNull(legend);
    Helper.isObjectNull(imageMap[0]);
    Helper.isObjectNull(pixelArr[0]);
    Helper.isObjectNull(pixelArr);

    Map<String, String> dmcColorMap;
    try {
      dmcColorMap = DmcRbcProperties.getInstance().getDmcPropertiesColorKey();
    } catch (IOException e) {
      throw new IOException("Unable to read properties file");
    }

    String dmcColorName = null;
    SuperPixel newSuperPixel = null;
    Colour newSuperPixelColor = null;
    this.imageMap = new SuperPixel[imageMap.length][imageMap[0].length];
    for (int i = 0; i < imageMap.length; i++) {
      for (int j = 0; j < imageMap[0].length; j++) {
        int red = imageMap[i][j].getRedColor();
        int green = imageMap[i][j].getGreenColor();
        int blue = imageMap[i][j].getBlueColor();

       
        newSuperPixelColor = new RgbColor(red, green, blue);
        newSuperPixel = new SuperPixelImpl(newSuperPixelColor, imageMap[i][j].getSuperPixelPosY(),
            imageMap[i][j].getSuperPixelPosX(), imageMap[i][j].getSymbol(), 
            imageMap[i][j].getDmcColorName(), imageMap[i][j].getSuperPixelHeight(), 
            imageMap[i][j].getSuperPixelWidth());

        this.imageMap[i][j] = newSuperPixel;
      }
    }

    this.width = imageMap[0].length;
    this.height = imageMap.length;

    this.legend = new TreeMap<String, String>();
    Set<Entry<String, String>> entries = legend.entrySet();

    for (Map.Entry<String, String> entry : entries) {
      this.legend.put(entry.getKey(), entry.getValue());
    }

    this.pixelArray = new PixelImpl[pixelArr.length][pixelArr[0].length];
    for (int i = 0; i < pixelArr.length; i++) {
      for (int j = 0; j < pixelArr[0].length; j++) {
        Colour color = new RgbColor(pixelArr[i][j].getRedColor(), pixelArr[i][j].getGreenColor(),
            pixelArr[i][j].getBlueColor());
        Pixel pixelNew = new PixelImpl(color);
        this.pixelArray[i][j] = pixelNew;

      }
    }

  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("CrossStitchedPattern [width=");
    sb.append(width);
    sb.append(", height=");
    sb.append(height);
    sb.append(", imageMap=");
    if (imageMap != null) {
      sb.append(Arrays.toString(imageMap));
    }
    sb.append(", legend=");
    sb.append(legend);
    sb.append("]");
    return sb.toString();
  }

  @Override
  public int getWidth() {
    int width = this.width;
    return width;
  }

  @Override
  public int getHeight() {
    int height = this.height;
    return height;
  }

  @Override
  public SuperPixel[][] getImageMap() {

    SuperPixel[][] imageMap = new SuperPixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        imageMap[i][j] = this.imageMap[i][j];
      }
    }

    return imageMap;
  }

  @Override
  public Map<String, String> getLegend() {

    Map<String, String> legend = new TreeMap<String, String>();
    Set<Entry<String, String>> entries = this.legend.entrySet();

    for (Map.Entry<String, String> entry : entries) {
      legend.put(entry.getKey(), entry.getValue());
    }

    return legend;
  }

  @Override
  public Pixel[][] getPixelArray() {
    Pixel[][] newpixelArr = new PixelImpl[pixelArray.length][pixelArray[0].length];
    for (int i = 0; i < pixelArray.length; i++) {
      for (int j = 0; j < pixelArray[0].length; j++) {
        Colour color = new RgbColor(pixelArray[i][j].getRedColor(),
            pixelArray[i][j].getGreenColor(), pixelArray[i][j].getBlueColor());
        Pixel pixelNew = new PixelImpl(color);
        newpixelArr[i][j] = pixelNew;
      }
    }
    return newpixelArr;
  }

  @Override
  public int getImageHeight() {
    int height = pixelArray.length;
    return height;
  }

  @Override
  public int getImageWidth() {
    int width = pixelArray[0].length;
    return width;
  }

}
