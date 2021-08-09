package model;

import model.exception.ModelValidationException;
import model.imageprocessor.ImageOperator;
import model.imageprocessor.ImagePixelateOperator;
import model.imageprocessor.PatternGenerator;
import model.images.Image;
import model.images.RgbImage;
import model.pattern.CrossStitchedPattern;
import model.pattern.DmcRbcProperties;
import model.pattern.ImagePattern;
import model.pixel.Colour;
import model.utilities.Helper;
import model.utilities.ImageUtilities;
import model.utilities.WriteToFile;

import java.io.IOException;
import java.util.Map;

/**
 * ImageModelImpl is the model class in MVC structure.It implements the various
 * image operation that can be performed on an image like filter, transform,
 * reduce, dither etc. The operations take in respective values or kernels to
 * perform a given operation.
 */
public class ImageModelImpl implements ImageModel {

  private Image image;
  private ImagePattern imagePattern;
  private int pixelation;

  /**
   * Constructor for the Image Model Implementation class to perform the various
   * operations.
   */
  public ImageModelImpl() {
    super();
    this.image = null;
    this.imagePattern = null;
    this.pixelation = 0;
  }

  @Override
  public Image applyImageOperation(ImageOperator operator)
      throws ModelValidationException, IOException {
    Helper.isObjectNull(operator);
    Helper.isObjectNullObject(image, "Please load the image file and then perform image operation");
    Image newImage = operator.apply(image);
    image = new RgbImage(newImage.getPixelArray(), newImage.getImageHeight(),
        newImage.getImageWidth());
    this.imagePattern = null;
    this.pixelation = 0;
    return image;

  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("ImageModelImpl [image=");
    sb.append("]");
    return sb.toString();
  }

  @Override
  public Image pixelate(ImagePixelateOperator operator)
      throws ModelValidationException, IOException {
    Helper.isObjectNull(operator);
    Helper.isObjectNullObject(image, "Please load the image file and then perform image operation");
    Image newImage = operator.apply(image);
    image = new RgbImage(newImage.getPixelArray(), newImage.getImageHeight(),
        newImage.getImageWidth());
    this.pixelation = operator.getPixelation();
    this.imagePattern = null;
    return image;
  }

  @Override
  public ImagePattern generateImagePattern(PatternGenerator patternGenerator)
      throws IOException, ModelValidationException {
    Helper.isObjectNull(patternGenerator);
    Helper.isObjectNullObject(image,
        "Please load the image file,pixelate it and then perform cross stitched operation");
    Helper.pixelationValidation(pixelation);
    ImagePattern newImagePattern = patternGenerator.generateImagePattern(image, pixelation);
    imagePattern = new CrossStitchedPattern(newImagePattern.getImageMap(),
        newImagePattern.getLegend(), newImagePattern.getPixelArray());

    return imagePattern;
  }

  @Override
  public void writeFile(String filename, String isBatchProcess, PatternGenerator patternGenerator)
      throws IOException, ModelValidationException {
    Helper.isObjectNull(patternGenerator);
    Helper.isObjectNull(isBatchProcess);

    Helper.isObjectNullObject(filename, " file name is blank .");

    Helper.isObjectNullObject(image,
        "Please load the image file and then perform cross stitch operation and then save it");
    Helper.isObjectNullObject(imagePattern,
        "Please perform cross stitch operation and then save it");

    String absoluteFileName = null;
    if ("Y".equalsIgnoreCase(isBatchProcess)) {
      absoluteFileName = Helper.getAbsoluteFileName(filename, false);
    } else {
      absoluteFileName = filename;
    }
    // add code here for save pattern generation.
    WriteToFile.writeFile(imagePattern, absoluteFileName);
  }

  @Override
  public void readImage(String filename, String isBatchProcess)
      throws IOException, ModelValidationException {
    Helper.isObjectNullObject(filename, " file name is blank .");

    Helper.isObjectNull(isBatchProcess);

    String absoluteFileName = null;
    if ("Y".equalsIgnoreCase(isBatchProcess)) {
      absoluteFileName = Helper.getAbsoluteFileName(filename, true);
    } else {
      absoluteFileName = filename;
    }
    int[][][] imageArray = ImageUtilities.readImage(absoluteFileName);
    image = new RgbImage(imageArray, ImageUtilities.getHeight(absoluteFileName),
        ImageUtilities.getWidth(absoluteFileName));
    this.pixelation = 0;
    this.imagePattern = null;

  }

