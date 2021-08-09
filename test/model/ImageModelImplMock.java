package model;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import model.exception.ModelValidationException;
import model.imageprocessor.ImageOperator;
import model.imageprocessor.ImagePixelateOperator;
import model.imageprocessor.PatternGenerator;
import model.images.Channel;
import model.images.Image;
import model.images.RgbImage;
import model.pattern.CrossStitchedPattern;
import model.pattern.ImagePattern;
import model.pixel.Colour;
import model.pixel.RgbColor;
import model.pixel.SuperPixel;
import model.pixel.SuperPixelImpl;

/**
 * ImageModelImpl is the model class in MVC structure.It implements the various
 * image operation that can be performed on an image like filter, transform,
 * reduce, dither etc. The operations take in respective values or kernels to
 * perform a given operation.
 */
public class ImageModelImplMock implements ImageModel {

  private StringBuilder input;
  private Image image;
  private ImagePattern imagePattern;
  private Map<String, Colour> map;

  /**
   * Constructor for mock model.
   *
   * @param input input
   * @throws IOException exception while reading or write file
   */
  public ImageModelImplMock(StringBuilder input) throws IOException {
    Colour color = new RgbColor(1, 1, 1);
    Map<String, String> mapstr = new TreeMap<String, String>();
    this.map = new TreeMap<String, Colour>();
    map.put("DMC-01", color);
    this.input = input;

    map = new TreeMap<String, Colour>();
    map.put("DMC-01", color);
    int[][][] imageArr = new int[3][3][Channel.values().length];

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {

        imageArr[i][j][Channel.RED.ordinal()] = i;
        imageArr[i][j][Channel.GREEN.ordinal()] = j;
        imageArr[i][j][Channel.BLUE.ordinal()] = i * j;
      }
    }

    image = new RgbImage(imageArr, 3, 3);

    SuperPixel[][] superPixelArr = new SuperPixelImpl[1][1];

    SuperPixel superPixel = new SuperPixelImpl(color, 1, 1, "#", "DMC-01", 3, 3);

    superPixelArr[0][0] = superPixel;
    imagePattern = new CrossStitchedPattern(superPixelArr, mapstr, superPixelArr);

  }

  @Override
  public Image applyImageOperation(ImageOperator operator) {

    input.append(" applyIn ");
    return image;
  }

  @Override
  public Image pixelate(ImagePixelateOperator operator)
      throws ModelValidationException, IOException {
    input.append(" pixelateIn ");
    return image;
  }

  @Override
  public ImagePattern generateImagePattern(PatternGenerator pattern)
      throws IOException, ModelValidationException {
    input.append(" patternIn ");
    return imagePattern;
  }

  @Override
  public void writeFile(String filename, String isBatchProcess, PatternGenerator imagePattern)
      throws IOException, ModelValidationException {
    input.append(" writeImageIn ");

  }

  @Override
  public void readImage(String filename, String isBatchProcess)
      throws IOException, ModelValidationException {
    input.append(" readIn ");
  }

  @Override
  public void writeImage(String filename, String isBatchProcess)
      throws IOException, ModelValidationException {
    input.append(" writeIn ");

  }

  @Override
  public ImagePattern swapDmcColorImagePattern(PatternGenerator generator, String xPosition,
      String yPosition, String dmcColorName) throws IOException, ModelValidationException {
    input.append("swap");

    return imagePattern;
  }

  @Override
  public ImagePattern replaceDmcColorImagePattern(PatternGenerator generator, String xPosition,
      String yPosition) throws IOException, ModelValidationException {
    input.append("replace");

    return imagePattern;
  }

  @Override
  public Map<String, Colour> getDmcColorPalleteMap() throws IOException {
    input.append("getdmc");
    return map;
  }

  @Override
  public ImagePattern createCustomDmcPattern(PatternGenerator generator, String dmcColorName)
      throws IOException, ModelValidationException {
    input.append("customdmc");
    return imagePattern;
  }

}
