package model.utilities;

import model.exception.ModelValidationException;
import model.imageprocessor.MatrixType;
import model.imageprocessor.PixelateImageMatrix;
import model.images.Channel;
import model.images.Image;
import model.pattern.DmcRbcProperties;
import model.pixel.Colour;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.Position;
import model.pixel.PositionImpl;
import model.pixel.RgbColor;
import model.pixel.SuperPixel;
import model.pixel.SuperPixelImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This class represents helper class which contains static methods and utility
 * function for common operations performed across the project.
 */

public class Helper {

  /**
   * This method validate whether colors red, blue or green is within the range 0
   * and 256.
   *
   * @param arg arg with datatype as int.
   */
  public static boolean isNotValid(int arg) {

    return (Constant.COLOR_MIN > arg) && (arg > Constant.COLOR_MAX);
  }

  /**
   * This method validate whether depth is within the range.
   *
   * @param arg arg with datatype as int.
   * @return boolean true if the depth falls in the range 1 to 255 inclusive.
   */
  public static boolean isInValidDepth(int arg) {

    return (Constant.DEPTH_MIN > arg) && (arg > Constant.DEPTH_MAX);
  }

  /**
   * This method validate whether argument is negative or not.
   *
   * @param arg arg with datatype as int.
   * @return boolean true if the number is negative.
   */
  public static boolean isNegative(int arg) {

    return (arg < 0);
  }

  /**
   * This method validate whether argument is positive or not. It should not be
   * negative or 0.
   *
   * @param arg arg with datatype as int.
   * @return boolean true if the number is positve and not 0.
   */
  public static boolean isNotNegativeOrZero(int arg) {

    return (arg <= 0);
  }

  /**
   * This method validate whether argument is null or not.
   *
   * @param object Object with datatype as object.
   */

