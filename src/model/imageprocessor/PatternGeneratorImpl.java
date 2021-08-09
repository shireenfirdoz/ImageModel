package model.imageprocessor;

import model.exception.ModelValidationException;
import model.images.Image;
import model.pattern.CrossStitchedPattern;
import model.pattern.DmcRbcProperties;
import model.pattern.ImagePattern;
import model.pixel.Colour;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbColor;
import model.pixel.SuperPixel;
import model.pixel.SuperPixelImpl;
import model.utilities.Constant;
import model.utilities.Helper;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * This interface represents an operator for the image processing application to
 * generate a cross-stitched pattern for a given image. The pattern generation
 * is performed on a chunked image that is done using pixelation with a given
 * pixelation parameter.
 */
public class PatternGeneratorImpl implements PatternGenerator {

  @Override
  public ImagePattern generateImagePattern(Image image, int pixelation)
      throws IOException, ModelValidationException {
    Helper.imageChecks(image);
    Helper.pixelationValidation(pixelation);
    Pixel[][] imgPixelArr = image.getPixelArray();
    return applyImagePattern(imgPixelArr, loadDmcColorMap(), pixelation);
  }

  private Map<String, Colour> loadDmcColorMap() throws IOException {
    Map<String, Colour> dmcColorMap;
    try {
      dmcColorMap = DmcRbcProperties.getInstance().getDmcProperties();
    } catch (IOException e) {
      throw new IOException("Unable to read properties file");
    }
    return dmcColorMap;
  }

  private ImagePattern applyImagePattern(Pixel[][] imgPixelArr, Map<String, Colour> dmcColorMap,
      int pixelation) throws IOException {
    Helper.isObjectNull(imgPixelArr);
    Helper.isObjectNull(dmcColorMap);

    Pixel[][] newImagePixelMatrix = new PixelImpl[imgPixelArr.length][imgPixelArr[0].length];

    Map<String, String> legend = new TreeMap<String, String>();
    Colour currentColor = null;
    Colour colorDmc = null;
    Colour newColor = null;
    for (int i = 0; i < imgPixelArr.length; i++) {
      for (int j = 0; j < imgPixelArr[0].length; j++) {
        String closestDmc = null;
        int minDmcSq = -1;
        Set<Entry<String, Colour>> entries = dmcColorMap.entrySet();
        for (Map.Entry<String, Colour> entry : entries) {
          currentColor = new RgbColor(imgPixelArr[i][j].getRedColor(),
              imgPixelArr[i][j].getGreenColor(), imgPixelArr[i][j].getBlueColor());
          colorDmc = entry.getValue();
          int distDmc = Helper.calDistDeltaSquare(currentColor, colorDmc);
          if ((minDmcSq == -1) || (minDmcSq != -1 && (minDmcSq > distDmc))) {
            closestDmc = entry.getKey();
            minDmcSq = distDmc;
          }
        }

        if (!legend.containsKey(closestDmc)) {
          char symbol = Helper.findUniqueSymbolValue(legend);
          legend.put(closestDmc, String.valueOf(symbol));
        }
        Colour closestColorDmc = dmcColorMap.get(closestDmc);
        newColor = new RgbColor(closestColorDmc.getRedColor(), closestColorDmc.getGreenColor(),
            closestColorDmc.getBlueColor());
        newImagePixelMatrix[i][j] = new PixelImpl(newColor);
      }
    }

    Map<String, Object> resultMap = Helper.imageChunkingOnSuperPixel(newImagePixelMatrix,
        pixelation, MatrixType.TRANSFORMATION_PATTERN_TEXT.toString(), legend);

    SuperPixel[][] imageMap = (SuperPixel[][]) resultMap
        .get(MatrixType.TRANSFORMATION_PATTERN_TEXT.toString());

    return new CrossStitchedPattern(imageMap, legend, newImagePixelMatrix);
  }

  private Pixel[][] modifyImage(Pixel[][] imgPixelArr, String swapDmcColorName, Colour oldColor,
      Colour newColor) throws IOException {

    Helper.isObjectNull(imgPixelArr);
    Helper.isObjectNull(imgPixelArr[0]);
    Helper.isObjectNull(swapDmcColorName);
    Helper.isObjectNull(oldColor);

    Pixel[][] newImagePixelMatrix = new PixelImpl[imgPixelArr.length][imgPixelArr[0].length];

    Pixel newPixel = new PixelImpl(newColor);
    for (int i = 0; i < imgPixelArr.length; i++) {
      for (int j = 0; j < imgPixelArr[0].length; j++) {
        if (oldColor.equals(imgPixelArr[i][j].getColor())) {
          newImagePixelMatrix[i][j] = newPixel;
        } else {
          newImagePixelMatrix[i][j] = imgPixelArr[i][j];
        }
      }
    }

    return newImagePixelMatrix;
  }

  private Map<String, String> modifyLegend(Map<String, String> oldLegend, Colour oldColor,
      String swapDmcColorName, char newSymbol, String oldDmcColorName) throws IOException {

    Helper.isObjectNull(oldLegend);
    Helper.isObjectNull(oldColor);

    Map<String, String> newlegend = new TreeMap<String, String>();
    Set<Entry<String, String>> entries = oldLegend.entrySet();

    for (Map.Entry<String, String> entry : entries) {
      newlegend.put(entry.getKey(), entry.getValue());
    }

    newlegend.remove(oldDmcColorName);
    newlegend.put(swapDmcColorName, String.valueOf(newSymbol));

    return newlegend;
  }

