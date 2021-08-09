package imageview;

import java.awt.image.BufferedImage;
import java.util.Map;

import controller.control.ImageFeatures;
import model.pixel.Colour;
import model.pixel.SuperPixel;

/**
 * This class represents a mock controller for the view which is for testing the
 * correct functioning to check the correct calling of methods.
 */
public class ImageViewMock implements ImageView {

  private StringBuilder input;
  private String mockTestString;

  /**
   * Constructor for mock view.
   *
   * @param input input
   */
  public ImageViewMock(StringBuilder input) {
    this.input = input;
    this.mockTestString = "xyz";

  }

  @Override
  public void setFeatures(ImageFeatures f) {
    input.append("features");
  }

  @Override
  public void errorMessage(String message) {
    input.append("error");
  }

  @Override
  public void successMessage(String message) {
    input.append("success");
  }

  @Override
  public void showBufferedImage(BufferedImage image) {
    input.append("buffered");
  }

  @Override
  public void setVisible() {
    input.append("visible");
  }

  @Override
  public void getDmcPalleteMap(Map<String, Colour> dmcPallete) {
    input.append("getdmc");
  }

  @Override
  public void showDmcPallete(boolean multiSelect) {
    input.append("showdmc");
  }

  @Override
  public void showLegend(Map<String, String> legend) {
    input.append("showlegend");
  }

  @Override
  public void showImagePattern(BufferedImage image, SuperPixel[][] imageMap) {
    input.append("showPattern");
  }

  @Override
  public void showPatternDimension(int patternHeight, int patternWidth) {
    input.append("showPatternDimension");
  }

  @Override
  public void infoMessage(String message) {
    input.append("infoMessage");
  }

  @Override
  public void showRemoveDmcColorView() {
    input.append("showRemoveDmcColorView");

  }

  @Override
  public void showPatternView() {
    input.append("showPatternView");
  }

  @Override
  public void getImage() {
    input.append("getImage");
  }

  @Override
  public void removePreviousImage() {
    input.append("removePreviousImage");
  }

  @Override
  public String getFileName() {
    input.append("getFileName");

    return mockTestString;
  }

  @Override
  public String getInput(String message) {
    input.append("getInput");
    return mockTestString;
  }

  @Override
  public void appendTextArea(String command) {
    input.append("appendTextArea");
  }

  @Override
  public String getTextArea() {
    input.append("getTextArea");
    return mockTestString;
  }

}
