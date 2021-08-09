import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.ImageModel;
import model.ImageModelImpl;
import model.exception.ModelValidationException;
import model.imagematrix.Kernel;
import model.imagematrix.KernelImpl;
import model.imagematrix.TransformationMatrix;
import model.imagematrix.TransformationMatrixImpl;
import model.imageprocessor.BlurFilter;
import model.imageprocessor.GreyScaleTransformation;
import model.imageprocessor.ImageOperator;
import model.imageprocessor.ReduceImage;
import model.imageprocessor.ReduceImageEssence;
import model.imageprocessor.SepiaToneTransformation;
import model.imageprocessor.SharpenFilter;
import model.images.Channel;
import model.images.Image;
import model.images.RgbImage;
import model.pattern.DmcRbcProperties;
import model.pixel.Colour;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbColor;
import model.utilities.Helper;

/**
 * This class represents the ImageModel test cases scenarios which is test the
 * abstraction and features of color, pixel, image, image operator, kernel,
 * transformation matrix class.
 */
public class ImageModelTest {

  private Colour color;

  private Pixel pixel;
  private ImageModel imageModel;
  private Image image;

  private ImageOperator imageOperatorBlur;
  private ImageOperator imageOperatorSharp;
  private ImageOperator imageOperatorGrey;
  private ImageOperator imageOperatorSepia;

  private Kernel kernel;
  private Pixel[][] pixelArray;
  private int[][][] imageArr;

  private TransformationMatrix transformationMatrix;

  @Before
  public void setUp() {
    color = new RgbColor(1, 1, 1);
    pixel = new PixelImpl(color);
    imageArr = new int[3][3][Channel.values().length];

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {

        imageArr[i][j][Channel.RED.ordinal()] = i;
        imageArr[i][j][Channel.GREEN.ordinal()] = j;
        imageArr[i][j][Channel.BLUE.ordinal()] = i * j;
      }
    }
    pixelArray = new PixelImpl[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {

        pixelArray[i][j] = pixel;

      }
    }

    image = new RgbImage(imageArr, 3, 3);
    kernel = new KernelImpl(3,

        new float[] {

            1f / 16f, 1f / 8f, 1f / 16f,

            1f / 8f, 1f / 4f, 1f / 8f,

            1f / 16f, 1f / 8f, 1f / 16f });

    transformationMatrix = new TransformationMatrixImpl(3, 3,

        new float[] {

            0.2126f, 0.7152f, 0.0722f,

            0.2126f, 0.7152f, 0.0722f,

            0.2126f, 0.7152f, 0.0722f });

    imageOperatorBlur = new BlurFilter(kernel);

    imageOperatorSharp = new SharpenFilter(kernel);
    imageOperatorGrey = new GreyScaleTransformation(transformationMatrix);
    imageOperatorSepia = new SepiaToneTransformation(transformationMatrix);

