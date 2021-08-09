import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controller.control.ImageFeatures;
import controller.control.ImageViewController;
import imageview.ImageView;
import imageview.ImageViewMock;
import model.ImageModel;
import model.ImageModelImplMock;
import model.pixel.Position;
import model.pixel.PositionImpl;

/**
 * This class represents the various scenarios to test the functionality of the
 * controller that interacts with the user through the graphical user interface.
 */
public class ViewControllerTest {

  private StringBuilder input;
  private ImageFeatures controller;

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   * 
   * @throws IOException exception while reading file
   */
  @Before
  public void setUp() throws IOException {
    input = new StringBuilder();
    ImageModel model = new ImageModelImplMock(input);
    ImageView view = new ImageViewMock(input);
    controller = new ImageViewController(model, view);

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void loadFileTest() {
    controller.loadFile();
    Assert.assertEquals("featuresgetImagegetFileNameerror", input.toString());

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void blurTest() {
    controller.blur();
    Assert.assertEquals("features applyIn buffered", input.toString());

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void sharpenTest() {

    controller.sharpen();
    Assert.assertEquals("features applyIn buffered", input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void sepiaToneTest() {
    controller.sepiaTone();
    Assert.assertEquals("features applyIn buffered", input.toString());

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void greyScaleTest() {
    controller.loadFile();
    controller.greyScale();
    Assert.assertEquals("featuresgetImagegetFileNameerror applyIn buffered", input.toString());

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void ditherTest() {
    controller.dither();
    Assert.assertEquals("featuresgetInput applyIn buffered", input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void reduceColorTest() {
    controller.reduceColor();
    Assert.assertEquals("featuresgetInput applyIn buffered", input.toString());

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void mosaicTest() {
    controller.mosaic();
    Assert.assertEquals("featuresgetInput applyIn buffered", input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void pixelateTest() {

    controller.pixelate();
    Assert.assertEquals("featuresgetInput pixelateIn buffered", input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void saveFile() {
    controller.saveFile("abc.png");
    Assert.assertEquals("features writeIn ", input.toString());

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void generatePatternTest() {

    controller.generatePattern();
    Assert.assertEquals("featuressuccess patternIn getdmcgetdmcshowPatternDimensionshowlegendshow"
        + "PatternshowPatternViewsuccess", input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void swapDmcColorMenuClickTest() {

    controller.swapDmcColorMenuClick();
    Assert.assertEquals("featuresinfoMessage", input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void swapDmcColorTest() {
    Position pos = new PositionImpl(1, 1);
    String dmcName = "DMC-01";
    controller.swapDmcColor(pos, dmcName);
    Assert.assertEquals("featuresswapshowPatternDimensionshowlegendshowPatternsuccess",
        input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void customDmcPatternMenuClickTest() {

    controller.customDmcPatternMenuClick();
    Assert.assertEquals("featuresinfoMessage", input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void removeDmcColorTest() {
    Position pos = new PositionImpl(1, 1);
    controller.removeDmcColor(pos);
    Assert.assertEquals("featuresreplaceshowPatternDimensionshowlegendshowPatternsuccess",
        input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void removeDmcColorMenuClickTest() {
    controller.removeDmcColorMenuClick();
    Assert.assertEquals("featuresinfoMessage", input.toString());

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void customDmcColorPatternTest() {
    List<String> strList = new ArrayList<String>();
    strList.add("DMC-01");
    controller.customDmcColorPattern(strList);
    Assert.assertEquals("featurescustomdmcshowPatternDimensionshowlegendshowPatternsuccess",
        input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void savePattern() {
    controller.savePattern("abc.txt");
    Assert.assertEquals("features writeImageIn ", input.toString());

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void executeBatchTest() {
    controller.executeBatch();
    Assert.assertEquals("featureserror", input.toString());

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void loadCommandTest() {

    controller.loadCommand();
    Assert.assertEquals("featuresgetInputappendTextArea", input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void saveCommandTest() {

    controller.saveCommand();
    Assert.assertEquals("featuresgetInputappendTextArea", input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void savePatternCommandTest() {
    controller.savePatternCommand();
    Assert.assertEquals("featuresgetInputappendTextArea", input.toString());

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void blurCommandTest() {
    controller.blurCommand();
    Assert.assertEquals("featuresappendTextArea", input.toString());

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void sharpenCommandTest() {
    controller.sharpenCommand();
    Assert.assertEquals("featuresappendTextArea", input.toString());

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void greyScaleCommandTest() {
    controller.greyScaleCommand();
    Assert.assertEquals("featuresappendTextArea", input.toString());

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void sepiaCommandTest() {

    controller.sepiaCommand();
    Assert.assertEquals("featuresappendTextArea", input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void ditherCommandTest() {

    controller.ditherCommand();
    Assert.assertEquals("featuresgetInputappendTextArea", input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void mosaicCommandTest() {

    controller.mosaicCommand();
    Assert.assertEquals("featuresgetInputappendTextArea", input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void reduceCommandTest() {

    controller.reduceCommand();
    Assert.assertEquals("featuresgetInputappendTextArea", input.toString());
  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void pixelateCommandTest() {
    controller.pixelateCommand();
    Assert.assertEquals("featuresgetInputappendTextArea", input.toString());

  }

  /**
   * This method tests the feature execution for the given operation via the mock
   * user interface.
   */
  @Test
  public void patternCommandTest() {
    controller.patternCommand();
    Assert.assertEquals("featuresappendTextArea", input.toString());

  }
}