  public static void isObjectNull(Object object) {

    if (null == object) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * This method is used to apply padding to an array to ease the application of a
   * kernel i.e. a multiplication operation.
   *
   * @param imageArr        The input image array.
   * @param numberOfPadding The number of padding to add across.
   * @return A 2D Pixel array with padding.
   */
  public static Pixel[][] padChannel(Pixel[][] imageArr, int numberOfPadding) {
    isObjectNull(imageArr);

    Colour color = new RgbColor(0, 0, 0);
    Pixel pixelNew = new PixelImpl(color);
    isObjectNull(imageArr[0]);

    Pixel[][] newPadArr = new PixelImpl[imageArr.length + numberOfPadding * 2][imageArr[0].length
        + numberOfPadding * 2];
    isObjectNull(newPadArr);

    for (int i = 0; i < newPadArr.length; i++) {

      isObjectNull(newPadArr[i]);
      for (int j = 0; j < newPadArr[i].length; j++) {
        newPadArr[i][j] = pixelNew;
      }
    }
    for (int i = 0; i < imageArr.length; i++) {
      isObjectNull(imageArr[i]);
      for (int j = 0; j < imageArr[i].length; j++) {
        newPadArr[i + numberOfPadding][j + numberOfPadding] = imageArr[i][j];
      }
    }
    return newPadArr;
  }

  /**
   * Method to check whether a given number is an even or an odd number.
   *
   * @param arg An input number.
   * @return A boolean true if even.
   */
  public static boolean isEvenNumber(int arg) {

    return (arg % 2 == 0);
  }

  /**
   * This method gives a list of intensities of color within the 0-255 range with
   * equally spaced intensities. The input param determines the number of equally
   * sequenced intensities being asked for.
   *
   * @param maxNumbers The number of equally spaced intensities.
   * @return A list of equally spaced intensities.
   */
  public static List<Integer> getEquallySpacedIntensity(int maxNumbers) {

    List<Integer> colorPalette = new ArrayList<Integer>();
    int max = 255;
    int min = 0;

    for (int i = 0; i < maxNumbers - 1; i++) {
      int step = min + i * (max - min) / maxNumbers;
      colorPalette.add(step);
    }

    colorPalette.add(max);

    return colorPalette;

  }

  /**
   * This method gives the nearest value in the equally spaced intensities to
   * apply image reduction.
   *
   * @param num        The number to look nearest for.
   * @param maxNumbers The total number of equally spaced intensities.
   * @return The intensity that is nearest to the input num.
   */
  public static int getNearestColor(int num, int maxNumbers) {
    List<Integer> paletteRange = getEquallySpacedIntensity(maxNumbers);

    if (paletteRange == null) {
      throw new IllegalArgumentException();
    }

    int nearestIntensity = paletteRange.stream()
        .min(Comparator.comparingInt(i -> Math.abs(i - num)))
        .orElseThrow(() -> new IllegalArgumentException("Value not found"));
    return nearestIntensity;

  }

  /**
   * This method validate whether argument is equal or not. If not equal then
   * throws IllegalArgumentException.
   *
   * @param object1 arg with datatype as int.
   * @param object2 arg with datatype as int.
   */
  public static void isNotEqual(int object1, int object2) {

    if (object1 != object2) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * This method iterates over the passed image array to look and capture random
   * position across the image to collect as seed for applying the image mosaic
   * for a given image.
   *
   * @param seedLength    the number of seed to capture in the image
   * @param pixelImgArray the given input image array
   * @return a Position[] array containing random Positions
   */
  public static Position[] chooseRandomSeed(int seedLength, Pixel[][] pixelImgArray) {

    isNotNegativeOrZero(seedLength);
    isObjectNull(pixelImgArray);
    isObjectNull(pixelImgArray[0]);

    Position[] seedPostionArray = new PositionImpl[seedLength];
    int maxHeight = pixelImgArray.length - 1;
    int maxWidth = pixelImgArray[0].length - 1;
    int min = 0;
    Position seedPostion = null;
    for (int i = 0; i < seedLength; i++) {

      int randomHeightPos = (int) (Math.random() * ((maxHeight - min) + 1)) + min;
      int randomWidthPos = (int) (Math.random() * ((maxWidth - min) + 1)) + min;
      seedPostion = new PositionImpl(randomHeightPos, randomWidthPos);
      seedPostionArray[i] = seedPostion;
    }

    return seedPostionArray;

  }

  /**
   * This method calculates the Eucledian distance between two given object of
   * Position class. These two objects represents different coordinates and the
   * euclidean distance is calculated using the coordinate values of the x and the
   * y.
   *
   * @param first  the first position
   * @param second the second position
   * @return an int value representing distance between the positions
   */
  public static int calEuclideanDistanceSq(Position first, Position second) {
    Helper.isObjectNull(first);
    Helper.isObjectNull(second);

    int result = (int) (Math.pow((first.getPositionHeight() - second.getPositionHeight()), 2)
        + Math.pow((first.getPositionWidth() - second.getPositionWidth()), 2));
    return result;
  }

  /**
   * This method calculates the redmean distance between two given colors. The
   * distance value calculated is used to find the nearest matching value of
   * color.
   *
   * @param first  the first color
   * @param second the second color
   * @return an int value representing distance between colors
   */
  public static int calDistDeltaSquare(Colour first, Colour second) {
    Helper.isObjectNull(first);
    Helper.isObjectNull(second);

    float redMean = (first.getRedColor() + second.getRedColor()) / 2;
    float deltaRedSq = (float) Math.pow((first.getRedColor() - second.getRedColor()), 2);
    float deltaGreenSq = (float) Math.pow((first.getGreenColor() - second.getGreenColor()), 2);
    float deltaBlueSq = (float) Math.pow((first.getBlueColor() - second.getBlueColor()), 2);
    int result = Math.round((2 + redMean / 256) * deltaRedSq + 4 * deltaGreenSq
        + (2 + (255 - redMean) / 256) * deltaBlueSq);
    return result;
  }

  /**
   * This method shows the occurrence of the super-pixel for the height and the
   * width of an image when trying to find the super-pixels for an image for an
   * image that has dimensions that are not divisible by the pixelation count.
   *
   * @param imageParameter the image parameter
   * @param pixelation     the number of super-pixels
   * @return as int value of occurrence
   */
  public static int calculateOccurrence(int imageParameter, int pixelation) {
    int superPixelParameter1 = imageParameter / pixelation;
    int occurence2 = imageParameter - superPixelParameter1 * pixelation;
    return pixelation - occurence2;
  }

  /**
   * This method shows the occurrence of the super-pixel for the height and the
   * width of an image when trying to find the super-pixels for an image for an
   * image that has dimensions that are not divisible by the pixelation count.
   *
   * @param imageHeight   the image parameter height
   * @param superPixelDim the dimension of super-pixels
   * @return as int value of occurrence
   */
  public static int calculateOccurrenceHeight(int imageHeight, int superPixelDim) {
    int occurenceSuperPixel = imageHeight / superPixelDim;
    return occurenceSuperPixel - (imageHeight % superPixelDim);
  }

  /**
   * This method checks whether a given number is a single digit number.
   *
   * @param number the input int number
   */
  public static void inValidSingleNumber(int number) {

    if (number < 10) {
      throw new IllegalArgumentException();
    }

  }

  /**
   * This method combines all the helper checks that can be verified over an
   * image.
   *
   * @param image an input Image
   */
  public static void imageChecks(Image image) {
    Helper.isObjectNull(image);
    Pixel[][] imagePixelArr = image.getPixelArray();
    Helper.isObjectNull(imagePixelArr);
    Helper.isObjectNull(imagePixelArr[0]);
    Helper.isNotEqual(image.getImageHeight(), imagePixelArr.length);
    Helper.isNotEqual(image.getImageWidth(), imagePixelArr[0].length);
  }

  /**
   * This method perform the pixelation of an image to generate an pixelated image
   * with chunking.
   * 
   * @param imgPixelArr          the input image of pixel array
   * @param pixelation           pixelation parameter
   * @param matrixTransformation matrixtransformation performed for image
   *                             pixelation or pattern generation text
   * @legend legend map which contains superpixel unicode symbol and dmc color
   * @return pixel array of image
   * @throws IOException for IO issue
   */
  public static Map<String, Object> imageChunkingOnSuperPixel(Pixel[][] imgPixelArr, int pixelation,
      String matrixTransformation, Map<String, String> legend) throws IOException {
    if (null == imgPixelArr || null == matrixTransformation || null == legend) {
      throw new IllegalArgumentException();
    }
    Map<String, Object> resultMap = new HashMap<String, Object>();
    boolean superPixelTwoWidth = false;
    boolean superPixelTwoHeight = false;
    Map<String, Integer> iMap = new HashMap<>();
    iMap.put("pixelation", pixelation);

    int imageWidth = imgPixelArr[0].length;
    int imageHeight = imgPixelArr.length;

    if (imageWidth % pixelation == 0) {
      iMap.put("superPixelWidth", imageWidth / pixelation);
      iMap.put("occrenceWidth", pixelation);
    } else {
      superPixelTwoWidth = true;
      iMap.put("superPixelWidth", imageWidth / pixelation);
      iMap.put("occrenceWidth", Helper.calculateOccurrence(imageWidth, pixelation));
    }

    if (imageHeight % iMap.get("superPixelWidth") == 0) {
      iMap.put("superPixelHeight", imageWidth / pixelation);
      iMap.put("occrenceHeight", pixelation);
    } else {
      superPixelTwoHeight = true;
      iMap.put("superPixelHeight", imageWidth / pixelation);
      iMap.put("occrenceHeight",
          Helper.calculateOccurrenceHeight(imageHeight, iMap.get("superPixelWidth")));
    }

    int superPixelMatrixWidth = iMap.get("occrenceWidth") * iMap.get("superPixelWidth");
    int superPixelMatrixHeight = iMap.get("occrenceHeight") * iMap.get("superPixelHeight");

    if (!superPixelTwoHeight && !superPixelTwoWidth) {

      resultMap = Helper.imagePixelationOnMatrix(imgPixelArr, iMap,
          PixelateImageMatrix.SUPERPIXEL_ONE_WIDTH_ONE_HEIGHT.toString(), imgPixelArr.length,
          imgPixelArr[0].length, matrixTransformation, pixelation, legend);

    } else if (!superPixelTwoHeight && superPixelTwoWidth) {

      resultMap = Helper.imagePixelationOnMatrix(imgPixelArr, iMap,
          PixelateImageMatrix.SUPERPIXEL_TWO_WIDTH_ONE_HEIGHT.toString(), imgPixelArr.length,
          superPixelMatrixWidth, matrixTransformation, pixelation, legend);

    } else if (superPixelTwoHeight && !superPixelTwoWidth) {
      resultMap = Helper.imagePixelationOnMatrix(imgPixelArr, iMap,
          PixelateImageMatrix.SUPERPIXEL_ONE_WIDTH_TWO_HEIGHT.toString(), superPixelMatrixHeight,
          imgPixelArr[0].length, matrixTransformation, pixelation, legend);
    } else if (superPixelTwoHeight && superPixelTwoWidth) {
      resultMap = Helper.imagePixelationOnMatrix(imgPixelArr, iMap,
          PixelateImageMatrix.SUPERPIXEL_TWO_WIDTH_TWO_HEIGHT.toString(), superPixelMatrixHeight,
          superPixelMatrixWidth, matrixTransformation, pixelation, legend);
    } else {
      throw new IllegalArgumentException();
    }

    return resultMap;
  }

  /**
   * This method perform the pixelation of an image to generate an pixelated image
   * with chunking on image matrix. The passed argument of instruction map is
   * executed for 4 scenarios depending on the variation in the height and the
   * width of superpixel.
   *
   * @param imgPixelArr         the input image pixel array
   * @param iMap                the input instruction map
   * @param pixelateImgMatrix   the identified scenario in PixelateImgMatrix
   * @param matrixInitialHeight the image height based on scenario
   * @param matrixInitialWidth  the image width based on scenario
   * @param matrixType          the operation is for pixelation or pattern
   *                            generation image or pattern generation text
   * @param pixelation          the pixelation parameter
   * @return a Pixel[][] array for the resultant image after pixelation
   * @throws IOException for IO issue
   */
  private static Map<String, Object> imagePixelationOnMatrix(Pixel[][] imgPixelArr,
      Map<String, Integer> iMap, String pixelateImgMatrix, int matrixInitialHeight,
      int matrixInitialWidth, String matrixType, int pixelation, Map<String, String> legend)
      throws IOException {

    if (null == imgPixelArr || null == iMap || null == iMap.get("superPixelWidth")
        || null == iMap.get("superPixelHeight") || null == iMap.get("occrenceWidth")
        || null == iMap.get("occrenceHeight") || null == pixelateImgMatrix || null == matrixType
        || null == legend) {
      throw new IllegalArgumentException();
    }

    Pixel[][] newImagePixelMatrix = null;
    SuperPixel[][] newSuperPixelImageMatrix = null;
    Map<String, Object> resultMap = new HashMap<String, Object>();

    if (MatrixType.TRANSFORMATION_PATTERN_TEXT.toString().equals(matrixType)) {
      int height = imgPixelArr.length / iMap.get("superPixelWidth");
      newSuperPixelImageMatrix = new SuperPixelImpl[height][pixelation];
    } else if (MatrixType.TRANSFORMATION_PIXELATE.toString().equals(matrixType)) {
      newImagePixelMatrix = new PixelImpl[imgPixelArr.length][imgPixelArr[0].length];
    }

    int superPixelMatrixWidth = iMap.get("occrenceWidth") * iMap.get("superPixelWidth");
    int superPixelMatrixHeight = iMap.get("occrenceHeight") * iMap.get("superPixelHeight");
    int superPixelWidthOne = (iMap.get("superPixelWidth") + 1);
    int superPixelHeightOne = (iMap.get("superPixelHeight") + 1);
    iMap.put("startHeight", 0);
    iMap.put("startWidth", 0);
    iMap.put("matrixHeight", matrixInitialHeight);
    iMap.put("matrixWidth", matrixInitialWidth);

    if (PixelateImageMatrix.SUPERPIXEL_TWO_WIDTH_ONE_HEIGHT.toString().equals(pixelateImgMatrix)) {
      if (MatrixType.TRANSFORMATION_PATTERN_TEXT.toString().equals(matrixType)) {
        newSuperPixelImageMatrix = imageSuperPixelOnSubMatrix(imgPixelArr, newSuperPixelImageMatrix,
            iMap, matrixType, legend);
      } else if (MatrixType.TRANSFORMATION_PIXELATE.toString().equals(matrixType)) {
        newImagePixelMatrix = imagePixelationOnSubMatrix(imgPixelArr, newImagePixelMatrix, iMap,
            matrixType);
      }

      iMap.put("matrixWidth", imgPixelArr[0].length);
      iMap.put("superPixelWidth", superPixelWidthOne);
      iMap.put("startWidth", superPixelMatrixWidth);

    } else if (PixelateImageMatrix.SUPERPIXEL_ONE_WIDTH_TWO_HEIGHT.toString()
        .equals(pixelateImgMatrix)) {

      if (MatrixType.TRANSFORMATION_PATTERN_TEXT.toString().equals(matrixType)) {
        newSuperPixelImageMatrix = imageSuperPixelOnSubMatrix(imgPixelArr, newSuperPixelImageMatrix,
            iMap, matrixType, legend);
      } else if (MatrixType.TRANSFORMATION_PIXELATE.toString().equals(matrixType)) {

        newImagePixelMatrix = imagePixelationOnSubMatrix(imgPixelArr, newImagePixelMatrix, iMap,
            matrixType);
      }
      iMap.put("matrixHeight", imgPixelArr.length);
      iMap.put("superPixelHeight", superPixelHeightOne);
      iMap.put("startHeight", superPixelMatrixHeight);

    } else if (PixelateImageMatrix.SUPERPIXEL_TWO_WIDTH_TWO_HEIGHT.toString()
        .equals(pixelateImgMatrix)) {

      if (MatrixType.TRANSFORMATION_PATTERN_TEXT.toString().equals(matrixType)) {
        newSuperPixelImageMatrix = imageSuperPixelOnSubMatrix(imgPixelArr, newSuperPixelImageMatrix,
            iMap, matrixType, legend);
      } else if (MatrixType.TRANSFORMATION_PIXELATE.toString().equals(matrixType)) {
        newImagePixelMatrix = imagePixelationOnSubMatrix(imgPixelArr, newImagePixelMatrix, iMap,
            matrixType);
      }

      iMap.put("matrixWidth", imgPixelArr[0].length);
      iMap.put("superPixelWidth", superPixelWidthOne);
      iMap.put("startWidth", superPixelMatrixWidth);

      if (MatrixType.TRANSFORMATION_PATTERN_TEXT.toString().equals(matrixType)) {
        newSuperPixelImageMatrix = imageSuperPixelOnSubMatrix(imgPixelArr, newSuperPixelImageMatrix,
            iMap, matrixType, legend);
      } else if (MatrixType.TRANSFORMATION_PIXELATE.toString().equals(matrixType)) {
        newImagePixelMatrix = imagePixelationOnSubMatrix(imgPixelArr, newImagePixelMatrix, iMap,
            matrixType);
      }

      iMap.put("matrixWidth", superPixelMatrixWidth);
      iMap.put("superPixelWidth", superPixelWidthOne - 1);
      iMap.put("startWidth", 0);
      iMap.put("matrixHeight", imgPixelArr.length);
      iMap.put("superPixelHeight", superPixelHeightOne);
      iMap.put("startHeight", superPixelMatrixHeight);

      if (MatrixType.TRANSFORMATION_PATTERN_TEXT.toString().equals(matrixType)) {
        newSuperPixelImageMatrix = imageSuperPixelOnSubMatrix(imgPixelArr, newSuperPixelImageMatrix,
            iMap, matrixType, legend);
      } else if (MatrixType.TRANSFORMATION_PIXELATE.toString().equals(matrixType)) {
        newImagePixelMatrix = imagePixelationOnSubMatrix(imgPixelArr, newImagePixelMatrix, iMap,
            matrixType);
      }

      iMap.put("matrixWidth", imgPixelArr[0].length);
      iMap.put("superPixelWidth", superPixelWidthOne);
      iMap.put("startWidth", superPixelMatrixWidth);
    }
    if (MatrixType.TRANSFORMATION_PATTERN_TEXT.toString().equals(matrixType)) {
      newSuperPixelImageMatrix = imageSuperPixelOnSubMatrix(imgPixelArr, newSuperPixelImageMatrix,
          iMap, matrixType, legend);
      resultMap.put(matrixType, newSuperPixelImageMatrix);
    } else if (MatrixType.TRANSFORMATION_PIXELATE.toString().equals(matrixType)) {
      newImagePixelMatrix = imagePixelationOnSubMatrix(imgPixelArr, newImagePixelMatrix, iMap,
          matrixType);
      resultMap.put(matrixType, newImagePixelMatrix);
    }

    return resultMap;
  }

  private static Pixel[][] imagePixelationOnSubMatrix(Pixel[][] imgPixelArr,
      Pixel[][] newImagePixelMatrix, Map<String, Integer> iMap, String matrixTransformation) {

    if (null == imgPixelArr || null == iMap || null == newImagePixelMatrix
        || iMap.get("matrixHeight") == null || iMap.get("matrixWidth") == null
        || iMap.get("superPixelHeight") == null || iMap.get("superPixelWidth") == null
        || iMap.get("startHeight") == null || iMap.get("startWidth") == null
        || matrixTransformation == null) {
      throw new IllegalArgumentException();
    }

    int centerSuperPixelHeight = 0;
    int centerSuperPixelWidth = 0;

    for (int i = iMap.get("startHeight"); i < iMap.get("matrixHeight"); i = i
        + iMap.get("superPixelHeight")) {
      for (int j = iMap.get("startWidth"); j < iMap.get("matrixWidth"); j = j
          + iMap.get("superPixelWidth")) {
        centerSuperPixelHeight = i + iMap.get("superPixelHeight") / 2;
        centerSuperPixelWidth = j + iMap.get("superPixelWidth") / 2;
        for (int m = 0; m < iMap.get("superPixelHeight"); m++) {

          for (int n = 0; n < iMap.get("superPixelWidth"); n++) {
            newImagePixelMatrix[i + m][j
                + n] = imgPixelArr[centerSuperPixelHeight][centerSuperPixelWidth];
          }
        }
      }
    }

    return newImagePixelMatrix;
  }

  private static SuperPixel[][] imageSuperPixelOnSubMatrix(Pixel[][] imgPixelArr,
      SuperPixel[][] newImagePixelMatrix, Map<String, Integer> iMap, String matrixTransformation,
      Map<String, String> legend) throws IOException {

    if (null == imgPixelArr || null == iMap || null == newImagePixelMatrix
        || iMap.get("matrixHeight") == null || iMap.get("matrixWidth") == null
        || iMap.get("superPixelHeight") == null || iMap.get("superPixelWidth") == null
        || iMap.get("startHeight") == null || iMap.get("startWidth") == null
        || matrixTransformation == null || null == legend) {
      throw new IllegalArgumentException();
    }

    int centerSuperPixelHeight = 0;
    int centerSuperPixelWidth = 0;

    int m = 0;
    if (iMap.get("startHeight") != 0) {
      m = iMap.get("startHeight") / (iMap.get("superPixelHeight") - 1);
    }
    int startWidthN = 0;
    if (iMap.get("startWidth") != 0) {
      startWidthN = iMap.get("startWidth") / (iMap.get("superPixelWidth") - 1);
    }
    Colour newColor = null;
    for (int i = iMap.get("startHeight"); i < iMap.get("matrixHeight"); i = i
        + iMap.get("superPixelHeight")) {
      int n = startWidthN;
      for (int j = iMap.get("startWidth"); j < iMap.get("matrixWidth"); j = j
          + iMap.get("superPixelWidth")) {
        centerSuperPixelHeight = i + iMap.get("superPixelHeight") / 2;
        centerSuperPixelWidth = j + iMap.get("superPixelWidth") / 2;

        int red = imgPixelArr[centerSuperPixelHeight][centerSuperPixelWidth].getRedColor();
        int green = imgPixelArr[centerSuperPixelHeight][centerSuperPixelWidth].getGreenColor();
        int blue = imgPixelArr[centerSuperPixelHeight][centerSuperPixelWidth].getBlueColor();
        newColor = new RgbColor(red, green, blue);

        Map<String, String> dmcColorMap;
        try {
          dmcColorMap = DmcRbcProperties.getInstance().getDmcPropertiesColorKey();
        } catch (IOException e) {
          throw new IOException("Unable to read properties file");
        }

        String dmcColorName = null;
        if (null != dmcColorMap) {
          StringBuilder strBuilder = new StringBuilder();
          strBuilder.append(String.valueOf(red) + ",");
          strBuilder.append(String.valueOf(green) + ",");
          strBuilder.append(String.valueOf(blue));
          dmcColorName = dmcColorMap.get(strBuilder.toString());

        } else {
          throw new IllegalArgumentException("Dmc color list could not be loaded. ");
        }
        int height = iMap.get("superPixelHeight");
        int width = iMap.get("superPixelWidth");
        newImagePixelMatrix[m][n] = new SuperPixelImpl(newColor, centerSuperPixelHeight,
            centerSuperPixelWidth, legend.get(dmcColorName), dmcColorName, height, width);
        n++;
      }
      m++;
    }

    return newImagePixelMatrix;
  }

  /**
   * This method separates the values of the different intensities in the DMC
   * properties file into specific red, green, and the blue channel intensity.
   *
   * @param colorStr the color string containing the intensities separated by
   *                 comma
   * @return an object of Color type
   */
  public static Colour decodeDmcColor(String colorStr) {
    Helper.isObjectNull(colorStr);
    String[] colorStrArray = colorStr.split("[,]", 0);
    int red = Integer.parseInt(colorStrArray[0]);
    int green = Integer.parseInt(colorStrArray[1]);
    int blue = Integer.parseInt(colorStrArray[2]);
    Colour colorNew = new RgbColor(red, green, blue);
    return colorNew;
  }

  /**
   * This method generates a random unicode symbol for the pattern generation for
   * an image. The pattern is generated for cross-stitched pattern using the
   * nearest DMC floss color.
   *
   * @return a unicode string of symbol
   */
  public static String generateRandomSymbol() {
    Random rand = new Random(); // instance of random class
    int min = 32;
    int upperbound = 140000;
    int int_random = rand.nextInt((upperbound - min)) + min;
    // 127 to 159
    if (int_random >= 127 || int_random <= 159) {
      int_random = int_random + 33;
    }
    char symbol = (char) int_random;
    String strSymbol = String.valueOf(symbol).replaceAll("\\p{C}", "?");
    if (strSymbol.equals("?")) {
      strSymbol = generateRandomSymbol();
    }

    return String.valueOf(strSymbol);

  }

  /**
   * This method looks for and finds the next unique unicode symbol for
   * representation of the cross-stitched pattern.
   *
   * @param legend the cross-stitched pattern legend map
   * @return a new unique unicode symbol
   */
  public static String findUniqueSymbol(Map<String, String> legend) {
    isObjectNull(legend);
    String symbol = generateRandomSymbol();
    while (legend.containsValue(symbol)) {
      symbol = generateRandomSymbol();
    }
    return symbol;
  }

  /**
   * Method to generate random integer values within specific range.
   * 
   * @return an int value
   */
  public static int generateRandomIntegerValue() {
    Random rand = new Random(); // instance of random class
    int min = 33;
    int upperbound = 700;
    int intRandom = rand.nextInt((upperbound - min)) + min;

    if (intRandom >= 33 && intRandom <= 45) {
      return intRandom;
    } else if (intRandom >= 47 && intRandom <= 126) {
      return intRandom;
    } else if (intRandom >= 161 && intRandom <= 172) {
      return intRandom;
    } else if (intRandom >= 174 && intRandom <= 591) {
      return intRandom;
    } else if (intRandom >= 647 && intRandom <= 669) {
      return intRandom;
    } else {
      return generateRandomIntegerValue();
    }

  }

  /**
   * method to generate a random unicode symbol to be used for pattern in cross
   * stitch representation.
   * 
   * @return a char for a unicode symbol
   */
  public static char generateRandomSymbolValue() {
    int intRandom = generateRandomIntegerValue();
    char symbol = (char) intRandom;

    return symbol;

  }

  /**
   * Method to look for a new unique unicode symbol to be used for pattern in
   * cross stitch representation.
   * 
   * @param legend the current set of unicode symbols
   * @return a new char unique unicode
   */
  public static char findUniqueSymbolValue(Map<String, String> legend) {
    if (legend == null) {
      throw new IllegalArgumentException();
    }
    char symbol = generateRandomSymbolValue();
    while (legend.containsValue(symbol)) {
      symbol = generateRandomSymbolValue();
    }
    return symbol;
  }

  /**
   * THis method get the absolute location for a file name in the project
   * directory.
   *
   * @param filename        a string filename
   * @param isLoadOperation isLoad operation
   * @return a string of absolute filename
   */
  public static String getAbsoluteFileName(String filename, boolean isLoadOperation) {

    File f = new File("abc");

    // Get the absolute path of file f
    String path = f.getAbsolutePath();
    String absolute = path.substring(0, path.length() - 3);
    // the file path of absolute file
    StringBuffer fileNameBuffer = new StringBuffer();
    if (isLoadOperation) {
      fileNameBuffer.append(absolute).append("input/").append(filename);
    } else {
      fileNameBuffer.append(absolute).append("output/").append(filename);
    }

    String absolutefileName = fileNameBuffer.toString();
    return absolutefileName;
  }

  /**
   * This method converts an object of Image type into a 3D int array.
   *
   * @param image an input of Image type
   * @return a 3D int[][][] aray
   */
  public static int[][][] createImgIntArray(Image image) {

    isObjectNull(image);
    Pixel[][] pixelArray = image.getPixelArray();
    isObjectNull(pixelArray);

    int[][][] newImgArr = new int[image.getImageHeight()][image
        .getImageWidth()][Channel.values().length];

    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {

        isObjectNull(pixelArray[i][j]);
        newImgArr[i][j][Channel.RED.ordinal()] = pixelArray[i][j].getRedColor();
        newImgArr[i][j][Channel.GREEN.ordinal()] = pixelArray[i][j].getGreenColor();
        newImgArr[i][j][Channel.BLUE.ordinal()] = pixelArray[i][j].getBlueColor();
      }
    }

    return newImgArr;
  }

  /**
   * This method validate whether string is null or not.
   *
   * @param object String with datatype as object.
   * @throws ModelValidationException excption if string is invalid.
   */
  public static void isObjectNullStr(String object, String message)
      throws ModelValidationException {

    if (null == object && "".equals(object)) {
      throw new ModelValidationException(message);
    }
  }

  /**
   * This method decodes tht command to get the value that is related to the
   * operation to be performed.
   *
   * @param inputStr with an int value contained
   * @return an int value for the parameter
   * @throws ModelValidationException on invalid string
   */
  public static int inputParameterDecode(String inputStr, String name)
      throws ModelValidationException {
    isObjectNull(name);
    StringBuilder message = new StringBuilder();
    message.append(name);
    message.append(" parameter is not valid.");
    isObjectNullStr(inputStr, message.toString());
    int seed = 0;
    try {
      seed = Integer.parseInt(inputStr);
    } catch (NumberFormatException e) {
      throw new ModelValidationException(message.toString());
    }
    return seed;
  }

  /**
   * This method validates the pixelation parameter and if it zero then pixelation
   * is not performed on the image and to do cross stiched pattern pixelation is
   * must.
   *
   * @param pixelation an int value for the parameter
   * @throws ModelValidationException on invalid string
   */
  public static void pixelationValidation(int pixelation) throws ModelValidationException {
    if ((pixelation <= 0)) {
      throw new ModelValidationException(
          "Please perform pixelate and then cross stitched pattern generation");
    }
  }

  /**
   * This method validate whether object is null or not.
   *
   * @param object String with datatype as object.
   * @throws ModelValidationException excption if command is invalid.
   */
  public static void isObjectNullObject(Object object, String message)
      throws ModelValidationException {

    if (null == object) {
      throw new ModelValidationException(message);
    }
  }

  /**
   * This method decodes the command to get the value that is related to the
   * operation to be performed.
   *
   * @param inputStr with an int value contained
   * @return an int value for the parameter
   */
  public static int inputParameterDecodeDmcColor(String inputStr) {
    isObjectNull(inputStr);
    int seed = 0;
    try {
      seed = Integer.parseInt(inputStr);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException();
    }
    return seed;
  }

  /**
   * This method separates the values of the different dmc colors passed as
   * string.
   *
   * @param colorStr the color string containing the different dmc color separated
   *                 by comma
   * @return an List of dmc color name
   */
  public static String[] decodeDMCColorStr(String colorStr) {
    Helper.isObjectNull(colorStr);
    String[] colorStrArray = colorStr.split("[,]", 0);
    return colorStrArray;

  }

  /**
   * Method to validate the position of mouse click.
   * 
   * @param x x co-ordinate
   * @param y y co-ordinate
   */
  public static void validateMouseClick(int x, int y) {
    if ((x <= 0) || (y <= 0)) {
      new ModelValidationException(
          " Please click  on the pattern to select the color to be swapped .");
    }
  }
}