    imageModel = new ImageModelImpl();

  }

  /**
   * This method is for testing the constructor of Color class.
   */
  @Test
  public void testConstructorColor() {

    assertNotNull(color);
    assertEquals("RgbColor [redColor=1, blueColor=1, greenColor=1]", color.toString());
  }

  /**
   * This method is for testing the constructor of pixel class.
   */
  @Test
  public void testConstructorPixel() {

    assertNotNull(pixel);
    assertEquals("RgbColor [redColor=1, blueColor=1, greenColor=1]", color.toString());
  }

  /**
   * This method is for testing the constructor of Image class.
   */
  @Test
  public void testConstructorImage() {

    assertNotNull(image);
    assertEquals("RgbImage [pixel=3]", image.toString());
  }

  /**
   * This method is for testing the constructor of Model class.
   */
  @Test
  public void testConstructorModel() {

    assertNotNull(imageModel);
    assertEquals("ImageModelImpl [image=]", imageModel.toString());
  }

  /**
   * This method is for testing the constructor of Kernel.
   */
  @Test
  public void testConstructorKernel() {

    assertNotNull(kernel);
    assertEquals("Kernel, kernelHeight=3, kernelWidth=3]", kernel.toString());
  }

  /**
   * This method is for testing the constructor of TransformationMatrix.
   */
  @Test
  public void testConstructorTransformationMatrix() {

    assertNotNull(transformationMatrix);
    assertEquals("TransformationImpl [ matrixHeight=3, matrixWidth=3]",
        transformationMatrix.toString());
  }

  /**
   * This method is for testing the constructor of BlurFilter.
   */
  @Test
  public void testConstructorBlurFilter() {

    assertNotNull(imageOperatorBlur);
    assertEquals("BlurFilter [kernel=Kernel, kernelHeight=3, kernelWidth=3]]",
        imageOperatorBlur.toString());
  }

  /**
   * This method is for testing the constructor of SharpenFilter.
   */
  @Test
  public void testConstructorSharpenFilter() {

    assertNotNull(imageOperatorSharp);
    assertEquals("SharpenFilter [kernel=Kernel, kernelHeight=3, kernelWidth=3]]",
        imageOperatorSharp.toString());
  }

  /**
   * This method is for testing the constructor of GreyScaleTransformation.
   */
  @Test
  public void testConstructorGreyScale() {

    assertNotNull(imageOperatorGrey);
    assertEquals("GreyScaleTransformation [transformationMatrix=TransformationImpl "
        + "[ matrixHeight=3, matrixWidth=3]]", imageOperatorGrey.toString());
  }

  /**
   * This method is for testing the constructor of SepiaToneTransformation.
   */
  @Test
  public void testConstructorSepiaTone() {

    assertNotNull(imageOperatorSepia);
    assertEquals("SepiaToneTransformation [transformationMatrix=TransformationImpl "
        + "[ matrixHeight=3, matrixWidth=3]]", imageOperatorSepia.toString());
  }

  /**
   * This method is for testing invalid scenario of Color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testColorInvalid() {
    Colour colorNeg = new RgbColor(-1, 256, -1);

  }

  /**
   * This method is for testing invalid scenario of Pixel.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPixelInvalid() {

    Pixel pixelNeg = new PixelImpl(null);
  }

  /**
   * This method is for testing invalid scenario of Image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImageInvalid() {
    int[][][] intArray = null;
    Image imageNeg = new RgbImage(intArray, 0, 0);
  }

  /**
   * This method is for testing invalid scenario of kernel.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testKernelInvalid() {
    Kernel kernelNeg = new KernelImpl(0, null);
  }

  /**
   * This method is for testing invalid scenario of transformation matrix.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTransformationMatInvalid() {
    TransformationMatrix transformationMatrixNeg = new TransformationMatrixImpl(0, 0, null);
  }

  /**
   * This method is for testing operation of Image Model .
   * 
   * @throws ModelValidationException exception
   * @throws IOException              exception
   */
  @Test
  public void testSharpFilter() throws ModelValidationException, IOException {

    ImageOperator sharpenFilter = new SharpenFilter(kernel);

    imageModel.applyImageOperation(sharpenFilter);
    assertEquals("RgbImage [pixel=3]", imageModel.toString());
  }

  /**
   * This method is for testing operation of Image Model .
   * 
   * @throws ModelValidationException exception
   * @throws IOException              exception
   */
  @Test
  public void testBlurFilter() throws ModelValidationException, IOException {

    ImageOperator blurFilter = new BlurFilter(kernel);

    imageModel.applyImageOperation(blurFilter);

    assertEquals("RgbImage [pixel=3]", imageModel.toString());
  }

  /**
   * This method is for testing operation of Image Model .
   * 
   * @throws ModelValidationException exception
   * @throws IOException              exception
   */
  @Test
  public void testSepiaTone() throws ModelValidationException, IOException {

    ImageOperator sepiaToneOperator = new GreyScaleTransformation(transformationMatrix);

    imageModel.applyImageOperation(sepiaToneOperator);

    assertEquals("RgbImage [pixel=3]", imageModel.toString());
  }

  /**
   * This method is for testing operation of Image Model .
   * 
   * @throws ModelValidationException exception
   * @throws IOException              exception
   */
  @Test
  public void testGreyScale() throws ModelValidationException, IOException {

    ImageOperator greyScaleOperator = new GreyScaleTransformation(transformationMatrix);

    imageModel.applyImageOperation(greyScaleOperator);
    assertEquals("RgbImage [pixel=3]", imageModel.toString());
  }

  /**
   * This method is for testing operation of Image Model .
   *
   * @throws ModelValidationException exception
   * @throws IOException              exception
   */
  @Test
  public void testReduce() throws ModelValidationException, IOException {

    ImageOperator reduceImageOperator = new ReduceImage("8");

    imageModel.applyImageOperation(reduceImageOperator);
    assertEquals("RgbImage [pixel=3]", imageModel.toString());

  }

  /**
   * This method is for testing operation of Image Model .
   *
   * @throws ModelValidationException exception
   * @throws IOException              exception
   */
  @Test
  public void testReduceEssence() throws ModelValidationException, IOException {
    ImageOperator reduceImageOperatorEssence = new ReduceImageEssence("8");

    imageModel.applyImageOperation(reduceImageOperatorEssence);

    assertEquals("RgbImage [pixel=3]", imageModel.toString());
  }

  /**
   * This method is for testing invalid scenario of Image.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImageInvalid1() {
    Pixel[][] pixelArray = null;
    Image imageNeg = new RgbImage(pixelArray, 0, 0);
  }

  /**
   * This method is for testing valid scenario of Image from constructor loaded
   * using 2D array of pixel.
   */
  @Test
  public void testImagePixelArrValid() {

    Image image = new RgbImage(pixelArray, 3, 3);
    assertEquals("RgbImage [pixel=3]", image.toString());
  }

  /**
   * This method is for testing invalid scenario of Image from constructor loaded
   * using 2D array of pixel.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImagePixelArrInValidArray() {
    pixelArray = new PixelImpl[3][3];
    for (int i = 0; i < 3; i++) {

      pixelArray[i][1] = pixel;

    }
    Image image = new RgbImage(pixelArray, 2, 3);
    assertEquals("RgbImage [pixel=3]", image.toString());
  }

  /**
   * This method is for testing invalid scenario of Image from constructor loaded
   * using 2D array of pixel.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImagePixelArrInValidArrayNUll() {
    pixelArray = null;
    Image image = new RgbImage(pixelArray, 2, 3);
    assertEquals("RgbImage [pixel=3]", image.toString());
  }

  /**
   * This method is for testing invalid scenario of Image from constructor loaded
   * using 2D array of pixel.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImagePixelArrInValidArrayHeight() {
    Image image = new RgbImage(pixelArray, 2, 3);
    assertEquals("RgbImage [pixel=3]", image.toString());
  }

  /**
   * This method is for testing invalid scenario of Image from constructor loaded
   * using 2D array of pixel.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImagePixelArrInValidArrayWidth() {
    Image image = new RgbImage(pixelArray, 3, 2);
    assertEquals("RgbImage [pixel=3]", image.toString());
  }

  /**
   * This method is for testing invalid scenario of Image from constructor loaded
   * using 3D array of int.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImageIntArrInValidArrayNUll() {
    imageArr = null;
    Image image = new RgbImage(imageArr, 2, 3);
    assertEquals("RgbImage [pixel=3]", image.toString());
  }

  /**
   * This method is for testing invalid scenario of Image from constructor loaded
   * using 3D array of int.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImageIntArrInValidArrayHeight() {
    Image image = new RgbImage(imageArr, 2, 3);
    assertEquals("RgbImage [pixel=3]", image.toString());
  }

  /**
   * This method is for testing invalid scenario of Image from constructor loaded
   * using 3D array of int.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImageIntlArrInValidArrayWidth() {
    Image image = new RgbImage(imageArr, 3, 2);
    assertEquals("RgbImage [pixel=3]", image.toString());
  }

  /**
   * This method is used for testing the closet color from a pixel by using a
   * formula.
   */
  @Test
  public void testCalClosestColorDeltaSq() {
    Colour colorFirst = new RgbColor(5, 2, 3);
    Colour colorSecond = new RgbColor(78, 22, 30);

    int result = Helper.calDistDeltaSquare(colorFirst, colorSecond);
    assertEquals(47940, result);

  }

  /**
   * This method is for testing the loading of dmc color for an image pattern.
   */
  @Test
  public void loadProperties() throws IOException {
    Map<String, Colour> map = DmcRbcProperties.getInstance().getDmcProperties();
    assertNotNull(map);
  }

  /**
   * This method is for testing the color decoding.
   */
  @Test
  public void decodeColorTest() throws IOException {
    Colour color = Helper.decodeDmcColor("255,250,234");
    assertEquals("RgbColor [redColor=255, blueColor=234, greenColor=250]", color.toString());
  }

  /**
   * This method is for testing the color decoding.
   */
  @Test(expected = IllegalArgumentException.class)
  public void decodeColorTestNeg1() throws IOException {
    Colour color = Helper.decodeDmcColor(",250,234");
    assertEquals("RgbColor [redColor=255, blueColor=234, greenColor=250]", color.toString());
  }

  /**
   * This method is for testing the unique symbol for dmc .
   */
  @Test
  public void testFindUniqueSymbol() {
    Map<String, String> legendMap = new HashMap<>();
    legendMap.put("DMC-001", "a");
    legendMap.put("DMC-002", "b");
    legendMap.put("DMC-003", "c");
    String symbol = Helper.findUniqueSymbol(legendMap);
    assertEquals(false, legendMap.containsValue(symbol));

  }

}