  private SuperPixel[][] modifyImageMap(SuperPixel[][] oldImageMap, char newSymbol,
      String swapDmcColorName, Colour newColor, String oldDmcColorName) {

    Helper.isObjectNull(oldImageMap);
    Helper.isObjectNull(newColor);

    SuperPixel[][] newImageMapPixel = new SuperPixel[oldImageMap.length][oldImageMap[0].length];
    SuperPixel newSuperPixel = null;
    for (int i = 0; i < oldImageMap.length; i++) {
      for (int j = 0; j < oldImageMap[0].length; j++) {
        if (oldDmcColorName.equalsIgnoreCase(oldImageMap[i][j].getDmcColorName())) {
          newSuperPixel = new SuperPixelImpl(newColor, oldImageMap[i][j].getSuperPixelPosY(),
              oldImageMap[i][j].getSuperPixelPosX(), String.valueOf(newSymbol), swapDmcColorName,
              oldImageMap[i][j].getSuperPixelHeight(), oldImageMap[i][j].getSuperPixelWidth());
          newImageMapPixel[i][j] = newSuperPixel;
        } else {
          newImageMapPixel[i][j] = oldImageMap[i][j];
        }
      }
    }
    return newImageMapPixel;
  }

  @Override
  public ImagePattern swapDmcColorImagePattern(ImagePattern imagePattern, String xPosition,
      String yPositon, String swapDmcColorName) throws IOException, ModelValidationException {
    Helper.isObjectNull(imagePattern);

    int xPos = Helper.inputParameterDecodeDmcColor(xPosition);
    int yPos = Helper.inputParameterDecodeDmcColor(yPositon);

    Helper.validateMouseClick(xPos, yPos);

    if (swapDmcColorName == null || (swapDmcColorName != null && "".equals(swapDmcColorName))) {
      throw new ModelValidationException(Constant.DMC_INFO);
    }

    Pixel[][] imgPixelArr = imagePattern.getPixelArray();

    Colour oldColor = new RgbColor(imgPixelArr[yPos][xPos].getRedColor(),
        imgPixelArr[yPos][xPos].getGreenColor(), imgPixelArr[yPos][xPos].getBlueColor());

    Map<String, Colour> dmcMap = loadDmcColorMap();
    Colour newColor = dmcMap.get(swapDmcColorName);

    char newSymbol = Helper.findUniqueSymbolValue(imagePattern.getLegend());

    Map<String, String> dmcColorMapKey = DmcRbcProperties.getInstance().getDmcPropertiesColorKey();
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append(String.valueOf(oldColor.getRedColor()) + ",");
    strBuilder.append(String.valueOf(oldColor.getGreenColor()) + ",");
    strBuilder.append(String.valueOf(oldColor.getBlueColor()));
    String oldDmcColorName = dmcColorMapKey.get(strBuilder.toString());

    Pixel[][] newImagePixelMatrix = modifyImage(imgPixelArr, swapDmcColorName, oldColor, newColor);

    Map<String, String> newLegend = modifyLegend(imagePattern.getLegend(), oldColor,
        swapDmcColorName, newSymbol, oldDmcColorName);

    SuperPixel[][] newSuperPixelArr = modifyImageMap(imagePattern.getImageMap(), newSymbol,
        swapDmcColorName, newColor, oldDmcColorName);

    return new CrossStitchedPattern(newSuperPixelArr, newLegend, newImagePixelMatrix);
  }

  @Override
  public ImagePattern replaceDmcColorImagePattern(ImagePattern imagePattern, String xPosition,
      String yPositon) throws IOException, ModelValidationException {
    Helper.isObjectNull(imagePattern);

    int xPos = Helper.inputParameterDecodeDmcColor(xPosition);
    int yPos = Helper.inputParameterDecodeDmcColor(yPositon);

    Helper.validateMouseClick(xPos, yPos);

    Pixel[][] imgPixelArr = imagePattern.getPixelArray();

    Colour oldColor = new RgbColor(imgPixelArr[yPos][xPos].getRedColor(),
        imgPixelArr[yPos][xPos].getGreenColor(), imgPixelArr[yPos][xPos].getBlueColor());

    Map<String, String> dmcColorMapKey = DmcRbcProperties.getInstance().getDmcPropertiesColorKey();
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append(String.valueOf(oldColor.getRedColor()) + ",");
    strBuilder.append(String.valueOf(oldColor.getGreenColor()) + ",");
    strBuilder.append(String.valueOf(oldColor.getBlueColor()));
    String oldDmcColorName = dmcColorMapKey.get(strBuilder.toString());

    Map<String, String> newLegend = modifyLegend(imagePattern.getLegend(), oldColor, "blank", '.',
        oldDmcColorName);

    SuperPixel[][] newSuperPixelArr = modifyImageMap(imagePattern.getImageMap(), '.', "blank",
        oldColor, oldDmcColorName);

    return new CrossStitchedPattern(newSuperPixelArr, newLegend, imgPixelArr);
  }

  @Override
  public ImagePattern createCustomDmcPattern(ImagePattern imagePattern, String dmcColorName,
      int pixelation) throws IOException, ModelValidationException {
    Helper.isObjectNull(imagePattern);

    if (dmcColorName == null || (dmcColorName != null && "".equals(dmcColorName))) {
      throw new ModelValidationException(Constant.DMC_INFO);
    }
    String[] strArray = Helper.decodeDMCColorStr(dmcColorName);
    Map<String, Colour> loadDmcColorMap = loadDmcColorMap();
    Map<String, Colour> dmcColorSelectedMap = new TreeMap<String, Colour>();

    for (int i = 0; i < strArray.length; i++) {
      dmcColorSelectedMap.put(strArray[i], loadDmcColorMap.get(strArray[i]));
    }

    return applyImagePattern(imagePattern.getPixelArray(), dmcColorSelectedMap, pixelation);
  }

}
