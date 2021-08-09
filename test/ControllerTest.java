import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controller.control.ImageController;
import controller.control.ImageControllerImpl;
import model.ImageModel;
import model.ImageModelImplMock;
import model.exception.ModelValidationException;

/**
 * This class represents the various scenarios to test the functionality of the
 * controller.
 */
public class ControllerTest {

  private ImageModel mockImageModel;
  private StringBuilder input;

  @Before
  public void setUp() throws IOException {
    input = new StringBuilder();
    mockImageModel = new ImageModelImplMock(input);
  }

  /**
   * This method is for test command for image not loaded .
   */
  @Test
  public void testCommandInvalid() throws ModelValidationException, IOException {
    Appendable outstream = System.out;
    ImageModel mockImageModel = new ImageModelImplMock(input);
    String initialString = "blur save abc.png quit";
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" applyIn  writeIn ", input.toString());
  }

  /**
   * This method is for testBlurCommand.
   */
  @Test
  public void testBlurCommand() throws ModelValidationException, IOException {
    Appendable outstream = System.out;
    ImageModel mockImageModel = new ImageModelImplMock(input);
    String initialString = "load abc.png blur save abc.png quit";
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  writeIn ", input.toString());

  }

  /**
   * This method is for testSharpenCommand.
   */
  @Test
  public void testSharpenCommand() throws ModelValidationException, IOException {
    String initialString = "load abc.png sharpen save abc.png quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  writeIn ", input.toString());

  }

  /**
   * This method is for testSepiaCommand.
   */
  @Test
  public void testSepiaCommand() throws ModelValidationException, IOException {
    String initialString = "load abc.png sepia save abc.png quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  writeIn ", input.toString());

  }

  /**
   * This method is for testGreyScaleCommand.
   */
  @Test
  public void testGreyScaleCommand() throws ModelValidationException, IOException {
    String initialString = "load abc.png grey save abc.png quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  writeIn ", input.toString());

  }

  /**
   * This method is for testGreyScaleCommand invalid.
   */
  @Test
  public void testGreyScaleCommandInvalid() throws ModelValidationException, IOException {
    String initialString = "load abc.png grey save abc.txt quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn ", input.toString());

  }

  /**
   * This method is for testReduceCommand Invalid.
   */
  @Test
  public void testReduceCommand() throws ModelValidationException, IOException {
    String initialString = "load abc.png reduce save abc.png quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  writeIn ", input.toString());

  }

  /**
   * This method is for testReduceCommand valid.
   */
  @Test
  public void testReduceCommandValid() throws ModelValidationException, IOException {
    String initialString = "load abc.png reduce 8 save abc.png quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  applyIn  writeImageIn ", input.toString());

  }

  /**
   * This method is for testDitherCommand.
   */
  @Test
  public void testDitherCommandInvalid() throws ModelValidationException, IOException {
    String initialString = "load abc.png dither save abc.png quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  writeIn ", input.toString());

  }

  /**
   * This method is for testDitherCommand.
   */
  @Test
  public void testDitherCommandValid() throws ModelValidationException, IOException {
    String initialString = "load abc.png dither 8 save abc.png quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  applyIn  writeImageIn ", input.toString());

  }

  /**
   * This method is for testPixelateCommand invalid.
   */
  @Test
  public void testPixelateCommand() throws ModelValidationException, IOException {
    String initialString = "load abc.png pixelate save abc.png quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  writeIn ", input.toString());

  }

  /**
   * This method is for testPixelateCommand.
   */
  @Test
  public void testPixelateCommand1() throws ModelValidationException, IOException {
    String initialString = "load abc.png pixelate 100 save abc.png quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  applyIn  writeImageIn ", input.toString());

  }

  /**
   * This method is for testMosaicCommand.
   */
  @Test
  public void testMosaicCommand() throws ModelValidationException, IOException {
    String initialString = "load abc.png mosaic 100 save abc.png quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  applyIn  writeImageIn ", input.toString());

  }

  /**
   * This method is for testMosaicCommand invalid.
   */
  @Test
  public void testMosaicCommandInvalid() throws ModelValidationException, IOException {
    String initialString = "load abc.png mosaic save abc.png quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  writeIn ", input.toString());

  }

  /**
   * This method is for testPatternCommand invalid file name.
   */
  @Test
  public void testPatternCommand() throws ModelValidationException, IOException {

    String initialString = "load abc.png pattern save abc.textt quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn ", input.toString());

  }

  /**
   * This method is for testPatternCommand valid scenario.
   */
  @Test
  public void testPatternCommand11() throws ModelValidationException, IOException {
    String initialString = "load abc.png pattern savePattern abc.txt quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  writeImageIn ", input.toString());

  }

  /**
   * This method is for testLoadCommand invalid scenario where file name is not
   * provided.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadCommand() throws IOException {
    String initialString = "load quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals("readIn", input.toString());

  }

  /**
   * This method is for testLoadCommand valid scenario.
   */
  @Test
  public void testLoadCommand1() throws IOException {
    String initialString = "load filename.png quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn ", input.toString());

  }

  /**
   * This method is for testSaveCommand invalid scenario where file name is not
   * provided.
   */
  @Test
  public void testSaveCommand() throws IOException {
    String initialString = "load abc.png blur save quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals("writeImageIn", input.toString());

  }

  /**
   * This method is for testSaveCommandPattern invalid scenario where file name is
   * not provided.
   */
  @Test
  public void testSaveCommandPattern() throws IOException {
    String initialString = "load abc.png pattern savePattern quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  patternIn  writeImageIn", input.toString());

  }

  /**
   * This method is for testSaveCommand valid scenario.
   */
  @Test
  public void testSaveCommandValid() throws IOException {
    String initialString = "load abc.png blur save abc.png quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  writeIn ", input.toString());

  }

  /**
   * This method is for testSaveCommandPattern valid scenario.
   */
  @Test
  public void testSaveCommandPatternValid() throws IOException {
    String initialString = "load abc.png pattern savePattern abc.txt quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  writeImageIn ", input.toString());

  }

  /**
   * This method is for testSaveCommandPattern valid scenario.
   */
  @Test
  public void testMixedImageValid() throws IOException {
    String initialString = "load abc.png sharpen blur sepia save abc.png quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  applyIn  applyIn  writeIn ", input.toString());

  }

  /**
   * This method is for testSaveCommandPattern valid scenario.
   */
  @Test
  public void testMixedPatternValid() throws IOException {
    String initialString = "load abc.png sharpen blur sepia pixelate "
        + "100 pattern savePattern abc.txt quit";
    Appendable outstream = System.out;
    InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
    ImageController imageController1 = new ImageControllerImpl(new InputStreamReader(inputStream),
        outstream, mockImageModel);
    imageController1.start();
    Assert.assertNotNull(mockImageModel);
    Assert.assertEquals(" readIn  applyIn  applyIn  pixelateIn  patternIn  writeImageIn ",
        input.toString());

  }
}