  @Override
  public void writeImage(String filename, String isBatchProcess)
      throws IOException, ModelValidationException {
    Helper.isObjectNull(filename);
    Helper.isObjectNull(isBatchProcess);
    Helper.isObjectNullObject(image,
        "Please load the image file and then perform save image operation");

    String absoluteFileName = null;
    if ("Y".equalsIgnoreCase(isBatchProcess)) {
      absoluteFileName = Helper.getAbsoluteFileName(filename, false);
    } else {
      absoluteFileName = filename;
    }

    int[][][] imageArray = Helper.createImgIntArray(image);
    ImageUtilities.writeImage(imageArray, image.getImageWidth(), image.getImageHeight(),
        absoluteFileName);

  }

  @Override
  public ImagePattern swapDmcColorImagePattern(PatternGenerator patternGenerator, String xPosition,
      String yPosition, String swapDmcColorName) throws IOException, ModelValidationException {
    // swapDmcColorName, xPosition, yPosition will be validated in the
    // PatternGenerator.
    // if these are null that means user has not selected the color to be replace or
    // swap with color.
    Helper.isObjectNull(patternGenerator);
    Helper.isObjectNullObject(image,
        "Please load the image file, pixelate it,perform cross stitched and "
            + "then perform swap color operation.");
    Helper.pixelationValidation(pixelation);
    Helper.isObjectNullObject(imagePattern,
        "Please perform cross stitched pattern and then perform swap color operation.");
    ImagePattern newImagePattern = patternGenerator.swapDmcColorImagePattern(imagePattern,
        xPosition, yPosition, swapDmcColorName);
    imagePattern = new CrossStitchedPattern(newImagePattern.getImageMap(),
        newImagePattern.getLegend(), newImagePattern.getPixelArray());

    return imagePattern;

  }

  @Override
  public ImagePattern replaceDmcColorImagePattern(PatternGenerator patternGenerator,
      String xPosition, String yPosition) throws IOException, ModelValidationException {
    // xPosition, yPosition will be validated in the
    // PatternGenerator.
    // if these are null that means user has not selected the color to be replace
    Helper.isObjectNull(patternGenerator);
    Helper.isObjectNullObject(image,
        "Please load the image file, pixelate it,perform cross stitched and then "
            + "perform replace color operation.");
    Helper.pixelationValidation(pixelation);
    Helper.isObjectNullObject(imagePattern,
        "Please perform cross stitched pattern and then perform replace dmc color operation.");

    ImagePattern newImagePattern = patternGenerator.replaceDmcColorImagePattern(imagePattern,
        xPosition, yPosition);
    imagePattern = new CrossStitchedPattern(newImagePattern.getImageMap(),
        newImagePattern.getLegend(), newImagePattern.getPixelArray());

    return imagePattern;
  }

  @Override
  public Map<String, Colour> getDmcColorPalleteMap() throws IOException {
    return DmcRbcProperties.getInstance().getDmcProperties();

  }

  @Override
  public ImagePattern createCustomDmcPattern(PatternGenerator patternGenerator, String dmcColorName)
      throws IOException, ModelValidationException {
    Helper.isObjectNull(patternGenerator);

    // dmcColorName will be validated in the PatternGenerator and if it null user
    // has not selected
    // any color from the pallete.
    Helper.isObjectNullObject(image,
        "Please load the image file, pixelate it,perform cross stitched and then perform "
            + "generate pattern using custom dmc color operation.");
    Helper.pixelationValidation(pixelation);
    Helper.isObjectNullObject(imagePattern,
        "Please perform cross stitched pattern and then perform "
            + "generate pattern using custom dmc color operation.");

    ImagePattern newImagePattern = patternGenerator.createCustomDmcPattern(imagePattern,
        dmcColorName, pixelation);
    imagePattern = new CrossStitchedPattern(newImagePattern.getImageMap(),
        newImagePattern.getLegend(), newImagePattern.getPixelArray());

    return imagePattern;
  }
}
